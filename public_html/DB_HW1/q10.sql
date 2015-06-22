select p.post_author, (extract( YEAR from sysdate)- extract( YEAR from f.date_of_birth)) AS Age from post p, fbUser f
where
f.facebook_id = p.post_author
and
p.post_location = '	Los Angeles, CA, United States'
and 
p.post_author IN
(
select fb.facebook_id from fbUser fb
where fb.birth_place <> 'LOS ANGELES,CA 90007'
);