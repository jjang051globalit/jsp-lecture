drop table member;

create table member(
    id         varchar2(100) not null,
    name       varchar2(100),
    password   varchar2(100)
);
-- Create  Read  Update  Delete   
insert into member (id,password,name) values ('jjang052','1234','장동건');
insert into member values ('hong','홍길동','5678');
delete from member where id = 'jjang052';
commit;
rollback;
--transaction

select id,name,password from member;
select * from member where id = 'jjang051' and password = '1234';





