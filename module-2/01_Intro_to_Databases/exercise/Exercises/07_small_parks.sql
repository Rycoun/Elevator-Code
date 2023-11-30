-- 7. The name, date established, and area of parks with an area less than 100 square kilometers (6 rows)
select park_name, date_established, area
from park
where area < 100
limit 6;


