#ONLINE_SALES 테이블에서 동일한 회원이 동일한 상품을 재구매한 데이터
#재구매한 회원 ID,재구매한 상품ID 출력
#회원 ID기준 오름차순 정렬, 상품 ID 기준 내림차순 정렬
SELECT USER_ID, PRODUCT_ID
FROM ONLINE_SALE
GROUP BY USER_ID, PRODUCT_ID
HAVING COUNT(*) > 1
ORDER BY USER_ID ASC, PRODUCT_ID DESC;