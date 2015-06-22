select post_author from post where post_ID in(select post_ID from people_like_post where people_like_post_ID='F2');
