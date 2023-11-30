-- 10. For all people born before 1900 whose profile_path does NOT end in ".jpg", set their
--     home_page to "No image" and their profile_path to NULL (64 rows)

UPDATE person
SET	home_page = 'No image', profile_path = NULL
WHERE
	birthday < '1900-01-01' AND profile_path NOT LIKE '%.jpg';

