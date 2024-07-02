with recursive generations as (
    select id, parent_id, 1 as gen
    from ecoli_data
    where parent_id is null
    union all
    select e.id, e.parent_id, g.gen+1
    from ecoli_data e
    join generations g on e.parent_id = g.id
)

select id from generations where gen = 3 order by id;