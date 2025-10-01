#REST_INFO와 REST_REVIEW 테이블에서 서울에 위치한 식당들의 식당ID, 식당 이름, 음식 종류, 즐겨찾기수, 주소, 리뷰 평균 점수를 조회하는 SQL문을 작성
# 리뷰 평균점수는 소수점 세번째 자리에서 반올림
# 결과는 평균점수를 기준으로 내림차순 정렬, 즐겨찾기 기준으로 내림차순 정렬

SELECT RI.REST_ID, RI.REST_NAME, RI.FOOD_TYPE, RI.FAVORITES, RI.ADDRESS, ROUND(AVG(RR.REVIEW_SCORE), 2) AS SCORE
FROM REST_INFO AS RI
JOIN REST_REVIEW AS RR ON RI.REST_ID = RR.REST_ID
WHERE RI.ADDRESS LIKE '서울%'
GROUP BY RI.REST_ID
ORDER BY SCORE DESC, FAVORITES DESC;
