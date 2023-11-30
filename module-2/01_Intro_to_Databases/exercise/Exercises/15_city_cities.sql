-- 15. The name, state abbreviation, and population for cities that end with the word "City" (11 rows)
select city_name, state_abbreviation, population
from city
where city_name like '% City'
limit 11;