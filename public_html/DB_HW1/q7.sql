select comment_author, comment_ID 
from comments 
where comment_date= (select max(comment_date) from comments where comment_author='F2');
