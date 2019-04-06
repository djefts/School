CREATE TABLE  ZIPCODES(
zip int(5) PRIMARY KEY,
city varchar(255) 
);

CREATE TABLE  EMPLOYEES(
eno int PRIMARY KEY,
ename varchar(255),
zip int(5),
hdate Date
);

CREATE TABLE  PARTS(
pno int PRIMARY KEY,
pname varchar(255),
qoh int,
price double,
olevel int
);

CREATE TABLE CUSTOMERS(
cno int PRIMARY KEY,
cname varchar(255), 
street varchar(255),
zip int(5),
phone char(12)
);

CREATE TABLE ORDERS(
ono int PRIMARY KEY,
cno int, 
eno int, 
received Date, 
shipped Date,
FOREIGN KEY (cno)
REFERENCES CUSTOMERS(cno),
FOREIGN KEY (eno)
REFERENCES EMPLOYEES(eno)
);

CREATE TABLE ODETAILS(
ono int,
pno int,
qty int,
PRIMARY KEY(ono,pno),
FOREIGN KEY (ono)
REFERENCES ORDERS(ono),
FOREIGN KEY (pno)
REFERENCES PARTS(pno)
);


CREATE TABLE RESTOCK(
RES_DATE Date,
pno int,
FOREIGN KEY (pno)
REFERENCES PARTS(pno)
);

CREATE TABLE ORDERS_ERRORS(
Error_Date Date,
ono int,
Error_Msg varchar(255)
);

