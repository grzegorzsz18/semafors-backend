INSERT into user(email,is_admin,login,password,creation_time) VALUES("grzeswag@wp.pl",false,"login","password",CURRENT_TIME );
INSERT into user(email,is_admin,login,password,creation_time) VALUES("grzeswag@wp.pl",true,"admin","password",CURRENT_TIME );

INSERT into reservation_places values(1,"miejsce3");
INSERT into reservation_places values(2,"miejsce2");
INSERT into reservation_places values(3,"miejsce1");

INSERT into reservation values(1,100000,CURRENT_TIME +100000,CURRENT_TIME,true,1,1);
INSERT into reservation values(2,100000,CURRENT_TIME +100000,CURRENT_TIME,true,1,1);
INSERT into reservation values(3,100000,CURRENT_TIME +100000,CURRENT_TIME,true,1,1);