package com.ttipl.service;

public class EndSessionService {

	public static String[] TABLE_TO_RENAME = { "candidate_qa", "question", "answer_option", "question_images",
			"candidate" };
	public static String[] TABLE_TO_CREATE = { "CREATE TABLE `candidate` (\r\n"
			+ "  `candidate_id` varchar(30) NOT NULL,\r\n" + "  `candidate_first_name` varchar(150) DEFAULT NULL,\r\n"
			+ "  `candidate_last_name` varchar(150) DEFAULT NULL,\r\n" + "  `gender` varchar(10) DEFAULT NULL,\r\n"
			+ "  `father_name` varchar(150) DEFAULT NULL,\r\n" + "  `dob` date DEFAULT NULL,\r\n"
			+ "  `caste` varchar(40) DEFAULT NULL,\r\n" + "  `religion` varchar(40) DEFAULT NULL,\r\n"
			+ "  `address` text,\r\n" + "  `district` varchar(100) DEFAULT NULL,\r\n"
			+ "  `state` varchar(40) DEFAULT NULL,\r\n" + "  `pincode` int DEFAULT NULL,\r\n"
			+ "  `email_id` varchar(150) DEFAULT NULL,\r\n" + "  `contact_no` bigint DEFAULT NULL,\r\n"
			+ "  `exam_loc_session_id` int DEFAULT NULL,\r\n" + "  `community` varchar(30) DEFAULT NULL,\r\n"
			+ "  `exam_end_time` datetime DEFAULT NULL,\r\n" + "  `exam_is_end` tinyint(1) DEFAULT '0',\r\n"
			+ "  `login_ip` varchar(40) DEFAULT NULL,\r\n" + "  `lab_location_id` int DEFAULT NULL,\r\n"
			+ "  `photo` blob,\r\n" + "  `exam_code` varchar(255) DEFAULT NULL,\r\n"
			+ "  `exam_duration` varchar(255) DEFAULT NULL,\r\n" + "  `post_id` varchar(255) DEFAULT NULL,\r\n"
			+ "  `post_name` varchar(255) DEFAULT NULL,\r\n" + "  `password` varchar(255) DEFAULT NULL,\r\n"
			+ "  `exam_id` int DEFAULT NULL,\r\n" + "  `candidate_photo` longtext,\r\n"
			+ "  PRIMARY KEY (`candidate_id`),\r\n" + "  KEY `exam_loc_session_id` (`exam_loc_session_id`),\r\n"
			+ "  CONSTRAINT `candidate_ibfk_1` FOREIGN KEY (`exam_loc_session_id`) REFERENCES `exam_loc_session` (`exam_loc_session_id`)\r\n"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\r\n" ,
			
			"CREATE TABLE `question_images` (\r\n" + "  `image_id` int NOT NULL AUTO_INCREMENT,\r\n"
					+ "  `image` longtext,\r\n" + "  `image_name` varchar(255) DEFAULT NULL,\r\n"
					+ "  PRIMARY KEY (`image_id`)\r\n"
					+ ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ;\r\n",

			"CREATE TABLE `question` (\r\n" + "  `question_id` int NOT NULL AUTO_INCREMENT,\r\n"
					+ "  `exam_id` int DEFAULT NULL,\r\n" + "  `question` longtext,\r\n"
					+ "  `question_hindi` longtext,\r\n" + "  `question_type` varchar(40) DEFAULT NULL,\r\n"
					+ "  `exam_loc_session_id` int DEFAULT NULL,\r\n" + "  `bank_question_id` int DEFAULT NULL,\r\n"
					+ "  `question_paper_code` varchar(30) DEFAULT NULL,\r\n" + "  `status` tinyint DEFAULT NULL,\r\n"
					+ "  `question_no` int DEFAULT NULL,\r\n" + "  `english_image_id` int DEFAULT NULL,\r\n"
					+ "  `hindi_image_id` int DEFAULT NULL,\r\n" + "  PRIMARY KEY (`question_id`),\r\n"
					+ "  KEY `exam_id` (`exam_id`),\r\n" + "  KEY `exam_loc_session_id` (`exam_loc_session_id`),\r\n"
					+ "  KEY `question_ibfk_3` (`english_image_id`),\r\n"
					+ "  KEY `question_ibfk_4` (`hindi_image_id`),\r\n"
					+ "  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`),\r\n"
					+ "  CONSTRAINT `question_ibfk_2` FOREIGN KEY (`exam_loc_session_id`) REFERENCES `exam_loc_session` (`exam_loc_session_id`),\r\n"
					+ "  CONSTRAINT `question_ibfk_3` FOREIGN KEY (`english_image_id`) REFERENCES `question_images` (`image_id`),\r\n"
					+ "  CONSTRAINT `question_ibfk_4` FOREIGN KEY (`hindi_image_id`) REFERENCES `question_images` (`image_id`)\r\n"
					+ ") ENGINE=InnoDB AUTO_INCREMENT=1665 DEFAULT CHARSET=utf8;\r\n",

			"CREATE TABLE `answer_option` (\r\n" + "  `answer_option_id` int NOT NULL AUTO_INCREMENT,\r\n"
					+ "  `question_id` int DEFAULT NULL,\r\n" + "  `answer_option` longtext,\r\n"
					+ "  `answer_option_hindi` longtext,\r\n" + "  `is_answer` tinyint(1) DEFAULT NULL,\r\n"
					+ "  `option_order` int DEFAULT NULL,\r\n" + "  `english_image_id` int DEFAULT NULL,\r\n"
					+ "  `hindi_image_id` int DEFAULT NULL,\r\n" + "  PRIMARY KEY (`answer_option_id`),\r\n"
					+ "  KEY `question_id` (`question_id`),\r\n"
					+ "  KEY `answer_option_ibfk_2` (`english_image_id`),\r\n"
					+ "  KEY `answer_option_ibfk_3` (`hindi_image_id`),\r\n"
					+ "  CONSTRAINT `answer_option_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`),\r\n"
					+ "  CONSTRAINT `answer_option_ibfk_2` FOREIGN KEY (`english_image_id`) REFERENCES `question_images` (`image_id`),\r\n"
					+ "  CONSTRAINT `answer_option_ibfk_3` FOREIGN KEY (`hindi_image_id`) REFERENCES `question_images` (`image_id`)\r\n"
					+ ") ENGINE=InnoDB AUTO_INCREMENT=6642 DEFAULT CHARSET=utf8;\r\n",
			"CREATE TABLE `candidate_qa` (\r\n" + "  `answer_option_id` int DEFAULT NULL,\r\n"
					+ "  `question_id` int NOT NULL DEFAULT '0',\r\n"
					+ "  `candidate_id` varchar(30) NOT NULL DEFAULT '',\r\n"
					+ "  `date_time` datetime DEFAULT NULL,\r\n" + "  `ipaddress` varchar(40) DEFAULT NULL,\r\n"
					+ "  `review` tinyint(1) DEFAULT NULL,\r\n" + "  `question_no` int DEFAULT NULL,\r\n"
					+ "  `remaining_time` time DEFAULT NULL,\r\n" + "  `candidate_qa_id` int DEFAULT '0',\r\n"
					+ "  `flag` bit(1) DEFAULT b'0',\r\n" + "  PRIMARY KEY (`question_id`,`candidate_id`),\r\n"
					+ "  KEY `answer_option_id` (`answer_option_id`),\r\n"
					+ "  KEY `candidate_id` (`candidate_id`),\r\n"
					+ "  CONSTRAINT `candidate_qa_ibfk_1` FOREIGN KEY (`answer_option_id`) REFERENCES `answer_option` (`answer_option_id`),\r\n"
					+ "  CONSTRAINT `candidate_qa_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`),\r\n"
					+ "  CONSTRAINT `candidate_qa_ibfk_3` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`candidate_id`)\r\n"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;"

			, };

}
