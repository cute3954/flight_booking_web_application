CREATE TABLE `fb_user` (
  `fb_userno` int auto_increment PRIMARY KEY,
  `fb_userid` varchar(30) NOT NULL,
  `fb_userpwd` varchar(200) NOT NULL,
  `fb_userfirstname` varchar(30) NOT NULL,
  `fb_userlastname` varchar(30) NOT NULL,
  `fb_userphonenumber` varchar(20) NOT NULL,
  `fb_useremail` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;