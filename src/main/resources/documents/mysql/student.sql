CREATE TABLE `marksfinal` (
  `studentid` int NOT NULL,
  `semister` int DEFAULT NULL,
  `english` decimal(10,0) DEFAULT NULL,
  `maths` decimal(10,0) DEFAULT NULL,
  `science` decimal(10,0) DEFAULT NULL,
  `social` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`studentid`),
  UNIQUE KEY `studentid_UNIQUE` (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `standard` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `age` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `acceptForm` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='student details';
 
 CREATE TABLE `marks` (
  `studentid` int NOT NULL,
  `semister` int DEFAULT NULL,
  `english` decimal(10,0) DEFAULT NULL,
  `maths` decimal(10,0) DEFAULT NULL,
  `science` decimal(10,0) DEFAULT NULL,
  `social` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`studentid`),
  UNIQUE KEY `studentid_UNIQUE` (`studentid`),
  CONSTRAINT `studentmarksid` FOREIGN KEY (`studentid`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addStudent`(
IN p_name varchar(45),
IN p_age varchar(45),
IN p_phone varchar(45),
IN p_country varchar(45),
IN p_gender varchar(45),
IN p_standard varchar(45),
IN p_address varchar(45),
IN p_acceptForm varchar(45))
BEGIN
    insert into student(name, age, phone, country , gender , standard , address , acceptForm) values (p_name, p_age, p_phone, p_country , p_gender , p_standard , p_address , p_acceptForm);
    END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteStudent`(
IN p_id varchar(45))
BEGIN
    DELETE FROM student WHERE id=p_id;
    END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `findAllStudents`(
IN NAMEIN varchar(45),
out NAMEOUT varchar(45),
out COUNT int)
BEGIN
		SET NAMEOUT=NAMEIN;
		SELECT * FROM STUDENT.STUDENT as resultone;
        SELECT * FROM STUDENT.marks ;
		 SELECT COUNT(*) FROM STUDENT.STUDENT INTO COUNT;
	END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `findStudentByID`(IN id int)
BEGIN
		SELECT * FROM student stu WHERE stu.id = id;
	END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateStudent`(
IN p_id varchar(45),
IN p_name varchar(45),
IN p_age varchar(45),
IN p_phone varchar(45),
IN p_country varchar(45),
IN p_gender varchar(45),
IN p_standard varchar(45),
IN p_address varchar(45),
IN p_acceptForm varchar(45))
BEGIN
    UPDATE student SET name=p_name , phone=p_phone , age=p_age , gender=p_gender , country=p_country , standard=p_standard , address=p_address ,  acceptForm=p_acceptForm WHERE id=p_id ;
    END$$
DELIMITER ;
