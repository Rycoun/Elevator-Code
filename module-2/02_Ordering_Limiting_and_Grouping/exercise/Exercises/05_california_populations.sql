-- 5. The sum of the population of all cities in California. Name the column 'california_population'.
-- Expected answer is around 20,000,000
-- (1 row)
select sum(population) as california_population
from city
where state_abbreviation = 'CA';
