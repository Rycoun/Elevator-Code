-- ORDERING RESULTS

-- Populations of all states from largest to smallest.
select state_name, population
from state
order by population desc;

-- States sorted alphabetically (A-Z) within their census region. The census regions are sorted in reverse alphabetical (Z-A) order.
select census_region, state_name
from state
order by census_region desc, state_name asc;

-- The biggest park by area
select park_name, area
from park
order by area desc;


-- LIMITING RESULTS

-- The 10 largest cities by populations
select city_name, population
from city
order by population desc
limit 10;

-- The 20 oldest parks from oldest to youngest in years, sorted alphabetically by name.
select park_name, (date_part('year', current_date) - date_part('year', date_established)) as age_in_years
from park
order by age_in_years desc, park_name asc
limit 20;





-- CONCATENATING OUTPUTS

-- All city names and their state abbreviation.
-- Pittsburgh, PA
select city_name || ', ' || state_abbreviation as city_and_state
from city
order by state_abbreviation asc, city_name asc;

-- The all parks by name and date established.
-- Name: ____, Established: ____
select ('Name: ' || park_name || ', Established: ' || date_established)
from park
order by park_name;

-- The census region and state name of all states in the West & Midwest sorted in ascending order.
-- Midwest: Iowa
select (census_region || ': ' || state_name)
from state
where census_region = 'West' or census_region = 'Midwest'
order by state_name asc; 



-- AGGREGATE FUNCTIONS

-- Average population across all the states. Note the use of alias, common with aggregated values.
select avg(population) as avg_population
from state;


-- Total population in the West and South census regions
select sum(population) as west_and_south_population
from state
where census_region in ('West', 'South');

-- The number of cities with populations greater than 1 million
select count(city_name)
from city
where population > 1000000;

-- The number of state nicknames.
select count(state_nickname)
from state;

-- The area of the smallest and largest parks.
select min(area) as smallest_park_area, max(area) as largest_park_area
from park;

-- GROUP BY

-- Count the number of cities in each state, ordered from most cities to least.
select state_abbreviation, count(city_name) as city_count
from city
group by state_abbreviation
order by city_count desc;

-- Determine the average park area depending upon whether parks allow camping or not.
select avg(area), has_camping
from park
group by has_camping;

-- Sum of the population of cities in each state ordered by state abbreviation.
select state_abbreviation, sum(population)
from city
group by state_abbreviation
order by state_abbreviation asc;

-- The smallest city population in each state ordered by city population.
select state_abbreviation, min(population) as min_population
from city
group by state_abbreviation
order by min_population;

-- HAVING
-- Sum of the population of cities in each state ordered by state abbreviation 
-- where the total population exceeds 5,000,000
select state_abbreviation, sum(population) as tot_city_population
from city
group by state_abbreviation
having sum(population) > 5000000
order by state_abbreviation;

-- Count the number of cities starting with the letter 'A' in each state, 
-- ordered from most cities to least, where there are at least 3 cities
select state_abbreviation, count(city_name)
from city
where city_name like 'A%'
group by state_abbreviation
having count(city_name) >= 3
order by count(city_name) desc;

-- Miscelleneous

-- While you can use LIMIT to limit the number of results returned by a query,
-- it's recommended to use OFFSET and FETCH if you want to get
-- "pages" of results.
-- For instance, to get the first 10 rows in the city table
-- ordered by the name, you could use the following query.
-- (Skip 0 rows, and return only the first 10 rows from the sorted result set.)

-- offset = (curr_page_num - 1) * results_per_page

select *
from city
order by city_name
offset 30 rows fetch next 10 rows only;


-- SUBQUERIES (optional)

-- Include state name rather than the state abbreviation while counting the number of cities in each state,
select (select state_name from state where city.state_abbreviation = state.state_abbreviation), count(city_name)
from city
group by state_abbreviation
order by state_abbreviation asc;

-- Include the names of the smallest and largest parks
select park_name, area
from park, (select min(area) as min_area, max(area) as max_area from park) as min_and_max
where park.area = min_and_max.min_area or park.area = min_and_max.max_area;

-- List the capital cities for the states in the Northeast census region.
select city_name
from city
where city_id in (select capital
					from state
					where census_region = 'Northeast');

-- Show min and max park areas in same column (multiple rows)

select park_name, area
from park
where area in (
			select min(area)
			from park

			union

			select max(area)
			from park
			)

	