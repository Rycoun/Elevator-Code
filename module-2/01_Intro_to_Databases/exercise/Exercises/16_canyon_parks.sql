-- 16. The name, date established, and area of parks that contain the string "Canyon" anywhere in the name (5 rows)
select park_name, date_established, area
from park
where park_name like '%Canyon%'
limit 5;
