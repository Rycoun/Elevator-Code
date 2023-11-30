-- 2. The name and nickname of all states and territories that have a nickname (not NULL).
-- The name and nickname should be returned as a single column named 'state_and_nickname' and should contain values such as "New Jersey (Garden State)".
-- Order the results alphabetically by nickname.
-- (51 rows)
select state_name || ' (' || state_nickname || ')' as state_and_nickname
from state
where state_nickname is not null
order by state_nickname
limit 51


