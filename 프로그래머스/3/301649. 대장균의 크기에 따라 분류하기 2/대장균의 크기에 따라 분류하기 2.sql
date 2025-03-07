-- 코드를 작성해주세요
# select id, 
#     case 
#     when percent_rank() over(order by size_of_colony desc) * 100 <= 25
#     then 'CRITICAL'
#     when percent_rank() over(order by size_of_colony desc) * 100 <= 50
#     then 'HIGH'
#     when percent_rank() over(order by size_of_colony desc) * 100 <= 75
#     then 'MEDIUM'
#     else 'LOW'
#     end
#     as COLONY_NAME
# from ecoli_data
# order by id;

SELECT ID,
    CASE 
    WHEN PERCENT_RANK() OVER(ORDER BY SIZE_OF_COLONY DESC) *100 <= 25
    THEN 'CRITICAL'
    WHEN PERCENT_RANK() OVER(ORDER BY SIZE_OF_COLONY DESC)
    * 100 <= 50
    THEN 'HIGH'
    WHEN PERCENT_RANK() OVER(ORDER BY SIZE_OF_COLONY DESC) * 100 <= 75
    THEN 'MEDIUM'
    ELSE 'LOW'
    END AS COLONY_NAME
FROM ECOLI_DATA
ORDER BY ID;