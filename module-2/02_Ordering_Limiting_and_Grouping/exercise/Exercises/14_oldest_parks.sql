-- 14. The name and date established of the top 10 oldest national parks.
-- Order the results with the oldest park first.
-- (10 rows)
select park_name, date_established
from park
order by date_established
limit 10;;
