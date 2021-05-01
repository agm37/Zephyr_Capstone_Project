#Create Database 
CREATE DATABASE `zephyr` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

#Create Company Logins table to hold usernames and passwords
CREATE TABLE `company_logins` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_password` varchar(60) NOT NULL,
  `hashed_password` varchar(60) DEFAULT NULL,
  `is_admin` tinyint(1) NOT NULL,
  `shareholder_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`company_id`),
  KEY `shareholder_id` (`shareholder_id`),
  CONSTRAINT `company_logins_ibfk_1` FOREIGN KEY (`shareholder_id`) REFERENCES `shareholder_info` (`shareholder_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4;

#Create Shareholder Info table to hold specific info for each shareholder
CREATE TABLE `shareholder_info` (
  `shareholder_id` int(11) NOT NULL AUTO_INCREMENT,
  `shareholder_name` varchar(45) NOT NULL,
  `company_name` varchar(45) NOT NULL,
  `num_shares` int(12) NOT NULL,
  `has_voted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`shareholder_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

#Create Shareholder Votes table to store when a shareholder votes in a poll
CREATE TABLE `shareholder_votes` (
  `shareholder_id` int(11) NOT NULL,
  `poll_id` int(11) NOT NULL,
  PRIMARY KEY (`shareholder_id`,`poll_id`),
  KEY `poll_id` (`poll_id`),
  CONSTRAINT `shareholder_votes_ibfk_1` FOREIGN KEY (`shareholder_id`) REFERENCES `shareholder_info` (`shareholder_id`),
  CONSTRAINT `shareholder_votes_ibfk_2` FOREIGN KEY (`poll_id`) REFERENCES `vote_info` (`poll_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#Create Vote Count table to store each parameter name and total votes for each parameter
CREATE TABLE `vote_count` (
  `poll_id` int(12) NOT NULL AUTO_INCREMENT,
  `parameter_name_1` varchar(45) DEFAULT NULL,
  `vote_count_1` int(12) DEFAULT NULL,
  `parameter_name_2` varchar(45) DEFAULT NULL,
  `vote_count_2` int(12) DEFAULT NULL,
  `parameter_name_3` varchar(45) DEFAULT NULL,
  `vote_count_3` int(12) DEFAULT NULL,
  `parameter_name_4` varchar(45) DEFAULT NULL,
  `vote_count_4` int(12) DEFAULT NULL,
  `parameter_name_5` varchar(45) DEFAULT NULL,
  `vote_count_5` int(12) DEFAULT NULL,
  `parameter_name_6` varchar(45) DEFAULT NULL,
  `vote_count_6` int(12) DEFAULT NULL,
  `parameter_name_7` varchar(45) DEFAULT NULL,
  `vote_count_7` int(12) DEFAULT NULL,
  `parameter_name_8` varchar(45) DEFAULT NULL,
  `vote_count_8` int(12) DEFAULT NULL,
  `parameter_name_9` varchar(45) DEFAULT NULL,
  `vote_count_9` int(12) DEFAULT NULL,
  `parameter_name_10` varchar(45) DEFAULT NULL,
  `vote_count_10` int(12) DEFAULT NULL,
  PRIMARY KEY (`poll_id`),
  CONSTRAINT `poll_id` FOREIGN KEY (`poll_id`) REFERENCES `vote_info` (`poll_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

#Create Vote Info table to hold specific information regarding each poll
CREATE TABLE `vote_info` (
  `poll_id` int(12) NOT NULL AUTO_INCREMENT,
  `poll_name` varchar(45) NOT NULL,
  `company_name` varchar(45) NOT NULL,
  `is_closed` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`poll_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;