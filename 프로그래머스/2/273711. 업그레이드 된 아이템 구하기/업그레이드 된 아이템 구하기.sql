# SELECT it.ITEM_ID, ii.ITEM_NAME, ii.RARITY
# FROM ITEM_INFO ii
# join item_tree it on ii.item_id = it.item_id
# where it.parent_item_id in (
# select item_id from item_info where rarity = 'rare')
# order by item_id desc;

# SELECT IT.ITEM_ID, II.ITEM_NAME, II.RARITY
# FROM ITEM_INFO II
# JOIN ITEM_TREE IT ON II.ITEM_ID = IT.PARENT_ID
# ORDER BY IT.ITEM_ID DESC;


# WHERE ITEM_ID IN (
#     SELECT ITEM_ID
#     FROM 

# )

SELECT II.ITEM_ID, II.ITEM_NAME, II.RARITY
FROM ITEM_INFO AS II
JOIN ITEM_TREE AS IT ON II.ITEM_ID = IT.ITEM_ID
WHERE IT.PARENT_ITEM_ID IN (
SELECT ITEM_ID
FROM ITEM_INFO
WHERE RARITY = 'RARE')
ORDER BY II.ITEM_ID DESC;












