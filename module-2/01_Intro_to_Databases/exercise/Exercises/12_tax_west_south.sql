-- 12. The state name, nickname, and sales tax from records in the state table in the "West" and "South" census regions with a sales tax that isn't 0% (26 rows)
select state_name, state_nickname, sales_tax
from state
where census_region in ('West', 'South') and sales_tax <> 0
limit 26;
