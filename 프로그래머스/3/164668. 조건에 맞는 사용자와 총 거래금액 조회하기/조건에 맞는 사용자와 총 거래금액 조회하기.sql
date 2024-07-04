select u.user_id as USER_ID, u.nickname as NICKNAME, sum(b.price) as TOTAL_SALES
from used_goods_board b
join used_goods_user u on b.writer_id = u.user_id
where b.status = 'DONE'
group by b.writer_id
having sum(b.price) >= 700000
order by sum(b.price)



