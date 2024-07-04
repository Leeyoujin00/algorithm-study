-- 코드를 입력하세요
select R.FOOD_TYPE, R.REST_ID, R.REST_NAME, R.FAVORITES
from rest_info r
join (
    select food_type, max(favorites) AS MAX_FAVORITES
    from rest_info
    group by food_type
) f on r.food_type = f.food_type and r.favorites = F.MAX_FAVORITES
order by r.food_type DESC;


# select *
# from rest_info
# group by food_type;