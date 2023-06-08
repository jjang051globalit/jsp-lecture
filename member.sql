select * from member where id = 'jjang051';

drop table member;

create table member(
    id               varchar2(100) unique not null, -- 제약 조건
    -- id               varchar2(100) primary key,
    name             varchar2(100) not null,
    password         varchar2(100) not null,
    zonecode         number(5) not null,
    address          varchar2(300) not null,
    detailaddress    varchar2(100),
    extraaddress     varchar2(100)
);

-- Create  Read  Update  Delete   
insert into member (id,password,name) values ('jjang052','1234','장동건');
insert into member values ('hong','홍길동','5678');
delete from member where id = 'jjang052';
commit;
rollback;
--transaction
select * from member;
select id,name,password from member;
select * from member where id = 'jjang051' and password = '1234';

select id,name,address,lpad(zonecode,5,'0') as zonecode,detailAddress from member;

delete from member where id = 'jjang051' and password = '1234';
rollback;




