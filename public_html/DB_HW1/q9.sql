select facebook_id from fbUser fb
where extract( YEAR from sysdate)- extract( YEAR from fb.date_of_birth) > 23
and fb.facebook_id IN (select facebook_id from friendList_table group by facebook_id having count(friendList)>2)
and fb.facebook_id NOT IN (select comment_author from comments)
and fb.facebook_id NOT IN (select post_author from post);