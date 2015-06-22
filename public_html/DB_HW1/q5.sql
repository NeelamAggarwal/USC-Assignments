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