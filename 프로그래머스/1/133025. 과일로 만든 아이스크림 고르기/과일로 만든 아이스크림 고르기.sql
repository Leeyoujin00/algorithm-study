#상반기 아이스크림 총주문량이 3000보다 높으면서, 아이스크림의 주성분이 과일인 아이스크림의 맛(flaver)을 총주문량이 큰 순서대로 조회
#first_half, icecream_info

select i.flavor
from icecream_info as i
join first_half as f
on i.flavor = f.flavor
where i.ingredient_type = 'fruit_based' and f.total_order > 3000
order by f.total_order desc;

