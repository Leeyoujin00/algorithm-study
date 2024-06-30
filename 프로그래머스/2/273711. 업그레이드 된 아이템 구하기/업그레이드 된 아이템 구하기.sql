SELECT it.ITEM_ID, ii.ITEM_NAME, ii.RARITY
FROM ITEM_INFO ii
join item_tree it on ii.item_id = it.item_id
where it.parent_item_id in (
select item_id from item_info where rarity = 'rare')
order by item_id desc;