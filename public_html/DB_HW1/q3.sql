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