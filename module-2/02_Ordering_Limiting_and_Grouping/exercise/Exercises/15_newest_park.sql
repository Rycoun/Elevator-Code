-- 15. The name and date established of the newest national park.
-- (1 row)
select park_name, date_established
from park
order by date_established desc
limit 1
