-- 6. The name, abbreviation, population, and area of states with an area greater than 200,000 square kilometers (16 rows)
select state_name, state_abbreviation, population, area
from state
where area > 200000
limit 16;
