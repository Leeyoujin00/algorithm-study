# SELECT USER_ID, PRODUCT_ID
# FROM ONLINE_SALE
# GROUP BY USER_ID, PRODUCT_ID
# HAVING COUNT(*) > 1
# ORDER BY USER_ID, PRODUCT_ID DESC;


# SELECT USER_ID, PRODUCT_ID
# FROM (SELECT *
#      FROM ONLINE_SALE
#      GROUP BY USER_ID, PRODUCT_ID
#      WHERE COUNT(*) > 1)
# GROUP BY USER_ID

# SELECT USER_ID, PRODUCT_ID
# FROM ONLINE_SALE
# GROUP BY USER_ID, PRODUCT_ID
# HAVING COUNT(*) > 1
# ORDER BY USER_ID ASC, PRODUCT_ID DESC;


SELECT USER_ID, PRODUCT_ID
FROM ONLINE_SALE
GROUP BY USER_ID, PRODUCT_ID
HAVING COUNT(*) > 1
ORDER BY USER_ID, PRODUCT_ID DESC;













