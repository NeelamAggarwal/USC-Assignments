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