INSERT into user(email,is_admin,login,password,creation_time) VALUES("grzeswag@wp.pl",false,"login","password",CURRENT_DATE );
INSERT into user(email,is_admin,login,password,creation_time) VALUES("grzeswag@wp.pl",true,"admin","password",CURRENT_DATE );

INSERT into reservation_places(place_name) values("miejsce3");
INSERT into reservation_places(place_name) values("miejsce2");
INSERT into reservation_places(place_name) values("miejsce1");

INSERT into reservation(during,end_time,start_time,vissibility,reservation_place_id,user_id) VALUES (100000,CURRENT_DATE +100000,CURRENT_DATE ,true,1,1);
INSERT into reservation(during,end_time,start_time,vissibility,reservation_place_id,user_id) values(100000,CURRENT_DATE +100000,CURRENT_DATE ,true,1,1);
INSERT into reservation(during,end_time,start_time,vissibility,reservation_place_id,user_id) values(100000,CURRENT_DATE +100000,CURRENT_DATE ,true,1,1);
