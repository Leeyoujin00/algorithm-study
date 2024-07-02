-- 코드를 작성해주세요
# SELECT CONCAT(CAST(MAX(length) AS CHAR(10)), 'cm') AS MAX_LENGTH
# FROM fish_info;

SELECT CONCAT(MAX(length), 'cm') AS MAX_LENGTH
FROM fish_info;

