Select sum(price) as TOTAL_PRICE
From item_info
Group by rarity
Having rarity= 'legend'