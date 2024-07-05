-- 코드를 작성해주세요
with lens as (
    select fish_type, 
    case when length <= 10 or length is null then 10 
    else length end as length 
    from fish_info
),
avg_len as (
    select fish_type, avg(l.length) as avg_length
    from lens l
    group by fish_type
)
select count(*) as FISH_COUNT, max(length) as MAX_LENGTH, f.fish_type as FISH_TYPE
from fish_info f
join avg_len al on f.fish_type = al.fish_type
where al.avg_length >= 33
group by f.fish_type
order by f.fish_type
