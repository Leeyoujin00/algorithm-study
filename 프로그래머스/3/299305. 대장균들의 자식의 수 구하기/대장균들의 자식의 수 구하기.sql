select id ,if(ci.cc is null, 0, ci.cc) as CHILD_COUNT
from ecoli_data e
left join (select parent_id, count(*) as cc
from ecoli_data
group by parent_id) ci on e.id = ci.parent_id
order by id;