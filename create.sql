-- ��ɾ� �� �� ����...
SELECT * FROM RECYCLEBIN;

FLASHBACK TABLE "member" TO BEFORE DROP;


create table member (
    id               varchar2(100) unique not null,   --�ߺ�����  
    --�÷���          ������Ÿ��      ��������                                   
    name             varchar2(100) not null,
    -- name          varchar2(100)primary key,        --�ߺ�����
    password         VARCHAR2(100) not null,          --�ݵ�� �Է¹޾ƾ� �ϴ� ������ not null�� �־��ٰ�    
    email            VARCHAR2(100) not null,
    zonecode         NUMBER(5)not null,
    address          VARCHAR2(500)not null,
    detailaddress    VARCHAR2(100),
    extraaddress     VARCHAR2(100)
);
alter table member add profile varchar2(100);
alter table member add realprofile varchar2(100);




drop table member;

insert into member (id,name,password) values ('jjang051','�强ȣ','1234');
insert into member (id,name,password) values ('hong','ȫ�浿','1234');
commit;

rollback;

select * from member;

update member set password = '1234' where id = 'jjang051' and password = '5678';


-- �ڵ�����  auto increament  my sql 
create table board (
    id        number primary key,  -- ���� ���� ��ȣ
    userId    varchar2(100) not null, -- member id�� ���� ��ȸ
    name      varchar2(100) not null,
    title     varchar2(300) not null,
    contents  clob not null,
    regdate   date default sysdate,
    hit       number,
    constraint fk_userid foreign key(userId) references member (id)
    -- constraint [�������ϴ� ����Ű �̸�] foreign key([���� ���̺��� �÷���]) references [�ٸ� ���̺� ��] ([�ٸ� ���̺��� �÷���])
);

drop table board;

rollback;

insert into board values (seq_board.nextval,'jjang051','�嵿��','�����Դϴ�.','�����Դϴ�.',sysdate,0,'jjang051');

select rownum,board.* from board;

select * from board  where id < 105 and id > 95 order by id desc;

select * from member;


delete from board where id =  15;

commit;

rollback;

select * from board where id > 20 and id <= 30 order by id desc;

select * from member;

select * from board;

update board set hit = hit + 1 where id = 14;

select * from board order by id desc;

update board set  title = 'aaa', name = 'bbb', contents = 'cccc' where id = 22;

-- spring boot 


-- �������� 
--select * from
--(select rownum as no,b.* from 
--    (select * from board order by id desc) b) where no >=1  and no <= 10;

--select * from board order by id desc;
select * from
    (select rownum as no,b.* from
    (select * from board order by id desc) b) 
    where no >=1 and no<=10;

select count(*) as total from board;

ALTER TABLE BOARD  MODIFY (CONTENTS CLOB );

--alter table board modify contents CLOB;


create table replyboard (
    id          number primary key,  -- ���� ���� ��ȣ
    userId      varchar2(100) not null, -- member id�� ���� ��ȸ
    name        varchar2(100) not null,
    title       varchar2(300) not null,
    contents    clob not null,
    regdate     date default sysdate,
    hit         number,
    regroup     number not null,
    relevel     number not null,
    restep      number not null,
    constraint fk02_userid foreign key(userId) references member (id)
    -- constraint [�������ϴ� ����Ű �̸�] foreign key([���� ���̺��� �÷���]) references [�ٸ� ���̺� ��] ([�ٸ� ���̺��� �÷���])
);

drop table replyboard;

select   nvl( max(regroup),0 ) as regroupmax from replyboard;

select * from replyboard;



