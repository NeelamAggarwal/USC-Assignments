Q1)
--------------------------
CREATE TABLE MOVIE (MOVIE_ID	VARCHAR2(10 BYTE),
TITLE	VARCHAR2(50 BYTE),
DIRECTOR	VARCHAR2(50 BYTE),
WRITER	VARCHAR2(50 BYTE),
GENRE	VARCHAR2(50 BYTE),
RELEASE_DATE	DATE,
RUNTIME	NUMBER,
REVIEWS	XMLTYPE);
--------------------------


Q2)
------------------------------------------------------------------
SELECT R.REVIEWER, R.TITLE
FROM movie,
XMLTable('for $i in /Reviews/REVIEW
return $i'
PASSING REVIEWS
COLUMNS RID VARCHAR(5) PATH 'ID',
TITLE VARCHAR2(50) PATH 'TITLE',
RATING NUMBER PATH 'RATING',
REVIEWER VARCHAR(50) PATH 'REVIEWER',
REVIEW_DATE DATE PATH 'REVIEW_DATE',
DESCRIPTION VARCHAR(10) PATH 'DESCRIPTION') R ORDER BY R.REVIEWER;
-------------------------------------------------------------------------------



Q3)
-------------------------------------------------------------------------------
SELECT R.TITLE, DIRECTOR
FROM movie,
XMLTable('for $i in /Reviews/REVIEW
return $i'
PASSING REVIEWS
COLUMNS RID VARCHAR(5) PATH 'ID',
TITLE VARCHAR2(50) PATH 'TITLE',
RATING NUMBER PATH 'RATING',
REVIEWER VARCHAR(50) PATH 'REVIEWER',
REVIEW_DATE DATE PATH 'REVIEW_DATE',
DESCRIPTION VARCHAR(10) PATH 'DESCRIPTION') R WHERE R.REVIEWER='Charles Walters';
-------------------------------------------------------------------------------------

Q4)
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

Q5)
----------------------------------------------------------------------------------
SELECT R.REVIEWER, R.RATING, R.REVIEW_DATE
FROM movie,
XMLTable('for $i in /Reviews/REVIEW
return $i'
PASSING REVIEWS
COLUMNS RID VARCHAR(5) PATH 'ID',
TITLE VARCHAR2(50) PATH 'TITLE',
RATING NUMBER PATH 'RATING',
REVIEWER VARCHAR(50) PATH 'REVIEWER',
REVIEW_DATE DATE PATH 'REVIEW_DATE',
DESCRIPTION VARCHAR(10) PATH 'DESCRIPTION') R WHERE R.RATING>3 ORDER BY R.REVIEW_DATE;
----------------------------------------------------------------------------------------


Q6)
-----------------------------------------------------------------------
SELECT avg(r.rating),count(*),
R.REVIEWER
FROM movie,
XMLTable('for $i in /Reviews/REVIEW
return $i'
PASSING REVIEWS
COLUMNS RID VARCHAR(5) PATH 'ID',
TITLE VARCHAR2(50) PATH 'TITLE',
RATING NUMBER PATH 'RATING',
REVIEWER VARCHAR(50) PATH 'REVIEWER',
REVIEW_DATE DATE PATH 'REVIEW_DATE',
DESCRIPTION VARCHAR(10) PATH 'DESCRIPTION') R group by r.reviewer;
----------------------------------------------------------------------------







