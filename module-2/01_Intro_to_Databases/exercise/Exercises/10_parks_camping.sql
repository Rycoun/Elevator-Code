-- 10. The name and area of parks that have an area less than or equal to 700 square kilometers and provides camping (21 rows)
select park_name, area
from park
where area <= 700 and has_camping = true
limit 21
