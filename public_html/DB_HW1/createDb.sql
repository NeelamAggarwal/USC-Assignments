create table fbuser(facebook_ID varchar(50) primary key, email varchar(50), first_name varchar(50), last_name varchar(50), date_of_birth date, birth_place varchar(50), gender char(2));

insert into fbuser values('F1', 'abi@yahoo.com', 'Abhinav', 'Cheatham' ,to_date('19690601','YYYYMMDD'), 'LAKE CITY,FL 32056', 'M');
insert into fbuser values ('F2', 'abhishk@gmail.com', 'Abhishek',	'deponto', to_date('19840205','YYYYMMDD'), 'Adak,AK 99546',	'M');	
insert into fbuser values ('F3', 'ald@gmail.com',	'Aldrich', 'chapel', to_date('19880527','YYYYMMDD'),'VERNON HILLS,IL 60061',	'M');
insert into fbuser values ('F4','dennis@yahoo.com','Dennis','brandy', to_date('19670801','YYYYMMDD'),'SAN FRANCISCO, CA 94102','M');
insert into fbuser values ('F5','davy@hotmail.com',	'Davy',	'ritacco', to_date('19901010','YYYYMMDD'),'NEW YORK, NY 10010','F');
insert into fbuser values ('F6','dawa@yahoo.com', 'Dawa',	'cusenz', to_date('19781012','YYYYMMDD'), 'CEDAR FALLS, NC 27230','F');
insert into fbuser values ('F7','hila@yahoo.com',	'Hilary',	'chapman', to_date('19880909','YYYYMMDD'),'FISHERS ISLAND,NY 06390','F');
insert into fbuser values ('F8','hilda@gmail.com',	'Hilda',	'restaino', to_date('19530315','YYYYMMDD'),'LAS VEGAS,NV 89030','F');
insert into fbuser values ('F9','lli@gmail.com',	'Lillian',	'scharfman', to_date('19541228','YYYYMMDD'),'LOS ANGELES,CA 90007',	'F');
insert into fbuser values ('F10',	'mic@yahoo.com',	'Michael',	'devany', to_date('19870723','YYYYMMDD'),'CALDWELL,NJ 07004','M');
insert into fbuser values ('F11',	'miyoko@hotmail.com',	'Miyoko','orth ', to_date('19830811','YYYYMMDD'),'LOS ANGELES,CA 90007',	'M');
insert into fbuser values ('F12',	'freya@yahoo.com'	,'Freya',	'chapman', to_date('19871113','YYYYMMDD'),'CLIFFSIDE PARK ,NJ 07010','M');
insert into fbuser values ('F13',	'kell@gmail.com',	'Kelley',	'ruth ', to_date('19860112','YYYYMMDD'),'REDONDO BEACH,CA 90278','F');
insert into fbuser values ('F14',	'kris@yahoo.com',	'Kristine',	'corbitt', to_date('19861220','YYYYMMDD'),'ALLEMAN,IA 50007','F');
insert into fbuser values ('F15',	'yas@yahoo.com',	'Yasmine',	'altschiller', to_date('19670704','YYYYMMDD'),'AMHERST,MA 01004','F');
insert into fbuser values ('F16', 'paras@gmail.com','Paras',	'hubertus', to_date('19781002','YYYYMMDD'),'SANTA MONICA ,CA 90411',	'M');
insert into fbuser values ('F17',	'pert@gmail.com',	'Perth',	'irven', to_date('19770321','YYYYMMDD'),'CHARLOTTE HALL ,MD 20622',	'M');
insert into fbuser values ('F18',	'steve@yahoo.com',	'Stevenson',	'aubry', to_date('19890429','YYYYMMDD'),'PHOENIX, AZ 85009','M');
insert into fbuser values ('F19',	'shrey@yahoo.co.in',	'Shrey',	'pancho', to_date('19680714','YYYYMMDD'),' BELLEVUE,NE 68005','F');
insert into fbuser values ('F20',	'rose@hotmail.com','Rosemary',	'glass', to_date('19881215','YYYYMMDD'),'AMA ,LA 70031', 'F');


create table friendlist_table(facebook_ID varchar(50), friendlist varchar(50), foreign key(facebook_ID) references fbuser);
alter table friendlist_table add foreign key(friendlist) references fbuser;

insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F2' from fbuser f where f.facebook_ID='F1';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F7' from fbuser f where f.facebook_ID='F1';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F9' from fbuser f where f.facebook_ID='F1';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F1' from fbuser f where f.facebook_ID='F2';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F6' from fbuser f where f.facebook_ID='F2';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F9' from fbuser f where f.facebook_ID='F2';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F16' from fbuser f where f.facebook_ID='F2';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F18' from fbuser f where f.facebook_ID='F2';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F7' from fbuser f where f.facebook_ID='F4';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F2' from fbuser f where f.facebook_ID='F6';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F15' from fbuser f where f.facebook_ID='F6';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F1' from fbuser f where f.facebook_ID='F7';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F4' from fbuser f where f.facebook_ID='F7';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F17' from fbuser f where f.facebook_ID='F7';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F13' from fbuser f where f.facebook_ID='F8';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F15' from fbuser f where f.facebook_ID='F8';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F1' from fbuser f where f.facebook_ID='F9';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F2' from fbuser f where f.facebook_ID='F9';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F16' from fbuser f where f.facebook_ID='F10';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F12' from fbuser f where f.facebook_ID='F11';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F11' from fbuser f where f.facebook_ID='F12';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F14' from fbuser f where f.facebook_ID='F12';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F8' from fbuser f where f.facebook_ID='F13';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F12' from fbuser f where f.facebook_ID='F14';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F6' from fbuser f where f.facebook_ID='F15';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F8' from fbuser f where f.facebook_ID='F15';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F2' from fbuser f where f.facebook_ID='F16';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F10' from fbuser f where f.facebook_ID='F16';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F7' from fbuser f where f.facebook_ID='F17';
insert into friendlist_table(facebook_id,friendlist) select facebook_ID,'F2' from fbuser f where f.facebook_ID='F18';


create table wall(wall_ID varchar(30) primary key, facebook_ID varchar(30), public_view char(2), foreign key(facebook_ID) references fbuser);

insert into wall values( 'W1','F1','N');
insert into wall values( 'W2','F2','N');
insert into wall values( 'W3','F3','Y');
insert into wall values( 'W4','F4','N');
insert into wall values( 'W5','F5','Y');
insert into wall values( 'W6','F6','Y');
insert into wall values( 'W7','F7','N');
insert into wall values( 'W8','F8','Y');
insert into wall values( 'W9','F9','N');
insert into wall values( 'W10','F10','N');
insert into wall values( 'W11','F11','N');
insert into wall values( 'W12','F12','N');
insert into wall values( 'W13','F13','N');
insert into wall values( 'W14','F14','N');
insert into wall values( 'W15','F15','N');
insert into wall values( 'W16','F16','N');
insert into wall values( 'W17','F17','N');
insert into wall values( 'W18','F18','N');
insert into wall values( 'W19','F19','Y');
insert into wall values( 'W20','F20','N');


create table visible_to_table(wall_ID varchar(30), visible_to_facebook_ID varchar(30), foreign key(wall_ID) references wall, foreign key(visible_to_facebook_ID) references fbuser);

insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F2' from wall w where w.wall_ID='W1';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F7' from wall w where w.wall_ID='W1';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F1' from wall w where w.wall_ID='W2';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F6' from wall w where w.wall_ID='W2';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F18' from wall w where w.wall_ID='W2';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F7' from wall w where w.wall_ID='W4';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F2' from wall w where w.wall_ID='W6';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F15' from wall w where w.wall_ID='W6';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F1' from wall w where w.wall_ID='W7';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F4' from wall w where w.wall_ID='W7';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F17' from wall w where w.wall_ID='W7';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F13' from wall w where w.wall_ID='W8';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F15' from wall w where w.wall_ID='W8';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F1' from wall w where w.wall_ID='W9';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F2' from wall w where w.wall_ID='W9';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F16' from wall w where w.wall_ID='W10';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F12' from wall w where w.wall_ID='W11';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F14' from wall w where w.wall_ID='W12';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F8' from wall w where w.wall_ID='W13';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F12' from wall w where w.wall_ID='W14';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F6' from wall w where w.wall_ID='W15';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F8' from wall w where w.wall_ID='W15';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F10' from wall w where w.wall_ID='W16';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F7' from wall w where w.wall_ID='W17';
insert into visible_to_table(wall_id,visible_to_facebook_id) select wall_ID,'F2' from wall w where w.wall_ID='W18';


create table post( post_ID integer primary key, post_author varchar(30), post_wall varchar(30), post_date varchar(50), post_location varchar(50),
foreign key(post_author) references fbuser, foreign key(post_wall) references wall);

insert into post(post_id, post_author, post_wall, post_date, post_location) select 1201, f.facebook_ID, w.wall_ID, 'Oct-02-07 09:11:17', 'Gonzales, LA, United States' 
from fbuser f, wall w where f.facebook_ID='F2' and w.wall_ID='W6';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1202, f.facebook_ID, w.wall_ID, 'Oct-02-07 01:31:39', 'Gonzales, LA, United States' 
from fbuser f, wall w where f.facebook_ID='F3' and w.wall_ID='W8';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1203, f.facebook_ID, w.wall_ID, 'Oct-02-07 09:10:54', '	Pasadena,LA,United States' 
from fbuser f, wall w where f.facebook_ID='F12' and w.wall_ID='W14';

insert into post(post_id, post_author, post_wall, post_date, post_location) select 1204, f.facebook_ID, w.wall_ID, 'Oct-02-07 13:05:56 PDT',	'Paterson, NJ, United States'
from fbuser f, wall w where f.facebook_ID='F2' and w.wall_ID='W6';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1205, f.facebook_ID, w.wall_ID, 'Sep-29-07 10:38:25 PDT',	'Mishawaka, Indiana, United States'
 from fbuser f, wall w where f.facebook_ID='F7' and w.wall_ID='W14';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1206, f.facebook_ID, w.wall_ID, 'Sep-29-07 14:30:47 PDT',	'Salt Lake City, Utah, United States'
 from fbuser f, wall w where f.facebook_ID='F12' and w.wall_ID='W11';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1207, f.facebook_ID, w.wall_ID, 'Sep-29-07 13:52:25 PDT', 	'Direct from the Publishers!, United States'
 from fbuser f, wall w where f.facebook_ID='F12' and w.wall_ID='W14';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1208, f.facebook_ID, w.wall_ID, 'Sep-28-07 19:46:08 PDT', 	'United States'
 from fbuser f, wall w where f.facebook_ID='F3' and w.wall_ID='W19';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1209, f.facebook_ID, w.wall_ID, 'Sep-29-07 13:45:00 PDT',	'New York & New Jersey, United States' 
from fbuser f, wall w where f.facebook_ID='F15' and w.wall_ID='W6';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1210, f.facebook_ID, w.wall_ID, 'Sep-30-07 05:12:29 PDT', 'Nieuwegein, Netherlands'
 from fbuser f, wall w where f.facebook_ID='F16' and w.wall_ID='W2';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1211, f.facebook_ID, w.wall_ID, 'Sep-29-07 16:06:00 PDT	','91754, United States'
 from fbuser f, wall w where f.facebook_ID='F11' and w.wall_ID='W12';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1212, f.facebook_ID, w.wall_ID, 'Oct-02-07 14:28:20 PDT'	,'Hollywood,CA -Famous camera source!'
 from fbuser f, wall w where f.facebook_ID='F3' and w.wall_ID='W5';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1213, f.facebook_ID, w.wall_ID, 'Sep-27-07 11:00:33 PDT	','Woodstock, Illinois, United States'
 from fbuser f, wall w where f.facebook_ID='F3' and w.wall_ID='W8';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1214, f.facebook_ID, w.wall_ID, 'Oct-02-07 12:45:00 PDT ','	Brooklyn, New York, United States'
 from fbuser f, wall w where f.facebook_ID='F12' and w.wall_ID='W11';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1215, f.facebook_ID, w.wall_ID, 'Sep-29-07 16:08:39 PDT','	Woodside, Delaware, United States'
 from fbuser f, wall w where f.facebook_ID='F12' and w.wall_ID='W14';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1216, f.facebook_ID, w.wall_ID, 'Sep-29-07 16:45:34 PDT','	The Heartland, United States'
 from fbuser f, wall w where f.facebook_ID='F3' and w.wall_ID='W5';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1217, f.facebook_ID, w.wall_ID, 'Sep-25-07 17:18:31 PDT','	New York, United States'
 from fbuser f, wall w where f.facebook_ID='F11' and w.wall_ID='W12';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1218, f.facebook_ID, w.wall_ID, 'Sep-25-07 17:15:00 PDT','	Hong Kong'
 from fbuser f, wall w where f.facebook_ID='F12' and w.wall_ID='W14';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1219, f.facebook_ID, w.wall_ID, 'Oct-01-07 17:15:05 PDT',' 	Authorized Dealer, United States'
 from fbuser f, wall w where f.facebook_ID='F16' and w.wall_ID='W2';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1220, f.facebook_ID, w.wall_ID, 'Sep-29-07 13:41:37 PDT ','	Philadelphia, Pennsylvania, United States'
 from fbuser f, wall w where f.facebook_ID='F3' and w.wall_ID='W19';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1221, f.facebook_ID, w.wall_ID, 'Sep-25-07 16:47:57 PDT	','Vaughan, Ontario, Canada'
 from fbuser f, wall w where f.facebook_ID='F6' and w.wall_ID='W2';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1222, f.facebook_ID, w.wall_ID, 'Sep-30-07 14:34:54 PDT ','	New Jersey, USA, United States'
 from fbuser f, wall w where f.facebook_ID='F12' and w.wall_ID='W11';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1223, f.facebook_ID, w.wall_ID, 'Sep-29-07 17:28:02 PDT',' 	Fresno, California, United States'
from fbuser f, wall w where f.facebook_ID='F2' and w.wall_ID='W1';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1224, f.facebook_ID, w.wall_ID, 'Sep-29-07 17:29:40 PDT','	Winter Haven, Florida, United States'
 from fbuser f, wall w where f.facebook_ID='F2' and w.wall_ID='W1';
insert into post(post_id, post_author, post_wall, post_date, post_location) select 1225, f.facebook_ID, w.wall_ID, 'Oct-02-07 00:44:09 PDT','	Los Angeles, CA, United States'
 from fbuser f, wall w where f.facebook_ID='F11' and w.wall_ID='W12';


create table people_like_post(post_ID integer, people_like_post_ID varchar(30), foreign key(post_ID) references post, foreign key(people_like_post_ID) references fbuser);

insert into people_like_post(post_id, people_like_post_id) select post_id, 'F1' from post p where p.post_ID=1201;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F5' from post p where p.post_ID=1202;

insert into people_like_post(post_id,people_like_post_id) select post_id, 'F1' from post p where p.post_ID=1203;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F9' from post p where p.post_ID=1203;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F7' from post p where p.post_ID=1204;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F8' from post p where p.post_ID=1205;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F2' from post p where p.post_ID=1205;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F6' from post p where p.post_ID=1205;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F11' from post p where p.post_ID=1205;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F13' from post p where p.post_ID=1205;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F13' from post p where p.post_ID=1206;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F17' from post p where p.post_ID=1207;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F14' from post p where p.post_ID=1208;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F1' from post p where p.post_ID=1209;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F2' from post p where p.post_ID=1209;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F7' from post p where p.post_ID=1209;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F8' from post p where p.post_ID=1209;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F19' from post p where p.post_ID=1210;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F2' from post p where p.post_ID=1210;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F1' from post p where p.post_ID=1210;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F20' from post p where p.post_ID=1211;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F1' from post p where p.post_ID=1212;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F20' from post p where p.post_ID=1213;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F3' from post p where p.post_ID=1213;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F2' from post p where p.post_ID=1213;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F8' from post p where p.post_ID=1214;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F8' from post p where p.post_ID=1215;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F1' from post p where p.post_ID=1216;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F18' from post p where p.post_ID=1217;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F2' from post p where p.post_ID=1217;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F18' from post p where p.post_ID=1218;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F5' from post p where p.post_ID=1219;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F5' from post p where p.post_ID=1220;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F9' from post p where p.post_ID=1221;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F2' from post p where p.post_ID=1221;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F10' from post p where p.post_ID=1222;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F4' from post p where p.post_ID=1223;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F5' from post p where p.post_ID=1224;
insert into people_like_post(post_id,people_like_post_id) select post_id, 'F9' from post p where p.post_ID=1225;


create table comments(comment_ID integer primary key, c_post_ID integer, comment_author varchar(30), comment_date varchar(30), foreign key(c_post_ID) references post, foreign key(comment_author) references fbuser);
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 21201, p.post_id, f.facebook_ID,'Oct-02-07 09:11:17 PDT' from post p, fbuser f where p.post_id=1201 and f.facebook_id='F2';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 21202, p.post_id, f.facebook_ID,'Oct-02-07 01:31:39 PDT' from post p, fbuser f where p.post_id=1202 and f.facebook_id='F3';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12023, p.post_id, f.facebook_ID,' Oct-02-07 09:10:54 PDT' from post p, fbuser f where p.post_id=1203 and f.facebook_id='F12';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 13204, p.post_id, f.facebook_ID,'Oct-02-07 13:05:56 PDT' from post p, fbuser f where p.post_id=1204 and f.facebook_id='F2';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12305, p.post_id, f.facebook_ID,'Sep-29-07 10:38:25 PDT' from post p, fbuser f where p.post_id=1205 and f.facebook_id='F7';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12106, p.post_id, f.facebook_ID,'Sep-29-07 14:30:47 PDT' from post p, fbuser f where p.post_id=1206 and f.facebook_id='F12';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12607, p.post_id, f.facebook_ID,'Sep-29-07 13:52:25 PDT' from post p, fbuser f where p.post_id=1207 and f.facebook_id='F12';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12088, p.post_id, f.facebook_ID,'Sep-28-07 19:46:08 PDT' from post p, fbuser f where p.post_id=1208 and f.facebook_id='F3';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12609, p.post_id, f.facebook_ID,'Sep-29-07 13:45:00 PDT' from post p, fbuser f where p.post_id=1209 and f.facebook_id='F15';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12810, p.post_id, f.facebook_ID,'Sep-30-07 05:12:29 PDT' from post p, fbuser f where p.post_id=1210 and f.facebook_id='F16';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12911, p.post_id, f.facebook_ID,'Sep-29-07 16:06:00 PDT' from post p, fbuser f where p.post_id=1211 and f.facebook_id='F11';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12012, p.post_id, f.facebook_ID,'Oct-02-07 14:28:20 PDT' from post p, fbuser f where p.post_id=1212 and f.facebook_id='F3';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12013, p.post_id, f.facebook_ID,'Sep-27-07 11:00:33 PDT' from post p, fbuser f where p.post_id=1211 and f.facebook_id='F3';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12164, p.post_id, f.facebook_ID,'Oct-02-07 12:45:00 PDT' from post p, fbuser f where p.post_id=1214 and f.facebook_id='F12';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12415, p.post_id, f.facebook_ID,'Sep-29-07 16:08:39 PDT' from post p, fbuser f where p.post_id=1211 and f.facebook_id='F12';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12316, p.post_id, f.facebook_ID,'Sep-29-07 16:45:34 PDT' from post p, fbuser f where p.post_id=1216 and f.facebook_id='F3';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12127, p.post_id, f.facebook_ID,'Sep-25-07 17:18:31 PDT' from post p, fbuser f where p.post_id=1212 and f.facebook_id='F11';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12138, p.post_id, f.facebook_ID,'Sep-25-07 17:15:00 PDT' from post p, fbuser f where p.post_id=1218 and f.facebook_id='F12';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12419, p.post_id, f.facebook_ID,'Oct-01-07 17:15:05 PDT' from post p, fbuser f where p.post_id=1213 and f.facebook_id='F16';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12250, p.post_id, f.facebook_ID,'Sep-29-07 13:41:37 PDT' from post p, fbuser f where p.post_id=1220 and f.facebook_id='F3';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 12421, p.post_id, f.facebook_ID,'Sep-25-07 16:47:57 PDT' from post p, fbuser f where p.post_id=1215 and f.facebook_id='F6';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 122322, p.post_id, f.facebook_ID,'Sep-30-07 14:34:54 PDT' from post p, fbuser f where p.post_id=1215 and f.facebook_id='F12';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 124423, p.post_id, f.facebook_ID,'Sep-29-07 17:28:02 PDT' from post p, fbuser f where p.post_id=1215 and f.facebook_id='F2';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 124424, p.post_id, f.facebook_ID,'Sep-29-07 17:29:40 PDT' from post p, fbuser f where p.post_id=1214 and f.facebook_id='F2';
insert into comments(comment_id, c_post_id, comment_author, comment_date) select 134225, p.post_id, f.facebook_ID,'Oct-02-07 00:44:09 PDT' from post p, fbuser f where p.post_id=1210 and f.facebook_id='F11';

create table people_like_comment(comment_id integer, people_like_comment_ID varchar(30), foreign key(comment_id) references comments, foreign key(people_like_comment_ID) references fbuser);

insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F2' from comments c where c.comment_id=21201;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F7' from comments c where c.comment_id=21201;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F9' from comments c where c.comment_id=21201;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F1' from comments c where c.comment_id=21202;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F6' from comments c where c.comment_id=21202;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F9' from comments c where c.comment_id=21202;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F16' from comments c where c.comment_id=21202;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F18' from comments c where c.comment_id=21202;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F7' from comments c where c.comment_id=13204;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F2' from comments c where c.comment_id=12106;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F15' from comments c where c.comment_id=12106;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F1' from comments c where c.comment_id=12607;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F4' from comments c where c.comment_id=12607;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F17' from comments c where c.comment_id=12607;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F13' from comments c where c.comment_id=12088;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F15' from comments c where c.comment_id=12088;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F2' from comments c where c.comment_id=12088;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F1' from comments c where c.comment_id=12609;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F2' from comments c where c.comment_id=12609;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F16' from comments c where c.comment_id=12810;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F12' from comments c where c.comment_id=12911;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F11' from comments c where c.comment_id=12012;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F14' from comments c where c.comment_id=12012;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F2' from comments c where c.comment_id=12012;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F8' from comments c where c.comment_id=12013;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F12' from comments c where c.comment_id=12164;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F6' from comments c where c.comment_id=12415;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F8' from comments c where c.comment_id=12415;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F2' from comments c where c.comment_id=12316;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F10' from comments c where c.comment_id=12316;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F7' from comments c where c.comment_id=12127;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F2' from comments c where c.comment_id=12138;
insert into people_like_comment(comment_id, people_like_comment_id) select comment_id, 'F2' from comments c where c.comment_id=122322;


