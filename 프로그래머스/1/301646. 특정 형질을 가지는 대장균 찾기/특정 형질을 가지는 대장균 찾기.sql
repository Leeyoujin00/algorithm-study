-- 코드를 작성해주세요
# select count(*) as COUNT
# from ecoli_data
# where (genotype & 4) <> 0 or (genotype & 1)<>0 and (genotype & 2) = 0;

select count(*) as COUNT
from ecoli_data
where (genotype & 2) = 0 and ((genotype & 4) <> 0 or (genotype & 1) <> 0);