-- 코드를 입력하세요
SELECT fp.PRODUCT_ID, FP.PRODUCT_NAME, sum(FO.AMOUNT*FP.PRICE) AS TOTAL_SALES
FROM FOOD_PRODUCT FP
JOIN FOOD_ORDER FO ON FP.PRODUCT_ID = FO.PRODUCT_ID
WHERE DATE_FORMAT(FO.produce_DATE, '%Y-%m') = '2022-05'
group by fp.product_id
ORDER BY TOTAL_SALES DESC, FP.PRODUCT_ID
 