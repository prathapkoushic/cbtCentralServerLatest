package com.ttipl.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.ttipl.pojo.ExamLocationSess;
@Component
public class QuestionsPushService 
{
	
	@Autowired
	HttpSession httpSession;

	public Connection conn = null, local_conn = null;;
	public PreparedStatement pstmt_q_q = null, pstmt_q_op = null, pstmt_q_insert = null, pstmt_op_insert = null,
			pstmt_q_slt = null,pstmt_q_slt1 = null , pstmt_q_slt2 = null, pstmt_op_slt = null, pstmt_img_slt = null, pstmt_img_insert = null,
			pstmt_lang_opt = null, pstmt_lang = null, pstmt_q_check = null, pstmt_op_check = null, pstmt_op = null,
			pstmt_ql_check = null, pstmt_opl_check = null;
	public ResultSet rs_q_q = null, rs_o_q = null, rs_q = null,rs_q1 = null , rs_q2 = null,rs_i = null, rs_op = null, rs_ql_check = null,
			rs_ol_check = null;

	String optionLangCheck = "SELECT * FROM answer_option_languages where answer_option_id=? and language=?";
	String questionLangCheck = "SELECT * FROM question_languages where question_id=? and language=?";
	
	
	String questionLangCheck2 = "SELECT * FROM descriptive_question_languages where desquestion_id=? and language=?";
	
	

	public String pullQuestionImage(Integer imageId) 
	{
		int counter = 0;
		try 
		{
			String query2 = "SELECT * from question_images " + "where " + "image_id=" + imageId;

			if (conn != null && local_conn != null) {

				pstmt_img_slt = local_conn.prepareStatement(query2);
				rs_i = pstmt_img_slt.executeQuery();

				String query3 = "insert into question_images(image_id,image,image_name) values(?,?,?)";
				pstmt_img_insert = conn.prepareStatement(query3);

				counter = 0;
				while (rs_i.next()) 
				{
					counter++;
					pstmt_img_insert.setInt(1, rs_i.getInt("image_id"));
					pstmt_img_insert.setString(2, rs_i.getString("image"));
					pstmt_img_insert.setString(3, rs_i.getString("image_name"));
					pstmt_img_insert.executeUpdate();
				}

				if (counter > 0) 
				{
					return "Data successfully Pushed - Records Count - " + counter;
				} 
				else 
				{
					return "No Data Available to Pushed";
				}

			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return e.getMessage();
		} 
		finally 
		{
			try 
			{
				if (rs_i != null)
					rs_i.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return "Success";

	}
	
	
	public String pullQuestionImage2(Integer imageId) 
	{
		int counter = 0;

		try {
			String query2 = "SELECT * from descriptive_question_images " + "where " + "image_id=" + imageId;

			if (conn != null && local_conn != null) 
			{
				pstmt_img_slt = local_conn.prepareStatement(query2);
				rs_i = pstmt_img_slt.executeQuery();

				String query3 = "insert into descriptive_question_images(image_id,image,image_name) values(?,?,?)";
				pstmt_img_insert = conn.prepareStatement(query3);

				counter = 0;
				while (rs_i.next()) {

					counter++;
					pstmt_img_insert.setInt(1, rs_i.getInt("image_id"));
					pstmt_img_insert.setString(2, rs_i.getString("image"));
					pstmt_img_insert.setString(3, rs_i.getString("image_name"));

					pstmt_img_insert.executeUpdate();

				}

				if (counter > 0) {

					return "Data successfully Pushed - Records Count - " + counter;
				} else {
					return "No Data Available to Pushed";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			try {
				if (rs_i != null)
					rs_i.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Success";

	}

	public int pullQuestionsToLocationServerDb(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, int examId, int locationSessionId,Model model, int totalExamQuestions ) throws SQLException 
	{
		int counter = 0;
		boolean flag = false;
		
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			pstmt_opl_check = conn.prepareStatement(optionLangCheck);
			pstmt_ql_check = conn.prepareStatement(questionLangCheck);
			String query2 = "SELECT * from question where ";
			if (examId != 0) {
				query2 = query2 + "exam_id='" + examId + "'";
			}

			if (conn != null && local_conn != null) {

				pstmt_q_slt = local_conn.prepareStatement(query2);
				rs_q = pstmt_q_slt.executeQuery();
				String query4 = "select question_id from question where question_id=?";
				pstmt_q_check = conn.prepareStatement(query4);
				int question_id = 0;

				String query3 = "insert into question (question_id,bank_question_id,exam_id,exam_loc_session_id,question,question_no,question_type,question_paper_code,status,question_image_id) values(?,?,?,?,?,?,?,?,?,?)";
				String question_lang_query = "INSERT INTO question_languages(`language`,`question`,`question_image_id`,`question_id`) VALUES (?,?,?,?)";
				pstmt_q_insert = conn.prepareStatement(query3);
				pstmt_lang = conn.prepareStatement(question_lang_query);
				counter = 0;
				
				try 
				{
				while (rs_q.next()) 
				{
                    conn.setAutoCommit(false);
					flag = true;
					question_id = rs_q.getInt("question_id");
					pstmt_q_check.setInt(1, question_id);
					rs_q_q = pstmt_q_check.executeQuery();
					if (rs_q_q.next()) {} 
					else 
					{
						question_id = rs_q.getInt("question_id");
						pstmt_q_insert.setInt(1, question_id);
						pstmt_q_insert.setInt(2, rs_q.getInt("bank_question_id"));
						pstmt_q_insert.setInt(3, rs_q.getInt("exam_id"));
						pstmt_q_insert.setInt(4, locationSessionId);
						pstmt_q_insert.setString(5, rs_q.getString("question"));
						pstmt_q_insert.setInt(6, rs_q.getInt("question_no"));
						pstmt_q_insert.setString(7, rs_q.getString("question_type"));
						pstmt_q_insert.setString(8, rs_q.getString("question_paper_code"));
						pstmt_q_insert.setBoolean(9, rs_q.getBoolean("status"));

						Object obj = rs_q.getObject("english_image_id");
						if (obj != null) 
						{
							pstmt_q_insert.setObject(10, obj);
							this.pullQuestionImage((Integer) obj);
						} 
						else 
						{
							pstmt_q_insert.setObject(10, null);
						}
						this.insertQuestionLangs("hindi", rs_q.getString("question_hindi"),
								rs_q.getObject("hindi_image_id"), question_id);
						this.insertQuestionLangs("kanada", rs_q.getString("question_kanada"),
								rs_q.getObject("kanada_image_id"), question_id);
						this.insertQuestionLangs("tamil", rs_q.getString("question_tamil"),
								rs_q.getObject("tamil_image_id"), question_id);
						this.insertQuestionLangs("telugu", rs_q.getString("question_telugu"),
								rs_q.getObject("telugu_image_id"), question_id);
						this.insertQuestionLangs("marathi", rs_q.getString("question_marathi"),
								rs_q.getObject("marathi_image_id"), question_id);
						this.insertQuestionLangs("urdu", rs_q.getString("question_urdu"),
								rs_q.getObject("urdu_image_id"), question_id);
						this.pullOptionsBasedOnQuestionIdToLocationServer(question_id);
						pstmt_q_insert.executeUpdate();
						
						counter++;
						
				    }
				 }
				                    if(counter==totalExamQuestions && counter!=0)
				                     {
				                      conn.commit();
				                      conn.setAutoCommit(true);
										/*
										 * Time time1=null; Time time2=null; String query =
										 * "SELECT `start_time`, `end_time` from `exam_loc_session` where exam_loc_session_id=? AND exam_id=?"
										 * ; PreparedStatement ps = local_conn.prepareStatement(query);
										 * ps.setInt(1,locationSessionId); ps.setInt(2, examId);
										 * 
										 * 
										 * ResultSet rs= ps.executeQuery();
										 * 
										 * if(rs.next()) { time1 = rs.getTime("start_time"); time2 =
										 * rs.getTime("end_time"); }
										 * 
										 * String queryUpdate =
										 * "UPDATE exam_loc_session SET start_time=?,end_time=? WHERE exam_loc_session_id=? AND exam_id=?"
										 * ; PreparedStatement pd= conn.prepareStatement(queryUpdate); pd.setTime(1,
										 * time1); pd.setTime(2, time2); pd.setInt(3, locationSessionId); pd.setInt(4,
										 * examId); pd.executeUpdate(); ExamLocationSess sess = (ExamLocationSess)
										 * httpSession.getAttribute("Location"); sess.setStart_time(time1);
										 * sess.setEnd_time(time2); httpSession.setAttribute("Location", sess);
										 */
					              	    
				                     }
				                  else
				                      {
					                  conn.rollback();
				                      }
				   }
					catch(Exception e)
				   {
						conn.rollback();
				   }
				
				
				 if(counter!=totalExamQuestions &&  counter!=0)
				{
					return -100;
				}
				 else if (counter > 0) 
				{
					return counter;
				} 
				else if (counter == 0 && flag)
				{
					return -1526;
				} 
				
				else 
				{
					return -404;
				}

			}
				return counter;
	}
	
	
	public int pullDesQuestionsToLocationServerDb(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, int examId, int locationSessionId,Model model, int totalExamQuestions) throws SQLException 
	{
		int easy = 0;
		int medium = 0;
		int difficult=0;
		int counter = 0;
		boolean flag = false;
		
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			pstmt_ql_check = conn.prepareStatement(questionLangCheck2);
			String query2 = " SELECT * FROM descriptive_question  where exam_id='"+examId+"' and level='EASY' ORDER BY RAND() LIMIT ?";
			String query21 = "SELECT * FROM descriptive_question  where exam_id='"+examId+"' and level='MEDIUM' ORDER BY RAND() LIMIT ?";
			String query22 = "SELECT * FROM descriptive_question  where exam_id='"+examId+"' and level='DIFFICULT' ORDER BY RAND() LIMIT ?";
			
			 String easy1 = "SELECT * FROM exam_questions where exam_id='"+examId+"'";
			 String medium1 = "SELECT * FROM exam_questions where exam_id='"+examId+"'";
			 String difficult1 = "SELECT * FROM exam_questions where exam_id='"+examId+"'";
			PreparedStatement easyqry =  local_conn.prepareStatement(easy1);
			ResultSet rsEasy = easyqry.executeQuery();
			 while (rsEasy.next()) {
				  easy = rsEasy.getInt("easy");
		         }
			
			PreparedStatement medQry =  local_conn.prepareStatement(medium1);
			ResultSet rsMedium = medQry.executeQuery();
			while (rsMedium.next()) {
			 medium = rsMedium.getInt("medium");
		         }
			
			
			PreparedStatement defQry =  local_conn.prepareStatement(difficult1);
			ResultSet rsDef = defQry.executeQuery();
			while (rsDef.next()) {
			 difficult = rsDef.getInt("difficult");
		         }
			
			
			if (conn != null && local_conn != null) {

				pstmt_q_slt = local_conn.prepareStatement(query2);
				pstmt_q_slt.setInt(1, easy);
				rs_q = pstmt_q_slt.executeQuery();
				
				pstmt_q_slt1 = local_conn.prepareStatement(query21);
				pstmt_q_slt1.setInt(1, medium);
				rs_q1 = pstmt_q_slt1.executeQuery();
				
				pstmt_q_slt2 = local_conn.prepareStatement(query22);
				pstmt_q_slt2.setInt(1, difficult);
				rs_q2 = pstmt_q_slt2.executeQuery();
				
				List<ResultSet> list = new ArrayList<ResultSet>();				
				list.add(rs_q);
				list.add(rs_q1);
				list.add(rs_q2);
				
				String query4 = "select * from descriptive_question";
				pstmt_q_check = conn.prepareStatement(query4);
				int question_id = 0;

				String query3 = "insert into descriptive_question (desquestion_id,exam_id,exam_loc_session_id,question,question_no,question_type,question_paper_code,status,question_image_id,level) values(?,?,?,?,?,?,?,?,?,?)";
				String question_lang_query = "INSERT INTO descriptive_question_languages(`language`,`question`,`question_image_id`,`desquestion_id`) VALUES (?,?,?,?)";
				pstmt_q_insert = conn.prepareStatement(query3);
				pstmt_lang = conn.prepareStatement(question_lang_query);
				counter = 0;
				
				try 
				{
					for(ResultSet rs_q : list)
					{
				while (rs_q.next()) 
				{
					conn.setAutoCommit(false);
					flag = true;
					
					rs_q_q = pstmt_q_check.executeQuery();
					if (rs_q_q.next()) {} 
					else 
					{
						question_id = rs_q.getInt("desquestion_id");
						pstmt_q_insert.setInt(1, question_id);
						pstmt_q_insert.setInt(2, rs_q.getInt("exam_id"));
						pstmt_q_insert.setInt(3, locationSessionId);
						pstmt_q_insert.setString(4, rs_q.getString("question"));
						pstmt_q_insert.setInt(5, rs_q.getInt("question_no"));
						pstmt_q_insert.setString(6, rs_q.getString("question_type"));
						pstmt_q_insert.setString(7, rs_q.getString("question_paper_code"));
						pstmt_q_insert.setBoolean(8, rs_q.getBoolean("status"));
						

						Object obj = rs_q.getObject("english_image_id");
						if (obj != null) 
						{
							pstmt_q_insert.setObject(9, obj);
							this.pullQuestionImage2((Integer) obj);
						} 
						else 
						{
							pstmt_q_insert.setObject(9, null);
						}
						pstmt_q_insert.setString(10, rs_q.getString("level"));
						this.insertQuestionLangs2("hindi", rs_q.getString("question_hindi"),
								rs_q.getObject("hindi_image_id"), question_id);
						pstmt_q_insert.executeUpdate();
						counter++;
						
				    }
				 }
					}
				
				
				
				 if(counter==totalExamQuestions && counter!=0)
                 {
                  conn.commit();
                  conn.setAutoCommit(true);
                  Time time1=null;
                  Time time2=null;
              		String query = "SELECT `start_time`, `end_time` from `exam_loc_session` where exam_loc_session_id=? AND exam_id=?";
              		PreparedStatement ps = local_conn.prepareStatement(query);
              		ps.setInt(1,locationSessionId);
              		ps.setInt(2, examId);
              		
              		
              		ResultSet rs= ps.executeQuery();
              		
              		if(rs.next())
              		  {
              			 time1 = rs.getTime("start_time"); 		
              			 time2 = rs.getTime("end_time");
                      }
              		
              		String queryUpdate = "UPDATE exam_loc_session SET start_time=?,end_time=? WHERE exam_loc_session_id=? AND exam_id=?";
              		PreparedStatement pd= conn.prepareStatement(queryUpdate);
              		pd.setTime(1, time1);
              		pd.setTime(2, time2);
              		pd.setInt(3, locationSessionId);
              		pd.setInt(4, examId);
              	    pd.executeUpdate();
              	    ExamLocationSess sess = (ExamLocationSess) httpSession.getAttribute("Location");
              	    sess.setStart_time(time1);
              	    sess.setEnd_time(time2);
              	    httpSession.setAttribute("Location", sess);
              	    
                 }
             
				                   
				}
					catch(Exception e)
				   {
						conn.rollback();
				   }
				
				 if(counter!=totalExamQuestions &&  counter!=0)
					{
						return -100;
					}
				 else if (counter > 0) 
				  {
					return counter;
			      } 
				  else if (counter == 0 && flag)
				  {
					return -1526;
				  } 
				  else 
				  {
					return -404;
				  }

			}
				return counter;
	}

	public String pullOptionsBasedOnQuestionIdToLocationServer(Integer questionId) throws SQLException 
	{

		int counter = 0;

		try
		{
			String query2 = "SELECT * from answer_option where ";
			if (questionId != 0) 
			{
				query2 = query2 + "question_id='" + questionId + "'";
			}

			if (conn != null && local_conn != null)
			{

				pstmt_op_slt = local_conn.prepareStatement(query2);
				rs_op = pstmt_op_slt.executeQuery();

				String query4 = "select answer_option_id from answer_option where answer_option_id= ?";
				pstmt_op_check = conn.prepareStatement(query4);
				int answer_option_id = 0;
				int optionOrder = 0;

				String query3 = "insert into answer_option(answer_option_id,is_answer,answer_option,option_order,question_id,answer_option_image_id) values(?,?,?,?,?,?)";
				String answerOptionLangQuery = "INSERT INTO answer_option_languages (language,answer_option,answer_option_image_id,answer_option_id,option_order)VALUES(?,?,?,?,?)";
				pstmt_op_insert = conn.prepareStatement(query3);
				pstmt_lang_opt = conn.prepareStatement(answerOptionLangQuery);

				counter = 0;
				while (rs_op.next())
				{
					answer_option_id = rs_op.getInt("answer_option_id");
					pstmt_op_check.setInt(1, answer_option_id);
					rs_o_q = pstmt_op_check.executeQuery();
					if (rs_o_q.next()) 
					{} 
					else 
					{
						questionId = rs_op.getInt("question_id");
						answer_option_id = rs_op.getInt("answer_option_id");
						optionOrder = rs_op.getInt("option_order");
						pstmt_op_insert.setInt(1, rs_op.getInt("answer_option_id"));
						pstmt_op_insert.setBoolean(2, rs_op.getBoolean("is_answer"));
						pstmt_op_insert.setString(3, rs_op.getString("answer_option"));
						pstmt_op_insert.setInt(4, optionOrder);
						pstmt_op_insert.setInt(5, questionId);
						Object obj = rs_op.getObject("english_image_id");
						if (obj != null) {
							pstmt_op_insert.setObject(6, obj);
							this.pullQuestionImage((Integer) obj);
						} else {
							pstmt_op_insert.setObject(6, null);
						}
						this.insertOptionLangs("hindi", rs_op.getString("answer_option_hindi"),
								rs_op.getObject("hindi_image_id"), answer_option_id, optionOrder);
						this.insertOptionLangs("kanada", rs_op.getString("answer_option_kanada"),
								rs_op.getObject("kanada_image_id"), answer_option_id, optionOrder);
						this.insertOptionLangs("tamil", rs_op.getString("answer_option_tamil"),
								rs_op.getObject("tamil_image_id"), answer_option_id, optionOrder);
						this.insertOptionLangs("telugu", rs_op.getString("answer_option_telugu"),
								rs_op.getObject("telugu_image_id"), answer_option_id, optionOrder);
						this.insertOptionLangs("marathi", rs_op.getString("answer_option_marathi"),
								rs_op.getObject("marathi_image_id"), answer_option_id, optionOrder);
						this.insertOptionLangs("urdu", rs_op.getString("answer_option_urdu"),
								rs_op.getObject("urdu_image_id"), answer_option_id, optionOrder);

						pstmt_op_insert.executeUpdate();
						counter++;
					}

				}

				if (counter > 0) 
				{
					return "Data successfully Pushed - Records Count - " + counter;
				} 
				else 
				{
					return "No Data Available to Pushed";
				}

			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return e.getMessage();
		}
		return "Success";

	}

	public void insertQuestionLangs(String language, String question, Object imageId, int questionId) {
		try {
			pstmt_ql_check.setInt(1, questionId);
			pstmt_ql_check.setString(2, language);
			rs_ql_check = pstmt_ql_check.executeQuery();
			if (rs_ql_check.next()) {
			} else {

				pstmt_lang.setString(1, language);

				pstmt_lang.setString(2, question);
				if (imageId != null) {
					pstmt_lang.setObject(3, imageId);
					this.pullQuestionImage((Integer) imageId);
				} else {
					pstmt_lang.setObject(3, null);
				}
				pstmt_lang.setInt(4, questionId);
				pstmt_lang.executeUpdate();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public void insertQuestionLangs2(String language, String question, Object imageId, int desquestion_id) {
		try {
			pstmt_ql_check.setInt(1, desquestion_id);
			pstmt_ql_check.setString(2, language);
			rs_ql_check = pstmt_ql_check.executeQuery();
			if (rs_ql_check.next()) {
			} else {

				pstmt_lang.setString(1, language);

				pstmt_lang.setString(2, question);
				if (imageId != null) {
					pstmt_lang.setObject(3, imageId);
					this.pullQuestionImage2((Integer) imageId);
				} else {
					pstmt_lang.setObject(3, null);
				}
				pstmt_lang.setInt(4, desquestion_id);
				pstmt_lang.executeUpdate();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public void insertOptionLangs(String language, String answerOption, Object imageId, int answerOptionId,
			int optionOrder) {
		try {
			pstmt_opl_check.setInt(1, answerOptionId);
			pstmt_opl_check.setString(2, language);
			rs_ol_check = pstmt_opl_check.executeQuery();
			if (rs_ol_check.next()) {
			} else {
				pstmt_lang_opt.setString(1, language);

				pstmt_lang_opt.setString(2, answerOption);
				if (imageId != null) {
					pstmt_lang_opt.setObject(3, imageId);
					this.pullQuestionImage((Integer) imageId);
				} else {
					pstmt_lang_opt.setObject(3, null);
				}
				pstmt_lang_opt.setInt(4, answerOptionId);
				pstmt_lang_opt.setInt(5, optionOrder);
				pstmt_lang_opt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		Connection con = null;
		String sql = "insert into testtbl values(?,?) ";
		String slt = "select * from testtbl";
		PreparedStatement pstmt, pstmt1 = null;
		ResultSet set = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://hssccbt.cm0bksyl0mvc.ap-south-1.rds.amazonaws.com:3306/test?useUnicode=true&characterEncoding=UTF-8",
					"admin", "ttil_2020");
			pstmt = con.prepareStatement(sql);

			pstmt1 = con.prepareStatement(slt);
			set = pstmt1.executeQuery();
			if (set.next()) {
				pstmt.setString(1, set.getString("language"));
				pstmt.setString(2, set.getString("language_value"));
				pstmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
