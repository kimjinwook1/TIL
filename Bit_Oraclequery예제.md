# 20211223 Oracle query 예제
서브쿼리 , 조인 , 함수 ,뷰

alter session set nls_date_format=‘YYYY-MM-DD HH24:MI:SS’;

select sysdate from dual;

* 사원의 급여가 1000 이상이고 수당을 받지 않는 사원의 사번 , 이름  , 직종 , 급여, 수당을 출력하세요.
* 수당을 받지 않는다 의미는 comm 컬럼의 데이터가 null 인 것 
select empno , ename, job , sal , comm
from emp
where sal >= 1000 and comm is null;

* 단일 테이블
select    3 
from      1
where     2
order by  4  (select 한 결과를 정렬)

select job , deptno
from emp
order by job asc , deptno desc;

게시판  처음(계층형 게시판)
사원테이블(employees)에서 사원의 이름은 last_name , first_name 합쳐서 fullname 별칭 부여해서 출력하고
입사일은  YYYY-MM-DD 형식으로 출력하고 연봉(급여 *12)을 구하고 연봉의 10%(연봉 * 1.1)인상한 값을
출력하고 그 결과는 1000단위 콤마 처리해서 출력하세요
단 2005년 이후 입사자들만 출력하세요 그리고 연봉이 높은 순으로  출력하세요

show user;
—USER이(가) “HR”입니다.

select * from employees;

select employee_id , 
       first_name || last_name as “fullname”,
       to_char(hire_date,’YYYY-MM-DD’) as “hire_date”,
       salary,
       salary * 12 as “연봉”,
       to_char((salary * 12)*1.1 ,’$9,999,999,999’) as “인상분”,
       hire_date
from employees
where to_char(hire_date,’YYYY’) >= ‘2005’
order by “연봉” desc;

alter session set nls_date_format=‘YYYY-MM-DD HH24:MI:SS’;

**집계함수**
1. count()
2. sum()
3. avg()
4. max()
5. min()

1. 집계함수는 group by  절과 같이 사용
2. 집계함수는  null 값을 무시
3. select 절에 집계함수 이외에 다른 컬럼이 오면 받드시 그 컬럼은  group by 절에 명시

select count(*) from emp;
select count(comm) from emp; 
select comm from emp;

select count(nvl(comm,0)) from emp; // nvl() 

부서별 평균 급여를 구하세요
select deptno , round(avg(sal),0)
from emp
group by deptno;

직종별 평균 급여를 구하세요
select job , trunc(avg(sal),0)
from emp
group by job;

**순서**
select                4
from                  1
where                 2
group by              3
order by              5


직종별 평균급여가 3000달러 이사인 사원의 직종과 평균급여를 출력하세요
select job , avg(sal) as avgsal
from emp
group by job
having avg(sal) >= 3000;

**순서**
select       5 
from         1
where        2 
group by     3
having       4
order by     6

사원테이블에서 직종별 급여합을 출력하되 수당은 지급받고 급여의 합이 5000 이상인 사원들의 목록을 출력
select job , sum(sal) as sumsal
from emp
where comm is not null
group by job
having sum(sal) >= 5000
order by sumsal desc;


사원테이블에서 부서인원이 4명보다 많은 부서의 부서번호 , 인원수 , 급여의 합을 출력하세요
select deptno , count(*) as c , sum(sal) as sumsal
from emp
group by deptno
having count(*) > 4;

- - - -
HR계정
1. 사번 , 이름(last_name) , 부서번호 , 부서이름을 출력하세요
select * from employees;
select * from departments;
select e.employee_id , e.last_name , e.department_id, d.department_name
from employees e join departments d
on e.department_id = d.department_id;

1. 사번 , 이름(last_name) , 부서번호 , 부서이름을 출력하세요
select * from employees where department_id is null;
178	Kimberely	Grant  부서가 null
107명
106건

select e.employee_id , e.last_name , e.department_id, d.department_name
from employees e left outer join departments d
on e.department_id = d.department_id;

BITUSER 이동
사원번호 , 사원이름 , 그 사원을 관리하는 관리자의 사번 , 이름을 출력하세요
select * from emp;

select e.empno , e.ename , m.empno , m.ename
from emp e join emp m
on e.mgr = m.empno;

select e.empno , e.ename , m.empno , m.ename
from emp e left outer join emp m
on e.mgr = m.empno;


한 개 이상 데이터 - join, subquery, view

* 부서번호가 10번, 20번인 사원들의 부서번호, 부서이름, 사원이름, 월급, 급여등급을 출력하라. 그리고 그 출력된 결과물을 부서번호가 낮은 순으로, 월급이 높은 순으로 정렬하라.

select e.deptno , d.dname, e.ename , e.sal , s.grade
from emp e join dept d on e.deptno = d.deptno
           join salgrade s on e.sal between s.losal and s.hisal
where d.deptno in (10,20)
order by e.deptno asc , e.sal desc;
