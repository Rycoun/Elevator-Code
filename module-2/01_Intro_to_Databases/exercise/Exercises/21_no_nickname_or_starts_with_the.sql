-- 21. The name and nickname for all records in the state table with no official nickname (NULL) or nickname starts with the word "The" (13 rows)
select state_name, state_nickname
from state
where state_nickname is null or state_nickname like 'The%'
limit 13;
