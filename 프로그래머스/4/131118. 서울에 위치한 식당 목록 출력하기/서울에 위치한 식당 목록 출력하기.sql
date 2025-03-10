# SELECT i.REST_ID, i.REST_NAME, i.FOOD_TYPE, i.FAVORITES, i.ADDRESS, ROUND(AVG(r.REVIEW_SCORE), 2) AS SCORE
# FROM REST_INFO i
# JOIN REST_REVIEW r ON i.REST_ID = r.REST_ID
# WHERE i.ADDRESS LIKE '서울%'
# GROUP BY i.REST_ID
# ORDER BY SCORE DESC, i.FAVORITES DESC;




# SELECT I.REST_ID, I.REST_NAME, I.FOOD_TYPE, I.FAVORITES, I.ADDRESS, ROUND(AVG(R.REVIEW_SCORE), 2) AS SCORE
# FROM REST_INFO I
# JOIN REST_REVIEW R ON I.REST_ID = R.REST_ID
# WHERE I.ADDRESS LIKE '서울%'
# GROUP BY I.REST_ID
# ORDER BY SCORE DESC, I.FAVORITES DESC;

SELECT I.REST_ID, REST_NAME, FOOD_TYPE, FAVORITES, ADDRESS, ROUND(AVG(REVIEW_SCORE), 2) AS SCORE
FROM REST_INFO I
JOIN REST_REVIEW R ON I.REST_ID = R.REST_ID
WHERE I.ADDRESS LIKE '서울%'
GROUP BY I.REST_ID
ORDER BY SCORE DESC, FAVORITES DESC;


# SELECT R.REST_INFO, R.REST_NAME, R.FPPD_TYPE, R.FAVORITES, R.ADDRESS, ROUND(AVG(R.REVIEW_SCORE), 2) AS SCORE
# FROM REST_INFO RI
# WHERE RI.ADDRESS LIKE '%서울%'
# JOIN REST_REVIEW RR ON RI.REST_ID = RR.REST.ID AS R
# GROUP BY R.REST_ID
# ORDER BY SCORE DESC, R.FAVORITES DESC;












