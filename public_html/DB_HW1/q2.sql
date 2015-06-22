 select visible_to_facebook_id  from
 (
 select count(v.wall_id) as count_wall_Id, v.visible_to_facebook_id, dense_rank() over (order by count(v.wall_id) desc) dr
 from visible_to_table v
 join wall w on w.wall_id = v.wall_Id
 where w.public_view = 'N'
 group by v.visible_to_facebook_id
 order by v.visible_to_facebook_id)
WHERE dr = 1;