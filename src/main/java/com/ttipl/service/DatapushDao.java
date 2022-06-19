package com.ttipl.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.json.JSONObject;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DatapushDao {
	public Connection conn = null, local_conn = null;;
	public PreparedStatement pstmt = null, pstmt2 = null, pstmt3 = null, pstmt4 = null, pstmt_can = null,
			pstmt_q = null, pstmt_ans = null;
	public ResultSet rs = null, rs1 = null, rs2 = null, rs_can = null, rs_q = null, rs_ans = null;;

	/*
	 * public String pushQuestionToServer(DriverManagerDataSource dataSourceLocal,
	 * DriverManagerDataSource dataSourceServer, String examName, String setNo) {
	 * 
	 * int counter = 0;
	 * 
	 * try { local_conn = dataSourceLocal.getConnection(); conn =
	 * dataSourceServer.getConnection();
	 * 
	 * String query2 = "SELECT  from storing_question where"; if (examName != null)
	 * { query2 = query2 + " and examName='" + examName + "'  "; } if (setNo !=
	 * null) { query2 = query2 + " and setNo='" + setNo + "'  "; }
	 * 
	 * if (conn != null && local_conn != null) {
	 * 
	 * pstmt = local_conn.prepareStatement(query2); rs = pstmt.executeQuery();
	 * 
	 * String query3 =
	 * "insert into storing_question(question_id,exam_name,option1,option2,option3,option4,question,set_no) values(?,?,?,?,?,?,?,?)"
	 * ; pstmt2 = conn.prepareStatement(query3);
	 * 
	 * counter = 0; while (rs.next()) {
	 * 
	 * counter++; pstmt2.setString(1, rs.getString("question_id"));
	 * pstmt2.setString(2, rs.getString("exam_name")); pstmt2.setString(3,
	 * rs.getString("option1")); pstmt2.setString(4, rs.getString("option2"));
	 * pstmt2.setString(5, rs.getString("option3")); pstmt2.setString(6,
	 * rs.getString("option4")); pstmt2.setString(7, rs.getString("question"));
	 * pstmt2.setString(8, rs.getString("set_no")); pstmt2.executeUpdate();
	 * 
	 * }
	 * 
	 * if (counter > 0) {
	 * 
	 * return "Data successfully Pushed - Records Count - " + counter; } else {
	 * return "No Data Available to Pushed"; }
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); return e.getMessage(); }
	 * finally { try { if (local_conn != null && !local_conn.isClosed())
	 * local_conn.close(); if (conn != null && !conn.isClosed()) conn.close(); }
	 * catch (Exception e) { e.printStackTrace(); } } return "Success"; }
	 */
	public String pushPostsToLocationServerDb(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, int postId) throws SQLException {
		int counter = 0;

		try {
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from post where ";
			if (postId != 0) {
				query2 = query2 + "post_id ='" + postId + "'";
			}

			if (conn != null && local_conn != null) {

				pstmt = local_conn.prepareStatement(query2);
				rs = pstmt.executeQuery();

				String query3 = "insert into post(post_id,no_of_vacancies,post_description,post_name,post_qualifications,post_category_id) values(?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(query3);

				counter = 0;
				while (rs.next()) {

					counter++;
					pstmt2.setString(1, rs.getString("post_id"));
					pstmt2.setString(2, rs.getString("no_of_vacancies"));
					pstmt2.setString(3, rs.getString("post_description"));
					pstmt2.setString(4, rs.getString("post_name"));
					pstmt2.setString(5, rs.getString("post_qualifications"));
					pstmt2.setString(6, rs.getString("post_category_id"));
					pstmt2.executeUpdate();

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
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Success";

	}

	public String pushExamsToLocationServerDb(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, int examId) throws SQLException {
		int counter = 0;

		try {
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from exam where ";
			if (examId != 0) {
				query2 = query2 + "id='" + examId + "'";
			}

			if (conn != null && local_conn != null) {

				pstmt = local_conn.prepareStatement(query2);
				rs = pstmt.executeQuery();

				String query3 = "insert into exam(id,duration,exam_code,exam_date,exam_description,exam_name,marks_per_question,negativemarks_per_question,post_id) values(?,?,?,?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(query3);

				counter = 0;
				while (rs.next()) {

					counter++;
					pstmt2.setString(1, rs.getString("id"));
					pstmt2.setString(2, rs.getString("duration"));
					pstmt2.setString(3, rs.getString("exam_code"));
					pstmt2.setString(4, rs.getString("exam_date"));
					pstmt2.setString(5, rs.getString("exam_description"));
					pstmt2.setString(6, rs.getString("exam_name"));
					pstmt2.setString(7, rs.getString("marks_per_question"));
					pstmt2.setString(8, rs.getString("negativemarks_per_question"));
					pstmt2.setString(9, rs.getString("post_id"));

					pstmt2.executeUpdate();

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
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Success";

	}

	public String pushExamLocationSessionsToLocationServerDb(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, int examId) throws SQLException {
		int counter = 0;

		try {
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from exam_loc_session where ";
			if (examId != 0) {
				query2 = query2 + "exam_id='" + examId + "'";
			}

			if (conn != null && local_conn != null) {

				pstmt = local_conn.prepareStatement(query2);
				rs = pstmt.executeQuery();

				String query3 = "insert into exam_loc_session (exam_loc_session_id,contact_name,address,moblie_number,session_date_time,end_time,exam_id,ipaddress,location_name,start_time) values(?,?,?,?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(query3);

				counter = 0;
				while (rs.next()) {

					counter++;
					pstmt2.setInt(1, rs.getInt("exam_loc_session_id"));
					pstmt2.setString(2, rs.getString("contact_name"));
					pstmt2.setString(3, rs.getString("address"));
					pstmt2.setString(4, rs.getString("moblie_number"));
					pstmt2.setString(5, rs.getString("session_date_time"));
					pstmt2.setString(6, rs.getString("end_time"));
					pstmt2.setInt(7, rs.getInt("exam_id"));
					pstmt2.setString(8, rs.getString("ipaddress"));
					pstmt2.setString(9, rs.getString("location_name"));
					pstmt2.setString(9, rs.getString("start_time"));

					pstmt2.executeUpdate();

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
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Success";

	}

	public int pullQuestionsToLocationServerDb(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, int examId, int locationSessionId) throws SQLException {
		int counter = 0;
		boolean flag = false;
		try {
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from question where ";
			if (examId != 0) {
				query2 = query2 + "exam_id='" + examId + "'";
			}

			if (conn != null && local_conn != null) {

				pstmt = local_conn.prepareStatement(query2);
				rs = pstmt.executeQuery();
				String query4 = "select question_id from question where question_id=?";
				pstmt_q = conn.prepareStatement(query4);
				int question_id = 0;

				String query3 = "insert into question (question_id,bank_question_id,exam_id,exam_loc_session_id,question,question_no,question_type,question_hindi,question_paper_code,status,english_image_id,hindi_image_id) values(?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(query3);

				counter = 0;
				while (rs.next()) {
					flag = true;
					question_id = rs.getInt("question_id");
					pstmt_q.setInt(1, question_id);
					rs_q = pstmt_q.executeQuery();
					if (rs_q.next()) {

					} else {

						pstmt2.setInt(1, rs.getInt("question_id"));
						pstmt2.setInt(2, rs.getInt("bank_question_id"));
						pstmt2.setInt(3, rs.getInt("exam_id"));
						pstmt2.setInt(4, locationSessionId);
						pstmt2.setString(5, rs.getString("question"));
						pstmt2.setInt(6, rs.getInt("question_no"));
						pstmt2.setString(7, rs.getString("question_type"));
						pstmt2.setString(8, rs.getString("question_hindi"));
						pstmt2.setString(9, rs.getString("question_paper_code"));
						pstmt2.setBoolean(10, rs.getBoolean("status"));
						pstmt2.setObject(11, rs.getObject("english_image_id"));
						pstmt2.setObject(12, rs.getObject("hindi_image_id"));
						pstmt2.executeUpdate();
						counter++;
					}

				}

				if (counter > 0) {
					return counter;
				} else if (counter == 0 && flag) {
					return -1526;
				} else {
					return -404;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return counter - 1;
		} finally {
			try {
				if (rs_q != null)
					rs_q.close();
				if (pstmt_q != null)
					pstmt_q.close();
				if (pstmt2 != null)
					pstmt2.close();
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return counter;
	}

	public int pullCandidatesToLocationServerDb(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, int examId, int locationSessionId) throws SQLException {
		int counter = 0;
		boolean flag = false;
		try {
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from candidate where ";
			if (examId != 0) {
				query2 = query2 + "exam_id=" + examId + " ";
			}
			if (locationSessionId != 0) {
				query2 = query2 + "and exam_loc_session_id=" + locationSessionId + "";
			}

			if (conn != null && local_conn != null) {

				pstmt = local_conn.prepareStatement(query2);
				rs = pstmt.executeQuery();
				String exam_is_end = "";
				String end_status = "";
				String query4 = "select candidate_id from candidate where candidate_id=?";
				pstmt_q = conn.prepareStatement(query4);
				String candidate_id = null;
				String query3 = "insert into candidate(candidate_id,address,candidate_first_name,candidate_last_name,caste,community,contact_no,district,dob,email_id,exam_code,exam_duration,exam_end_time,exam_id,exam_is_end,exam_loc_session_id,father_name,gender,lab_location_id,login_ip,password,candidate_photo,pincode,post_id,post_name,religion,state,end_status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(query3);
				counter = 0;
				try
				{
				while (rs.next()) 
				{
                    conn.setAutoCommit(false);
					flag = true;
					candidate_id = rs.getString("candidate_id");
					pstmt_q.setString(1, candidate_id);
					rs_q = pstmt_q.executeQuery();
					if (rs_q.next())
					{} 
					else
					{
						pstmt2.setString(1, candidate_id);
						pstmt2.setString(2, rs.getString("address"));
						pstmt2.setString(3, rs.getString("candidate_first_name"));
						pstmt2.setString(4, rs.getString("candidate_last_name"));
						pstmt2.setString(5, rs.getString("caste"));
						pstmt2.setString(6, rs.getString("community"));
						pstmt2.setString(7, rs.getString("contact_no"));
						pstmt2.setString(8, rs.getString("district"));
						pstmt2.setDate(9, rs.getDate("dob"));
						pstmt2.setString(10, rs.getString("email_id"));
						pstmt2.setString(11, rs.getString("exam_code"));
						pstmt2.setString(12, rs.getString("exam_duration"));
						pstmt2.setString(13, rs.getString("exam_end_time"));
						pstmt2.setInt(14, rs.getInt("exam_id"));
						exam_is_end = rs.getString("exam_is_end");
						if (exam_is_end != null && exam_is_end.equals("1")) 
						{
							pstmt2.setBoolean(15, true);
						} else {
							pstmt2.setBoolean(15, false);
						}

						pstmt2.setInt(16, rs.getInt("exam_loc_session_id"));
						pstmt2.setString(17, rs.getString("father_name"));
						pstmt2.setString(18, rs.getString("gender"));
						pstmt2.setInt(19, rs.getInt("lab_location_id"));
						pstmt2.setString(20, rs.getString("login_ip"));
						pstmt2.setString(21, rs.getString("password"));
						pstmt2.setBlob(22, rs.getBlob("candidate_photo"));
						pstmt2.setString(23, rs.getString("pincode"));
						pstmt2.setInt(24, rs.getInt("post_id"));
						pstmt2.setString(25, rs.getString("post_name"));
						pstmt2.setString(26, rs.getString("religion"));
						pstmt2.setString(27, rs.getString("state"));
						end_status = rs.getString("end_status");
						if (end_status != null && end_status.equals("1")) 
						{
							pstmt2.setBoolean(28, true);
						} else {
							pstmt2.setBoolean(28, false);
						}	pstmt2.executeUpdate();
						counter++;
					}

				}
				conn.commit();
				}
				catch(Exception e)
				{
					System.out.println("Exception message  "+e.getMessage());
					conn.rollback();
				}
				if (counter > 0) {

					return counter;
				} else if (counter == 0 && flag) {
					return -1526;
				} else {
					return -404;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return counter - 1;
		} finally {
			try {
				if (rs_q != null)
					rs_q.close();
				if (pstmt_q != null)
					pstmt_q.close();
				if (pstmt2 != null)
					pstmt2.close();
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return counter;
	}

	public String pullOptionsBasedOnQuestionIdToLocationServer(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, Integer questionId) throws SQLException {

		int counter = 0;

		try {
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from answer_option where ";
			if (questionId != 0) {
				query2 = query2 + "question_id='" + questionId + "'";
			}

			if (conn != null && local_conn != null) {

				pstmt = local_conn.prepareStatement(query2);
				rs = pstmt.executeQuery();

				String query4 = "select answer_option_id from answer_option where answer_option_id=?";
				pstmt_q = conn.prepareStatement(query4);
				int answer_option_id = 0;

				String query3 = "insert into answer_option(answer_option_id,is_answer,answer_option,answer_option_hindi,option_order,question_id,english_image_id,hindi_image_id) values(?,?,?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(query3);

				counter = 0;
				while (rs.next()) {

					answer_option_id = rs.getInt("answer_option_id");
					pstmt_q.setInt(1, answer_option_id);
					rs_q = pstmt_q.executeQuery();
					if (rs_q.next()) {

					} else {
						pstmt2.setInt(1, rs.getInt("answer_option_id"));
						pstmt2.setBoolean(2, rs.getBoolean("is_answer"));
						pstmt2.setString(3, rs.getString("answer_option"));
						pstmt2.setString(4, rs.getString("answer_option_hindi"));
						pstmt2.setInt(5, rs.getInt("option_order"));
						pstmt2.setInt(6, rs.getInt("question_id"));
						pstmt2.setObject(7, rs.getObject("english_image_id"));
						pstmt2.setObject(8, rs.getObject("hindi_image_id"));
						pstmt2.executeUpdate();
						counter++;
					}

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
				if (rs_q != null)
					rs_q.close();
				if (pstmt_q != null)
					pstmt_q.close();
				if (pstmt2 != null)
					pstmt2.close();
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Success";

	}

	public String pullOtpAuthenticationData(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, int examId, int locationSessionId) {
		int counter = 0;

		try {
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from otp_verification where ";
			if (examId != 0) {
				query2 = query2 + "exam_id='" + examId + "'";
			}
			if (locationSessionId != 0) {
				query2 = query2 + " and exam_loc_session_id=" + locationSessionId + "";
			}

			if (conn != null && local_conn != null) {

				pstmt = local_conn.prepareStatement(query2);
				rs = pstmt.executeQuery();
				int id = 0;

				String query3 = "insert into otp_verification(id,generated_time,exam_id,exam_loc_session_id,otp,verified_time) values(?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(query3);
				String query4 = "select id from otp_verification where id=?";
				pstmt_q = conn.prepareStatement(query4);
				counter = 0;
				while (rs.next()) {
					id = rs.getInt("id");
					pstmt_q.setInt(1, id);
					rs_q = pstmt_q.executeQuery();
					if (rs_q.next()) {

					} else {
						counter++;
						pstmt2.setInt(1, id);
						pstmt2.setString(2, rs.getString("generated_time"));
						pstmt2.setInt(3, rs.getInt("exam_id"));
						pstmt2.setInt(4, rs.getInt("exam_loc_session_id"));
						pstmt2.setString(5, rs.getString("otp"));
						pstmt2.setString(6, rs.getString("verified_time"));
						pstmt2.executeUpdate();
					}
				}

				if (counter > 0) {

					return "Data successfully Pushed - Records Count - " + counter;
				} else {
					return "No Data Available to Pull";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			try {
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Success";

	}

	public int pushCandidateAnswers(DriverManagerDataSource dataSourceLocal, DriverManagerDataSource dataSourceServer, Integer count_candidate_qa_local) 
	{
		int counter = 0;

		try 
		{
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from candidate_qa where candidate_id=?";
			String query_to_get_distinct_can = "select distinct candidate_id from candidate_qa";

			if (conn != null && local_conn != null) 
			{
				pstmt4 = local_conn.prepareStatement(query_to_get_distinct_can);
				rs1 = pstmt4.executeQuery();

				pstmt = local_conn.prepareStatement(query2);

				String query_to_check_can = "select candidate_id from candidate_qa where candidate_id=?";

				String query3 = "insert into candidate_qa(candidate_id,date_time,ipaddress,answer_option_id,question_id,question_no,remaining_time,review) values(?,?,?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(query3);
				pstmt3 = conn.prepareStatement(query_to_check_can);
				int answer_option_id = 0;
				counter = 0;
				String candidate_id = "";
				try {
				while (rs1.next()) 
				{
					conn.setAutoCommit(false);
					candidate_id = rs1.getString("candidate_id");
					pstmt3.setString(1, candidate_id);
					rs2 = pstmt3.executeQuery();
					if (rs2.next()) 
					{} 
					else 
					{
						pstmt.setString(1, candidate_id);
						rs = pstmt.executeQuery();
						
						while (rs.next()) 
						{
							answer_option_id = rs.getInt("answer_option_id");
							counter++;

							pstmt2.setString(1, rs.getString("candidate_id"));
							pstmt2.setTimestamp(2, rs.getTimestamp("date_time"));
							pstmt2.setString(3, rs.getString("ipaddress"));

							if (answer_option_id == 0) 
							{
								pstmt2.setString(4, null);
							} 
							else 
							{
								pstmt2.setInt(4, answer_option_id);
							}
							pstmt2.setInt(5, rs.getInt("question_id"));
							pstmt2.setInt(6, rs.getInt("question_no"));
							pstmt2.setTime(7, rs.getTime("remaining_time"));
							pstmt2.setBoolean(8, rs.getBoolean("review"));
							pstmt2.executeUpdate();

						}
					}
				}
				
				 if(counter==count_candidate_qa_local)
                 {
                  conn.commit();
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
				if(counter > 0 && counter!=count_candidate_qa_local)
				{
					return -100;
				}
				else if (counter > 0) 
				{

					return counter;
				}
				else
				{
					return counter - 1;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return 25;
		} finally {
			try {
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return counter;

	}

	public String pullQuestionImages(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer) {
		int counter = 0;

		try {
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from question_images" + "" + "";

			if (conn != null && local_conn != null) {

				pstmt = local_conn.prepareStatement(query2);
				rs = pstmt.executeQuery();

				String query3 = "insert into question_images(image_id,image,image_name) values(?,?,?)";
				pstmt2 = conn.prepareStatement(query3);

				counter = 0;
				while (rs.next()) {

					counter++;
					pstmt2.setInt(1, rs.getInt("image_id"));
					pstmt2.setString(2, rs.getString("image"));
					pstmt2.setString(3, rs.getString("image_name"));

					pstmt2.executeUpdate();

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
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Success";

	}

	public Integer candidatePhotoVerificationApi(String apiUrl, String cId, String username, String password, String ip)
			throws Exception {

		String geturl = apiUrl.concat("?").concat("candidateId=").concat(cId).concat("&ipAddress=").concat(ip)
				.concat("&username=").concat(username).concat("&password=").concat(password);
		HttpURLConnection conn = null;
		try {
			URL url = new URL(geturl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded charset=UTF-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
		} catch (ConnectException e) {
			e.printStackTrace();
			return 500;
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output = "";
		StringBuilder jsonOutput = new StringBuilder();
		while ((output = br.readLine()) != null) {
			jsonOutput.append(output);
		}
		if (jsonOutput.length() != 1) {
			JSONObject obj = new JSONObject(jsonOutput.toString());
			if (obj.get("status").equals("200")) {
				return obj.getInt("count");
			} else {
				return -500;
			}
		}
		return 0;

	}

	public int pullCount(DriverManagerDataSource dataSourceLocal, int examId, int locationSessionId)
			throws SQLException {
		int count = 0;
		local_conn = dataSourceLocal.getConnection();

		String query2 = "SELECT COUNT(*) from candidate where ";
		if (examId != 0) {
			query2 = query2 + "exam_id='" + examId + "'";
		}
		if (locationSessionId != 0) {
			query2 = query2 + " and exam_loc_session_id=" + locationSessionId + "";
		}

		if (local_conn != null) {
			pstmt = local_conn.prepareStatement(query2);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
			count = rs.getInt("count(*)");
		}

		return count;
	}

	public int pullCountQue(DriverManagerDataSource dataSourceLocal, int examId, int locationSessionId)
			throws SQLException {
		int count = 0;
		local_conn = dataSourceLocal.getConnection();

		String query2 = "SELECT COUNT(*) from question where ";
		if (examId != 0) {
			query2 = query2 + "exam_id='" + examId + "'";
		}
		if (locationSessionId != 0) {
			//query2 = query2 + " and exam_loc_session_id=" + locationSessionId + "";
		}

		if (local_conn != null) {
			pstmt = local_conn.prepareStatement(query2);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
			count = rs.getInt("count(*)");
		}

		return count;

	}
	
	public int pullDesCountQue(DriverManagerDataSource dataSourceLocal, int examId, int locationSessionId)
			throws SQLException {
		int count = 0;
		local_conn = dataSourceLocal.getConnection();

		String query2 = "SELECT * from `exam_questions` where ";
		if (examId != 0) {
			query2 = query2 + "exam_id='" + examId + "'";
		}
		if (locationSessionId != 0) {
			//query2 = query2 + " and exam_loc_session_id=" + locationSessionId + "";
		}

		if (local_conn != null) {
			pstmt = local_conn.prepareStatement(query2);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				count = rs.getInt("easy")+rs.getInt("medium")+rs.getInt("difficult");
			}
			
		}

		return count;

	}

	public int pullCandidateQa(DriverManagerDataSource dataSourceLocal, int examId, int locationSessionId) throws SQLException
	{
		int count = 0;
		local_conn = dataSourceLocal.getConnection();
		String query2 = "SELECT COUNT(*) from candidate_qa where candidate_id in (SELECT candidate_id from candidate where exam_id=? AND exam_loc_session_id=?) ";
		if (local_conn != null) 
		{
			pstmt = local_conn.prepareStatement(query2);
			pstmt.setInt(1, examId);
			pstmt.setInt(2, locationSessionId);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
			count = rs.getInt("count(*)");
		}

		return count;
	}

}