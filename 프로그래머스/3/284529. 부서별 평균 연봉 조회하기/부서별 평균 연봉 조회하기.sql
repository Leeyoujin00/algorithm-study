-- 코드를 작성해주세요
SELECT e.DEPT_ID, d.DEPT_NAME_EN, round(avg(sal), 0) as AVG_SAL
from hr_department d
join hr_employees e on d.dept_id = e.dept_id
group by e.dept_id
order by avg(sal) desc