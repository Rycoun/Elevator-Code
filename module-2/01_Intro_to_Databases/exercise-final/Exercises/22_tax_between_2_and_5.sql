-- 22. The name and sales tax for states and territories with sales tax greater than or equal to 2% and less than or equal to 5% (15 rows)

SELECT state_name, sales_tax
FROM state
--also acceptable: sales_tax BETWEEN 2 AND 5
WHERE sales_tax >= 2 AND sales_tax <= 5;
