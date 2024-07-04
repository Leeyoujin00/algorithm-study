select ii.ingredient_type as INGREDIENT_TYPE, sum(fh.total_order) as TOTAL_ORDER
from first_half fh
join icecream_info ii on fh.flavor = ii.flavor
group by ii.ingredient_type
order by sum(fh.total_order);