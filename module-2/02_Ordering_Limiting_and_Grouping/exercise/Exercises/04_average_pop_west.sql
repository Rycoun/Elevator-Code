-- 4. The average population of states in the "West" census region. Name the column 'average_population'.
-- Expected answer is around 6,000,000
-- (1 row)
select avg(population) as average_population
from state
where census_region = 'West';

