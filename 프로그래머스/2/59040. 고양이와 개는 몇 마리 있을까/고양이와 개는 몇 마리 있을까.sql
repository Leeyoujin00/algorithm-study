-- 동물보호소에 들어온 동물 중 고양이와 개가 각각 몇마리인지 조회
SELECT ANIMAL_TYPE, COUNT(*) AS count
FROM ANIMAL_INS
WHERE ANIMAL_TYPE IN ('Cat', 'Dog')
GROUP BY ANIMAL_TYPE
ORDER BY 1;