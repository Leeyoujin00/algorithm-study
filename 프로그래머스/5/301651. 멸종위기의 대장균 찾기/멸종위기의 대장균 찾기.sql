-- 코드를 작성해주세요
# 각 대장균의 세대 구하기
# with recursive generations as (
#     select id, parent_id, 1 as gen 
#     from ecoli_data
#     where parent_id is null
#     union all
#     select e.id, e.parent_id, g.gen+1
#     from ecoli_data e
#     join generations g on e.parent_id = g.id
# )
# select id from ecoli_data
# where id not in (select e.parent_id from ecoli_data e
#                          group by e.parent_id);

with RECURSIVE GENERATIONHIERARCHY as (
    -- 최초의 대장균(1세대)
    select ID, PARENT_ID, 1 as GENERATION
    FROM ECOLI_DATA
    WHERE PARENT_ID IS NULL
    
    UNION ALL
    
    -- 다음 세대 대장균
    SELECT E.ID, E.PARENT_ID, GH.GENERATION + 1
    FROM ECOLI_DATA E
    JOIN GENERATIONHIERARCHY GH ON E.PARENT_ID = GH.ID

)

select count(*) AS COUNT, GENERATION
from generationHierarchy
where id not in (select DISTINCT parent_id from ecoli_data WHERE PARENT_ID IS NOT NULL)
group by generation
order by generation

# SELECT ID
# FROM ECOLI_DATA
# WHERE ID NOT IN (select DISTINCT parent_id from ecoli_data WHERE PARENT_ID IS NOT NULL)


