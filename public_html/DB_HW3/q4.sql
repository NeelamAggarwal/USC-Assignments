
------------------------------------------------------------------------------------
SELECT R.REVIEWER, R.RATING, RELEASE_DATE
FROM movie,
XMLTable('for $i in /Reviews/REVIEW
return $i'
PASSING REVIEWS
COLUMNS RID VARCHAR(5) PATH 'ID',
TITLE VARCHAR2(50) PATH 'TITLE',
RATING NUMBER PATH 'RATING',
REVIEWER VARCHAR(50) PATH 'REVIEWER',
REVIEW_DATE DATE PATH 'REVIEW_DATE',
DESCRIPTION VARCHAR(10) PATH 'DESCRIPTION') R WHERE R.RATING>3 ORDER BY RELEASE_DATE;
----------------------------------------------------------------------------------------