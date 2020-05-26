/* EQUIPMENT */

INSERT INTO equipments(id,name,location) VALUES (1,'Squat-Calf','Muscle and Tonification');
INSERT INTO equipments(id,name,location) VALUES (2,'Bars 30-50 mm','Muscle and Tonification');
INSERT INTO equipments(id,name,location) VALUES (3,'Weight-Disc 30-50 mm','Muscle and Tonification');
INSERT INTO equipments(id,name,location) VALUES (4,'Dumbbells','Muscle and Tonification');
INSERT INTO equipments(id,name,location) VALUES (5,'Kettlebells','Muscle and Tonification');
INSERT INTO equipments(id,name,location) VALUES (6,'Treadmill','Cardio');
INSERT INTO equipments(id,name,location) VALUES (7,'Elliptical','Cardio');
INSERT INTO equipments(id,name,location) VALUES (8,'Spin-Bike','Cardio');
INSERT INTO equipments(id,name,location) VALUES (9,'Stair-Mill','Cardio');
INSERT INTO equipments(id,name,location) VALUES (10,'Rowing Machine','Cardio');

/* EXERCISE */

/* Equipment 1 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (1,'Squat','Crouch and stand up', 100,1,1,2,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (2,'Pistol Squat','Crouch and stand up with one leg. Alter legs', 100,3,1,2,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (3,'Calf Raises','Stand on your tiptoes. Support weight with both legs',100,1,1,3,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (4,'Single Leg Calf Raises','Stand on your tiptoes with one leg. Just support your weight with one log. Alter legs', 100,2,1,3,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (5,'Lunges','Take a step behind with one leg, then shift your weight down to kneel. Alter legs',100,3,1,2,2);

/* Equipment 2 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (6,'Squat','Crouch and stand up', 100, 2, 2,4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (7,'Pistol Squat','Crouch and stand up with one leg. Alter legs', 100, 2, 2,4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (8,'Calf Raises','Stand on your tiptoes. Support weight with both legs', 100, 2, 2,4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (9,'Single Leg Calf Raises','Stand on your tiptoes with one leg. Just support your weight with one log. Alter legs', 100, 2, 2, 4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (10,'Lunges','Take a step behind with one leg, then shift your weight down to kneel. Alter legs', 100, 2, 2, 4, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (11,'Curtsy Lunges','Normal Lunge but crossing legs', 100, 2,2,4, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (12,'Dead Lift','Normal Lunge but crossing legs', 100, 2, 2, 4, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (13,'Bent-Over Row','Bend your back forward. Bend knees a little bit and hold the bar horizontally at your hips. Extend your arms straight down and then up again', 100, 2, 2,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (14,'Single Arm Row','Like Bent-Over Row, but positioning varies. Back bend forward and one knee forward. One arm hold the forward knee, the other hold the bar vertically.  Extend your arm straight down and then up to the hip', 100, 2, 2,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (15,'Biceps Curl','Hold bar horizontally with both hands. Bend your arms to your chest', 100, 2, 2,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (16,'Shoulder Press','Knees a bit bend and your a bit separated. Hold bar horizontally up your chest. Extends your arms right above your head', 100, 2, 2,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (17,'Chest Press','Lie on the ground. Hold bar horizontally to your chest. Kness bent and feet planted on the floor. Extends your arms straight up and then down', 100, 2, 2,0,2);

/* Equipment 3 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (18,'Plank Disc Switch','Start by doing a plank. Place one disc next to one arm. Then move the disc next to the grabbing arm. Now move the disc to the starting place an repeat',100,2,3,0,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (19,'Disc Push Up','Make a Push up having under your hands discs. The more disc the difficultier',100,2,3,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (20,'Front Shoulder Raise','Hold the disc with both hands with your arms straight forward and the disc at chest height. Like a pendulum raise down the disc until your reach your heap. Then go up and repeat',100,2,3,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (21,'Lateral Raise','Grab with each hand a disc. Start with them at your hips, then rise up your arms until they are horizontal to the floor. Return to starting position and repeat',100,2,3,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (22,'Halo','Hold one disc with both hands with your arms slightly bend in fron of you at head height. Move the disc all around your head and return to starting position, then repeat',100,2,3,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (23,'Squat Press','Start at a crouching position holding a disc with both hands and support your hands with your knees. Stand up and hold the disc above your head. Return to starting position and repeat',100,2,3,4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (24,'Squat Reach','Start at a crouching position holding a disc with both hands having you arms straighten and the disc at chest height. Stand up and bend arms until the disc touches your chest. Return to starting position and repeat',100,2,3,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (25,'Side Bend','Hold a disc with one hand, the other at your hip. Slightly bend your upper trunk towards the disc side and then straighten up. Repeat and alternate the arm supporting the disc',100,2,3,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (26,'Overhead Press','Hold a disc with both hands and horizontal to the floor. Disc should be at neck height, then move it above your head with extended arms. Return to starting position and repeat',100,2,3,1,2);

/* Equipment 4 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (27,'Goblet Squat','Standing up, with your feet separated widely (distance should be wider than shoulders distance). Grab a dumbbell with both hands and make a squat. Return to starting position and repeat',100,2,4,3,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (28,'Dumbbell Clean','Start crouching. Both hands hold each a dumbell. Stand up and flip your wrist so now your holding the dumbbells at shoulder height. Then procede to make a squat and flip wrists again. Repeat',100,2,4,4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (29,'Bent-Over Row','Grab a dumbbell with each hand. Bend knees a bit and bend your back forward. Extend your hands to hip height, then bend arms until you reach your chest. Return to starting position and repeat',100,2,4,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (30,'One Arm Swing','One hand holds a dumbell. Start with knees bend, feet separated at shoulder width and arm extended between legs. Stand up straight and pull your arm up like a pendulum untill you reach head height. Return to starting position and repeat',100,2,4,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (31,'Dumbbell Bench Press','Lie on a flat bench holding two dumbbells over your chest with an overhand grip. Push up until your arms are straight, then lower under control',100,2,4,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (32,'Cross Body Hammer Curl','Stand up with two dumbbells, one at each hand. One at a time, curl each weight up towards your opposing shoulder. Return under control to the start position and repeat on the other side',100,2,4,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (33,'Steps-ups','Stand still with each hand grabbing a dumbbell. Stand with one feet on the elevated source, then move the other feet up and stand on it. Return to starting position one feet at a time and repeat',100,2,4,3,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (34,'Dumbbell Scaption','Standing up grab a dumbbell with each hand and raise your arms along the side untill both dumbbells are above shoulder height. Return to starting position and repeat',100,2,4,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (35,'Dumbell Lunge','Grab with each hand a dumbbell. Make one step, and then squat. Return to starting position, alter stepping feet and repeat',100,2,4,2,2);

/* Equipment 5 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (36,'Kettlebell Swing','Knees a bit bend forward. Feet separated a bit more then shoulder distance. Grab kettle with both hands and like a pendulum movemente move the kettle to chest height. Return to starting position and repeat',100,2,5,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (37,'Kettlebell One Arm Swing','Same as Kettlebell Swing but with one arm',100,2,5,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (38,'Kettlebell Thrusters','Each hand with a kettle. Squat and when your all standing, extend your arm fully up above your head. Return to starting position and repeat',100,2,5,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (39,'Kettlebell Clean and Press','Stand holding two kettlebells by your thighs, knees slightly bent and legs shoulder-width apart. In one swift movement, slightly jump off the ground and raise your arms to extend above your head',100,2,5,4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (40,'Kettlebell Snatch','Holding a kettlebell in one hand between your legs, squat down. Drive upwards through your hips and knees as the kettlebell rises to shoulder height. Return to starting position and repeat',100,2,5,4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (41,'Kettlebell Pistol Squad','Hold one kettlebell with both hands just under your chin. Lift one leg off the floor and squat down with the other. Return to starting position and repeat',100,2,5,3,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (42,'Kettlebell Gobel Squat','Stand with your legs slightly wider than shoulder-width apart, clasping a kettlebell in each hand in front of your chest with palms facing each other. Bend your knees and lower yourself into a squat. Return to starting position and repeat.',100,2,5,3,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (43,'Kettlebell Shoulder Press','Stand with your feet shoulder-width apart holding two kettlebells at shoulder height. Press one of the weights above your head until your arm is fully extended. Lower and repeat with the other arm.',100,2,5,1,2);

/* Equipment 6 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (44,'Sprint','Run at full speed',100,2,6,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (45,'Jogging','Run at a speed that you could maintain "forever"',100,2,6,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (46,'Walking','Walk at a suitable speed',100,2,6,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (47,'Course-Navette','Alternate between high intensity running (85%) and medium-low intensity (60%). 1:30 min at 85% and 3:30 at 60% ',100,2,6,4,0);

/* Equipment 7 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (48,'Elliptical Max Speed','Use the Ellipitical at the higuest pace you can keep',100,2,7,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (49,'Elliptical Med Speed','Use the Ellipitical at a pace you can keep "forever"',100,2,7,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (50,'Elliptical Min Speed','Use the Ellipitical at a pace sutiable for you and fairly slow',100,2,7,4,0);

/* Equipment 8 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (51,'Sprint','Bike at the maximum speed you can handle',100,2,8,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (52,'Standing','Bike standing up and keep it up.',100,2,8,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (53,'Normal','Bike at a pace you could keep "forever"',100,2,8,4,0);

/* Equipment 9 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (54,'Normal Steps','Step on step at a time',100,2,9,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (55,'Skipping Steps','Step skipping one or two at a time',100,2,9,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (56,'Side Steps','Like the name says rotate yourself 90º and then step the steps',100,2,9,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (57,'Speed Steps','Step the fastest you can without skipping steps',100,2,9,4,0);

/* Equipment 10 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (58,'Rowing Max Intesity','Row at the fastest pace you can keeping a controlled movement. In this exercise technique is key, more than speed',100,2,10,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (59,'Rowing Med Intesity','Row at a pace you can keep "forever" without sacrificing technique',100,2,10,4,0);

/* No equipment*/
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (60,'Abs','Lie on the floor looking upwards. Bend knees and rotate your upper trunk to your knees. Return to starting position and repeat',100,2,null,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (61,'Plank','Lie on the floor looking downwards. Support yourself only with your bend forearms and feet. Keep a straight position',100,2,null,0,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (62,'Push ups','Lying with the face down by raising and lowering the body with the straightening and bending of the arms',100,2,null,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (63,'Squats','Crouch and stand up',100,2,null,3,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (64,'Burpees','First make a squat then place the palms of the hands on the floor in front of the feet and finally jumps back into a push-up position',100,2,null,4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (65,'Jumping Jacks','First start by jumping to a position with legs spread and arms raised and then return to the original position',100,2,null,4,2);

/* Administrators */
INSERT INTO users(username, password, enabled) VALUES ('admin1', 'admin1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (1, 'admin', 'admin1');
INSERT INTO authorities(id, authority, username) VALUES (2, 'client', 'admin1');
INSERT INTO admins(id, email, first_name, last_name, nif, username) VALUES (1, 'admin0@yogoym.com','AdminName1', 'AdminSurname', '10000000A','admin1');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (1, 'client1@yogoym.com','AdminClientName1', 'AdminClientSurname', '10000000A', 49,0.8692024,1.80, false, 80, null,'admin1');

INSERT INTO users(username, password, enabled) VALUES ('admin2', 'admin1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (3, 'admin', 'admin2');
INSERT INTO authorities(id, authority, username) VALUES (4, 'client', 'admin2');
INSERT INTO admins(id, email, first_name, last_name, nif, username) VALUES (2, 'admin1@yogoym.com','AdminName2', 'AdminSurname', '10000001A','admin2');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (2, 'client2@yogoym.com','AdminClientName2', 'AdminClientSurname', '10000001A', 35,0.22877467,1.80, true, 80, null,'admin2');

INSERT INTO users(username, password, enabled) VALUES ('admin3', 'admin1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (5, 'admin', 'admin3');
INSERT INTO authorities(id, authority, username) VALUES (6, 'client', 'admin3');
INSERT INTO admins(id, email, first_name, last_name, nif, username) VALUES (3, 'admin2@yogoym.com','AdminName3', 'AdminSurname', '10000002A','admin3');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (3, 'client3@yogoym.com','AdminClientName3', 'AdminClientSurname', '10000002A', 27,0.9685106,1.80, true, 80, null,'admin3');

INSERT INTO users(username, password, enabled) VALUES ('admin4', 'admin1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (7, 'admin', 'admin4');
INSERT INTO authorities(id, authority, username) VALUES (8, 'client', 'admin4');
INSERT INTO admins(id, email, first_name, last_name, nif, username) VALUES (4, 'admin3@yogoym.com','AdminName4', 'AdminSurname', '10000003A','admin4');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (4, 'client4@yogoym.com','AdminClientName4', 'AdminClientSurname', '10000003A', 33,0.45583093,1.80, false, 80, null,'admin4');

INSERT INTO users(username, password, enabled) VALUES ('admin5', 'admin1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (9, 'admin', 'admin5');
INSERT INTO authorities(id, authority, username) VALUES (10, 'client', 'admin5');
INSERT INTO admins(id, email, first_name, last_name, nif, username) VALUES (5, 'admin4@yogoym.com','AdminName5', 'AdminSurname', '10000004A','admin5');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (5, 'client5@yogoym.com','AdminClientName5', 'AdminClientSurname', '10000004A', 38,0.6131572,1.80, false, 80, null,'admin5');

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
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (6, 'client6@yogoym.com','ClientName6', 'ClientSurname','10000005A', 43,0.6430606,1.80, false, 80,1, 'client6');

/* Client Creator of Guild */
INSERT INTO users(username, password, enabled) VALUES ('client7', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (17, 'client', 'client7');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (7, 'client7@yogoym.com','ClientName7', 'ClientSurname','10000006A', 25,0.32738084,1.80, false, 80,2, 'client7');

/* Client Creator of Guild */
INSERT INTO users(username, password, enabled) VALUES ('client8', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (18, 'client', 'client8');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (8, 'client8@yogoym.com','ClientName8', 'ClientSurname','10000007A', 26,0.2799878,1.80, false, 80,3, 'client8');

INSERT INTO users(username, password, enabled) VALUES ('client9', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (19, 'client', 'client9');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (9, 'client9@yogoym.com','ClientName9', 'ClientSurname','10000008A', 26,0.25139296, 1.80, false, 80, null, 'client9');

INSERT INTO users(username, password, enabled) VALUES ('client10', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (20, 'client', 'client10');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (10, 'client10@yogoym.com','ClientName10', 'ClientSurname','10000009A', 43,0.41277498,1.80, true, 80,1, 'client10');

INSERT INTO users(username, password, enabled) VALUES ('client11', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (21, 'client', 'client11');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (11, 'client11@yogoym.com','ClientName11', 'ClientSurname','10000010A', 46,0.8781723,1.80, true, 80,2, 'client11');

INSERT INTO users(username, password, enabled) VALUES ('client12', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (22, 'client', 'client12');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (12, 'client12@yogoym.com','ClientName12', 'ClientSurname','10000011A', 47,0.27194858,1.80, false, 80,3, 'client12');

INSERT INTO users(username, password, enabled) VALUES ('client13', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (23, 'client', 'client13');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (13, 'client13@yogoym.com','ClientName13', 'ClientSurname','10000012A', 38,0.3325454, 1.80, true, 80, null, 'client13');

INSERT INTO users(username, password, enabled) VALUES ('client14', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (24, 'client', 'client14');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (14, 'client14@yogoym.com','ClientName14', 'ClientSurname','10000013A', 48,0.6480475,1.80, true, 80,1, 'client14');

INSERT INTO users(username, password, enabled) VALUES ('client15', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (25, 'client', 'client15');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (15, 'client15@yogoym.com','ClientName15', 'ClientSurname','10000014A', 36,0.116367936,1.80, false, 80,2, 'client15');

INSERT INTO users(username, password, enabled) VALUES ('client16', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (26, 'client', 'client16');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (16, 'client16@yogoym.com','ClientName16', 'ClientSurname','10000015A', 47,0.949082,1.80, true, 80,3, 'client16');

INSERT INTO users(username, password, enabled) VALUES ('client17', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (27, 'client', 'client17');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (17, 'client17@yogoym.com','ClientName17', 'ClientSurname','10000016A', 37,0.464302, 1.80, true, 80, null, 'client17');

INSERT INTO users(username, password, enabled) VALUES ('client18', 'client1999', TRUE);
INSERT INTO authorities(id, authority, username) VALUES (28, 'client', 'client18');
INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES (18, 'client18@yogoym.com','ClientName18', 'ClientSurname','10000017A', 46,0.2138009,1.80, false, 80,1, 'client18');

/* Challenges */
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (1,'Challenge1','Desc challenge 1','2020-1-1','2020-1-7','Reward1', 31, 4, 40, 23);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (2,'Challenge2','Desc challenge 2','2020-1-8','2020-1-13','Reward2', 97, 4, 40, 8);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (3,'Challenge3','Desc challenge 3','2020-1-14','2020-1-19','Reward3', 84, 4, 40, 5);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (4,'Challenge4','Desc challenge 4','2020-1-20','2020-1-25','Reward4', 61, 4, 40, 8);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (5,'Challenge5','Desc challenge 5','2020-1-26','2020-1-31','Reward5', 80, 4, 40, 58);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (6,'Challenge6','Desc challenge 6','2020-2-1','2020-2-6','Reward6', 21, 4, 40, 17);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (7,'Challenge7','Desc challenge 7','2020-2-7','2020-2-12','Reward7', 46, 4, 40, 54);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (8,'Challenge8','Desc challenge 8','2020-2-13','2020-2-18','Reward8', 30, 4, 40, 26);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (9,'Challenge9','Desc challenge 9','2020-2-19','2020-2-24','Reward9', 28, 4, 40, 42);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (10,'Challenge10','Desc challenge 10','2020-2-25','2020-3-1','Reward10', 50, 4, 40, 24);

/* Inscriptions */
/* Inscriptions Challenge 1 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (1,2,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,1);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (2,2,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,2);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (3,2,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,3);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (4,2,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,4);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (5,2,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (14,5);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (6,3,'https://',1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,6);

/* Inscriptions Challenge 2 */

/* Inscriptions Challenge 3 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (7,0,'https://',3);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,7);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (8,0,'https://',3);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,8);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (9,0,'https://',3);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (8,9);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (10,1,'https://',3);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,10);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (11,2,'https://',3);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (10,11);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (12,2,'https://',3);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,12);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (13,2,'https://',3);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,13);

/* Inscriptions Challenge 4 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (14,2,'https://',4);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,14);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (15,3,'https://',4);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,15);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (16,3,'https://',4);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,16);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (17,3,'https://',4);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (9,17);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (18,3,'https://',4);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,18);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (19,3,'https://',4);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,19);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (20,3,'https://',4);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,20);

/* Inscriptions Challenge 5 */

/* Inscriptions Challenge 6 */

/* Inscriptions Challenge 7 */

/* Inscriptions Challenge 8 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (21,3,'https://',8);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,21);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (22,3,'https://',8);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,22);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (23,3,'https://',8);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (6,23);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (24,2,'https://',8);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (11,24);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (25,2,'https://',8);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (13,25);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (26,2,'https://',8);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (15,26);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (27,2,'https://',8);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (17,27);

/* Inscriptions Challenge 9 */

/* Inscriptions Challenge 10 */
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (28,2,'https://',10);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,28);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (29,3,'https://',10);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (5,29);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (30,3,'https://',10);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (12,30);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (31,3,'https://',10);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (16,31);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (32,3,'https://',10);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (18,32);


/* Diets */
INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (1, 'Dieta 1','Desc 1',222,4,3,3);
INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (2, 'Dieta 2','Desc 2',434,3,4,1);
INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (3, 'Dieta 3','Desc 3',257,4,2,1);
INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (4, 'Dieta 4','Desc 4',328,1,2,2);
INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (5, 'Dieta 5','Desc 5',340,2,1,2);
INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (6, 'Dieta 6','Desc 6',472,1,1,2);
INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (7, 'Dieta 7','Desc 7',324,3,1,1);
INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (8, 'Dieta 8','Desc 8',480,1,1,4);
INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (9, 'Dieta 9','Desc 9',495,2,3,4);
INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (10, 'Dieta 10','Desc 10',330,3,3,3);

/* Foods */
INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (1, 'Food 1',213,1,3,2,4,1);
INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (2, 'Food 2',415,1,3,4,4,2);
INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (3, 'Food 3',256,4,4,4,3,3);
INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (4, 'Food 4',482,1,3,4,1,4);
INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (5, 'Food 5',224,1,1,4,1,1);
INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (6, 'Food 6',438,4,3,2,2,2);
INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (7, 'Food 7',375,1,3,2,4,3);
INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (8, 'Food 8',406,1,2,2,1,4);
INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (9, 'Food 9',470,1,1,4,1,1);
INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (10, 'Food 10',427,2,4,3,1,2);

/* Foods in Diet 1*/
INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,3);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,4);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,6);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,7);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,9);

/* Foods in Diet 2*/

/* Foods in Diet 3*/

/* Foods in Diet 4*/
INSERT INTO diets_foods(diet_id,foods_id) VALUES (4,1);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (4,3);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (4,6);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (4,7);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (4,9);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (4,10);

/* Foods in Diet 5*/
INSERT INTO diets_foods(diet_id,foods_id) VALUES (5,3);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (5,5);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (5,6);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (5,7);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (5,9);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (5,10);

/* Foods in Diet 6*/
INSERT INTO diets_foods(diet_id,foods_id) VALUES (6,1);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (6,4);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (6,6);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (6,8);

/* Foods in Diet 7*/
INSERT INTO diets_foods(diet_id,foods_id) VALUES (7,3);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (7,4);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (7,9);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (7,10);

/* Foods in Diet 8*/
INSERT INTO diets_foods(diet_id,foods_id) VALUES (8,1);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (8,2);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (8,4);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (8,5);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (8,7);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (8,8);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (8,9);

/* Foods in Diet 9*/

/* Foods in Diet 10*/

/* Trainings */
/* Trainings by Trainers*/
/* Trainer1*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (1, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training1', 0, 'trainer1', 3);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (2, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training2', 0, 'trainer1', 4);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (3, '2021-6-6 00:00:00.0','2021-6-11 00:00:00.0','Training3', 0, 'trainer1', 9);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (4, '2021-6-12 00:00:00.0','2021-6-17 00:00:00.0','Training4', 0, 'trainer1', 4);

/* Trainer2*/

/* Trainer3*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (5, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training5', 2, 'trainer3', 10);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (6, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training6', 2, 'trainer3', 6);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (7, '2021-6-6 00:00:00.0','2021-6-11 00:00:00.0','Training7', 2, 'trainer3', 1);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (8, '2021-6-12 00:00:00.0','2021-6-17 00:00:00.0','Training8', 2, 'trainer3', 10);

/* Trainer4*/

/* Trainer5*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (9, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training9', 2, 'trainer5', 3);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (10, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training10', 0, 'trainer5', 8);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (11, '2021-6-6 00:00:00.0','2021-6-11 00:00:00.0','Training11', 2, 'trainer5', 1);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (12, '2021-6-12 00:00:00.0','2021-6-17 00:00:00.0','Training12', 2, 'trainer5', 6);

/* Trainings by Clients*/
/* Client1*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (13, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training13', 1, 'client1', 1);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (14, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training14', 2, 'client1', 9);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (15, '2021-6-6 00:00:00.0','2021-6-11 00:00:00.0','Training15', 2, 'client1', 2);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (16, '2021-6-12 00:00:00.0','2021-6-17 00:00:00.0','Training16', 1, 'client1', 5);

/* Client2*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (17, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training17', 1, 'client2', 5);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (18, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training18', 1, 'client2', 7);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (19, '2021-6-6 00:00:00.0','2021-6-11 00:00:00.0','Training19', 2, 'client2', 5);

/* Client3*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (20, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training20', 2, 'client3', 5);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (21, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training21', 2, 'client3', 9);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (22, '2021-6-6 00:00:00.0','2021-6-11 00:00:00.0','Training22', 1, 'client3', 8);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (23, '2021-6-12 00:00:00.0','2021-6-17 00:00:00.0','Training23', 1, 'client3', 4);

/* Client4*/

/* Client5*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (24, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training24', 1, 'client5', 4);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (25, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training25', 2, 'client5', 9);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (26, '2021-6-6 00:00:00.0','2021-6-11 00:00:00.0','Training26', 2, 'client5', 5);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (27, '2021-6-12 00:00:00.0','2021-6-17 00:00:00.0','Training27', 1, 'client5', 3);

/* Client6*/

/* Client7*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (28, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training28', 2, 'client7', 3);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (29, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training29', 1, 'client7', 4);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (30, '2021-6-6 00:00:00.0','2021-6-11 00:00:00.0','Training30', 1, 'client7', 5);

/* Client8*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (31, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training31', 2, 'client8', 3);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (32, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training32', 1, 'client8', 2);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (33, '2021-6-6 00:00:00.0','2021-6-11 00:00:00.0','Training33', 1, 'client8', 4);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (34, '2021-6-12 00:00:00.0','2021-6-17 00:00:00.0','Training34', 1, 'client8', 9);

/* Client9*/

/* Client10*/

/* Client11*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (35, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training35', 1, 'client11', 10);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (36, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training36', 2, 'client11', 3);

/* Client12*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (37, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training37', 2, 'client12', 3);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (38, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training38', 2, 'client12', 7);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (39, '2021-6-6 00:00:00.0','2021-6-11 00:00:00.0','Training39', 2, 'client12', 1);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (40, '2021-6-12 00:00:00.0','2021-6-17 00:00:00.0','Training40', 2, 'client12', 4);

/* Client13*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (41, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training41', 1, 'client13', 8);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (42, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training42', 1, 'client13', 5);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (43, '2021-6-6 00:00:00.0','2021-6-11 00:00:00.0','Training43', 2, 'client13', 8);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (44, '2021-6-12 00:00:00.0','2021-6-17 00:00:00.0','Training44', 1, 'client13', 9);

/* Client14*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (45, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training45', 1, 'client14', 7);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (46, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training46', 2, 'client14', 1);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (47, '2021-6-6 00:00:00.0','2021-6-11 00:00:00.0','Training47', 2, 'client14', 7);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (48, '2021-6-12 00:00:00.0','2021-6-17 00:00:00.0','Training48', 2, 'client14', 1);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (49, '2021-6-18 00:00:00.0','2021-6-23 00:00:00.0','Training49', 1, 'client14', 8);

/* Client15*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (50, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training50', 2, 'client15', 9);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (51, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training51', 2, 'client15', 7);

/* Client16*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (52, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training52', 2, 'client16', 1);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (53, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training53', 2, 'client16', 7);

/* Client17*/

/* Client18*/
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (54, '2021-5-25 00:00:00.0','2021-5-30 00:00:00.0','Training54', 1, 'client18', 6);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (55, '2021-5-31 00:00:00.0','2021-6-5 00:00:00.0','Training55', 1, 'client18', 2);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (56, '2021-6-6 00:00:00.0','2021-6-11 00:00:00.0','Training56', 2, 'client18', 2);


/* Routines */
/* Routines of Training1 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (1,'Routine 1', 'Desc Routine1',5,1);

/* Routines of Training2 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (2,'Routine 2', 'Desc Routine2',12,2);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (3,'Routine 3', 'Desc Routine3',9,2);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (4,'Routine 4', 'Desc Routine4',12,2);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (5,'Routine 5', 'Desc Routine5',7,2);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (6,'Routine 6', 'Desc Routine6',15,2);

/* Routines of Training3 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (7,'Routine 7', 'Desc Routine7',5,3);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (8,'Routine 8', 'Desc Routine8',2,3);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (9,'Routine 9', 'Desc Routine9',15,3);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (10,'Routine 10', 'Desc Routine10',16,3);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (11,'Routine 11', 'Desc Routine11',16,3);

/* Routines of Training4 */

/* Routines of Training5 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (12,'Routine 12', 'Desc Routine12',14,5);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (13,'Routine 13', 'Desc Routine13',5,5);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (14,'Routine 14', 'Desc Routine14',8,5);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (15,'Routine 15', 'Desc Routine15',4,5);

/* Routines of Training6 */

/* Routines of Training7 */

/* Routines of Training8 */

/* Routines of Training9 */

/* Routines of Training10 */

/* Routines of Training11 */

/* Routines of Training12 */

/* Routines of Training13 */

/* Routines of Training14 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (16,'Routine 16', 'Desc Routine16',14,14);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (17,'Routine 17', 'Desc Routine17',17,14);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (18,'Routine 18', 'Desc Routine18',10,14);

/* Routines of Training15 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (19,'Routine 19', 'Desc Routine19',11,15);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (20,'Routine 20', 'Desc Routine20',13,15);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (21,'Routine 21', 'Desc Routine21',2,15);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (22,'Routine 22', 'Desc Routine22',15,15);

/* Routines of Training16 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (23,'Routine 23', 'Desc Routine23',12,16);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (24,'Routine 24', 'Desc Routine24',15,16);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (25,'Routine 25', 'Desc Routine25',2,16);

/* Routines of Training17 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (26,'Routine 26', 'Desc Routine26',5,17);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (27,'Routine 27', 'Desc Routine27',5,17);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (28,'Routine 28', 'Desc Routine28',10,17);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (29,'Routine 29', 'Desc Routine29',14,17);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (30,'Routine 30', 'Desc Routine30',18,17);

/* Routines of Training18 */

/* Routines of Training19 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (31,'Routine 31', 'Desc Routine31',8,19);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (32,'Routine 32', 'Desc Routine32',3,19);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (33,'Routine 33', 'Desc Routine33',3,19);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (34,'Routine 34', 'Desc Routine34',17,19);

/* Routines of Training20 */

/* Routines of Training21 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (35,'Routine 35', 'Desc Routine35',18,21);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (36,'Routine 36', 'Desc Routine36',3,21);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (37,'Routine 37', 'Desc Routine37',4,21);

/* Routines of Training22 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (38,'Routine 38', 'Desc Routine38',12,22);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (39,'Routine 39', 'Desc Routine39',17,22);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (40,'Routine 40', 'Desc Routine40',5,22);

/* Routines of Training23 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (41,'Routine 41', 'Desc Routine41',11,23);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (42,'Routine 42', 'Desc Routine42',2,23);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (43,'Routine 43', 'Desc Routine43',17,23);

/* Routines of Training24 */

/* Routines of Training25 */

/* Routines of Training26 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (44,'Routine 44', 'Desc Routine44',6,26);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (45,'Routine 45', 'Desc Routine45',14,26);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (46,'Routine 46', 'Desc Routine46',17,26);

/* Routines of Training27 */

/* Routines of Training28 */

/* Routines of Training29 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (47,'Routine 47', 'Desc Routine47',11,29);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (48,'Routine 48', 'Desc Routine48',4,29);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (49,'Routine 49', 'Desc Routine49',9,29);

/* Routines of Training30 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (50,'Routine 50', 'Desc Routine50',18,30);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (51,'Routine 51', 'Desc Routine51',4,30);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (52,'Routine 52', 'Desc Routine52',6,30);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (53,'Routine 53', 'Desc Routine53',11,30);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (54,'Routine 54', 'Desc Routine54',9,30);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (55,'Routine 55', 'Desc Routine55',19,30);

/* Routines of Training31 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (56,'Routine 56', 'Desc Routine56',12,31);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (57,'Routine 57', 'Desc Routine57',2,31);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (58,'Routine 58', 'Desc Routine58',5,31);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (59,'Routine 59', 'Desc Routine59',19,31);

/* Routines of Training32 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (60,'Routine 60', 'Desc Routine60',4,32);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (61,'Routine 61', 'Desc Routine61',7,32);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (62,'Routine 62', 'Desc Routine62',17,32);

/* Routines of Training33 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (63,'Routine 63', 'Desc Routine63',14,33);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (64,'Routine 64', 'Desc Routine64',8,33);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (65,'Routine 65', 'Desc Routine65',15,33);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (66,'Routine 66', 'Desc Routine66',9,33);

/* Routines of Training34 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (67,'Routine 67', 'Desc Routine67',17,34);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (68,'Routine 68', 'Desc Routine68',19,34);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (69,'Routine 69', 'Desc Routine69',9,34);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (70,'Routine 70', 'Desc Routine70',9,34);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (71,'Routine 71', 'Desc Routine71',10,34);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (72,'Routine 72', 'Desc Routine72',10,34);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (73,'Routine 73', 'Desc Routine73',4,34);

/* Routines of Training35 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (74,'Routine 74', 'Desc Routine74',12,35);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (75,'Routine 75', 'Desc Routine75',1,35);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (76,'Routine 76', 'Desc Routine76',1,35);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (77,'Routine 77', 'Desc Routine77',19,35);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (78,'Routine 78', 'Desc Routine78',2,35);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (79,'Routine 79', 'Desc Routine79',12,35);

/* Routines of Training36 */

/* Routines of Training37 */

/* Routines of Training38 */

/* Routines of Training39 */

/* Routines of Training40 */

/* Routines of Training41 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (80,'Routine 80', 'Desc Routine80',18,41);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (81,'Routine 81', 'Desc Routine81',15,41);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (82,'Routine 82', 'Desc Routine82',4,41);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (83,'Routine 83', 'Desc Routine83',2,41);

/* Routines of Training42 */

/* Routines of Training43 */

/* Routines of Training44 */

/* Routines of Training45 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (84,'Routine 84', 'Desc Routine84',11,45);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (85,'Routine 85', 'Desc Routine85',16,45);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (86,'Routine 86', 'Desc Routine86',7,45);

/* Routines of Training46 */

/* Routines of Training47 */

/* Routines of Training48 */

/* Routines of Training49 */

/* Routines of Training50 */

/* Routines of Training51 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (87,'Routine 87', 'Desc Routine87',14,51);

/* Routines of Training52 */

/* Routines of Training53 */

/* Routines of Training54 */

/* Routines of Training55 */

/* Routines of Training56 */
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (88,'Routine 88', 'Desc Routine88',4,56);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (89,'Routine 89', 'Desc Routine89',1,56);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (90,'Routine 90', 'Desc Routine90',9,56);

/* Routine Lines */
/* Routine Line from Routine1*/

/* Routine Line from Routine2*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (1,3, 7, 3, 47,1,2);

/* Routine Line from Routine3*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (2,4, 9, 8, 19,55,3);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (3,8, 4, 4, 23,19,3);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (4,3, 1, 13, 24,26,3);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (5,9, 4, 3, 18,1,3);

/* Routine Line from Routine4*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (6,8, 1, 9, 9,62,4);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (7,4, 6, 2, 29,31,4);

/* Routine Line from Routine5*/

/* Routine Line from Routine6*/

/* Routine Line from Routine7*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (8,4, 6, 18, 4,1,7);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (9,7, 6, 9, 48,59,7);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (10,3, 2, 5, 2,37,7);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (11,2, 8, 12, 12,45,7);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (12,9, 6, 3, 23,59,7);

/* Routine Line from Routine8*/

/* Routine Line from Routine9*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (13,9, 8, 19, 40,54,9);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (14,1, 3, 9, 41,1,9);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (15,4, 9, 2, 24,29,9);

/* Routine Line from Routine10*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (16,8, 1, 2, 19,38,10);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (17,3, 2, 1, 26,41,10);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (18,5, 8, 6, 24,13,10);

/* Routine Line from Routine11*/

/* Routine Line from Routine12*/

/* Routine Line from Routine13*/

/* Routine Line from Routine14*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (19,6, 8, 1, 12,9,14);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (20,7, 9, 15, 34,31,14);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (21,1, 6, 2, 31,45,14);

/* Routine Line from Routine15*/

/* Routine Line from Routine16*/

/* Routine Line from Routine17*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (22,5, 5, 12, 20,29,17);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (23,1, 6, 11, 38,17,17);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (24,6, 1, 13, 48,61,17);

/* Routine Line from Routine18*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (25,5, 1, 10, 27,57,18);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (26,9, 3, 2, 9,10,18);

/* Routine Line from Routine19*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (27,5, 2, 13, 16,43,19);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (28,9, 2, 4, 7,30,19);

/* Routine Line from Routine20*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (29,3, 2, 19, 34,6,20);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (30,6, 1, 7, 29,25,20);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (31,1, 3, 8, 28,8,20);

/* Routine Line from Routine21*/

/* Routine Line from Routine22*/

/* Routine Line from Routine23*/

/* Routine Line from Routine24*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (32,4, 9, 19, 28,53,24);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (33,4, 4, 16, 3,56,24);

/* Routine Line from Routine25*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (34,8, 9, 1, 49,48,25);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (35,6, 1, 5, 6,41,25);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (36,9, 1, 12, 7,1,25);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (37,3, 5, 4, 24,14,25);

/* Routine Line from Routine26*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (38,2, 6, 7, 25,3,26);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (39,4, 3, 5, 27,23,26);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (40,5, 4, 12, 1,18,26);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (41,4, 3, 7, 22,63,26);

/* Routine Line from Routine27*/

/* Routine Line from Routine28*/

/* Routine Line from Routine29*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (42,2, 4, 9, 42,26,29);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (43,4, 5, 8, 34,34,29);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (44,7, 8, 5, 4,9,29);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (45,6, 2, 15, 43,30,29);

/* Routine Line from Routine30*/

/* Routine Line from Routine31*/

/* Routine Line from Routine32*/

/* Routine Line from Routine33*/

/* Routine Line from Routine34*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (46,8, 5, 6, 45,39,34);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (47,4, 9, 5, 1,50,34);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (48,9, 9, 19, 6,23,34);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (49,2, 9, 10, 43,5,34);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (50,1, 3, 2, 19,7,34);

/* Routine Line from Routine35*/

/* Routine Line from Routine36*/

/* Routine Line from Routine37*/

/* Routine Line from Routine38*/

/* Routine Line from Routine39*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (51,3, 3, 13, 43,48,39);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (52,6, 6, 4, 11,23,39);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (53,9, 1, 4, 34,19,39);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (54,7, 7, 6, 16,19,39);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (55,7, 7, 8, 20,50,39);

/* Routine Line from Routine40*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (56,6, 1, 8, 19,15,40);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (57,4, 8, 18, 2,23,40);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (58,8, 6, 8, 4,64,40);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (59,7, 5, 9, 31,40,40);

/* Routine Line from Routine41*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (60,3, 8, 9, 15,8,41);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (61,8, 8, 12, 47,25,41);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (62,3, 9, 13, 31,39,41);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (63,2, 4, 1, 17,61,41);

/* Routine Line from Routine42*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (64,7, 6, 11, 48,51,42);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (65,4, 7, 18, 20,55,42);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (66,8, 6, 5, 39,48,42);

/* Routine Line from Routine43*/

/* Routine Line from Routine44*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (67,6, 9, 4, 32,31,44);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (68,5, 2, 19, 15,8,44);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (69,1, 9, 8, 13,56,44);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (70,6, 4, 16, 1,51,44);

/* Routine Line from Routine45*/

/* Routine Line from Routine46*/

/* Routine Line from Routine47*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (71,2, 3, 17, 13,28,47);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (72,9, 6, 16, 42,41,47);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (73,6, 2, 16, 39,49,47);

/* Routine Line from Routine48*/

/* Routine Line from Routine49*/

/* Routine Line from Routine50*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (74,4, 8, 10, 18,63,50);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (75,4, 4, 14, 22,32,50);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (76,9, 2, 14, 12,40,50);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (77,6, 1, 16, 37,19,50);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (78,6, 4, 6, 22,8,50);

/* Routine Line from Routine51*/

/* Routine Line from Routine52*/

/* Routine Line from Routine53*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (79,7, 4, 6, 33,58,53);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (80,5, 8, 19, 38,16,53);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (81,4, 9, 14, 47,31,53);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (82,4, 7, 3, 15,12,53);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (83,7, 2, 7, 38,25,53);

/* Routine Line from Routine54*/

/* Routine Line from Routine55*/

/* Routine Line from Routine56*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (84,4, 9, 16, 4,52,56);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (85,3, 5, 16, 23,19,56);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (86,2, 2, 4, 27,37,56);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (87,2, 3, 12, 46,61,56);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (88,1, 5, 16, 19,45,56);

/* Routine Line from Routine57*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (89,7, 3, 1, 9,25,57);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (90,6, 5, 16, 29,59,57);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (91,4, 8, 18, 35,43,57);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (92,2, 6, 19, 45,52,57);

/* Routine Line from Routine58*/

/* Routine Line from Routine59*/

/* Routine Line from Routine60*/

/* Routine Line from Routine61*/

/* Routine Line from Routine62*/

/* Routine Line from Routine63*/

/* Routine Line from Routine64*/

/* Routine Line from Routine65*/

/* Routine Line from Routine66*/

/* Routine Line from Routine67*/

/* Routine Line from Routine68*/

/* Routine Line from Routine69*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (93,5, 3, 1, 9,61,69);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (94,4, 2, 2, 31,11,69);

/* Routine Line from Routine70*/

/* Routine Line from Routine71*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (95,3, 2, 10, 19,28,71);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (96,8, 4, 6, 18,9,71);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (97,9, 6, 2, 44,51,71);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (98,3, 9, 6, 2,58,71);

/* Routine Line from Routine72*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (99,9, 5, 2, 31,15,72);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (100,5, 5, 16, 26,20,72);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (101,7, 1, 13, 22,63,72);

/* Routine Line from Routine73*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (102,5, 3, 3, 3,57,73);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (103,2, 9, 2, 17,24,73);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (104,6, 1, 13, 37,63,73);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (105,1, 9, 13, 42,40,73);

/* Routine Line from Routine74*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (106,2, 7, 11, 33,32,74);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (107,5, 5, 1, 46,10,74);

/* Routine Line from Routine75*/

/* Routine Line from Routine76*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (108,2, 7, 13, 32,31,76);

/* Routine Line from Routine77*/

/* Routine Line from Routine78*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (109,5, 7, 19, 25,54,78);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (110,8, 8, 8, 25,17,78);

/* Routine Line from Routine79*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (111,4, 3, 4, 29,63,79);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (112,2, 2, 14, 38,57,79);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (113,8, 4, 16, 2,17,79);

/* Routine Line from Routine80*/

/* Routine Line from Routine81*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (114,4, 6, 18, 49,50,81);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (115,5, 8, 17, 44,50,81);

/* Routine Line from Routine82*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (116,9, 7, 8, 19,56,82);

/* Routine Line from Routine83*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (117,3, 8, 1, 35,52,83);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (118,1, 2, 8, 40,8,83);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (119,4, 5, 8, 18,37,83);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (120,6, 1, 12, 45,7,83);

/* Routine Line from Routine84*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (121,4, 9, 14, 15,51,84);

/* Routine Line from Routine85*/

/* Routine Line from Routine86*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (122,4, 8, 15, 13,6,86);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (123,7, 1, 13, 1,24,86);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (124,4, 4, 19, 32,43,86);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (125,3, 1, 12, 34,1,86);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (126,1, 4, 5, 12,32,86);

/* Routine Line from Routine87*/

/* Routine Line from Routine88*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (127,4, 7, 15, 43,48,88);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (128,2, 8, 12, 3,3,88);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (129,1, 9, 19, 9,10,88);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (130,3, 1, 4, 28,40,88);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (131,1, 5, 15, 13,8,88);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (132,7, 8, 12, 33,48,88);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (133,2, 1, 3, 28,18,88);

/* Routine Line from Routine89*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (134,3, 7, 1, 22,50,89);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (135,7, 6, 1, 49,30,89);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (136,4, 6, 15, 4,25,89);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (137,6, 5, 9, 40,4,89);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (138,5, 6, 6, 8,64,89);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (139,4, 6, 12, 15,49,89);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (140,3, 7, 16, 36,61,89);

/* Routine Line from Routine90*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (141,1, 8, 16, 48,61,90);


/* Trainer_Clients*/
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (2, 1);
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (5, 4);
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (2, 6);
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (4, 8);
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (1, 10);
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (5, 14);
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (1, 15);
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (2, 16);
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (3, 17);

INSERT INTO clients_trainings(client_id,trainings_id) VALUES (1, 13);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (1, 14);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (1, 15);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (1, 16);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (11, 35);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (11, 36);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (12, 37);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (12, 38);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (12, 39);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (12, 40);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (13, 41);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (13, 42);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (13, 43);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (13, 44);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (14, 45);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (14, 46);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (14, 47);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (14, 48);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (14, 49);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (15, 50);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (15, 51);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (16, 52);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (16, 53);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (18, 54);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (18, 55);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (18, 56);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (2, 17);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (2, 18);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (2, 19);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (3, 20);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (3, 21);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (3, 22);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (3, 23);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (5, 24);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (5, 25);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (5, 26);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (5, 27);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (7, 28);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (7, 29);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (7, 30);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (8, 31);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (8, 32);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (8, 33);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (8, 34);


-- /* ADMIN */
-- 
-- INSERT INTO users(username,password,enabled) VALUES ('admin1','admin1999',TRUE);
-- INSERT INTO authorities(id,authority,username) VALUES (1,'admin','admin1');
-- INSERT INTO authorities(id,authority,username) VALUES (2,'client','admin1');
-- INSERT INTO admins(id,email,first_name,last_name,nif,username) VALUES (1, 'proyectowip@vekto.com','Vekto', 'Rino', '12345678A','admin1');
-- INSERT INTO clients(id,email,first_name,last_name,nif,age,fat_percentage,height,is_public,weight,guild_id,username) VALUES (11, 'admin@admin.com', 'Admin', 'Admin', '12345678A',20,20.0,183.0,true,80.0,null,'admin1');
-- 
-- /* GUILD */
-- 
-- INSERT INTO guilds(id,creator,description,name,logo) VALUES (1,'client1','Here we practice Calisthenics for everyone','Calisthenics','https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/heria10-1566732385.jpg?crop=1.00xw:0.922xh;0,0.0778xh&resize=980:*');
-- INSERT INTO guilds(id,creator,description,name,logo) VALUES (2,'client2','Here we practice Weightlifting for everyone','Weightlifting','https://www.iwf.net/wp-content/uploads/2017/12/DSC00237.jpg');
-- INSERT INTO guilds(id,creator,description,name,logo) VALUES (3,'client3','Here we help you achieve your goals','Gym for Dummies','https://www.mobiefit.com/blog/wp-content/uploads/2017/06/shutterstock_326360432.jpg');
-- 
-- /* CLIENT */
-- 
-- 
-- INSERT INTO users(username,password,enabled) VALUES ('client1','client1999',TRUE);
-- INSERT INTO authorities(id,authority,username) VALUES (3,'client','client1');
-- INSERT INTO clients(id,email,first_name,last_name,nif,age,fat_percentage,height,is_public,weight,guild_id,username) VALUES (1, 'marantle@yogogym.com','Martin', 'Antonio Lera', '12345678B',18,20.0,183.0,true,80.0,1,'client1');
-- 
-- INSERT INTO users(username,password,enabled) VALUES ('client2','client1999',TRUE);
-- INSERT INTO authorities(id,authority,username) VALUES (4,'client','client2');
-- INSERT INTO clients(id,email,first_name,last_name,nif,age,fat_percentage,height,is_public,weight,guild_id,username) VALUES (2, 'fejasa@yogogym.com','Federico', 'Javier Saco', '12345678C',18,20.0,190.0,false,95.3,2,'client2');
-- 
-- INSERT INTO users(username,password,enabled) VALUES ('client3','client1999',TRUE);
-- INSERT INTO authorities(id,authority,username) VALUES (5,'client','client3');
-- INSERT INTO clients(id,email,first_name,last_name,nif,age,fat_percentage,height,is_public,weight,guild_id,username) VALUES (3, 'juengue@yogogym.com','Julio', 'Enrique Guerrero', '12345678D',18,20.0,177.0,true,110.7,3,'client3');
-- 
-- INSERT INTO users(username,password,enabled) VALUES ('client4','client1999',TRUE);
-- INSERT INTO authorities(id,authority,username) VALUES (6,'client','client4');
-- INSERT INTO clients(id,email,first_name,last_name,nif,age,fat_percentage,height,is_public,weight,guild_id,username) VALUES (4, 'anjomi@yogogym.com','Andrés', 'José Michelena', '12345678E',18,20.0,168.0,false,74.3, null ,'client4');
-- 
-- INSERT INTO users(username,password,enabled) VALUES ('client5','client1999',TRUE);
-- INSERT INTO authorities(id,authority,username) VALUES (7,'client','client5');
-- INSERT INTO clients(id,email,first_name,last_name,nif,age,fat_percentage,height,is_public,weight,guild_id,username) VALUES (5, 'caresro@yogogym.com','Carmelina', 'Esteso Rodríguez', '12345678F',18,20.0,170.0,true,83.7,1,'client5');
-- 
-- INSERT INTO users(username,password,enabled) VALUES ('client6','client1999',TRUE);
-- INSERT INTO authorities(id,authority,username) VALUES (8,'client','client6');
-- INSERT INTO clients(id,email,first_name,last_name,nif,age,fat_percentage,height,is_public,weight,guild_id,username) VALUES (6, 'soviob@yogogym.com','Sofia', 'Victoria Obeso', '12345678G',18,20.0,175.0,true,63.4,2,'client6');
-- 
-- INSERT INTO users(username,password,enabled) VALUES ('client7','client1999',TRUE);
-- INSERT INTO authorities(id,authority,username) VALUES (9,'client','client7');
-- INSERT INTO clients(id,email,first_name,last_name,nif,age,fat_percentage,height,is_public,weight,guild_id,username) VALUES (7, 'anmamu@yogogym.com','Ana', 'Maria Muniesa', '12345678H',18,20.0,156.0,false,54.2,3,'client7');
-- 
-- INSERT INTO users(username,password,enabled) VALUES ('client8','client1999',TRUE);
-- INSERT INTO authorities(id,authority,username) VALUES (10,'client','client8');
-- INSERT INTO clients(id,email,first_name,last_name,nif,age,fat_percentage,height,is_public,weight,guild_id,username) VALUES (8, 'lusoba@yogogym.com','Luisa', 'Soledad Basurto', '12345678I',18,20.0,163.0,true,75.6, null ,'client8');
-- 
-- INSERT INTO users(username,password,enabled) VALUES ('client9','client1999',TRUE);
-- INSERT INTO authorities(id,authority,username) VALUES (11,'client','client9');
-- INSERT INTO clients(id,email,first_name,last_name,nif,age,fat_percentage,height,is_public,weight,guild_id,username) VALUES (9, 'caralfe@yogogym.com','Carmen', 'Alejandra Fernandez', '12345678J',18,20.0,170.0,true,72.4,null,'client9');
-- 
-- INSERT INTO users(username,password,enabled) VALUES ('client10','client1999',TRUE);
-- INSERT INTO authorities(id,authority,username) VALUES (12,'client','client10');
-- INSERT INTO clients(id,email,first_name,last_name,nif,age,fat_percentage,height,is_public,weight,guild_id,username) VALUES (10, 'trinimon@yogogym.com','Trinidad', 'Maria Montosa', '12345678K',18,20.0,169.0,true,83.5,2,'client10');
-- 
-- 
-- /* TRAINER */
-- 
-- INSERT INTO users(username,password,enabled) VALUES ('trainer1','trainer1999',TRUE);
-- INSERT INTO authorities(id,authority,username) VALUES (13,'trainer','trainer1');
-- INSERT INTO trainers(id,email,first_name,last_name,nif,username) VALUES (1,  'josemadaci@yogogym.com', 'José Manuel', 'David Cifuentes', '12345678L','trainer1');
-- 
-- INSERT INTO users(username,password,enabled) VALUES ('trainer2','trainer1999',TRUE);
-- INSERT INTO authorities(id,authority,username) VALUES (14,'trainer','trainer2');
-- INSERT INTO trainers(id,email,first_name,last_name,nif,username) VALUES (2,  'jusien@yogogym.com', 'Juan', 'Simón Enciso', '12345678M','trainer2');
-- 
-- /* FOOD */
-- 
-- INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (1,'Bread',80,20,20,30,20,1);
-- INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (2,'Apple',57,10,20,30,20,2);
-- INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (3,'Steak',120,60,20,30,20,3);
-- INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (4,'Banana',65,20,20,30,20,4);
-- INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (5,'Scrambled Eggs',100,40,20,30,20,4);
-- 
-- /* DIET */
-- 
-- INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (1, 'Dieta 1','Desc 1', 1, 1, 1, 1);
-- INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (2, 'Dieta 2','Desc 2', 1, 1, 1, 1);
-- INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (3, 'Dieta 3','Desc 3', 1, 1, 1, 1);
-- INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (4, 'Dieta 4','Desc 4', 1, 1, 1, 1);
-- 
-- /* FOOD_DIET */
-- 
-- INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,1);
-- INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,2);
-- INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,3);
-- INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,4);
-- INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,5);
-- INSERT INTO diets_foods(diet_id,foods_id) VALUES (2,1);
-- INSERT INTO diets_foods(diet_id,foods_id) VALUES (3,2);
-- INSERT INTO diets_foods(diet_id,foods_id) VALUES (4,3);
-- 
-- /* TRAINING */
-- 
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (1, '2020-01-01 00:00:00.0', '2020-01-14 00:00:00.0', 'Entrenamiento1', 0, 'trainer1', 1);
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (2, '2020-02-01 00:00:00.0', '2020-02-14 00:00:00.0', 'Entrenamiento2', 2, 'trainer1', 2);
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (3, '2020-03-01 00:00:00.0', '2020-03-10 00:00:00.0', 'Entrenamiento3', 0, 'trainer1', 3);
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (4, '2020-04-01 00:00:00.0', '2020-04-20 00:00:00.0', 'Entrenamiento4', 0, 'trainer2', 4);
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (5, '2020-01-01 00:00:00.0', '2020-01-10 00:00:00.0', 'Entrenamiento1', 1, 'client4', null);
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (6, '2020-02-01 00:00:00.0', '2020-02-20 00:00:00.0', 'Entrenamiento2', 1, 'client5', null);
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (7, '2020-03-01 00:00:00.0', '2020-03-10 00:00:00.0', 'Entrenamiento3', 1, 'client6', null);
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (8, '2020-04-01 00:00:00.0', '2020-04-20 00:00:00.0', 'Entrenamiento4', 1, 'client7', null);
-- 
-- /* Always Updated Trainings */
-- 
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (9, DATE_ADD(CURDATE(), INTERVAL 100 DAY), DATE_ADD(CURDATE(), INTERVAL 101 DAY), 'Test', 2, 'trainer1', null);
-- 
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (10, DATE_ADD(CURDATE(), INTERVAL -7 DAY), DATE_ADD(CURDATE(), INTERVAL 7 DAY), 'Entrenamiento1', 0, 'trainer1', null);
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (11, DATE_ADD(CURDATE(), INTERVAL 14 DAY), DATE_ADD(CURDATE(), INTERVAL 21 DAY), 'Entrenamiento2', 2, 'client6', null);
-- 
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (12, DATE_ADD(CURDATE(), INTERVAL -7 DAY), DATE_ADD(CURDATE(), INTERVAL 7 DAY), 'Entrenamiento1', 1, 'client5', null);
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (13, DATE_ADD(CURDATE(), INTERVAL 14 DAY), DATE_ADD(CURDATE(), INTERVAL 21 DAY), 'Entrenamiento2', 2, 'trainer1', null);
-- 
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (14, DATE_ADD(CURDATE(), INTERVAL 200 DAY), DATE_ADD(CURDATE(), INTERVAL 208 DAY), 'Entrenamiento10_1', 0, 'trainer1', null);
-- INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES (15, DATE_ADD(CURDATE(), INTERVAL 210 DAY), DATE_ADD(CURDATE(), INTERVAL 215 DAY), 'Entrenamiento10_2', 1, 'client10', null);
-- 
-- /* ROUTINE */
-- 
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (1,'Cardio', 'Augment resistance',3,1);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (2,'Brazos', 'Biceps redondos',3,1);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (3,'Piernas', 'Aumentar fuerza',3,2);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (4,'Resistencia', 'Aumentar resistencia',3,3);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (5,'Musculación', 'Tonificación',3,4);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (6,'Triatlon', 'Para participantes del triatlon',3,5);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (7,'Culturismo', 'Para participar en competiciones de culturismo',3,5);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (8,'Relajación', 'Descanso',3,6);
-- 
-- /* Routine for Update Training */
-- 
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (9,'Routine 1', 'Desc',1,9);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (10,'Routine 2', 'Desc',2,9);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (11,'Routine 3', 'Desc',3,9);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (12,'Routine 4', 'Desc',4,9);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (13,'Routine 5', 'Desc',5,9);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (14,'Routine 6', 'Desc',6,9);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (15,'Routine 7', 'Desc',7,9);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (16,'Routine 8', 'Desc',8,9);
-- INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (17,'Routine 9', 'Desc',9,9);


-- 
-- /* ROUTINE LINE */
-- 
-- /* Routine 1*/
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (1, null, 2, 1, 0.0, 46, 1);
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (2, null, 20,1, 0.0, 45, 1);
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (3, null,1,1,0.0,44,1);
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (4, null,20,1,0.0,49,1);
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (5, null,10,1,0.0,45,1);
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (6, null,5,1,0.0,46,1);
-- 
-- /* Routine 2 */
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (7,10,null,3,30,15,2);
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (8,10,null,5,20,20,2);
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (9,20,null,2,10,31,2);
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (10,null,2,2,15,37,2);
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (11,10,null,3,20,43,2);
-- 
-- /* Routine 9 */
-- 
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (12,10,null,3,30,15,9);
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (13,10,null,5,20,20,9);
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (14,20,null,2,10,31,9);
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (15,null,2,2,15,37,9);
-- INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (16,10,null,3,20,43,9);
-- 
-- /* CHALLENGE */
-- 
-- INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (1,'Challenge1','Desc challenge 1','2020-01-01','2020-01-02','Reward1',10,10,10,1);
-- INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (2,'Challenge2','Desc challenge 2','2020-10-01','2020-10-02','Reward2',20,5,20,2);
-- INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (3,'Challenge3','Desc challenge 3','2020-10-10','2020-10-15','Reward3',30,4,40,10);
-- INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (4,'Challenge4','Desc challenge 4','2020-10-10','2020-10-15','Reward2',30,4,40,10);
-- INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (5,'Challenge5','Desc challenge 5','2021-01-01','2021-01-05','Reward5',30,4,40,10);
-- 
-- /* INSCRIPTION */
-- 
-- INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (1,3,'https://allamericanfitness.com/wp-content/uploads/2016/11/Treadmill-XR-Console.jpg',1);
-- INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (2,0,null,2);
-- INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (3,0,null,3);
-- INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (4,0,null,3);
-- INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (5,2,'https://google.com',1);
-- INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (6,1,'https://google.com',3);
-- INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (7,0,'https://google.com',5);
-- 
-- /* CLIENT_INSCRIPTION */
-- 
-- INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,1);
-- INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,2);
-- INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,3);
-- INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,4);
-- INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,5);
-- INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,6);
-- INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,7);
-- 
-- /* PHRASES */
-- 
-- INSERT INTO phrases(id,author,text) VALUES (1,'Enrique Reina','A veces cuando cierro los ojos no veo.');
-- 
-- /* TRAINER_CLIENT */
-- 
-- /* trainer 1 */
-- INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (1,1);
-- INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (1,2);
-- INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (1,5);
-- INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (1,6);
-- INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (1,10);
-- 
-- /* trainer 2 */
-- INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (2,3);
-- INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (2,4);
-- INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (2,7);
-- INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (2,8);
-- 
-- /* CLIENT_TRAINING */
-- 
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (1,1);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (1,2);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (1,9);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (2,3);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (3,4);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (4,5);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (5,6);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (5,12);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (5,13);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (6,7);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (6,10);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (6,11);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (7,8);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (10,14);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES (10,15);
-- 
-- INSERT INTO forums(id,guild_id) VALUES (1,1);
-- INSERT INTO forums(id,guild_id) VALUES (2,2);
-- INSERT INTO forums(id,guild_id) VALUES (3,3);
-- 
-- /*Primeros mensajes*/
-- INSERT INTO messages(id,forum_id,message_id,user_id,content,created_at,edited,is_parent) VALUES (1,1,null,'client1','Mensaje de prueba','2020-05-15 14:23:25',false,true);
-- INSERT INTO messages(id,forum_id,message_id,user_id,content,created_at,edited,is_parent) VALUES (2,1,null,'client1','Mensaje de prueba 2','2020-05-16 14:23:25',false,true);
-- INSERT INTO messages(id,forum_id,message_id,user_id,content,created_at,edited,is_parent) VALUES (3,1,null,'client1','Mensaje de prueba 3','2020-05-17 14:23:25',false,true);
-- 
-- INSERT INTO messages(id,forum_id,message_id,user_id,content,created_at,edited,is_parent) VALUES (7,2,null,'client2','Mensaje de prueba','2020-05-17 14:23:25',false,true);
-- 
-- /*Respuestas*/
-- INSERT INTO messages(id,forum_id,message_id,user_id,content,created_at,edited,is_parent) VALUES (4,1,1,'client5','Respuesta de prueba','2020-05-15 15:23:25',false,false);
-- INSERT INTO messages(id,forum_id,message_id,user_id,content,created_at,edited,is_parent) VALUES (5,1,1,'client5','Respuesta de prueba 2','2020-05-15 16:23:25',false,false);
-- INSERT INTO messages(id,forum_id,message_id,user_id,content,created_at,edited,is_parent) VALUES (6,1,2,'client5','Respuesta de prueba 3','2020-05-16 15:23:25',false,false);
/* DIET */

-- INSERT INTO diets(id,name,description,kcal) VALUES (1, 'Mantenimiento', 'Algo', 2000);
-- INSERT INTO trainings(id,initial_date,end_date,name,diet_id) VALUES (2, '2020-02-01', '2020-02-20', 'Entrenamiento2', 2);
-- INSERT INTO trainings(id,initial_date,end_date,name,diet_id) VALUES (3, '2020-03-01', '2020-03-10', 'Entrenamiento3', 3);
-- INSERT INTO trainings(id,initial_date,end_date,name,diet_id) VALUES (4, '2020-04-01', '2020-04-20', 'Entrenamiento4', 4);

/* CLIENT_DIET */

-- INSERT INTO clients_diets(client_id,diets_id) VALUES(2,1);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES(2,2);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES(2,3);
-- INSERT INTO clients_trainings(client_id,trainings_id) VALUES(2,4);

