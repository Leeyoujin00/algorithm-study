# SELECT DATE_FORMAT(SALES_DATE,'%Y-%m-%d') AS SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
# FROM (SELECT PRODUCT_ID, SALES_DATE, SALES_AMOUNT, USER_ID FROM ONLINE_SALE ons
#     UNION ALL 
#     SELECT PRODUCT_ID, SALES_DATE, SALES_AMOUNT, NULL AS USER_ID FROM OFFLINE_SALE offs) AS u
# WHERE DATE_FORMAT(u.SALES_DATE,'%Y-%m') = '2022-03'
# ORDER BY SALES_DATE, PRODUCT_ID, USER_ID;


SELECT DATE_FORMAT(U.SALES_DATE, '%Y-%m-%d') AS SALES_DATE, U.PRODUCT_ID, U.USER_ID, U.SALES_AMOUNT
FROM
(
    SELECT SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT FROM ONLINE_SALE AS ONS
    UNION ALL
    SELECT SALES_DATE, PRODUCT_ID, NULL AS USER_ID, SALES_AMOUNT
    FROM OFFLINE_SALE AS OFFS
) AS U
WHERE DATE_FORMAT(U.SALES_DATE, '%Y-%m') = '2022-03'
ORDER BY U.SALES_DATE, U.PRODUCT_ID, U.USER_ID;














