-- 12. The average population of all cities in Washington (WA). Name the column 'average_washington_population'.
-- Expected answer is around 202,000
-- (1 row)

select avg(population) as average_washington_population
from city
where state_abbreviation = 'WA';