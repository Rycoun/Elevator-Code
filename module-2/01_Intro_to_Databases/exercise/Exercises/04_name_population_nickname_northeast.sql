-- 4. The name, population, and nickname of the states in the "Northeast" census region (9 rows)
select state_name, population, state_nickname
from state
where census_region = 'Northeast'


