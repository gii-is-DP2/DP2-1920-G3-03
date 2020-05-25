/* Administrators */
INSERT INTO users(username, password, enabled) VALUES ('admin1', 'admin1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (1, 'admin', 'admin1');
INSERT INTO authorities(id, authority, username) VALUES (2, 'client', 'admin1');
INSERT INTO admins(id, email, first_name, last_name, nif, username) VALUES (1, 'admin0@yogoym.com','AdminName1', 'AdminSurname', '10000000A','admin1');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (1, 'client1@yogoym.com','AdminClientName1', 'AdminClientSurname', '10000000A', 41,0.61647487,1.80, true, 80, null,'admin1');

INSERT INTO users(username, password, enabled) VALUES ('admin2', 'admin1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (3, 'admin', 'admin2');
INSERT INTO authorities(id, authority, username) VALUES (4, 'client', 'admin2');
INSERT INTO admins(id, email, first_name, last_name, nif, username) VALUES (2, 'admin1@yogoym.com','AdminName2', 'AdminSurname', '10000001A','admin2');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (2, 'client2@yogoym.com','AdminClientName2', 'AdminClientSurname', '10000001A', 42,0.011274576,1.80, true, 80, null,'admin2');

INSERT INTO users(username, password, enabled) VALUES ('admin3', 'admin1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (5, 'admin', 'admin3');
INSERT INTO authorities(id, authority, username) VALUES (6, 'client', 'admin3');
INSERT INTO admins(id, email, first_name, last_name, nif, username) VALUES (3, 'admin2@yogoym.com','AdminName3', 'AdminSurname', '10000002A','admin3');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (3, 'client3@yogoym.com','AdminClientName3', 'AdminClientSurname', '10000002A', 30,0.5453379,1.80, false, 80, null,'admin3');

INSERT INTO users(username, password, enabled) VALUES ('admin4', 'admin1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (7, 'admin', 'admin4');
INSERT INTO authorities(id, authority, username) VALUES (8, 'client', 'admin4');
INSERT INTO admins(id, email, first_name, last_name, nif, username) VALUES (4, 'admin3@yogoym.com','AdminName4', 'AdminSurname', '10000003A','admin4');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (4, 'client4@yogoym.com','AdminClientName4', 'AdminClientSurname', '10000003A', 41,0.04824251,1.80, false, 80, null,'admin4');

INSERT INTO users(username, password, enabled) VALUES ('admin5', 'admin1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (9, 'admin', 'admin5');
INSERT INTO authorities(id, authority, username) VALUES (10, 'client', 'admin5');
INSERT INTO admins(id, email, first_name, last_name, nif, username) VALUES (5, 'admin4@yogoym.com','AdminName5', 'AdminSurname', '10000004A','admin5');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (5, 'client5@yogoym.com','AdminClientName5', 'AdminClientSurname', '10000004A', 32,0.4207517,1.80, true, 80, null,'admin5');

/* Trainers */
INSERT INTO users(username, password, enabled) VALUES ('trainer1', 'trainer1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (11, 'trainer', 'trainer1');
INSERT INTO trainers(id,email,first_name,last_name,nif,username) VALUES (1, 'trainer1@yogoym.com','TrainerName1', 'TrainerSurname','10000005A', 'trainer1');

INSERT INTO users(username, password, enabled) VALUES ('trainer2', 'trainer1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (12, 'trainer', 'trainer2');
INSERT INTO trainers(id,email,first_name,last_name,nif,username) VALUES (2, 'trainer2@yogoym.com','TrainerName2', 'TrainerSurname','10000005A', 'trainer2');

INSERT INTO users(username, password, enabled) VALUES ('trainer3', 'trainer1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (13, 'trainer', 'trainer3');
INSERT INTO trainers(id,email,first_name,last_name,nif,username) VALUES (3, 'trainer3@yogoym.com','TrainerName3', 'TrainerSurname','10000005A', 'trainer3');

INSERT INTO users(username, password, enabled) VALUES ('trainer4', 'trainer1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (14, 'trainer', 'trainer4');
INSERT INTO trainers(id,email,first_name,last_name,nif,username) VALUES (4, 'trainer4@yogoym.com','TrainerName4', 'TrainerSurname','10000005A', 'trainer4');

INSERT INTO users(username, password, enabled) VALUES ('trainer5', 'trainer1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (15, 'trainer', 'trainer5');
INSERT INTO trainers(id,email,first_name,last_name,nif,username) VALUES (5, 'trainer5@yogoym.com','TrainerName5', 'TrainerSurname','10000005A', 'trainer5');


/* Guilds */
INSERT INTO guilds(id, creator, description, name, logo) VALUES (1, 'client6', 'Description Guild1', 'Guild Name1','https://');
INSERT INTO guilds(id, creator, description, name, logo) VALUES (2, 'client7', 'Description Guild2', 'Guild Name2','https://');
INSERT INTO guilds(id, creator, description, name, logo) VALUES (3, 'client8', 'Description Guild3', 'Guild Name3','https://');

/* Clients */
/* Client Creator of Guild */
INSERT INTO users(username, password, enabled) VALUES ('client6', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (16, 'client', 'client6');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (6, 'client6@yogoym.com','ClientName6', 'ClientSurname','10000005A', 48,0.6868121,1.80, true, 80,1, 'client6');

/* Client Creator of Guild */
INSERT INTO users(username, password, enabled) VALUES ('client7', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (17, 'client', 'client7');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (7, 'client7@yogoym.com','ClientName7', 'ClientSurname','10000006A', 35,0.9160804,1.80, false, 80,2, 'client7');

/* Client Creator of Guild */
INSERT INTO users(username, password, enabled) VALUES ('client8', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (18, 'client', 'client8');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (8, 'client8@yogoym.com','ClientName8', 'ClientSurname','10000007A', 38,0.6604944,1.80, false, 80,3, 'client8');

INSERT INTO users(username, password, enabled) VALUES ('client9', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (19, 'client', 'client9');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (9, 'client9@yogoym.com','ClientName9', 'ClientSurname','10000008A', 27,0.7267895, 1.80, false, 80, null, 'client9');

INSERT INTO users(username, password, enabled) VALUES ('client10', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (20, 'client', 'client10');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (10, 'client10@yogoym.com','ClientName10', 'ClientSurname','10000009A', 47,0.32475048,1.80, true, 80,1, 'client10');

INSERT INTO users(username, password, enabled) VALUES ('client11', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (21, 'client', 'client11');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (11, 'client11@yogoym.com','ClientName11', 'ClientSurname','10000010A', 26,0.50733906,1.80, false, 80,2, 'client11');

INSERT INTO users(username, password, enabled) VALUES ('client12', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (22, 'client', 'client12');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (12, 'client12@yogoym.com','ClientName12', 'ClientSurname','10000011A', 44,0.4604981,1.80, false, 80,3, 'client12');

INSERT INTO users(username, password, enabled) VALUES ('client13', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (23, 'client', 'client13');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (13, 'client13@yogoym.com','ClientName13', 'ClientSurname','10000012A', 28,0.21905494, 1.80, true, 80, null, 'client13');

INSERT INTO users(username, password, enabled) VALUES ('client14', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (24, 'client', 'client14');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (14, 'client14@yogoym.com','ClientName14', 'ClientSurname','10000013A', 27,0.14560789,1.80, false, 80,1, 'client14');

INSERT INTO users(username, password, enabled) VALUES ('client15', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (25, 'client', 'client15');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (15, 'client15@yogoym.com','ClientName15', 'ClientSurname','10000014A', 29,0.60740054,1.80, true, 80,2, 'client15');

INSERT INTO users(username, password, enabled) VALUES ('client16', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (26, 'client', 'client16');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (16, 'client16@yogoym.com','ClientName16', 'ClientSurname','10000015A', 29,0.75720346,1.80, true, 80,3, 'client16');

INSERT INTO users(username, password, enabled) VALUES ('client17', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (27, 'client', 'client17');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (17, 'client17@yogoym.com','ClientName17', 'ClientSurname','10000016A', 32,0.43775713, 1.80, true, 80, null, 'client17');

INSERT INTO users(username, password, enabled) VALUES ('client18', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (28, 'client', 'client18');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (18, 'client18@yogoym.com','ClientName18', 'ClientSurname','10000017A', 28,0.96063626,1.80, true, 80,1, 'client18');

/* Challenges */
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (1,'Challenge1','Desc challenge 1','2020-1-1','2020-1-7','Reward1', 62, 4, 40, 53);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (2,'Challenge2','Desc challenge 2','2020-1-8','2020-1-13','Reward2', 48, 4, 40, 54);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (3,'Challenge3','Desc challenge 3','2020-1-14','2020-1-19','Reward3', 90, 4, 40, 63);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (4,'Challenge4','Desc challenge 4','2020-1-20','2020-1-25','Reward4', 68, 4, 40, 55);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (5,'Challenge5','Desc challenge 5','2020-1-26','2020-1-31','Reward5', 70, 4, 40, 34);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (6,'Challenge6','Desc challenge 6','2020-2-1','2020-2-6','Reward6', 29, 4, 40, 36);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (7,'Challenge7','Desc challenge 7','2020-2-7','2020-2-12','Reward7', 47, 4, 40, 33);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (8,'Challenge8','Desc challenge 8','2020-2-13','2020-2-18','Reward8', 50, 4, 40, 24);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (9,'Challenge9','Desc challenge 9','2020-2-19','2020-2-24','Reward9', 65, 4, 40, 19);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (10,'Challenge10','Desc challenge 10','2020-2-25','2020-3-1','Reward10', 43, 4, 40, 62);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (11,'Challenge11','Desc challenge 11','2020-3-2','2020-3-7','Reward11', 30, 4, 40, 52);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (12,'Challenge12','Desc challenge 12','2020-3-8','2020-3-13','Reward12', 61, 4, 40, 21);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (13,'Challenge13','Desc challenge 13','2020-3-14','2020-3-19','Reward13', 75, 4, 40, 32);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (14,'Challenge14','Desc challenge 14','2020-3-20','2020-3-25','Reward14', 84, 4, 40, 1);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (15,'Challenge15','Desc challenge 15','2020-3-26','2020-3-31','Reward15', 36, 4, 40, 3);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (16,'Challenge16','Desc challenge 16','2020-4-1','2020-4-6','Reward16', 88, 4, 40, 4);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (17,'Challenge17','Desc challenge 17','2020-4-7','2020-4-12','Reward17', 48, 4, 40, 59);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (18,'Challenge18','Desc challenge 18','2020-4-13','2020-4-18','Reward18', 50, 4, 40, 44);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (19,'Challenge19','Desc challenge 19','2020-4-19','2020-4-24','Reward19', 95, 4, 40, 25);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (20,'Challenge20','Desc challenge 20','2020-4-25','2020-4-30','Reward20', 72, 4, 40, 56);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (21,'Challenge21','Desc challenge 21','2020-5-1','2020-5-6','Reward21', 47, 4, 40, 4);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (22,'Challenge22','Desc challenge 22','2020-5-7','2020-5-12','Reward22', 68, 4, 40, 33);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (23,'Challenge23','Desc challenge 23','2020-5-13','2020-5-18','Reward23', 30, 4, 40, 47);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (24,'Challenge24','Desc challenge 24','2020-5-19','2020-5-24','Reward24', 83, 4, 40, 17);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (25,'Challenge25','Desc challenge 25','2020-5-25','2020-5-30','Reward25', 74, 4, 40, 21);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (26,'Challenge26','Desc challenge 26','2020-5-31','2020-6-5','Reward26', 25, 4, 40, 29);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (27,'Challenge27','Desc challenge 27','2020-6-6','2020-6-11','Reward27', 52, 4, 40, 23);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (28,'Challenge28','Desc challenge 28','2020-6-12','2020-6-17','Reward28', 65, 4, 40, 54);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (29,'Challenge29','Desc challenge 29','2020-6-18','2020-6-23','Reward29', 84, 4, 40, 17);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (30,'Challenge30','Desc challenge 30','2020-6-24','2020-6-29','Reward30', 50, 4, 40, 42);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (31,'Challenge31','Desc challenge 31','2020-6-30','2020-7-5','Reward31', 53, 4, 40, 63);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (32,'Challenge32','Desc challenge 32','2020-7-6','2020-7-11','Reward32', 46, 4, 40, 22);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (33,'Challenge33','Desc challenge 33','2020-7-12','2020-7-17','Reward33', 81, 4, 40, 37);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (34,'Challenge34','Desc challenge 34','2020-7-18','2020-7-23','Reward34', 68, 4, 40, 33);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (35,'Challenge35','Desc challenge 35','2020-7-24','2020-7-29','Reward35', 71, 4, 40, 26);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (36,'Challenge36','Desc challenge 36','2020-7-30','2020-8-4','Reward36', 69, 4, 40, 41);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (37,'Challenge37','Desc challenge 37','2020-8-5','2020-8-10','Reward37', 88, 4, 40, 17);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (38,'Challenge38','Desc challenge 38','2020-8-11','2020-8-16','Reward38', 51, 4, 40, 11);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (39,'Challenge39','Desc challenge 39','2020-8-17','2020-8-22','Reward39', 34, 4, 40, 56);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (40,'Challenge40','Desc challenge 40','2020-8-23','2020-8-28','Reward40', 86, 4, 40, 49);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (41,'Challenge41','Desc challenge 41','2020-8-29','2020-9-3','Reward41', 36, 4, 40, 27);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (42,'Challenge42','Desc challenge 42','2020-9-4','2020-9-9','Reward42', 88, 4, 40, 58);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (43,'Challenge43','Desc challenge 43','2020-9-10','2020-9-15','Reward43', 80, 4, 40, 6);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (44,'Challenge44','Desc challenge 44','2020-9-16','2020-9-21','Reward44', 48, 4, 40, 42);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (45,'Challenge45','Desc challenge 45','2020-9-22','2020-9-27','Reward45', 30, 4, 40, 53);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (46,'Challenge46','Desc challenge 46','2020-9-28','2020-10-3','Reward46', 76, 4, 40, 41);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (47,'Challenge47','Desc challenge 47','2020-10-4','2020-10-9','Reward47', 77, 4, 40, 5);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (48,'Challenge48','Desc challenge 48','2020-10-10','2020-10-15','Reward48', 39, 4, 40, 49);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (49,'Challenge49','Desc challenge 49','2020-10-16','2020-10-21','Reward49', 68, 4, 40, 17);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (50,'Challenge50','Desc challenge 50','2020-10-22','2020-10-27','Reward50', 42, 4, 40, 42);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (51,'Challenge51','Desc challenge 51','2020-10-28','2020-11-2','Reward51', 55, 4, 40, 4);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (52,'Challenge52','Desc challenge 52','2020-11-3','2020-11-8','Reward52', 62, 4, 40, 34);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (53,'Challenge53','Desc challenge 53','2020-11-9','2020-11-14','Reward53', 53, 4, 40, 5);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (54,'Challenge54','Desc challenge 54','2020-11-15','2020-11-20','Reward54', 48, 4, 40, 19);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (55,'Challenge55','Desc challenge 55','2020-11-21','2020-11-26','Reward55', 78, 4, 40, 56);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (56,'Challenge56','Desc challenge 56','2020-11-27','2020-12-2','Reward56', 30, 4, 40, 45);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (57,'Challenge57','Desc challenge 57','2020-12-3','2020-12-8','Reward57', 82, 4, 40, 10);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (58,'Challenge58','Desc challenge 58','2020-12-9','2020-12-14','Reward58', 30, 4, 40, 20);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (59,'Challenge59','Desc challenge 59','2020-12-15','2020-12-20','Reward59', 37, 4, 40, 26);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (60,'Challenge60','Desc challenge 60','2020-12-21','2020-12-26','Reward60', 29, 4, 40, 44);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (61,'Challenge61','Desc challenge 61','2020-12-27','2021-1-1','Reward61', 46, 4, 40, 33);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (62,'Challenge62','Desc challenge 62','2021-1-2','2021-1-7','Reward62', 36, 4, 40, 54);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (63,'Challenge63','Desc challenge 63','2021-1-8','2021-1-13','Reward63', 47, 4, 40, 19);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (64,'Challenge64','Desc challenge 64','2021-1-14','2021-1-19','Reward64', 23, 4, 40, 8);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (65,'Challenge65','Desc challenge 65','2021-1-20','2021-1-25','Reward65', 63, 4, 40, 44);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (66,'Challenge66','Desc challenge 66','2021-1-26','2021-1-31','Reward66', 56, 4, 40, 27);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (67,'Challenge67','Desc challenge 67','2021-2-1','2021-2-6','Reward67', 34, 4, 40, 10);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (68,'Challenge68','Desc challenge 68','2021-2-7','2021-2-12','Reward68', 88, 4, 40, 29);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (69,'Challenge69','Desc challenge 69','2021-2-13','2021-2-18','Reward69', 90, 4, 40, 40);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (70,'Challenge70','Desc challenge 70','2021-2-19','2021-2-24','Reward70', 78, 4, 40, 14);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (71,'Challenge71','Desc challenge 71','2021-2-25','2021-3-2','Reward71', 87, 4, 40, 18);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (72,'Challenge72','Desc challenge 72','2021-3-3','2021-3-8','Reward72', 96, 4, 40, 63);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (73,'Challenge73','Desc challenge 73','2021-3-9','2021-3-14','Reward73', 26, 4, 40, 34);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (74,'Challenge74','Desc challenge 74','2021-3-15','2021-3-20','Reward74', 77, 4, 40, 19);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (75,'Challenge75','Desc challenge 75','2021-3-21','2021-3-26','Reward75', 23, 4, 40, 23);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (76,'Challenge76','Desc challenge 76','2021-3-27','2021-4-1','Reward76', 29, 4, 40, 15);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (77,'Challenge77','Desc challenge 77','2021-4-2','2021-4-7','Reward77', 41, 4, 40, 46);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (78,'Challenge78','Desc challenge 78','2021-4-8','2021-4-13','Reward78', 64, 4, 40, 50);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (79,'Challenge79','Desc challenge 79','2021-4-14','2021-4-19','Reward79', 63, 4, 40, 17);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (80,'Challenge80','Desc challenge 80','2021-4-20','2021-4-25','Reward80', 99, 4, 40, 53);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (81,'Challenge81','Desc challenge 81','2021-4-26','2021-5-1','Reward81', 20, 4, 40, 56);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (82,'Challenge82','Desc challenge 82','2021-5-2','2021-5-7','Reward82', 82, 4, 40, 38);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (83,'Challenge83','Desc challenge 83','2021-5-8','2021-5-13','Reward83', 60, 4, 40, 43);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (84,'Challenge84','Desc challenge 84','2021-5-14','2021-5-19','Reward84', 87, 4, 40, 40);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (85,'Challenge85','Desc challenge 85','2021-5-20','2021-5-25','Reward85', 90, 4, 40, 38);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (86,'Challenge86','Desc challenge 86','2021-5-26','2021-5-31','Reward86', 48, 4, 40, 22);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (87,'Challenge87','Desc challenge 87','2021-6-1','2021-6-6','Reward87', 63, 4, 40, 8);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (88,'Challenge88','Desc challenge 88','2021-6-7','2021-6-12','Reward88', 79, 4, 40, 33);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (89,'Challenge89','Desc challenge 89','2021-6-13','2021-6-18','Reward89', 62, 4, 40, 14);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (90,'Challenge90','Desc challenge 90','2021-6-19','2021-6-24','Reward90', 75, 4, 40, 17);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (91,'Challenge91','Desc challenge 91','2021-6-25','2021-6-30','Reward91', 74, 4, 40, 64);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (92,'Challenge92','Desc challenge 92','2021-7-1','2021-7-6','Reward92', 35, 4, 40, 22);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (93,'Challenge93','Desc challenge 93','2021-7-7','2021-7-12','Reward93', 96, 4, 40, 22);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (94,'Challenge94','Desc challenge 94','2021-7-13','2021-7-18','Reward94', 83, 4, 40, 6);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (95,'Challenge95','Desc challenge 95','2021-7-19','2021-7-24','Reward95', 49, 4, 40, 44);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (96,'Challenge96','Desc challenge 96','2021-7-25','2021-7-30','Reward96', 95, 4, 40, 61);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (97,'Challenge97','Desc challenge 97','2021-7-31','2021-8-5','Reward97', 88, 4, 40, 10);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (98,'Challenge98','Desc challenge 98','2021-8-6','2021-8-11','Reward98', 92, 4, 40, 58);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (99,'Challenge99','Desc challenge 99','2021-8-12','2021-8-17','Reward99', 90, 4, 40, 25);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (100,'Challenge100','Desc challenge 100','2021-8-18','2021-8-23','Reward100', 78, 4, 40, 19);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (101,'Challenge101','Desc challenge 101','2021-8-24','2021-8-29','Reward101', 75, 4, 40, 20);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (102,'Challenge102','Desc challenge 102','2021-8-30','2021-9-4','Reward102', 44, 4, 40, 17);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (103,'Challenge103','Desc challenge 103','2021-9-5','2021-9-10','Reward103', 28, 4, 40, 47);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (104,'Challenge104','Desc challenge 104','2021-9-11','2021-9-16','Reward104', 80, 4, 40, 57);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (105,'Challenge105','Desc challenge 105','2021-9-17','2021-9-22','Reward105', 50, 4, 40, 3);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (106,'Challenge106','Desc challenge 106','2021-9-23','2021-9-28','Reward106', 74, 4, 40, 54);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (107,'Challenge107','Desc challenge 107','2021-9-29','2021-10-4','Reward107', 51, 4, 40, 4);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (108,'Challenge108','Desc challenge 108','2021-10-5','2021-10-10','Reward108', 36, 4, 40, 26);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (109,'Challenge109','Desc challenge 109','2021-10-11','2021-10-16','Reward109', 58, 4, 40, 6);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (110,'Challenge110','Desc challenge 110','2021-10-17','2021-10-22','Reward110', 32, 4, 40, 2);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (111,'Challenge111','Desc challenge 111','2021-10-23','2021-10-28','Reward111', 90, 4, 40, 5);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (112,'Challenge112','Desc challenge 112','2021-10-29','2021-11-3','Reward112', 62, 4, 40, 58);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (113,'Challenge113','Desc challenge 113','2021-11-4','2021-11-9','Reward113', 94, 4, 40, 51);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (114,'Challenge114','Desc challenge 114','2021-11-10','2021-11-15','Reward114', 26, 4, 40, 5);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (115,'Challenge115','Desc challenge 115','2021-11-16','2021-11-21','Reward115', 31, 4, 40, 33);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (116,'Challenge116','Desc challenge 116','2021-11-22','2021-11-27','Reward116', 34, 4, 40, 50);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (117,'Challenge117','Desc challenge 117','2021-11-28','2021-12-3','Reward117', 95, 4, 40, 62);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (118,'Challenge118','Desc challenge 118','2021-12-4','2021-12-9','Reward118', 53, 4, 40, 9);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (119,'Challenge119','Desc challenge 119','2021-12-10','2021-12-15','Reward119', 34, 4, 40, 61);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (120,'Challenge120','Desc challenge 120','2021-12-16','2021-12-21','Reward120', 70, 4, 40, 52);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (121,'Challenge121','Desc challenge 121','2021-12-22','2021-12-27','Reward121', 60, 4, 40, 15);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (122,'Challenge122','Desc challenge 122','2021-12-28','2022-1-2','Reward122', 80, 4, 40, 12);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (123,'Challenge123','Desc challenge 123','2022-1-3','2022-1-8','Reward123', 53, 4, 40, 61);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (124,'Challenge124','Desc challenge 124','2022-1-9','2022-1-14','Reward124', 99, 4, 40, 23);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (125,'Challenge125','Desc challenge 125','2022-1-15','2022-1-20','Reward125', 25, 4, 40, 17);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (126,'Challenge126','Desc challenge 126','2022-1-21','2022-1-26','Reward126', 61, 4, 40, 16);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (127,'Challenge127','Desc challenge 127','2022-1-27','2022-2-1','Reward127', 93, 4, 40, 13);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (128,'Challenge128','Desc challenge 128','2022-2-2','2022-2-7','Reward128', 85, 4, 40, 25);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (129,'Challenge129','Desc challenge 129','2022-2-8','2022-2-13','Reward129', 92, 4, 40, 35);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (130,'Challenge130','Desc challenge 130','2022-2-14','2022-2-19','Reward130', 96, 4, 40, 58);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (131,'Challenge131','Desc challenge 131','2022-2-20','2022-2-25','Reward131', 47, 4, 40, 33);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (132,'Challenge132','Desc challenge 132','2022-2-26','2022-3-3','Reward132', 22, 4, 40, 22);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (133,'Challenge133','Desc challenge 133','2022-3-4','2022-3-9','Reward133', 91, 4, 40, 32);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (134,'Challenge134','Desc challenge 134','2022-3-10','2022-3-15','Reward134', 46, 4, 40, 45);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (135,'Challenge135','Desc challenge 135','2022-3-16','2022-3-21','Reward135', 35, 4, 40, 27);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (136,'Challenge136','Desc challenge 136','2022-3-22','2022-3-27','Reward136', 22, 4, 40, 8);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (137,'Challenge137','Desc challenge 137','2022-3-28','2022-4-2','Reward137', 62, 4, 40, 63);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (138,'Challenge138','Desc challenge 138','2022-4-3','2022-4-8','Reward138', 79, 4, 40, 34);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (139,'Challenge139','Desc challenge 139','2022-4-9','2022-4-14','Reward139', 52, 4, 40, 8);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (140,'Challenge140','Desc challenge 140','2022-4-15','2022-4-20','Reward140', 21, 4, 40, 54);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (141,'Challenge141','Desc challenge 141','2022-4-21','2022-4-26','Reward141', 99, 4, 40, 20);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (142,'Challenge142','Desc challenge 142','2022-4-27','2022-5-2','Reward142', 77, 4, 40, 40);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (143,'Challenge143','Desc challenge 143','2022-5-3','2022-5-8','Reward143', 94, 4, 40, 45);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (144,'Challenge144','Desc challenge 144','2022-5-9','2022-5-14','Reward144', 71, 4, 40, 9);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (145,'Challenge145','Desc challenge 145','2022-5-15','2022-5-20','Reward145', 84, 4, 40, 50);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (146,'Challenge146','Desc challenge 146','2022-5-21','2022-5-26','Reward146', 82, 4, 40, 61);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (147,'Challenge147','Desc challenge 147','2022-5-27','2022-6-1','Reward147', 62, 4, 40, 51);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (148,'Challenge148','Desc challenge 148','2022-6-2','2022-6-7','Reward148', 30, 4, 40, 32);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (149,'Challenge149','Desc challenge 149','2022-6-8','2022-6-13','Reward149', 98, 4, 40, 36);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (150,'Challenge150','Desc challenge 150','2022-6-14','2022-6-19','Reward150', 93, 4, 40, 22);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (151,'Challenge151','Desc challenge 151','2022-6-20','2022-6-25','Reward151', 53, 4, 40, 9);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (152,'Challenge152','Desc challenge 152','2022-6-26','2022-7-1','Reward152', 51, 4, 40, 54);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (153,'Challenge153','Desc challenge 153','2022-7-2','2022-7-7','Reward153', 30, 4, 40, 50);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (154,'Challenge154','Desc challenge 154','2022-7-8','2022-7-13','Reward154', 45, 4, 40, 34);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (155,'Challenge155','Desc challenge 155','2022-7-14','2022-7-19','Reward155', 85, 4, 40, 46);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (156,'Challenge156','Desc challenge 156','2022-7-20','2022-7-25','Reward156', 30, 4, 40, 14);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (157,'Challenge157','Desc challenge 157','2022-7-26','2022-7-31','Reward157', 90, 4, 40, 59);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (158,'Challenge158','Desc challenge 158','2022-8-1','2022-8-6','Reward158', 52, 4, 40, 28);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (159,'Challenge159','Desc challenge 159','2022-8-7','2022-8-12','Reward159', 81, 4, 40, 28);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (160,'Challenge160','Desc challenge 160','2022-8-13','2022-8-18','Reward160', 56, 4, 40, 50);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (161,'Challenge161','Desc challenge 161','2022-8-19','2022-8-24','Reward161', 43, 4, 40, 39);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (162,'Challenge162','Desc challenge 162','2022-8-25','2022-8-30','Reward162', 84, 4, 40, 15);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (163,'Challenge163','Desc challenge 163','2022-8-31','2022-9-5','Reward163', 81, 4, 40, 45);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (164,'Challenge164','Desc challenge 164','2022-9-6','2022-9-11','Reward164', 64, 4, 40, 9);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (165,'Challenge165','Desc challenge 165','2022-9-12','2022-9-17','Reward165', 89, 4, 40, 13);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (166,'Challenge166','Desc challenge 166','2022-9-18','2022-9-23','Reward166', 69, 4, 40, 5);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (167,'Challenge167','Desc challenge 167','2022-9-24','2022-9-29','Reward167', 91, 4, 40, 14);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (168,'Challenge168','Desc challenge 168','2022-9-30','2022-10-5','Reward168', 98, 4, 40, 41);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (169,'Challenge169','Desc challenge 169','2022-10-6','2022-10-11','Reward169', 45, 4, 40, 62);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (170,'Challenge170','Desc challenge 170','2022-10-12','2022-10-17','Reward170', 77, 4, 40, 59);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (171,'Challenge171','Desc challenge 171','2022-10-18','2022-10-23','Reward171', 59, 4, 40, 38);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (172,'Challenge172','Desc challenge 172','2022-10-24','2022-10-29','Reward172', 81, 4, 40, 31);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (173,'Challenge173','Desc challenge 173','2022-10-30','2022-11-4','Reward173', 82, 4, 40, 56);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (174,'Challenge174','Desc challenge 174','2022-11-5','2022-11-10','Reward174', 37, 4, 40, 31);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (175,'Challenge175','Desc challenge 175','2022-11-11','2022-11-16','Reward175', 43, 4, 40, 64);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (176,'Challenge176','Desc challenge 176','2022-11-17','2022-11-22','Reward176', 62, 4, 40, 37);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (177,'Challenge177','Desc challenge 177','2022-11-23','2022-11-28','Reward177', 37, 4, 40, 37);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (178,'Challenge178','Desc challenge 178','2022-11-29','2022-12-4','Reward178', 27, 4, 40, 28);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (179,'Challenge179','Desc challenge 179','2022-12-5','2022-12-10','Reward179', 27, 4, 40, 33);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (180,'Challenge180','Desc challenge 180','2022-12-11','2022-12-16','Reward180', 45, 4, 40, 64);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (181,'Challenge181','Desc challenge 181','2022-12-17','2022-12-22','Reward181', 69, 4, 40, 44);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (182,'Challenge182','Desc challenge 182','2022-12-23','2022-12-28','Reward182', 90, 4, 40, 33);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (183,'Challenge183','Desc challenge 183','2022-12-29','2023-1-3','Reward183', 74, 4, 40, 55);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (184,'Challenge184','Desc challenge 184','2023-1-4','2023-1-9','Reward184', 55, 4, 40, 17);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (185,'Challenge185','Desc challenge 185','2023-1-10','2023-1-15','Reward185', 82, 4, 40, 4);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (186,'Challenge186','Desc challenge 186','2023-1-16','2023-1-21','Reward186', 50, 4, 40, 2);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (187,'Challenge187','Desc challenge 187','2023-1-22','2023-1-27','Reward187', 58, 4, 40, 41);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (188,'Challenge188','Desc challenge 188','2023-1-28','2023-2-2','Reward188', 50, 4, 40, 61);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (189,'Challenge189','Desc challenge 189','2023-2-3','2023-2-8','Reward189', 81, 4, 40, 24);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (190,'Challenge190','Desc challenge 190','2023-2-9','2023-2-14','Reward190', 22, 4, 40, 46);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (191,'Challenge191','Desc challenge 191','2023-2-15','2023-2-20','Reward191', 63, 4, 40, 2);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (192,'Challenge192','Desc challenge 192','2023-2-21','2023-2-26','Reward192', 74, 4, 40, 18);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (193,'Challenge193','Desc challenge 193','2023-2-27','2023-3-4','Reward193', 54, 4, 40, 18);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (194,'Challenge194','Desc challenge 194','2023-3-5','2023-3-10','Reward194', 25, 4, 40, 17);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (195,'Challenge195','Desc challenge 195','2023-3-11','2023-3-16','Reward195', 48, 4, 40, 28);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (196,'Challenge196','Desc challenge 196','2023-3-17','2023-3-22','Reward196', 76, 4, 40, 5);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (197,'Challenge197','Desc challenge 197','2023-3-23','2023-3-28','Reward197', 72, 4, 40, 55);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (198,'Challenge198','Desc challenge 198','2023-3-29','2023-4-3','Reward198', 48, 4, 40, 38);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (199,'Challenge199','Desc challenge 199','2023-4-4','2023-4-9','Reward199', 84, 4, 40, 30);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (200,'Challenge200','Desc challenge 200','2023-4-10','2023-4-15','Reward200', 85, 4, 40, 40);

/* Inscriptions */
/* Inscriptions Challenge 1 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (1,2,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,1);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (2,2,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,2);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (3,0,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,3);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (4,0,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,4);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (5,0,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,5);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (6,0,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,6);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (7,0,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,7);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (8,0,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,8);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (9,0,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,9);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (10,0,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,10);

/* Inscriptions Challenge 2 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (11,0,'https://',2);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,11);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (12,1,'https://',2);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,12);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (13,1,'https://',2);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,13);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (14,1,'https://',2);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,14);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (15,0,'https://',2);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,15);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (16,1,'https://',2);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,16);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (17,1,'https://',2);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,17);

/* Inscriptions Challenge 3 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (18,1,'https://',3);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,18);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (19,1,'https://',3);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,19);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (20,1,'https://',3);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,20);

/* Inscriptions Challenge 4 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (21,1,'https://',4);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,21);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (22,1,'https://',4);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,22);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (23,1,'https://',4);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,23);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (24,2,'https://',4);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,24);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (25,0,'https://',4);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,25);

/* Inscriptions Challenge 5 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (26,0,'https://',5);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,26);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (27,0,'https://',5);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,27);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (28,0,'https://',5);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,28);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (29,0,'https://',5);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,29);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (30,0,'https://',5);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,30);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (31,0,'https://',5);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,31);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (32,0,'https://',5);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,32);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (33,0,'https://',5);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,33);

/* Inscriptions Challenge 6 */

/* Inscriptions Challenge 7 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (34,3,'https://',7);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,34);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (35,3,'https://',7);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,35);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (36,1,'https://',7);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,36);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (37,1,'https://',7);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,37);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (38,1,'https://',7);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,38);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (39,1,'https://',7);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,39);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (40,2,'https://',7);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,40);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (41,2,'https://',7);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,41);

/* Inscriptions Challenge 8 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (42,1,'https://',8);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,42);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (43,1,'https://',8);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,43);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (44,1,'https://',8);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,44);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (45,1,'https://',8);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,45);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (46,1,'https://',8);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,46);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (47,1,'https://',8);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,47);

/* Inscriptions Challenge 9 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (48,1,'https://',9);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,48);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (49,0,'https://',9);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,49);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (50,0,'https://',9);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,50);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (51,2,'https://',9);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,51);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (52,2,'https://',9);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,52);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (53,2,'https://',9);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,53);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (54,2,'https://',9);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,54);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (55,0,'https://',9);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,55);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (56,2,'https://',9);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,56);

/* Inscriptions Challenge 10 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (57,3,'https://',10);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,57);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (58,3,'https://',10);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,58);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (59,3,'https://',10);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,59);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (60,3,'https://',10);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,60);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (61,3,'https://',10);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,61);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (62,0,'https://',10);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,62);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (63,0,'https://',10);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,63);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (64,0,'https://',10);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,64);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (65,0,'https://',10);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,65);

/* Inscriptions Challenge 11 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (66,0,'https://',11);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,66);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (67,3,'https://',11);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,67);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (68,0,'https://',11);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,68);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (69,0,'https://',11);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,69);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (70,0,'https://',11);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,70);

/* Inscriptions Challenge 12 */

/* Inscriptions Challenge 13 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (71,0,'https://',13);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,71);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (72,0,'https://',13);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,72);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (73,0,'https://',13);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,73);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (74,2,'https://',13);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,74);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (75,2,'https://',13);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,75);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (76,2,'https://',13);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,76);

/* Inscriptions Challenge 14 */

/* Inscriptions Challenge 15 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (77,2,'https://',15);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,77);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (78,2,'https://',15);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,78);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (79,2,'https://',15);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,79);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (80,1,'https://',15);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,80);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (81,2,'https://',15);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,81);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (82,2,'https://',15);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,82);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (83,0,'https://',15);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,83);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (84,0,'https://',15);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,84);

/* Inscriptions Challenge 16 */

/* Inscriptions Challenge 17 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (85,2,'https://',17);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,85);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (86,2,'https://',17);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,86);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (87,2,'https://',17);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,87);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (88,2,'https://',17);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,88);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (89,2,'https://',17);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,89);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (90,2,'https://',17);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,90);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (91,2,'https://',17);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,91);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (92,1,'https://',17);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,92);

/* Inscriptions Challenge 18 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (93,1,'https://',18);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,93);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (94,0,'https://',18);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,94);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (95,0,'https://',18);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,95);

/* Inscriptions Challenge 19 */

/* Inscriptions Challenge 20 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (96,0,'https://',20);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,96);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (97,0,'https://',20);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,97);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (98,0,'https://',20);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,98);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (99,2,'https://',20);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,99);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (100,2,'https://',20);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,100);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (101,2,'https://',20);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,101);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (102,2,'https://',20);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,102);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (103,2,'https://',20);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,103);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (104,2,'https://',20);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,104);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (105,2,'https://',20);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,105);

/* Inscriptions Challenge 21 */

/* Inscriptions Challenge 22 */

/* Inscriptions Challenge 23 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (106,2,'https://',23);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,106);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (107,2,'https://',23);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,107);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (108,2,'https://',23);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,108);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (109,2,'https://',23);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,109);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (110,2,'https://',23);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,110);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (111,2,'https://',23);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,111);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (112,2,'https://',23);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,112);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (113,3,'https://',23);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,113);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (114,3,'https://',23);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,114);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (115,3,'https://',23);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,115);

/* Inscriptions Challenge 24 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (116,3,'https://',24);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,116);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (117,3,'https://',24);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,117);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (118,3,'https://',24);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,118);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (119,3,'https://',24);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,119);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (120,3,'https://',24);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,120);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (121,3,'https://',24);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,121);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (122,3,'https://',24);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,122);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (123,3,'https://',24);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,123);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (124,3,'https://',24);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,124);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (125,3,'https://',24);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,125);

/* Inscriptions Challenge 25 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (126,3,'https://',25);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,126);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (127,3,'https://',25);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,127);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (128,3,'https://',25);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,128);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (129,3,'https://',25);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,129);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (130,3,'https://',25);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,130);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (131,3,'https://',25);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,131);

/* Inscriptions Challenge 26 */

/* Inscriptions Challenge 27 */

/* Inscriptions Challenge 28 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (132,3,'https://',28);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,132);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (133,3,'https://',28);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,133);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (134,3,'https://',28);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,134);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (135,3,'https://',28);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,135);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (136,3,'https://',28);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,136);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (137,3,'https://',28);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,137);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (138,3,'https://',28);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,138);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (139,1,'https://',28);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,139);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (140,1,'https://',28);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,140);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (141,1,'https://',28);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,141);

/* Inscriptions Challenge 29 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (142,1,'https://',29);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,142);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (143,1,'https://',29);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,143);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (144,1,'https://',29);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,144);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (145,1,'https://',29);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,145);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (146,0,'https://',29);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,146);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (147,0,'https://',29);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,147);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (148,0,'https://',29);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,148);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (149,0,'https://',29);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,149);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (150,0,'https://',29);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,150);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (151,0,'https://',29);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,151);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (152,0,'https://',29);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,152);

/* Inscriptions Challenge 30 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (153,3,'https://',30);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,153);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (154,3,'https://',30);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,154);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (155,0,'https://',30);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,155);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (156,0,'https://',30);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,156);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (157,0,'https://',30);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,157);

/* Inscriptions Challenge 31 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (158,0,'https://',31);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,158);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (159,0,'https://',31);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,159);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (160,0,'https://',31);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,160);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (161,0,'https://',31);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,161);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (162,0,'https://',31);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,162);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (163,0,'https://',31);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,163);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (164,2,'https://',31);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,164);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (165,2,'https://',31);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,165);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (166,2,'https://',31);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,166);

/* Inscriptions Challenge 32 */

/* Inscriptions Challenge 33 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (167,2,'https://',33);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,167);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (168,0,'https://',33);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,168);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (169,0,'https://',33);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,169);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (170,0,'https://',33);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,170);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (171,0,'https://',33);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,171);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (172,0,'https://',33);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,172);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (173,0,'https://',33);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,173);

/* Inscriptions Challenge 34 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (174,1,'https://',34);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,174);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (175,1,'https://',34);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,175);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (176,1,'https://',34);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,176);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (177,1,'https://',34);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,177);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (178,1,'https://',34);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,178);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (179,1,'https://',34);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,179);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (180,1,'https://',34);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,180);

/* Inscriptions Challenge 35 */

/* Inscriptions Challenge 36 */

/* Inscriptions Challenge 37 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (181,1,'https://',37);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,181);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (182,1,'https://',37);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,182);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (183,1,'https://',37);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,183);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (184,0,'https://',37);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,184);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (185,0,'https://',37);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,185);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (186,0,'https://',37);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,186);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (187,3,'https://',37);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,187);

/* Inscriptions Challenge 38 */

/* Inscriptions Challenge 39 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (188,3,'https://',39);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,188);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (189,3,'https://',39);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,189);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (190,1,'https://',39);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,190);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (191,1,'https://',39);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,191);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (192,1,'https://',39);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,192);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (193,1,'https://',39);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,193);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (194,1,'https://',39);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,194);

/* Inscriptions Challenge 40 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (195,0,'https://',40);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,195);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (196,0,'https://',40);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,196);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (197,0,'https://',40);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,197);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (198,0,'https://',40);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,198);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (199,0,'https://',40);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,199);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (200,0,'https://',40);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,200);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (201,0,'https://',40);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,201);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (202,1,'https://',40);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,202);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (203,3,'https://',40);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,203);

/* Inscriptions Challenge 41 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (204,3,'https://',41);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,204);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (205,3,'https://',41);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,205);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (206,3,'https://',41);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,206);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (207,3,'https://',41);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,207);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (208,3,'https://',41);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,208);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (209,3,'https://',41);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,209);

/* Inscriptions Challenge 42 */

/* Inscriptions Challenge 43 */

/* Inscriptions Challenge 44 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (210,3,'https://',44);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,210);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (211,3,'https://',44);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,211);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (212,3,'https://',44);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,212);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (213,3,'https://',44);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,213);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (214,1,'https://',44);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,214);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (215,1,'https://',44);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,215);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (216,1,'https://',44);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,216);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (217,1,'https://',44);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,217);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (218,1,'https://',44);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,218);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (219,1,'https://',44);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,219);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (220,1,'https://',44);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,220);

/* Inscriptions Challenge 45 */

/* Inscriptions Challenge 46 */

/* Inscriptions Challenge 47 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (221,1,'https://',47);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,221);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (222,1,'https://',47);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,222);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (223,1,'https://',47);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,223);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (224,3,'https://',47);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,224);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (225,3,'https://',47);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,225);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (226,3,'https://',47);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,226);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (227,0,'https://',47);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,227);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (228,0,'https://',47);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,228);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (229,0,'https://',47);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,229);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (230,0,'https://',47);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,230);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (231,0,'https://',47);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,231);

/* Inscriptions Challenge 48 */

/* Inscriptions Challenge 49 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (232,0,'https://',49);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,232);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (233,0,'https://',49);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,233);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (234,0,'https://',49);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,234);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (235,3,'https://',49);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,235);

/* Inscriptions Challenge 50 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (236,3,'https://',50);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,236);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (237,2,'https://',50);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,237);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (238,2,'https://',50);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,238);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (239,3,'https://',50);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,239);

/* Inscriptions Challenge 51 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (240,3,'https://',51);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,240);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (241,3,'https://',51);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,241);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (242,0,'https://',51);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,242);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (243,0,'https://',51);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,243);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (244,0,'https://',51);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,244);

/* Inscriptions Challenge 52 */

/* Inscriptions Challenge 53 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (245,0,'https://',53);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,245);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (246,0,'https://',53);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,246);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (247,0,'https://',53);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,247);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (248,3,'https://',53);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,248);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (249,3,'https://',53);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,249);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (250,3,'https://',53);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,250);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (251,3,'https://',53);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,251);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (252,3,'https://',53);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,252);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (253,2,'https://',53);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,253);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (254,2,'https://',53);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,254);

/* Inscriptions Challenge 54 */

/* Inscriptions Challenge 55 */

/* Inscriptions Challenge 56 */

/* Inscriptions Challenge 57 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (255,2,'https://',57);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,255);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (256,2,'https://',57);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,256);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (257,2,'https://',57);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,257);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (258,2,'https://',57);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,258);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (259,1,'https://',57);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,259);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (260,0,'https://',57);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,260);

/* Inscriptions Challenge 58 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (261,0,'https://',58);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,261);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (262,0,'https://',58);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,262);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (263,0,'https://',58);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,263);

/* Inscriptions Challenge 59 */

/* Inscriptions Challenge 60 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (264,1,'https://',60);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,264);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (265,3,'https://',60);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,265);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (266,3,'https://',60);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,266);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (267,3,'https://',60);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,267);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (268,3,'https://',60);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,268);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (269,3,'https://',60);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,269);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (270,3,'https://',60);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,270);

/* Inscriptions Challenge 61 */

/* Inscriptions Challenge 62 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (271,3,'https://',62);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,271);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (272,3,'https://',62);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,272);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (273,3,'https://',62);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,273);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (274,2,'https://',62);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,274);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (275,2,'https://',62);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,275);

/* Inscriptions Challenge 63 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (276,1,'https://',63);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,276);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (277,3,'https://',63);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,277);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (278,3,'https://',63);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,278);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (279,3,'https://',63);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,279);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (280,3,'https://',63);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,280);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (281,1,'https://',63);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,281);

/* Inscriptions Challenge 64 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (282,1,'https://',64);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,282);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (283,0,'https://',64);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,283);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (284,0,'https://',64);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,284);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (285,3,'https://',64);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,285);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (286,3,'https://',64);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,286);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (287,3,'https://',64);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,287);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (288,3,'https://',64);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,288);

/* Inscriptions Challenge 65 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (289,0,'https://',65);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,289);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (290,0,'https://',65);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,290);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (291,0,'https://',65);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,291);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (292,3,'https://',65);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,292);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (293,3,'https://',65);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,293);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (294,3,'https://',65);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,294);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (295,2,'https://',65);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,295);

/* Inscriptions Challenge 66 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (296,2,'https://',66);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,296);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (297,2,'https://',66);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,297);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (298,0,'https://',66);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,298);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (299,0,'https://',66);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,299);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (300,0,'https://',66);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,300);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (301,0,'https://',66);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,301);

/* Inscriptions Challenge 67 */

/* Inscriptions Challenge 68 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (302,0,'https://',68);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,302);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (303,0,'https://',68);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,303);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (304,0,'https://',68);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,304);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (305,0,'https://',68);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,305);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (306,0,'https://',68);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,306);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (307,0,'https://',68);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,307);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (308,2,'https://',68);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,308);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (309,2,'https://',68);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,309);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (310,2,'https://',68);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,310);

/* Inscriptions Challenge 69 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (311,2,'https://',69);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,311);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (312,2,'https://',69);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,312);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (313,2,'https://',69);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,313);

/* Inscriptions Challenge 70 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (314,2,'https://',70);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,314);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (315,2,'https://',70);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,315);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (316,2,'https://',70);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,316);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (317,3,'https://',70);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,317);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (318,3,'https://',70);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,318);

/* Inscriptions Challenge 71 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (319,1,'https://',71);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,319);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (320,1,'https://',71);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,320);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (321,1,'https://',71);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,321);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (322,1,'https://',71);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,322);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (323,1,'https://',71);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,323);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (324,1,'https://',71);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,324);

/* Inscriptions Challenge 72 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (325,2,'https://',72);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,325);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (326,2,'https://',72);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,326);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (327,2,'https://',72);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,327);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (328,0,'https://',72);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,328);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (329,1,'https://',72);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,329);

/* Inscriptions Challenge 73 */

/* Inscriptions Challenge 74 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (330,1,'https://',74);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,330);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (331,1,'https://',74);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,331);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (332,1,'https://',74);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,332);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (333,1,'https://',74);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,333);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (334,1,'https://',74);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,334);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (335,1,'https://',74);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,335);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (336,1,'https://',74);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,336);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (337,2,'https://',74);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,337);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (338,2,'https://',74);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,338);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (339,1,'https://',74);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,339);

/* Inscriptions Challenge 75 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (340,1,'https://',75);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,340);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (341,1,'https://',75);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,341);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (342,1,'https://',75);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,342);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (343,1,'https://',75);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,343);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (344,1,'https://',75);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,344);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (345,1,'https://',75);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,345);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (346,1,'https://',75);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,346);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (347,1,'https://',75);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,347);

/* Inscriptions Challenge 76 */

/* Inscriptions Challenge 77 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (348,1,'https://',77);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,348);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (349,1,'https://',77);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,349);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (350,1,'https://',77);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,350);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (351,1,'https://',77);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,351);

/* Inscriptions Challenge 78 */

/* Inscriptions Challenge 79 */

/* Inscriptions Challenge 80 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (352,3,'https://',80);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,352);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (353,0,'https://',80);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,353);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (354,1,'https://',80);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,354);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (355,1,'https://',80);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,355);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (356,1,'https://',80);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,356);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (357,1,'https://',80);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,357);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (358,1,'https://',80);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,358);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (359,0,'https://',80);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,359);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (360,0,'https://',80);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,360);

/* Inscriptions Challenge 81 */

/* Inscriptions Challenge 82 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (361,0,'https://',82);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,361);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (362,0,'https://',82);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,362);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (363,0,'https://',82);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,363);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (364,0,'https://',82);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,364);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (365,0,'https://',82);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,365);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (366,0,'https://',82);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,366);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (367,1,'https://',82);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,367);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (368,1,'https://',82);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,368);

/* Inscriptions Challenge 83 */

/* Inscriptions Challenge 84 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (369,3,'https://',84);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,369);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (370,3,'https://',84);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,370);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (371,1,'https://',84);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,371);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (372,3,'https://',84);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,372);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (373,3,'https://',84);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,373);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (374,0,'https://',84);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,374);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (375,0,'https://',84);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,375);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (376,3,'https://',84);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,376);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (377,3,'https://',84);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,377);

/* Inscriptions Challenge 85 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (378,3,'https://',85);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,378);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (379,3,'https://',85);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,379);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (380,3,'https://',85);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,380);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (381,1,'https://',85);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,381);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (382,1,'https://',85);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,382);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (383,1,'https://',85);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,383);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (384,1,'https://',85);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,384);

/* Inscriptions Challenge 86 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (385,1,'https://',86);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,385);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (386,1,'https://',86);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,386);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (387,1,'https://',86);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,387);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (388,1,'https://',86);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,388);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (389,1,'https://',86);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,389);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (390,0,'https://',86);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,390);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (391,0,'https://',86);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,391);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (392,0,'https://',86);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,392);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (393,0,'https://',86);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,393);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (394,2,'https://',86);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,394);

/* Inscriptions Challenge 87 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (395,0,'https://',87);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,395);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (396,0,'https://',87);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,396);

/* Inscriptions Challenge 88 */

/* Inscriptions Challenge 89 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (397,0,'https://',89);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,397);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (398,0,'https://',89);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,398);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (399,1,'https://',89);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,399);

/* Inscriptions Challenge 90 */

/* Inscriptions Challenge 91 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (400,1,'https://',91);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,400);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (401,1,'https://',91);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,401);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (402,1,'https://',91);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,402);

/* Inscriptions Challenge 92 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (403,1,'https://',92);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,403);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (404,1,'https://',92);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,404);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (405,1,'https://',92);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,405);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (406,1,'https://',92);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,406);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (407,1,'https://',92);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,407);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (408,1,'https://',92);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,408);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (409,1,'https://',92);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,409);

/* Inscriptions Challenge 93 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (410,1,'https://',93);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,410);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (411,1,'https://',93);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,411);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (412,1,'https://',93);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,412);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (413,1,'https://',93);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,413);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (414,1,'https://',93);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,414);

/* Inscriptions Challenge 94 */

/* Inscriptions Challenge 95 */

/* Inscriptions Challenge 96 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (415,0,'https://',96);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,415);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (416,0,'https://',96);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,416);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (417,0,'https://',96);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,417);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (418,1,'https://',96);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,418);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (419,1,'https://',96);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,419);

/* Inscriptions Challenge 97 */

/* Inscriptions Challenge 98 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (420,1,'https://',98);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,420);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (421,1,'https://',98);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,421);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (422,0,'https://',98);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,422);

/* Inscriptions Challenge 99 */

/* Inscriptions Challenge 100 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (423,0,'https://',100);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,423);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (424,0,'https://',100);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,424);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (425,0,'https://',100);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,425);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (426,0,'https://',100);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,426);

/* Inscriptions Challenge 101 */

/* Inscriptions Challenge 102 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (427,0,'https://',102);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,427);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (428,0,'https://',102);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,428);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (429,0,'https://',102);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,429);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (430,0,'https://',102);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,430);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (431,0,'https://',102);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,431);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (432,0,'https://',102);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,432);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (433,0,'https://',102);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,433);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (434,0,'https://',102);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,434);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (435,3,'https://',102);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,435);

/* Inscriptions Challenge 103 */

/* Inscriptions Challenge 104 */

/* Inscriptions Challenge 105 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (436,3,'https://',105);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,436);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (437,3,'https://',105);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,437);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (438,3,'https://',105);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,438);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (439,3,'https://',105);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,439);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (440,3,'https://',105);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,440);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (441,3,'https://',105);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,441);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (442,0,'https://',105);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,442);

/* Inscriptions Challenge 106 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (443,0,'https://',106);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,443);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (444,0,'https://',106);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,444);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (445,0,'https://',106);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,445);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (446,0,'https://',106);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,446);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (447,0,'https://',106);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,447);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (448,0,'https://',106);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,448);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (449,0,'https://',106);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,449);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (450,0,'https://',106);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,450);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (451,3,'https://',106);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,451);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (452,3,'https://',106);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,452);

/* Inscriptions Challenge 107 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (453,3,'https://',107);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,453);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (454,3,'https://',107);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,454);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (455,3,'https://',107);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,455);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (456,3,'https://',107);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,456);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (457,3,'https://',107);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,457);

/* Inscriptions Challenge 108 */

/* Inscriptions Challenge 109 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (458,3,'https://',109);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,458);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (459,3,'https://',109);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,459);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (460,1,'https://',109);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,460);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (461,3,'https://',109);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,461);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (462,3,'https://',109);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,462);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (463,3,'https://',109);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,463);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (464,3,'https://',109);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,464);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (465,3,'https://',109);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,465);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (466,0,'https://',109);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,466);

/* Inscriptions Challenge 110 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (467,0,'https://',110);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,467);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (468,0,'https://',110);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,468);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (469,0,'https://',110);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,469);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (470,0,'https://',110);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,470);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (471,0,'https://',110);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,471);

/* Inscriptions Challenge 111 */

/* Inscriptions Challenge 112 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (472,0,'https://',112);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,472);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (473,0,'https://',112);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,473);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (474,0,'https://',112);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,474);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (475,0,'https://',112);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,475);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (476,0,'https://',112);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,476);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (477,3,'https://',112);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,477);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (478,3,'https://',112);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,478);

/* Inscriptions Challenge 113 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (479,3,'https://',113);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,479);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (480,2,'https://',113);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,480);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (481,0,'https://',113);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,481);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (482,0,'https://',113);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,482);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (483,0,'https://',113);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,483);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (484,0,'https://',113);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,484);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (485,0,'https://',113);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,485);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (486,0,'https://',113);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,486);

/* Inscriptions Challenge 114 */

/* Inscriptions Challenge 115 */

/* Inscriptions Challenge 116 */

/* Inscriptions Challenge 117 */

/* Inscriptions Challenge 118 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (487,0,'https://',118);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,487);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (488,0,'https://',118);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,488);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (489,0,'https://',118);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,489);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (490,0,'https://',118);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,490);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (491,0,'https://',118);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,491);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (492,0,'https://',118);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,492);

/* Inscriptions Challenge 119 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (493,0,'https://',119);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,493);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (494,2,'https://',119);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,494);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (495,2,'https://',119);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,495);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (496,2,'https://',119);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,496);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (497,1,'https://',119);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,497);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (498,1,'https://',119);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,498);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (499,3,'https://',119);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,499);

/* Inscriptions Challenge 120 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (500,3,'https://',120);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,500);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (501,3,'https://',120);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,501);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (502,0,'https://',120);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,502);

/* Inscriptions Challenge 121 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (503,0,'https://',121);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,503);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (504,0,'https://',121);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,504);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (505,0,'https://',121);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,505);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (506,0,'https://',121);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,506);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (507,0,'https://',121);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,507);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (508,0,'https://',121);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,508);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (509,0,'https://',121);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,509);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (510,0,'https://',121);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,510);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (511,0,'https://',121);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,511);

/* Inscriptions Challenge 122 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (512,2,'https://',122);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,512);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (513,2,'https://',122);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,513);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (514,1,'https://',122);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,514);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (515,1,'https://',122);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,515);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (516,1,'https://',122);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,516);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (517,3,'https://',122);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,517);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (518,3,'https://',122);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,518);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (519,3,'https://',122);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,519);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (520,3,'https://',122);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,520);

/* Inscriptions Challenge 123 */

/* Inscriptions Challenge 124 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (521,3,'https://',124);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,521);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (522,3,'https://',124);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,522);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (523,3,'https://',124);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,523);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (524,3,'https://',124);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,524);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (525,3,'https://',124);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,525);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (526,3,'https://',124);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,526);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (527,3,'https://',124);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,527);

/* Inscriptions Challenge 125 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (528,3,'https://',125);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,528);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (529,3,'https://',125);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,529);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (530,3,'https://',125);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,530);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (531,3,'https://',125);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,531);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (532,3,'https://',125);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,532);

/* Inscriptions Challenge 126 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (533,3,'https://',126);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,533);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (534,3,'https://',126);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,534);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (535,3,'https://',126);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,535);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (536,3,'https://',126);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,536);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (537,3,'https://',126);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,537);

/* Inscriptions Challenge 127 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (538,3,'https://',127);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,538);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (539,3,'https://',127);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,539);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (540,3,'https://',127);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,540);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (541,3,'https://',127);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,541);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (542,3,'https://',127);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,542);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (543,3,'https://',127);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,543);

/* Inscriptions Challenge 128 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (544,3,'https://',128);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,544);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (545,3,'https://',128);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,545);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (546,0,'https://',128);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,546);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (547,0,'https://',128);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,547);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (548,1,'https://',128);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,548);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (549,1,'https://',128);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,549);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (550,3,'https://',128);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,550);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (551,2,'https://',128);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,551);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (552,2,'https://',128);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,552);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (553,2,'https://',128);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,553);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (554,3,'https://',128);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,554);

/* Inscriptions Challenge 129 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (555,3,'https://',129);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,555);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (556,3,'https://',129);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,556);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (557,3,'https://',129);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,557);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (558,0,'https://',129);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,558);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (559,0,'https://',129);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,559);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (560,0,'https://',129);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,560);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (561,0,'https://',129);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,561);

/* Inscriptions Challenge 130 */

/* Inscriptions Challenge 131 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (562,0,'https://',131);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,562);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (563,0,'https://',131);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,563);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (564,0,'https://',131);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,564);

/* Inscriptions Challenge 132 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (565,0,'https://',132);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,565);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (566,0,'https://',132);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,566);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (567,0,'https://',132);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,567);

/* Inscriptions Challenge 133 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (568,2,'https://',133);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,568);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (569,2,'https://',133);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,569);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (570,2,'https://',133);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,570);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (571,2,'https://',133);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,571);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (572,2,'https://',133);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,572);

/* Inscriptions Challenge 134 */

/* Inscriptions Challenge 135 */

/* Inscriptions Challenge 136 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (573,2,'https://',136);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,573);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (574,2,'https://',136);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,574);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (575,2,'https://',136);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,575);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (576,2,'https://',136);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,576);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (577,2,'https://',136);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,577);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (578,1,'https://',136);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,578);

/* Inscriptions Challenge 137 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (579,1,'https://',137);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,579);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (580,0,'https://',137);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,580);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (581,0,'https://',137);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,581);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (582,1,'https://',137);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,582);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (583,2,'https://',137);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,583);

/* Inscriptions Challenge 138 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (584,2,'https://',138);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,584);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (585,2,'https://',138);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,585);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (586,2,'https://',138);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,586);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (587,2,'https://',138);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,587);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (588,0,'https://',138);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,588);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (589,0,'https://',138);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,589);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (590,0,'https://',138);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,590);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (591,3,'https://',138);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,591);

/* Inscriptions Challenge 139 */

/* Inscriptions Challenge 140 */

/* Inscriptions Challenge 141 */

/* Inscriptions Challenge 142 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (592,3,'https://',142);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,592);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (593,3,'https://',142);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,593);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (594,3,'https://',142);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,594);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (595,3,'https://',142);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,595);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (596,3,'https://',142);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,596);

/* Inscriptions Challenge 143 */

/* Inscriptions Challenge 144 */

/* Inscriptions Challenge 145 */

/* Inscriptions Challenge 146 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (597,3,'https://',146);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,597);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (598,3,'https://',146);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,598);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (599,3,'https://',146);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,599);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (600,2,'https://',146);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,600);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (601,0,'https://',146);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,601);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (602,0,'https://',146);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,602);

/* Inscriptions Challenge 147 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (603,0,'https://',147);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,603);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (604,0,'https://',147);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,604);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (605,0,'https://',147);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,605);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (606,0,'https://',147);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,606);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (607,3,'https://',147);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,607);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (608,3,'https://',147);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,608);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (609,3,'https://',147);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,609);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (610,3,'https://',147);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,610);

/* Inscriptions Challenge 148 */

/* Inscriptions Challenge 149 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (611,3,'https://',149);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,611);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (612,3,'https://',149);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,612);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (613,3,'https://',149);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,613);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (614,2,'https://',149);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,614);

/* Inscriptions Challenge 150 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (615,3,'https://',150);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,615);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (616,3,'https://',150);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,616);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (617,3,'https://',150);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,617);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (618,3,'https://',150);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,618);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (619,0,'https://',150);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,619);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (620,0,'https://',150);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,620);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (621,0,'https://',150);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,621);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (622,0,'https://',150);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,622);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (623,0,'https://',150);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,623);

/* Inscriptions Challenge 151 */

/* Inscriptions Challenge 152 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (624,0,'https://',152);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,624);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (625,0,'https://',152);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,625);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (626,0,'https://',152);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,626);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (627,0,'https://',152);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,627);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (628,0,'https://',152);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,628);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (629,0,'https://',152);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,629);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (630,0,'https://',152);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,630);

/* Inscriptions Challenge 153 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (631,2,'https://',153);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,631);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (632,2,'https://',153);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,632);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (633,1,'https://',153);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,633);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (634,2,'https://',153);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,634);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (635,2,'https://',153);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,635);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (636,2,'https://',153);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,636);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (637,2,'https://',153);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,637);

/* Inscriptions Challenge 154 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (638,0,'https://',154);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,638);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (639,3,'https://',154);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,639);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (640,3,'https://',154);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,640);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (641,3,'https://',154);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,641);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (642,3,'https://',154);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,642);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (643,3,'https://',154);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,643);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (644,2,'https://',154);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,644);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (645,3,'https://',154);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,645);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (646,3,'https://',154);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,646);

/* Inscriptions Challenge 155 */

/* Inscriptions Challenge 156 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (647,3,'https://',156);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,647);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (648,0,'https://',156);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,648);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (649,0,'https://',156);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,649);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (650,0,'https://',156);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,650);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (651,0,'https://',156);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,651);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (652,0,'https://',156);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,652);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (653,3,'https://',156);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,653);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (654,3,'https://',156);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,654);

/* Inscriptions Challenge 157 */

/* Inscriptions Challenge 158 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (655,3,'https://',158);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,655);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (656,3,'https://',158);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,656);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (657,3,'https://',158);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,657);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (658,3,'https://',158);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,658);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (659,2,'https://',158);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,659);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (660,2,'https://',158);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,660);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (661,2,'https://',158);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,661);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (662,2,'https://',158);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,662);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (663,2,'https://',158);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,663);

/* Inscriptions Challenge 159 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (664,2,'https://',159);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,664);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (665,2,'https://',159);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,665);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (666,2,'https://',159);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,666);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (667,3,'https://',159);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,667);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (668,3,'https://',159);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,668);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (669,3,'https://',159);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,669);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (670,3,'https://',159);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,670);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (671,3,'https://',159);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,671);

/* Inscriptions Challenge 160 */

/* Inscriptions Challenge 161 */

/* Inscriptions Challenge 162 */

/* Inscriptions Challenge 163 */

/* Inscriptions Challenge 164 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (672,1,'https://',164);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,672);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (673,1,'https://',164);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,673);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (674,3,'https://',164);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,674);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (675,3,'https://',164);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,675);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (676,3,'https://',164);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,676);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (677,3,'https://',164);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,677);

/* Inscriptions Challenge 165 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (678,3,'https://',165);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,678);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (679,0,'https://',165);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,679);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (680,0,'https://',165);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,680);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (681,0,'https://',165);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,681);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (682,2,'https://',165);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,682);

/* Inscriptions Challenge 166 */

/* Inscriptions Challenge 167 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (683,1,'https://',167);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,683);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (684,0,'https://',167);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,684);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (685,0,'https://',167);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,685);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (686,0,'https://',167);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,686);

/* Inscriptions Challenge 168 */

/* Inscriptions Challenge 169 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (687,0,'https://',169);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,687);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (688,0,'https://',169);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,688);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (689,1,'https://',169);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,689);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (690,3,'https://',169);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,690);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (691,3,'https://',169);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,691);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (692,3,'https://',169);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,692);

/* Inscriptions Challenge 170 */

/* Inscriptions Challenge 171 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (693,3,'https://',171);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,693);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (694,3,'https://',171);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,694);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (695,3,'https://',171);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,695);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (696,2,'https://',171);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,696);

/* Inscriptions Challenge 172 */

/* Inscriptions Challenge 173 */

/* Inscriptions Challenge 174 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (697,2,'https://',174);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,697);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (698,2,'https://',174);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,698);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (699,2,'https://',174);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,699);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (700,2,'https://',174);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,700);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (701,1,'https://',174);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,701);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (702,1,'https://',174);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,702);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (703,1,'https://',174);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,703);

/* Inscriptions Challenge 175 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (704,1,'https://',175);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,704);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (705,1,'https://',175);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,705);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (706,1,'https://',175);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,706);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (707,0,'https://',175);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,707);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (708,2,'https://',175);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,708);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (709,3,'https://',175);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,709);

/* Inscriptions Challenge 176 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (710,3,'https://',176);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,710);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (711,3,'https://',176);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,711);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (712,3,'https://',176);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,712);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (713,3,'https://',176);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,713);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (714,3,'https://',176);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,714);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (715,3,'https://',176);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,715);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (716,3,'https://',176);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,716);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (717,3,'https://',176);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,717);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (718,1,'https://',176);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,718);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (719,1,'https://',176);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,719);

/* Inscriptions Challenge 177 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (720,1,'https://',177);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,720);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (721,1,'https://',177);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,721);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (722,0,'https://',177);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,722);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (723,0,'https://',177);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,723);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (724,1,'https://',177);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,724);

/* Inscriptions Challenge 178 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (725,1,'https://',178);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,725);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (726,1,'https://',178);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,726);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (727,2,'https://',178);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,727);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (728,2,'https://',178);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,728);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (729,3,'https://',178);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,729);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (730,2,'https://',178);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,730);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (731,1,'https://',178);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,731);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (732,1,'https://',178);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,732);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (733,1,'https://',178);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,733);

/* Inscriptions Challenge 179 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (734,1,'https://',179);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,734);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (735,0,'https://',179);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,735);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (736,2,'https://',179);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,736);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (737,2,'https://',179);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,737);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (738,0,'https://',179);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,738);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (739,0,'https://',179);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,739);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (740,0,'https://',179);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,740);

/* Inscriptions Challenge 180 */

/* Inscriptions Challenge 181 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (741,0,'https://',181);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,741);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (742,0,'https://',181);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,742);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (743,0,'https://',181);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,743);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (744,0,'https://',181);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,744);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (745,0,'https://',181);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,745);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (746,3,'https://',181);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,746);

/* Inscriptions Challenge 182 */

/* Inscriptions Challenge 183 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (747,3,'https://',183);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,747);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (748,3,'https://',183);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,748);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (749,0,'https://',183);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,749);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (750,1,'https://',183);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,750);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (751,1,'https://',183);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,751);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (752,1,'https://',183);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,752);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (753,1,'https://',183);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,753);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (754,1,'https://',183);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,754);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (755,1,'https://',183);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,755);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (756,1,'https://',183);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,756);

/* Inscriptions Challenge 184 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (757,1,'https://',184);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,757);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (758,3,'https://',184);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,758);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (759,2,'https://',184);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,759);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (760,2,'https://',184);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,760);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (761,2,'https://',184);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,761);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (762,1,'https://',184);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,762);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (763,1,'https://',184);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,763);

/* Inscriptions Challenge 185 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (764,1,'https://',185);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,764);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (765,1,'https://',185);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,765);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (766,1,'https://',185);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,766);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (767,1,'https://',185);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,767);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (768,1,'https://',185);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,768);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (769,1,'https://',185);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,769);

/* Inscriptions Challenge 186 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (770,1,'https://',186);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,770);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (771,1,'https://',186);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,771);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (772,1,'https://',186);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,772);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (773,1,'https://',186);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,773);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (774,1,'https://',186);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,774);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (775,3,'https://',186);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,775);

/* Inscriptions Challenge 187 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (776,3,'https://',187);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,776);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (777,3,'https://',187);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,777);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (778,3,'https://',187);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,778);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (779,3,'https://',187);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,779);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (780,3,'https://',187);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,780);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (781,3,'https://',187);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,781);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (782,3,'https://',187);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,782);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (783,0,'https://',187);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,783);

/* Inscriptions Challenge 188 */

/* Inscriptions Challenge 189 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (784,2,'https://',189);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,784);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (785,1,'https://',189);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,785);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (786,1,'https://',189);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,786);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (787,1,'https://',189);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,787);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (788,1,'https://',189);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,788);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (789,1,'https://',189);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,789);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (790,1,'https://',189);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,790);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (791,2,'https://',189);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,791);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (792,2,'https://',189);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,792);

/* Inscriptions Challenge 190 */

/* Inscriptions Challenge 191 */

/* Inscriptions Challenge 192 */

/* Inscriptions Challenge 193 */

/* Inscriptions Challenge 194 */

/* Inscriptions Challenge 195 */

/* Inscriptions Challenge 196 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (793,2,'https://',196);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,793);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (794,2,'https://',196);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,794);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (795,2,'https://',196);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,795);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (796,2,'https://',196);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,796);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (797,2,'https://',196);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,797);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (798,1,'https://',196);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,798);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (799,0,'https://',196);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,799);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (800,0,'https://',196);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,800);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (801,0,'https://',196);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,801);

/* Inscriptions Challenge 197 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (802,0,'https://',197);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,802);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (803,1,'https://',197);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,803);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (804,1,'https://',197);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,804);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (805,3,'https://',197);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,805);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (806,3,'https://',197);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,806);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (807,1,'https://',197);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,807);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (808,3,'https://',197);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,808);

/* Inscriptions Challenge 198 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (809,3,'https://',198);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,809);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (810,3,'https://',198);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,810);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (811,3,'https://',198);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,811);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (812,1,'https://',198);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,812);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (813,0,'https://',198);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,813);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (814,1,'https://',198);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,814);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (815,3,'https://',198);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,815);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (816,3,'https://',198);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,816);

/* Inscriptions Challenge 199 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (817,3,'https://',199);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,817);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (818,3,'https://',199);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,818);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (819,3,'https://',199);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,819);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (820,3,'https://',199);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,820);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (821,3,'https://',199);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,821);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (822,3,'https://',199);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,822);

/* Inscriptions Challenge 200 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (823,3,'https://',200);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,823);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (824,2,'https://',200);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,824);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (825,2,'https://',200);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,825);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (826,2,'https://',200);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,826);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (827,2,'https://',200);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,827);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (828,2,'https://',200);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (7,828);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (829,2,'https://',200);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,829);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (830,2,'https://',200);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,830);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (831,3,'https://',200);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,831);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (832,3,'https://',200);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,832);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (833,0,'https://',200);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,833);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (834,0,'https://',200);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,834);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (835,1,'https://',200);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,835);


