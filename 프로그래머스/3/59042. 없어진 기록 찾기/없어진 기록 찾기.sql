-- 코드를 입력하세요
select ao.animal_id as ANIMAL_ID, ao.name AS NAME
from animal_outs ao
where ao.animal_id not in (select ai.animal_id from animal_ins ai)
order by ao.animal_id;