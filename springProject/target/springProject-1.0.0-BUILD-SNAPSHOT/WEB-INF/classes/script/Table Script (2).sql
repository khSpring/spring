
--------------------------------------------------
--------------     MEMBER ����	------------------	
--------------------------------------------------
DROP TABLE MEMBER CASCADE CONSTRAINTS;

CREATE TABLE MEMBER (
  ID VARCHAR2(30) PRIMARY KEY,
  PWD VARCHAR2(100) NOT NULL,
  NAME VARCHAR2(15) NOT NULL,
  EMAIL VARCHAR2(100),
  GENDER VARCHAR2(1) CHECK (GENDER IN('M', 'F')),
  AGE NUMBER,
  PHONE VARCHAR2(13),
  ADDRESS VARCHAR2(100),
  ENROLL_DATE DATE DEFAULT SYSDATE,
  UPDATE_DATE DATE DEFAULT SYSDATE,
  M_STATUS VARCHAR2(1) DEFAULT 'Y' CHECK(M_STATUS IN('Y', 'N'))
);

COMMENT ON COLUMN MEMBER.ID IS 'ȸ�����̵�';
COMMENT ON COLUMN MEMBER.PWD IS 'ȸ����й�ȣ';
COMMENT ON COLUMN MEMBER.NAME IS 'ȸ���̸�';
COMMENT ON COLUMN MEMBER.EMAIL IS 'ȸ���̸���';
COMMENT ON COLUMN MEMBER.GENDER IS 'ȸ������';
COMMENT ON COLUMN MEMBER.AGE IS 'ȸ������';
COMMENT ON COLUMN MEMBER.PHONE IS 'ȸ����ȭ��ȣ';
COMMENT ON COLUMN MEMBER.ADDRESS IS 'ȸ���ּ�';
COMMENT ON COLUMN MEMBER.ENROLL_DATE IS 'ȸ�����Գ�¥';
COMMENT ON COLUMN MEMBER.UPDATE_DATE IS 'ȸ��������¥';
COMMENT ON COLUMN MEMBER.M_STATUS IS 'ȸ�����°�';

INSERT INTO MEMBER 
VALUES ('admin', '1234', '������', 'admin@kh.or.kr', 'F', 30, '010-1111-2222', '����� ������ ���ﵿ', '20180101', '20180101', DEFAULT);

INSERT INTO MEMBER 
VALUES ('user01', 'pass01', 'ȫ�浿', 'user01@kh.or.kr', 'M', 25, '010-3333-4444', '����� ��õ�� ��', '20180201', '20180202', DEFAULT);

INSERT INTO MEMBER 
VALUES ('user02', 'pass02', '�踻��', 'user02@kh.or.kr', 'F', 23, '010-5555-6666', '����� ������ ����', '20180301', '20180302', DEFAULT);

COMMIT;

----------------------------------------------------
----------------     NOTICE ����        -----------------	
----------------------------------------------------
DROP TABLE NOTICE CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_NID;

CREATE TABLE NOTICE (
  NID NUMBER PRIMARY KEY,
  NTITLE VARCHAR2(30) NOT NULL,
  NWRITER VARCHAR2(30),
  NCONTENT VARCHAR2(200),
  N_CREATE_DATE DATE DEFAULT SYSDATE,
  N_MODIFY_DATE DATE DEFAULT SYSDATE,
  FILE_PATH VARCHAR2(300),
  FOREIGN KEY (NWRITER) REFERENCES MEMBER(ID) ON DELETE CASCADE
);

COMMENT ON COLUMN NOTICE.NID IS '�������׹�ȣ';
COMMENT ON COLUMN NOTICE.NTITLE IS '������������';
COMMENT ON COLUMN NOTICE.NWRITER IS '���������ۼ��ھ��̵�';
COMMENT ON COLUMN NOTICE.NCONTENT IS '�������׳���';
COMMENT ON COLUMN NOTICE.N_CREATE_DATE IS '���������ۼ���¥';
COMMENT ON COLUMN NOTICE.N_MODIFY_DATE IS '�������׼�����¥';
COMMENT ON COLUMN NOTICE.FILE_PATH IS '÷�����ϰ��';

CREATE SEQUENCE SEQ_NID;

INSERT INTO NOTICE VALUES (SEQ_NID.NEXTVAL, '������ ����', 'admin',
                          '�������񽺸� �Խ��մϴ�.  ���� �̿��� �ּ���', 
                          '20180201', '20180201', NULL);
                          
INSERT INTO NOTICE VALUES (SEQ_NID.NEXTVAL, '�������� ���� ȯ��', 'admin',
                          '���� ���µǾ�����. ���� �̿��ϰڽ��ϴ�.', 
                          '20180201', '20180201', NULL);
                          
INSERT INTO NOTICE VALUES (SEQ_NID.NEXTVAL, '�������� �̿� �ȳ�', 'admin',
                          '�������񽺴� ȸ���� �̿��� �� �ֽ��ϴ�. ȸ�� �����ϼ���.', 
                          '20180201', '20180201', NULL);

COMMIT;


----------------------------------------------------
----------------     BOARD ����        -----------------	
----------------------------------------------------
DROP TABLE BOARD CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_BID;

CREATE TABLE BOARD(
  BID NUMBER PRIMARY KEY,
  BTITLE VARCHAR2(100) NOT NULL,
  BWRITER VARCHAR2(15),
  BCONTENT VARCHAR2(4000),
  ORIGINAL_FILENAME VARCHAR2(100),
  RENAME_FILENAME VARCHAR2(100),
  BCOUNT NUMBER DEFAULT 0,
  B_CREATE_DATE DATE,
  B_MODIFY_DATE DATE,
  B_STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (B_STATUS IN('Y', 'N')),
  FOREIGN KEY (BWRITER) REFERENCES MEMBER(ID) ON DELETE CASCADE
);

COMMENT ON COLUMN BOARD.BID IS '�Խñ۹�ȣ';
COMMENT ON COLUMN BOARD.BTITLE IS '�Խñ�����';
COMMENT ON COLUMN BOARD.BWRITER IS '�Խñ��ۼ��� ���̵�';
COMMENT ON COLUMN BOARD.BCONTENT IS '�Խñ۳���';
COMMENT ON COLUMN BOARD.ORIGINAL_FILENAME IS '÷�����Ͽ����̸�';
COMMENT ON COLUMN BOARD.RENAME_FILENAME IS '÷�����Ϻ����̸�';
COMMENT ON COLUMN BOARD.BCOUNT IS '�Խñ���ȸ��';
COMMENT ON COLUMN BOARD.B_CREATE_DATE IS '�Խñۿø���¥';
COMMENT ON COLUMN BOARD.B_MODIFY_DATE IS '�Խñۼ����ѳ�¥';
COMMENT ON COLUMN BOARD.B_STATUS IS '�Խñۻ��°�';


CREATE SEQUENCE SEQ_BID;

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, '������ �Խñ�', 'admin', 
       '���� ����Ʈ�� �̿��� �ּż� �����մϴ�.', NULL, NULL, 
       DEFAULT, '20180210', '20180210', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'MVC Model2 �����̶�', 'user01', 
       '�� ���ø����̼� ���� ��� �� �ϳ��Դϴ�.', NULL, NULL, 
       DEFAULT, '20180211', '20180211', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, '������ 2', 'user02', 
       '�� ���ø����̼� ���� ��� �� �ι�° ����� �� ���� ���� �տ� First Controller �� �δ� ���Դϴ�..', 
       NULL, NULL,  DEFAULT, '20180212', '20180212', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, '������3', 'user02', 
       '�� ���ø����̼� ���� ��� �� ����° ����� Front Controller ������ ����Ǵ� ��Ʈ�ѷ����� ������ �ƴ� �ڹ� Ŭ������ �ۼ��ؼ� �����ϴ� ����Դϴ�.', 
       NULL, NULL, DEFAULT, '20180220', '20180220', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'MVC Model1 ����', 'user01', 
       '�� ���ø����̼� ���� ��� �� JSP ������ ��� ��Ʈ�ѷ� �ΰ��� �ٸ� ó���ϴ� ����Դϴ�.', 
        NULL, NULL, DEFAULT, '20180220', '20180220', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'JSP��', 'user02', 'Java Server Page �� ����', NULL, NULL, 
       DEFAULT, '20180221', '20180221', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'Servlet �̶�', 'admin', '�������� �����Ǵ� �� �Ծ��� ����� Java EE ����� �����ϴ� ���� ó���� Ŭ������.', 
       NULL, NULL, DEFAULT, '20180221', '20180221', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'JSP Elements ��', 'admin', 
       'Derective(������) �±�, Decleation(����) �±�, Scriptlet �±�, Expression �±�, Comment �±װ� �ִ�.', 
      NULL, NULL, DEFAULT, '20180224', '20180225', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'HTML5', 'user02', 
       '���ο� �� ǥ�ر���� ��� ����̽� ��ġ�� ���������� �����ϰ� �۵��Ǵ� �� �������� ����� ���� ����� �����Ѵ�.', 
       NULL, NULL, DEFAULT, '20180301', '20180301', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'CSS3', 'user02', '�� �������� �ٹ̱� ���� ��Ÿ�Ͻ�Ʈ�� HTML5 ������ ���߾� �Ӽ����� ���׷��̵� �Ǿ���.', 
       NULL, NULL, DEFAULT, '20180301', '20180301', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'JavaScript ��', 'user01', '�� ǥ�� 2.0 ���� ���� �߰��� ������ API ���� �����Ѵ�.', 
       NULL, NULL, DEFAULT, '20180301', '20180301', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'jQuery ��', 'admin', 
       '�ڹٽ�ũ��Ʈ ���� �ҽ� ���̺귯���� �ϳ��� html ��ҵ��� css �����ڸ� �̿��Ͽ� ���� ������ �� �ִ� ��ɵ��� �����Ѵ�..', 
       NULL, NULL, DEFAULT, '20181021', '20181021', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'ajax ��', 'admin', 'asynchronos javascript and xml �� ���Ӹ��� ������ ������ ���� ����ϴ� �ڹٽ�ũ��Ʈ ����̴�.', 
       NULL, NULL, DEFAULT, '20181021', '20181021', DEFAULT);
       
INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, '����(Filter) ��', 'user02', 'Ŭ���̾�Ʈ ��û�� ���񽺰� �������� ���޵Ǳ� ���� ���� �����Ǵ� Ŭ�����̴�.', 
       NULL, NULL, DEFAULT, '20181021', '20181021', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, '����(Wrapper) ��', 'user01', '���Ͱ� ����æ ��û�� ���� ������ ó���� ����ϴ� Ŭ�����̴�.', 
       NULL, NULL, DEFAULT, '20181021', '20181021', DEFAULT);



----------------------------------------------------
---------------     REPLY ����         -------------------	
----------------------------------------------------
DROP TABLE REPLY CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_RID;

CREATE TABLE REPLY(
  RID NUMBER PRIMARY KEY,
  RCONTENT VARCHAR2(400),
  REF_BID NUMBER,
  RWRITER VARCHAR2(15),
  R_CREATE_DATE DATE,
  R_MODIFY_DATE DATE,
  R_STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (R_STATUS IN ('Y', 'N')),
  FOREIGN KEY (REF_BID) REFERENCES BOARD ON DELETE CASCADE, 
  FOREIGN KEY (RWRITER) REFERENCES MEMBER(ID) ON DELETE CASCADE 
);

COMMENT ON COLUMN REPLY.RID IS '��۹�ȣ';
COMMENT ON COLUMN REPLY.RCONTENT IS '��۳���';
COMMENT ON COLUMN REPLY.REF_BID IS '�����Խñ۹�ȣ';
COMMENT ON COLUMN REPLY.RWRITER IS '����ۼ��ھ��̵�';
COMMENT ON COLUMN REPLY.R_CREATE_DATE IS '����ۼ���¥';
COMMENT ON COLUMN REPLY.R_MODIFY_DATE IS '��ۼ�����¥';
COMMENT ON COLUMN REPLY.R_STATUS IS '��ۻ��°�';

CREATE SEQUENCE SEQ_RID;

INSERT INTO REPLY
VALUES(SEQ_RID.NEXTVAL, 'ù��° ����Դϴ�.', 1, 'admin', '20180213', '20180213', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RID.NEXTVAL, 'ù��° ����Դϴ�.', 13, 'user01', '20181030', '20181030', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RID.NEXTVAL, '�ι�° ����Դϴ�.', 13, 'user02', '20181030', '20181030', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RID.NEXTVAL, '������ ����Դϴ�.', 13, 'admin', '20181030', '20181030', DEFAULT);



COMMIT;
