------VIEW
create view user_no_post as
(select w.facebook_ID as myid from wall w where w.wall_ID NOT IN(Select post_wall from post));

------- Query
select * from
(select facebook_ID, count(friendlist) as maximum from friendlist_table ft,user_no_post u  where u.myid= ft.facebook_id group by (ft.facebook_ID) order by maximum desc) where rownum =1; 

