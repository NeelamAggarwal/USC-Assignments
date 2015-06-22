Q1)

SELECT
  f.facebook_ID
FROM fbuser f, wall w 
WHERE
  (f.facebook_ID=w.facebook_ID)
 AND
  (w.public_view='Y')
 AND
  ( (MONTHS_BETWEEN( CURRENT_DATE, f.date_of_birth )/12)>24); 
  

Q2)
 
 SELECT visible_to_facebook_id  FROM
 (
 SELECT count(v.wall_id) as count_wall_Id, v.visible_to_facebook_id, dense_rank() over (order by count(v.wall_id) desc) dr
 FROM visible_to_table v
 join wall w on w.wall_id = v.wall_Id
 WHERE w.public_view = 'N'
 group by v.visible_to_facebook_id
 order by v.visible_to_facebook_id)
WHERE dr = 1;
  
  
Q3)

select post from
(
select post, mycount, dense_rank() over (order by mycount desc) dr from
(
select post_ID as post, count(people_like_post_ID) mycount
from people_like_post 
group by post_ID
)
where post NOT IN (select c_post_id from comments)
)
where dr = 1;


Q4)

select post_author from post where post_ID in(select post_ID from people_like_post where people_like_post_ID='F2');


Q5)

CREATE VIEW total_post_likes AS
select post_author, sum(count) as post_sum from
(
select p.post_id,p.post_author, count(plp.people_like_post_id) as count
from post p
join people_like_post plp on plp.post_id = p.post_id
group by p.post_id, p.post_author
)
group by post_author
order by post_sum desc;
-------------------------------------------------
CREATE VIEW total_comment_likes AS
select comment_author, sum(count) as comment_sum from
(
select c.comment_id, p.post_id, c.comment_author, count(plc.people_like_comment_id) as count
from comments c
join people_like_comment plc on plc.comment_id = c.comment_id
join post p on p.post_id = c.c_post_id
group by c.comment_id, p.post_id, c.comment_author
order by c.comment_id
)
group by comment_author
order by comment_sum desc;
-----------------------------------------------------

---------------------Query--------------------------
select comment_author from
(
select c.comment_author, (p.post_sum+c.comment_sum) as sum_user
from TOTAL_POST_LIKES p, TOTAL_COMMENT_LIKES c
where p.post_author = c.comment_author order by (p.post_sum+c.comment_sum) desc
)
where rownum<3;


Q6)
------VIEW
create view user_no_post as
(select w.facebook_ID as myid from wall w where w.wall_ID NOT IN(Select post_wall from post));

------- Query
select * from
(select facebook_ID, count(friendlist) as maximum from friendlist_table ft,user_no_post u  where u.myid= ft.facebook_id group by (ft.facebook_ID) order by maximum desc) where rownum =1; 



Q7)

select comment_author, comment_ID 
from comments 
where comment_date= (select max(comment_date) from comments where comment_author='F2');


Q8)

-------------------------View------------------------------
create view sum_fbFriends
as select fl.facebook_id, count(fl.friendlist) mycount
from friendList_table fl
where fl.facebook_id IN
(
select fb.facebook_id from fbuser fb, friendlist_table fl
where
fb.facebook_id IN
(
select w.facebook_id from wall w  where w.wall_id not in (select post_wall from post)
)
group by fb.facebook_id
)
group by fl.facebook_id;
--------------------------------------------------------

--------------------------Query----------------------------
select facebook_id from sum_fbFriends where mycount=(select max(mycount) from sum_fbFriends);


Q9)

select facebook_id from fbUser fb
where extract( YEAR from sysdate)- extract( YEAR from fb.date_of_birth) > 23
and fb.facebook_id IN (select facebook_id from friendList_table group by facebook_id having count(friendList)>2)
and fb.facebook_id NOT IN (select comment_author from comments)
and fb.facebook_id NOT IN (select post_author from post);


Q10)

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