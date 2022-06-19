package com.ttipl.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.util.FileCopyUtils;

import com.ttipl.pojo.Candidate;

public class MAC {

	/*
	 * public static String getMacAddrHost(String host) throws IOException,
	 * InterruptedException { // boolean ok = ping3(host); // if (ok) { InetAddress
	 * address = InetAddress.getByName(host); String ip = address.getHostAddress();
	 * return run_program_with_catching_output("getmac /s " + ip); } // return null;
	 * // }
	 * 
	 * public static boolean ping3(String host) throws IOException,
	 * InterruptedException { boolean isWindows =
	 * System.getProperty("os.name").toLowerCase().contains("win");
	 * 
	 * ProcessBuilder processBuilder = new ProcessBuilder("ping", isWindows ? "-n" :
	 * "-c", "1", host); Process proc = processBuilder.start();
	 * 
	 * int returnVal = proc.waitFor(); return returnVal == 0; }
	 * 
	 * public static String run_program_with_catching_output(String param) throws
	 * IOException { Process p = Runtime.getRuntime().exec(param); BufferedReader
	 * input = new BufferedReader(new InputStreamReader(p.getInputStream())); String
	 * line; while ((line = input.readLine()) != null) { if
	 * (!line.trim().equals("")) { // keep only the process name line =
	 * line.substring(1); String mac = extractMacAddr(line); if (mac.isEmpty() ==
	 * false) { return mac; } }
	 * 
	 * } return null; }
	 * 
	 * public static String extractMacAddr(String str) { String arr[] =
	 * str.split("   "); for (String string : arr) { if (string.trim().length() ==
	 * 17) { return string.trim().toUpperCase(); } } return ""; }
	 */

	public static String getMac(String ipaddress) {
		InetAddress ip;
		StringBuilder sb = null;
		NetworkInterface network = null;
		try {

			ip = InetAddress.getLocalHost();
			network = NetworkInterface.getByInetAddress(ip);

			byte[] mac = network.getHardwareAddress();
			sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
		} catch (Exception e) {
			sb = new StringBuilder();
			e.printStackTrace();
		}
		return sb.toString();

	}

	public static void main(String[] args) {
		/*
		 * try { copyFile(
		 * "C:\\Users\\DELL\\Downloads\\cbt_photos\\cbt_photos\\1000000001.jpg",
		 * "C:\\Users\\DELL\\Downloads\\cbt_photos\\photos_byName\\first.jpg"); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		//readExcel("");
		selectPhotos();
	}

	public static void readExcel(String fileName) {

		try {
			File file = new File("C:\\Users\\DELL\\Downloads\\text2.xlsx"); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			XSSFDrawing dp = wb.getSheetAt(0).createDrawingPatriarch();
			List<XSSFShape> pics = dp.getShapes();
			int image_coulmn = 0, image_row = 0;
			XSSFPicture inpPic = null;
			XSSFClientAnchor clientAnchor = null;
			List<Candidate> canList = new ArrayList<Candidate>();
			int noOfCols = 0;
			String blob = null;
			Cell cell = null;
			String fName = "", CandidateName = "", option_eng_2 = "", rollNo = "", option_eng_3 = "";
			int rowCount = 0;
			for (Row row : sheet) {
				if (rowCount >= 1) {
					noOfCols =  row.getPhysicalNumberOfCells();
					for (int i = 0; i < noOfCols; i++) {
						cell = row.getCell(i);
						if (i == 0) {
							if (cell.getCellType() == CellType.STRING) {
								fName = cell.getStringCellValue();
								fName = fName.replaceAll("'", "");
								fName = fName.trim();
							} else if (cell.getCellType() == CellType.NUMERIC) {
								fName = (int) cell.getNumericCellValue() + "";

							}

						}

						if (i == 1) {
							if (cell.getCellType() == CellType.STRING) {
								CandidateName = cell.getStringCellValue();
								CandidateName = CandidateName.replaceAll("'", "");
								CandidateName = CandidateName.trim();
							} else if (cell.getCellType() == CellType.NUMERIC) {
								CandidateName = (int) cell.getNumericCellValue() + "";

							}

						}
						if (i == 2) {
							if (cell.getCellType() == CellType.STRING) {
								option_eng_2 = cell.getStringCellValue();
								option_eng_2 = option_eng_2.replaceAll("'", "");
								option_eng_2 = option_eng_2.trim();
							} else if (cell.getCellType() == CellType.NUMERIC) {
								option_eng_2 = (int) cell.getNumericCellValue() + "";

							}

						}
						if (i == 3) {
							if (cell.getCellType() == CellType.STRING) {
								rollNo = cell.getStringCellValue();
								rollNo = rollNo.replaceAll("'", "");
								rollNo = rollNo.trim();
							} else if (cell.getCellType() == CellType.NUMERIC) {
								rollNo = (int) cell.getNumericCellValue() + "";

							}
						}
					}
					if (pics != null && pics.size() > 0) {
						for (XSSFShape pic : pics) {
							// XSSFPicture inpPic = (XSSFPicture)pics.get(0);
							inpPic = (XSSFPicture) pic;
							clientAnchor = inpPic.getClientAnchor();
							image_coulmn = clientAnchor.getCol1();
							image_row = clientAnchor.getRow1();
							if (image_row == rowCount) {

								blob = null;
								inpPic.getShapeName();
								PictureData pict = inpPic.getPictureData();
								byte[] data = pict.getData();
								try {
									blob = Base64.getEncoder().encodeToString(data);
									System.out.println(image_row);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					if (option_eng_2 == "") {
						option_eng_2 = option_eng_3;
					} else {
						option_eng_3 = option_eng_2;
					}
					/*Candidate can = new Candidate();
					can.setCandidate_first_name(option_eng_2);
					can.setCandidate_id(fName);
					can.setCandidateIpAddress(CandidateName);
					can.setPost_name(blob);
					canList.add(can);*/
					 copyFile("C:\\Users\\DELL\\Downloads\\cbt_photos\\".concat(rollNo).concat(".jpg"),
					"C:\\Users\\DELL\\Downloads\\photos_byName\\".concat(fName));

				}
				rowCount++;

			}
			System.out.println(canList.size());
			// insertToDBip_photo_tbl(canList);
			insertToDB(canList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void copyFile(String from_path, String to_path) throws IOException {

		File source = new File(from_path);

		File destination = new File(to_path);
		if (!destination.exists())
			destination.createNewFile();

		InputStream oInStream = new FileInputStream(source);
		OutputStream oOutStream = new FileOutputStream(destination);

		// Transfer bytes from in to out
		byte[] oBytes = new byte[1024];
		int nLength;

		BufferedInputStream oBuffInputStream = new BufferedInputStream(oInStream);
		while ((nLength = oBuffInputStream.read(oBytes)) > 0) {
			oOutStream.write(oBytes, 0, nLength);
		}
		oInStream.close();
		oOutStream.close();
	}

	public static void insertToDB(List<Candidate> list) {
		Connection conn;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_cbt_photos", "root", "ttipl_hssc");

			PreparedStatement pspt = conn
					.prepareStatement("insert into photos(name,candidate_id, ipaddress, photo) values(?,?,?,?)");

			for (Candidate candidate : list) {
				pspt.setString(1, candidate.getCandidate_first_name());// name
				pspt.setString(2, candidate.getCandidate_id());// id
				pspt.setString(3, candidate.getCandidateIpAddress());
				pspt.setString(4, candidate.getPost_name());// filename
				pspt.execute();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void insertToDBip_photo_tbl(List<Candidate> list) {
		Connection conn;

		try {
			String filename = "";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_cbt_photos", "root", "ttipl_hssc");
			PreparedStatement psptq = conn
					.prepareStatement("select file_name from photos_name_relation where  candidate_name=? limit 1");

			PreparedStatement pspt = conn.prepareStatement(
					"insert into ip_photo_tbl_dummy(photo_name,candidate_id ,ipAddress) values(?,?,?)");

			for (Candidate candidate : list) {
				psptq.setString(1, candidate.getCandidate_first_name());
				ResultSet rs = psptq.executeQuery();
				if (rs.next()) {
					filename = rs.getString("file_name");
				} else {
					filename = "";
				}
				pspt.setString(1, filename);// name
				pspt.setString(2, candidate.getCandidate_id());// id
				pspt.setString(3, candidate.getCandidateIpAddress());// filename
				pspt.execute();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void selectPhotos() {
		Connection conn;

		try {
			StringBuilder builder = new StringBuilder();
			String filename = "";
			String id = "";
			String name = "";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_cbt_photos", "root", "ttipl_hssc");
			PreparedStatement psptq = conn.prepareStatement("select * from ip_photo_tbl_dummy where candidate_id=?");
			for (int i = 1000000000; i <= 1000000250; i++) {

				psptq.setInt(1, i);
				/*
				 * PreparedStatement pspt = conn.prepareStatement(
				 * "insert into ip_photo_tbl_dummy(photo_name,candidate_id ,ipAddress) values(?,?,?)"
				 * );
				 */
				ResultSet rs = psptq.executeQuery();
				while (rs.next()) {
					id = rs.getString("candidate_id");
					// name = rs.getString("name");
					InputStream is = null;
					try {
						filename = rs.getString("photo_name");
						is = new FileInputStream(
								"C:\\Users\\DELL\\Downloads\\photos_byName\\" + filename.concat(".jpg"));
					} catch (FileNotFoundException e) {
						e.getMessage();
					}
					if (is != null) {
						byte[] bs = new byte[is.available()];
						is.read(bs);
						String data = Base64.getEncoder().encodeToString(bs);
						//System.out.println(data);
						String query="update candidate set candidate_photo= \"" + data + "\", candidate_first_name=\""
								+ name + "\"  where candidate_id=" + id + ";";
						System.out.println(query);
						builder.append(query );
						is.close();
					}
					System.out.println(i);

				}

			}
			/*
			 * pspt.setString(1, filename);// name pspt.setString(2,
			 * candidate.getCandidate_id());// id pspt.setString(3,
			 * candidate.getCandidateIpAddress());// filename sytopspt.execute();
			 */
			
			File file = new File("C:\\Users\\DELL\\Downloads\\Data Cbt1\\updatePhoto.sql");
			// file.createNewFile();
			OutputStream st = new FileOutputStream(file);
			st.write(builder.toString().getBytes());
			st.close();
			;

		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
