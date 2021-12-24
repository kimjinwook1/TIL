# 20211224 Oracle query

* subquery (가상 테이블까지 데려올 수 있음)
* 사원 테이블에서 사원들의 평균 월급보다 더 많은 급여를 받는 사원의 사번, 이름, 급여를 출력하세요.

select *
from emp
where sal > (select avg(sal) from emp);

**Subquery**
1. Single row subquery : subquery 의 실행 결과가 단일 row 단일 column(단일 값) : 한 개의 값
기존 연산자 ( =, >, <, …)

**Multi row subquery : subquery 의 실행결과가 1개 이상의 row((여러 개의 값): 단일 컬럼**
 IN , NOT IN , ANY , ALL 연산자
ALL : sal > 1000 and sal > 4000 and …
ANY : sal > 1000 or sal < 4000 or …
why : 사용되는 연산자의 종류가 다르다

**subquery 문법**
1. 괄호안에 있어야 한다 >> (select max(sal) from emp)
2. 단일 컬럼으로 구성 되어야 한다 >> select empno, sal from emp (서브쿼리(x))
3. 단독으로 실행 가능해야 한다

**실행순서**
1. subquery 먼저 실행
2. subquert  결과를 가지고  query 가 실행


* 20번 부서의 사원 중에서 가장 많은 급여를 받는 사원보다 더 많은 급여를 받는 사원의 사번, 이름 급여, 부서번호를 출력하세요.

select *
from emp
where sal > (select max(sal) from emp where deptno=20);

* 자기 부서의 평균 월급보다 더 많은 월급을 받는 사원의 사번, 이름, 부서번호, 부서별 평균 월급을 출력하세요.

select *
from  emp e join (select deptno, avg(sal) as avgsal from emp group by deptno) s
on e.deptno = s.deptno  
where e.sal > s.avgsal;

**오라클 기준**
* DDL(데이터 정의어) : [create , alter , drop , truncate] , rename, modify
* DML(데이터 조작어) : insert , update , delete
* DQL(데이터 질의어) : select
* DCL(데이터 제어어) : 관리자 : grant , revoke
* TCL(트랜잭션)      :  commit , rollback , savepoint

* DML 작업 (insert , update , delete)
A 계좌에서 B라는 계좌 이체 (100)

A 계좌 (1000) 출금 : update 계좌 set 금액 
-> 900
B 계좌 (1000) 입금 : update 계좌 set 금액 +
-> 1100

A계좌에서 출금  B계좌 입금  [하나의 논리적의 단위 묶어 버리자]
둘다 성공  둘다 성패
트랜잭션 : 하나의 논리적의 단위
처리하는 방법 : commit , rollback

* 개발자 (SQL) >> CRUD (Create(insert) , Read(select) , Update , Delete)
* APP(JAVA)  -   JDBC API    -  Oracle
insert
update
delete
select (70%)

**CRUD**
* 전체조회 (함수) , 조건조회(함수) , 삽입(함수) , 수정(함수) , 삭제(함수) => 개발자 => JAVA코드
* JAVA코드
전체조회 public List<Emp> getEmpAllList() {  select * from emp  }
조건조회 public Emp getEmpListByEmpno(int empno) { select * from emp where empno=7788}
삽입    public int insertEmp(Emp emp){ insert into emp(..) values(….)}

**기본 작업**
class MemberDao{
        5개의 함수
        전체조회 public List<Emp> getEmpAllList() {  select * from emp  }
        조건조회 public Emp getEmpListByEmpno(int empno) { select * from emp where empno=7788}
        삽입  public int insertEmp(Emp emp){ insert into emp(..) values(….)}
        삭제  public int DeleteEmp(int empno){ }
        수정  public int UpdateEmp(Emp emp){ I}
    }

영문테이블 , 영문 컬럼명
[학생 성적 테이블]
학번의 데이터는 중복되거나 NULL 값을 허용하면 안된다
이름 NULL 값을 허용하지 않는다
국어
영어
수학 점수 컬럼은 숫자 타입이고 NULL 값을 허용
값을 입력하지 않으면 default로 0값을 갖는다
총점 ,평균 컬럼은 가상컬럼으로(조합컬럼) 생성한다
학과코드는 학과 테이블에 학과코드를 참조한다
학번 , 이름 , 국어 , 영어 , 수학 , 총점 , 평균 , 학과코드


학과 테이블
학과코드 데이터는 중복되거나 NULL 값을 허용하면 안된다,
학과명 은 null값을 허락하지 않는다

학과코드 , 학과명

샘플 데이터 insert ..
그리고 select 결과는
학번 , 이름 , 총점, 평균 , 학과코드 , 학과명 을 출력하세요

Oracle 11g >> 가상 컬럼 (조합컬럼)
학생 성적 테이블 :  국어 , 영어 , 수학 , 총점
국어 , 영어, 수학 데이터만  insert  를 하면 자동으로 총점 데이터가 만들어 지고 싶다

create table vtable(
  no1 number,
  no2 number,
  no3 number GENERATED ALWAYS as (no1 + no2) VIRTUAL
);

insert into vtable(no1, no2) values(100,50);

select * from vtable;
commit;


/* 사원 */
CREATE TABLE EMP (
	empno NUMBER NOT NULL, /* 사번 */
	ename VARCHAR2(20), /* 이름 */
	sal NUMBER, /* 급여 */
	deptno NUMBER /* 부서번호 */
);

ALTER TABLE EMP
ADD CONSTRAINT PK_EMP_EMPNO	PRIMARY KEY (empno);

/* 부서 */
CREATE TABLE DEPT (
	deptno NUMBER, /* 부서번호 */
	dname VARCHAR2(20) /* 부서명 */
);


ALTER TABLE DEPT
ADD 	CONSTRAINT PK_DEPT_DEPTNO 	PRIMARY KEY (deptno);

ALTER TABLE EMP
ADD CONSTRAINT FK_DEPT_TO_EMP 	FOREIGN KEY (deptno)	REFERENCES DEPT (deptno);
