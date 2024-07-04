-- 코드를 입력하세요
SELECT car_type, count(*) as CARS
from car_rental_company_car
where options Like '%통풍시트%' or  options Like '%열선시트%' or options Like '%가죽시트%'
group by car_type
order by car_type;