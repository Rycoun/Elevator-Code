-- 11. The name, state, and population of all cities in Colorado (CO) or Arizona (AZ) (22 rows)

SELECT city_name, state_abbreviation, population
FROM city
--also acceptable: state_abbreviation = 'CO' OR state_abbreviation = 'AZ'
WHERE state_abbreviation IN ('CO', 'AZ');
