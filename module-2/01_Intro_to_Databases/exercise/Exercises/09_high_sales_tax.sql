-- 9. The name, abbreviation, population, and sales tax of all states and territories with a sales tax greater than 6.6% (9 rows)
select state_name, state_abbreviation, population, sales_tax
from state
where sales_tax > 6.6
limit 9;
