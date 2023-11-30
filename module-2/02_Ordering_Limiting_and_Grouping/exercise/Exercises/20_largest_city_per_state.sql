-- 20. The state abbreviation, and population of the city with the largest population (name column 'city_population') for all states, territories, and districts.
-- Order the results from highest to lowest populations.
-- (56 rows)
select state_abbreviation, max(population) as city_population
from city
group by state_abbreviation
order by city_population desc;
