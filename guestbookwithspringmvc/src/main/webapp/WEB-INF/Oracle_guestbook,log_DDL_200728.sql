-- Guestbook table 持失 DDL (神虞適)


CREATE TABLE guestbook (
 id NUMBER(38) PRIMARY KEY,
 name varchar2(255) NOT NULL,
 content VARCHAR2(4000),
 regdate timestamp
 );

-- log table 持失 DDL (神虞適)
 CREATE TABLE LOG (
id NUMBER(38) PRIMARY KEY,
ip VARCHAR2(255) NOT NULL,
method VARCHAR2(10) NOT NULL,
regdate TIMESTAMP);
 