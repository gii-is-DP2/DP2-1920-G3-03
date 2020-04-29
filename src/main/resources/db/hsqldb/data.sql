/* ADMIN */

INSERT INTO users(username,password,enabled) VALUES ('admin1','admin1999',TRUE);
INSERT INTO authorities VALUES (1,'admin','admin1');
INSERT INTO authorities VALUES (2,'client','admin1');
INSERT INTO admins VALUES (1, 'proyectowip@vekto.com','Vekto', 'Rino', '12345678A','admin1');
INSERT INTO clients VALUES (11, 'admin@admin.com', 'Admin', 'Admin', '12345678A',20,20.0,183.0,true,80.0,null,'admin1');

/* GUILD */

INSERT INTO guilds(id,creator,description,name,logo) VALUES (1,'client1','Here we practice Calisthenics for everyone','Calisthenics','https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/heria10-1566732385.jpg?crop=1.00xw:0.922xh;0,0.0778xh&resize=980:*');
INSERT INTO guilds(id,creator,description,name,logo) VALUES (2,'client2','Here we practice Weightlifting for everyone','Weightlifting','https://www.iwf.net/wp-content/uploads/2017/12/DSC00237.jpg');
INSERT INTO guilds(id,creator,description,name,logo) VALUES (3,'client3','Here we help you achieve your goals','Gym for Dummies','https://www.mobiefit.com/blog/wp-content/uploads/2017/06/shutterstock_326360432.jpg');

/* CLIENT */


INSERT INTO users(username,password,enabled) VALUES ('client1','client1999',TRUE);
INSERT INTO authorities VALUES (3,'client','client1');
INSERT INTO clients VALUES (1, 'marantle@yogogym.com','Martin', 'Antonio Lera', '12345678B',18,20.0,183.0,true,80.0,1,'client1');

INSERT INTO users(username,password,enabled) VALUES ('client2','client1999',TRUE);
INSERT INTO authorities VALUES (4,'client','client2');
INSERT INTO clients VALUES (2, 'fejasa@yogogym.com','Federico', 'Javier Saco', '12345678C',18,20.0,190.0,false,95.3,2,'client2');

INSERT INTO users(username,password,enabled) VALUES ('client3','client1999',TRUE);
INSERT INTO authorities VALUES (5,'client','client3');
INSERT INTO clients VALUES (3, 'juengue@yogogym.com','Julio', 'Enrique Guerrero', '12345678D',18,20.0,177.0,true,110.7,3,'client3');

INSERT INTO users(username,password,enabled) VALUES ('client4','client1999',TRUE);
INSERT INTO authorities VALUES (6,'client','client4');
INSERT INTO clients VALUES (4, 'anjomi@yogogym.com','Andrés', 'José Michelena', '12345678E',18,20.0,168.0,false,74.3, null ,'client4');

INSERT INTO users(username,password,enabled) VALUES ('client5','client1999',TRUE);
INSERT INTO authorities VALUES (7,'client','client5');
INSERT INTO clients VALUES (5, 'caresro@yogogym.com','Carmelina', 'Esteso Rodríguez', '12345678F',18,20.0,170.0,true,83.7,1,'client5');

INSERT INTO users(username,password,enabled) VALUES ('client6','client1999',TRUE);
INSERT INTO authorities VALUES (8,'client','client6');
INSERT INTO clients VALUES (6, 'soviob@yogogym.com','Sofia', 'Victoria Obeso', '12345678G',18,20.0,175.0,true,63.4,2,'client6');

INSERT INTO users(username,password,enabled) VALUES ('client7','client1999',TRUE);
INSERT INTO authorities VALUES (9,'client','client7');
INSERT INTO clients VALUES (7, 'anmamu@yogogym.com','Ana', 'Maria Muniesa', '12345678H',18,20.0,156.0,false,54.2,3,'client7');

INSERT INTO users(username,password,enabled) VALUES ('client8','client1999',TRUE);
INSERT INTO authorities VALUES (10,'client','client8');
INSERT INTO clients VALUES (8, 'lusoba@yogogym.com','Luisa', 'Soledad Basurto', '12345678I',18,20.0,163.0,true,75.6, null ,'client8');

INSERT INTO users(username,password,enabled) VALUES ('client9','client1999',TRUE);
INSERT INTO authorities VALUES (11,'client','client9');
INSERT INTO clients VALUES (9, 'caralfe@yogogym.com','Carmen', 'Alejandra Fernandez', '12345678J',18,20.0,170.0,true,72.4,1,'client9');

INSERT INTO users(username,password,enabled) VALUES ('client10','client1999',TRUE);
INSERT INTO authorities VALUES (12,'client','client10');
INSERT INTO clients VALUES (10, 'trinimon@yogogym.com','Trinidad', 'Maria Montosa', '12345678K',18,20.0,169.0,true,83.5,2,'client10');


/* TRAINER */

INSERT INTO users(username,password,enabled) VALUES ('trainer1','trainer1999',TRUE);
INSERT INTO authorities VALUES (13,'trainer','trainer1');
INSERT INTO trainers VALUES (1,  'josemadaci@yogogym.com', 'José Manuel', 'David Cifuentes', '12345678L','trainer1');

INSERT INTO users(username,password,enabled) VALUES ('trainer2','trainer1999',TRUE);
INSERT INTO authorities VALUES (14,'trainer','trainer2');
INSERT INTO trainers VALUES (2,  'jusien@yogogym.com', 'Juan', 'Simón Enciso', '12345678M','trainer2');

/* FOOD */

INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (1,'Bread',80,20,20,30,20,1);
INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (2,'Apple',57,10,20,30,20,2);
INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (3,'Steak',120,60,20,30,20,3);
INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (4,'Banana',65,20,20,30,20,4);
INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES (5,'Scrambled Eggs',100,40,20,30,20,5);

/* DIET */

INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (1, 'Dieta 1','Desc 1', 1, 1, 1, 1);
INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (2, 'Dieta 2','Desc 2', 1, 1, 1, 1);
INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (3, 'Dieta 3','Desc 3', 1, 1, 1, 1);
INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES (4, 'Dieta 4','Desc 4', 1, 1, 1, 1);

/* FOOD_DIET */

INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,1);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,2);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,3);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,4);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (1,5);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (2,1);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (3,2);
INSERT INTO diets_foods(diet_id,foods_id) VALUES (4,3);

/* TRAINING */

INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id,client_id) VALUES (1, '2020-01-01', '2020-01-14', 'Entrenamiento1', 0, 'trainer1', 1, 1);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id,client_id) VALUES (2, '2020-02-01', '2020-02-14', 'Entrenamiento2', 2, 'trainer1', 2, 1);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id,client_id) VALUES (3, '2020-03-01', '2020-03-10', 'Entrenamiento3', 0, 'trainer1', 3, 2);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id,client_id) VALUES (4, '2020-04-01', '2020-04-20', 'Entrenamiento4', 0, 'trainer2', 4, 3);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id,client_id) VALUES (5, '2020-01-01', '2020-01-10', 'Entrenamiento1', 1, 'client4', null, 4);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id,client_id) VALUES (6, '2020-02-01', '2020-02-20', 'Entrenamiento2', 1, 'client5', null, 5);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id,client_id) VALUES (7, '2020-03-01', '2020-03-10', 'Entrenamiento3', 1, 'client6', null, 6);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id,client_id) VALUES (8, '2020-04-01', '2020-04-20', 'Entrenamiento4', 1, 'client7', null, 7);

/* Always Updated Trainings */

INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id,client_id) VALUES (9, DATEADD(day, 100, CURRENT_DATE), DATEADD(day, 101, CURRENT_DATE), 'Test', 2, 'trainer1', null, 1);
INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id,client_id) VALUES (10, DATEADD(day, -7, CURRENT_DATE), DATEADD(day, 7, CURRENT_DATE), 'Entrenamiento1', 2, 'client6', null, 6);


/* ROUTINE */

INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (1,'Cardio', 'Augment resistance',3,1);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (2,'Brazos', 'Biceps redondos',3,1);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (3,'Piernas', 'Aumentar fuerza',3,2);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (4,'Resistencia', 'Aumentar resistencia',3,3);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (5,'Musculación', 'Tonificación',3,4);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (6,'Triatlon', 'Para participantes del triatlon',3,5);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (7,'Culturismo', 'Para participar en competiciones de culturismo',3,5);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (8,'Relajación', 'Descanso',3,6);

/* Routine for Update Training */

INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (9,'Routine 1', 'Desc',1,9);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (10,'Routine 2', 'Desc',2,9);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (11,'Routine 3', 'Desc',3,9);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (12,'Routine 4', 'Desc',4,9);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (13,'Routine 5', 'Desc',5,9);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (14,'Routine 6', 'Desc',6,9);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (15,'Routine 7', 'Desc',7,9);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (16,'Routine 8', 'Desc',8,9);
INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (17,'Routine 9', 'Desc',9,9);

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
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (6,'Squat','Crouch and stand up', 100, 0, 2,4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (7,'Pistol Squat','Crouch and stand up with one leg. Alter legs', 100, 0, 2,4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (8,'Calf Raises','Stand on your tiptoes. Support weight with both legs', 100, 0, 2,4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (9,'Single Leg Calf Raises','Stand on your tiptoes with one leg. Just support your weight with one log. Alter legs', 100, 0, 2, 4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (10,'Lunges','Take a step behind with one leg, then shift your weight down to kneel. Alter legs', 100, 0, 2, 4, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (11,'Curtsy Lunges','Normal Lunge but crossing legs', 100, 0,2,4, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (12,'Dead Lift','Normal Lunge but crossing legs', 100, 0, 2, 4, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (13,'Bent-Over Row','Bend your back forward. Bend knees a little bit and hold the bar horizontally at your hips. Extend your arms straight down and then up again', 100, 0, 2,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (14,'Single Arm Row','Like Bent-Over Row, but positioning varies. Back bend forward and one knee forward. One arm hold the forward knee, the other hold the bar vertically.  Extend your arm straight down and then up to the hip', 100, 0, 2,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (15,'Biceps Curl','Hold bar horizontally with both hands. Bend your arms to your chest', 100, 0, 2,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (16,'Shoulder Press','Knees a bit bend and your a bit separated. Hold bar horizontally up your chest. Extends your arms right above your head', 100, 0, 2,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (17,'Chest Press','Lie on the ground. Hold bar horizontally to your chest. Kness bent and feet planted on the floor. Extends your arms straight up and then down', 100, 0, 2,0,2);

/* Equipment 3 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (18,'Plank Disc Switch','Start by doing a plank. Place one disc next to one arm. Then move the disc next to the grabbing arm. Now move the disc to the starting place an repeat',100,0,3,0,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (19,'Disc Push Up','Make a Push up having under your hands discs. The more disc the difficultier',100,0,3,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (20,'Front Shoulder Raise','Hold the disc with both hands with your arms straight forward and the disc at chest height. Like a pendulum raise down the disc until your reach your heap. Then go up and repeat',100,0,3,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (21,'Lateral Raise','Grab with each hand a disc. Start with them at your hips, then rise up your arms until they are horizontal to the floor. Return to starting position and repeat',100,1,3,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (22,'Halo','Hold one disc with both hands with your arms slightly bend in fron of you at head height. Move the disc all around your head and return to starting position, then repeat',100,1,3,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (23,'Squat Press','Start at a crouching position holding a disc with both hands and support your hands with your knees. Stand up and hold the disc above your head. Return to starting position and repeat',100,1,3,4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (24,'Squat Reach','Start at a crouching position holding a disc with both hands having you arms straighten and the disc at chest height. Stand up and bend arms until the disc touches your chest. Return to starting position and repeat',100,1,3,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (25,'Side Bend','Hold a disc with one hand, the other at your hip. Slightly bend your upper trunk towards the disc side and then straighten up. Repeat and alternate the arm supporting the disc',100,1,3,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (26,'Overhead Press','Hold a disc with both hands and horizontal to the floor. Disc should be at neck height, then move it above your head with extended arms. Return to starting position and repeat',100,1,3,1,2);

/* Equipment 4 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (27,'Goblet Squat','Standing up, with your feet separated widely (distance should be wider than shoulders distance). Grab a dumbbell with both hands and make a squat. Return to starting position and repeat',100,1,4,3,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (28,'Dumbbell Clean','Start crouching. Both hands hold each a dumbell. Stand up and flip your wrist so now your holding the dumbbells at shoulder height. Then procede to make a squat and flip wrists again. Repeat',100,1,4,4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (29,'Bent-Over Row','Grab a dumbbell with each hand. Bend knees a bit and bend your back forward. Extend your hands to hip height, then bend arms until you reach your chest. Return to starting position and repeat',100,1,4,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (30,'One Arm Swing','One hand holds a dumbell. Start with knees bend, feet separated at shoulder width and arm extended between legs. Stand up straight and pull your arm up like a pendulum untill you reach head height. Return to starting position and repeat',100,1,4,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (31,'Dumbbell Bench Press','Lie on a flat bench holding two dumbbells over your chest with an overhand grip. Push up until your arms are straight, then lower under control',100,1,4,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (32,'Cross Body Hammer Curl','Stand up with two dumbbells, one at each hand. One at a time, curl each weight up towards your opposing shoulder. Return under control to the start position and repeat on the other side',100,1,4,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (33,'Steps-ups','Stand still with each hand grabbing a dumbbell. Stand with one feet on the elevated source, then move the other feet up and stand on it. Return to starting position one feet at a time and repeat',100,1,4,3,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (34,'Dumbbell Scaption','Standing up grab a dumbbell with each hand and raise your arms along the side untill both dumbbells are above shoulder height. Return to starting position and repeat',100,1,4,1,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (35,'Dumbell Lunge','Grab with each hand a dumbbell. Make one step, and then squat. Return to starting position, alter stepping feet and repeat',100,1,4,2,2);

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
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (51,'Sprint','Bike at the maximum speed you can handle',100,3,8,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (52,'Standing','Bike standing up and keep it up.',100,3,8,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (53,'Normal','Bike at a pace you could keep "forever"',100,3,8,4,0);

/* Equipment 9 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (54,'Normal Steps','Step on step at a time',100,3,9,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (55,'Skipping Steps','Step skipping one or two at a time',100,3,9,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (56,'Side Steps','Like the name says rotate yourself 90º and then step the steps',100,3,9,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (57,'Speed Steps','Step the fastest you can without skipping steps',100,3,9,4,0);

/* Equipment 10 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (58,'Rowing Max Intesity','Row at the fastest pace you can keeping a controlled movement. In this exercise technique is key, more than speed',100,3,10,4,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (59,'Rowing Med Intesity','Row at a pace you can keep "forever" without sacrificing technique',100,3,10,4,0);

/* No equipment*/
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (60,'Abs','Lie on the floor looking upwards. Bend knees and rotate your upper trunk to your knees. Return to starting position and repeat',100,3,null,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (61,'Plank','Lie on the floor looking downwards. Support yourself only with your bend forearms and feet. Keep a straight position',100,3,null,0,0);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (62,'Push ups','Lying with the face down by raising and lowering the body with the straightening and bending of the arms',100,3,null,0,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (63,'Squats','Crouch and stand up',100,3,null,3,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (64,'Burpees','First make a squat then place the palms of the hands on the floor in front of the feet and finally jumps back into a push-up position',100,3,null,4,2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (65,'Jumping Jacks','First start by jumping to a position with legs spread and arms raised and then return to the original position',100,3,null,4,2);

/* ROUTINE LINE */

/* Routine 1*/
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (1, null, 2, 1, 0.0, 46, 1);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (2, null, 20,1, 0.0, 45, 1);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (3, null,1,1,0.0,44,1);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (4, null,20,1,0.0,49,1);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (5, null,10,1,0.0,45,1);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (6, null,5,1,0.0,46,1);

/* Routine 2 */
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (7,10,null,3,30,15,2);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (8,10,null,5,20,20,2);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (9,20,null,2,10,31,2);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (10,null,2,2,15,37,2);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (11,10,null,3,20,43,2);

/* Routine 9 */
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (12,10,null,3,30,15,9);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (13,10,null,5,20,20,9);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (14,20,null,2,10,31,9);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (15,null,2,2,15,37,9);
INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES (16,10,null,3,20,43,9);

/* CHALLENGE */

INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (1,'Challenge1','Desc challenge 1','2020-01-01','2020-01-02','Reward1',10,10,10,1);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (2,'Challenge2','Desc challenge 2','2020-10-01','2020-10-02','Reward2',20,5,20,2);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (3,'Challenge3','Desc challenge 3','2020-10-10','2020-10-15','Reward3',30,4,40,10);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (4,'Challenge4','Desc challenge 4','2020-10-10','2020-10-15','Reward2',30,4,40,10);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (5,'Challenge5','Desc challenge 5','2018-12-12','2019-01-01','Reward5',30,4,40,10);

/* INSCRIPTION */

INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (1,3,'https://allamericanfitness.com/wp-content/uploads/2016/11/Treadmill-XR-Console.jpg',1);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (2,0,null,2);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (3,0,null,3);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (4,0,null,3);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (5,2,'https://google.com',1);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (6,1,'https://google.com',3);
INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (7,0,'https://google.com',5);

/* CLIENT_INSCRIPTION */

INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,1);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,2);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,3);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,4);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (3,5);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (4,6);
INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (1,7);

/* PHRASES */

INSERT INTO phrases(id,author,text) VALUES (1,'Enrique Reina','A veces cuando cierro los ojos no veo.');

/* TRAINER_CLIENT */

/* trainer 1 */
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (1,1);
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (1,2);
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (1,5);
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (1,6);

/* trainer 2 */
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (2,3);
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (2,4);
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (2,7);
INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (2,8);

/* CLIENT_TRAINING */

INSERT INTO clients_trainings(client_id,trainings_id) VALUES (1,1);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (1,2);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (1,9);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (2,3);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (3,4);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (4,5);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (5,6);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (6,7);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (6,10);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES (7,8);


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

