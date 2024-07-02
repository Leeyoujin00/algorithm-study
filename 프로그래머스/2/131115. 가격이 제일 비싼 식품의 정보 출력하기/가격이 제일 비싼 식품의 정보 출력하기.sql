select * 
from food_product
where price = (Select max(price)
From food_product);