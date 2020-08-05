create table tbl_board(

  bid      number(10)  primary key,
  cate_cd  varchar2(20)   not null ,
  title    varchar2(200)  not null ,
  content  clob          not null ,
  tag      varchar2(1000) null ,
  view_cnt number(10) default 0 not null ,
  reg_id   varchar2(45)   not null ,
  reg_dt   date          not null ,
  edit_dt  date          not null 
);

-- Generate ID using sequence and trigger
create sequence tbl_board_seq start with 1 increment by 1;

create or replace trigger tbl_board_seq_tr
 before insert on tbl_board for each row
 when (new.bid is null)
begin
 select tbl_board_seq.nextval into :new.bid from dual;
end;
/ 

