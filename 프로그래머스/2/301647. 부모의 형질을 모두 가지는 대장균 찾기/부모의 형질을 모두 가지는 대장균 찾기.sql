-- 코드를 작성해주세요
# select id, (select genotype from ecoli_data where id = parent_id) as PARENT_GENOTYPE
# from ecoli_data
# where parent_id is not null and ((genotype & (select genotype from ecoli_data where id = parent_id)) = genotype)
# order by id;

# select id
# from ecoli_data e
# where e.parent_id is not null and 
#                     (e.genotype & (select pe.genotype from ecoli_data pe 
#                     where pe.id = e.parent_id) = e.genotype);
                    
select e.id, e.genotype, p.p_genotype as PARENT_GENOTYPE
from ecoli_data e
join (select id, genotype as p_genotype from ecoli_data) as p on e.parent_id = p.id
where (e.parent_id is not null) and (e.genotype & p.p_genotype) = p.p_genotype
order by e.id;



                    