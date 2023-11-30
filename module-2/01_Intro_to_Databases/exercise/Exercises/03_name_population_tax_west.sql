-- 3. The name, population, and sales tax of the states in the "West" census region (13 rows)
select state_name, population, sales_tax from state
where census_region = 'West';
