-- 명령어 쓸 수 있음...



create table member (
    id         varchar2(100),
    name       varchar2(100),
    password   varchar2(100)
);
drop table member;

insert into member (id,name,password) values ('jjang051','장성호','1234');
insert into member (id,name,password) values ('hong','홍길동','1234');
commit;

rollback;

select * from member;

update member set password = '1234' where id = 'jjang051' and password = '5678';


-- 자동증가  auto increament  my sql 
create table board (
    id        number primary key,
    name      varchar2(100) not null,
    title     varchar2(300) not null,
    contents  varchar2(3000) not null,
    regdate   date default sysdate,
    hit       number
);

insert into board values (seq_board.nextval);







