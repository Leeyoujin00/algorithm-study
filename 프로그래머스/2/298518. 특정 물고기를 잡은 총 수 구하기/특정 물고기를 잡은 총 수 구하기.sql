# select count(*) as FISH_COUNT
# from fish_info fi
# where fi.fish_type in (
#     select fish_type
#     from fish_name_info fni
#     where fni.fish_name in ('BASS', 'SNAPPER'));
    
SELECT COUNT(*) AS FISH_COUNT
FROM FISH_INFO
WHERE FISH_TYPE IN
(SELECT FISH_TYPE
FROM FISH_NAME_INFO
WHERE FISH_NAME IN ("BASS", "SNAPPER"));