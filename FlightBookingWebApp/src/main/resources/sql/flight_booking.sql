CREATE TABLE if not exists `fb_user` (
  `fb_userno` int auto_increment PRIMARY KEY,
  `fb_userid` varchar(30) NOT NULL,
  `fb_userpwd` varchar(200) NOT NULL,
  `fb_userfirstname` varchar(30) NOT NULL,
  `fb_userlastname` varchar(30) NOT NULL,
  `fb_userphonenumber` varchar(20) NOT NULL,
  `fb_useremail` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE if not exists `fb_mybookinglist` (
  `fb_userno` int PRIMARY KEY,
  `fb_flightno` int,
   FOREIGN KEY(`fb_userno`)
	REFERENCES `fb_user`(`fb_userno`),
   FOREIGN KEY(`fb_flightno`)
	REFERENCES `fb_flight`(`fb_flightno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE if not exists `fb_flight` (
  `fb_flightno` int auto_increment PRIMARY KEY,
  `fb_flightdate` date NOT NULL,
  `fb_flightfrom` varchar(50) NOT NULL,
  `fb_flightfrom_eng` varchar(50) NOT NULL,
  `fb_flightfromtime` time NOT NULL,
  `fb_flightto` varchar(50) NOT NULL,
  `fb_flightto_eng` varchar(50) NOT NULL,
  `fb_flighttotime` time NOT NULL,
  `fb_flightname` varchar(15) NOT NULL,
  `fb_flightequip` varchar(15) NOT NULL,
  `fb_flightprice` int NOT NULL,
  `fb_flightclass` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE if not exists `fb_flightdetails` (
  `fb_flightno` int PRIMARY KEY,
  `fb_flightdetail` varchar(20) NOT NULL,
  FOREIGN KEY(`fb_flightno`)
	REFERENCES `fb_flight`(`fb_flightno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;