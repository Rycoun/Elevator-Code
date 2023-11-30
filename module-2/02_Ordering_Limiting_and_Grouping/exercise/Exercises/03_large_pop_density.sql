-- 3. The name, population, area, and population density (name the column 'population_density') of cities with more than 5,000 people per square kilometer.
-- Population density is expressed as people per square kilometer. In other words, population divided by area.
-- Order the results by population density, highest number first.
-- (9 rows)
select city_name, population, area, (population / area) as population_density
from city
where (population / area) > 5000
order by population_density desc
limit 9;

