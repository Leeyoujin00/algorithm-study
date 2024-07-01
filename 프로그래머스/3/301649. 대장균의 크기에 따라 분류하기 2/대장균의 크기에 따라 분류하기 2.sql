-- 코드를 작성해주세요
select id, 
    case 
    when percent_rank() over(order by size_of_colony desc) * 100 <= 25
    then 'CRITICAL'
    when percent_rank() over(order by size_of_colony desc) * 100 <= 50
    then 'HIGH'
    when percent_rank() over(order by size_of_colony desc) * 100 <= 75
    then 'MEDIUM'
    else 'LOW'
    end
    as COLONY_NAME
from ecoli_data
order by id;
    