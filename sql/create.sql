CREATE TABLE clients (
  id int(10) NOT NULL AUTO_INCREMENT,
  lastname varchar(30) NOT NULL,
  firstname varchar(30) NOT NULL,
  middlename varchar(30) DEFAULT NULL,
  occupation varchar(50) DEFAULT NULL,
  email varchar(50) NOT NULL,
  address varchar(50) NOT NULL,
  city varchar(50) NOT NULL,
  state varchar(20) DEFAULT NULL,
  zip varchar(10) DEFAULT NULL,
  phone varchar(15) DEFAULT NULL,
  fax varchar(15) DEFAULT NULL,
  lastseen date DEFAULT NULL,
  location varchar(20) DEFAULT NULL,
  notes longtext,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
