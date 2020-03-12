/* ADMIN */

INSERT INTO users(username,password,enabled) VALUES ('admin1','admin1999',TRUE);
INSERT INTO authorities VALUES (1,'admin','admin1');
INSERT INTO authorities VALUES (2,'user','admin1');
INSERT INTO admins VALUES (1, 'proyectowip@vekto.com','Vekto', 'Rino', '12345678A','admin1');

/* GUILD */

INSERT INTO guilds(id,creator,description,name) VALUES (1,'user1','Aqui hacemos calistenia','Calistenia');

/* CLIENT */

INSERT INTO users(username,password,enabled) VALUES ('user1','user1999',TRUE);
INSERT INTO authorities VALUES (3,'user','user1');
INSERT INTO clients VALUES (2, 'proyectowip@keke.com','Keke', 'Rino', '12345678B',1.83,80.0,1,'user1');

/* TRAINER */

INSERT INTO users(username,password,enabled) VALUES ('trainer1','trainer1999',TRUE);
INSERT INTO authorities VALUES (4,'trainer','trainer1');
INSERT INTO trainers VALUES (3,  'proyectowip@alvaro.com', 'Alvaro', 'Rino', '12345678C','trainer1');

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

INSERT INTO trainings(id,initial_date,end_date,name,diet_id) VALUES (1, '2020-01-01', '2020-01-10', 'Entrenamiento1', 1);
INSERT INTO trainings(id,initial_date,end_date,name,diet_id) VALUES (2, '2020-02-01', '2020-02-20', 'Entrenamiento2', 2);
INSERT INTO trainings(id,initial_date,end_date,name,diet_id) VALUES (3, '2020-03-01', '2020-03-10', 'Entrenamiento3', 3);
INSERT INTO trainings(id,initial_date,end_date,name,diet_id) VALUES (4, '2020-04-01', '2020-04-20', 'Entrenamiento4', 4);

/* ROUTINE */

INSERT INTO routines(name,description,reps_per_week) VALUES ('Piernas', 'Piernas fuertes',4);
INSERT INTO routines(name,description,reps_per_week) VALUES ('Brazos', 'Biceps redondos',3);

/* TRAINING_ROUTINE */

INSERT INTO trainings_routines(training_id,routines_id) VALUES (1,1);
INSERT INTO trainings_routines(training_id,routines_id) VALUES (1,2);
INSERT INTO trainings_routines(training_id,routines_id) VALUES (2,1);
INSERT INTO trainings_routines(training_id,routines_id) VALUES (3,2);
INSERT INTO trainings_routines(training_id,routines_id) VALUES (4,1);

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
INSERT INTO equipments(id,name,location) VALUES (11,'Skierg','Cardio');
INSERT INTO equipments(id,name,location) VALUES (12,'Recumbent-Bike','Cardio');

/* EXERCISE */

/* Equipment 1 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (1,'Squat','Shift your weight like if you were crouching', 100, 2, 1);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (2,'Single Leg Squat','Shift your weight like if you were crouching with one leg. Alter legs', 100, 2, 1);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (3,'Calf Raises','Stand on your tiptoes. Support weight with both legs', 100, 2, 1);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (4,'Single Leg Calf Raises','Stand on your tiptoes with one leg. Just support your weight with one log. Alter legs', 100, 2, 1);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (5,'Lunges','Take a step behind with one leg, then shift your weight down to kneel. Alter legs', 100, 2, 1);

/* Equipment 2 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (6,'Squat','Shift your weight like if you were crouching', 100, 2, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (7,'Single Leg Squat','Shift your weight like if you were crouching with one leg. Alter legs', 100, 2, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (8,'Calf Raises','Stand on your tiptoes. Support weight with both legs', 100, 2, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (9,'Single Leg Calf Raises','Stand on your tiptoes with one leg. Just support your weight with one log. Alter legs', 100, 2, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (10,'Lunges','Take a step behind with one leg, then shift your weight down to kneel. Alter legs', 100, 2, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (11,'Curtsy Lunges','Normal Lunge but crossing legs', 100, 2, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (12,'Dead Lift','Normal Lunge but crossing legs', 100, 2, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (13,'Bent-Over Row','Bend your back forward. Bend knees a little bit and hold the bar horizontally at your hips. Extend your arms straight down and then up again', 100, 2, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (14,'Single Arm Row','Like Bent-Over Row, but positioning varies. Back bend forward and one knee forward. One arm hold the forward knee, the other hold the bar vertically.  Extend your arm straight down and then up to the hip', 100, 2, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (15,'Biceps Curl','Hold bar horizontally with both hands. Bend your arms to your chest', 100, 2, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (16,'Shoulder Press','Knees a bit bend and your a bit separated. Hold bar horizontally up your chest. Extends your arms right above your head', 100, 2, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (17,'Chest Press','Lie on the ground. Hold bar horizontally to your chest. Kness bent and feet planted on the floor. Extends your arms straight up and then down', 100, 2, 2);

/* Equipment 3 */
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (18,'Plank Disc Switch','Start by doing a plank. Place one disc next to one arm. Then move the disc next to the grabbing arm. Now move the disc to the starting place an repeat',100,2,3);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (19,'Disc Push Up','Make a Push up having under your hands discs. The more disc the difficultier',100,2,3);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (20,'Front Shoulder Raise','Hold the disc with both hands with your arms straight forward and the disc at chest height. Like a pendulum raise down the disc until your reach your heap. Then go up and repeat',100,2,3);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (21,'Lateral Raise','Grab with each hand a disc. Start with them at your hips, then rise up your arms until they are horizontal to the floor. Return to starting position and repeat',100,2,3);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (22,'Halo','Hold one disc with both hands with your arms slightly bend in fron of you at head height. Move the disc all around your head and return to starting position, then repeat',100,2,3);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (23,'Squat Press','Start at a crouching position holding a disc with both hands and support your hands with your knees. Stand up and hold the disc above your head. Return to starting position and repeat',100,2,3);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (24,'Squat Reach','Start at a crouching position holding a disc with both hands having you arms straighten and the disc at chest height. Stand up and bend arms until the disc touches your chest. Return to starting position and repeat',100,2,3);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (25,'Side Bend','Hold a disc with one hand, the other at your hip. Slightly bend your upper trunk towards the disc side and then straighten up. Repeat and alternate the arm supporting the disc',100,2,3);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (26,'Overhead Press','Hold a disc with both hands and horizontal to the floor. Disc should be at neck height, then move it above your head with extended arms. Return to starting position and repeat',100,2,3);


/* ROUTINE LINE */

INSERT INTO routines_lines(id,reps,series,weight,exercise_id,routine_id) VALUES (1,10,3,25.0, 1, 1);
INSERT INTO routines_lines(id,reps,series,weight,exercise_id,routine_id) VALUES (2,5,3,0.0, 2, 1);
INSERT INTO routines_lines(id,reps,series,weight,exercise_id,routine_id) VALUES (3,5,3,50.0, 1, 2);

/* CHALLENGE */

INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (1,'Challenge1','Desc challenge 1','2020-01-01','2020-01-02','Reward1',10,10,10,1);

/* INSCRIPTION */

INSERT INTO inscriptions(id,status,url,challenge_id) VALUES (1,1,'http://www.google.com',1);

/* CLIENT_INSCRIPTION */

INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES (2,1);

/* PHRASES */

INSERT INTO phrases(id,author,text) VALUES (1,'Enrique Reina','A veces cuando cierro los ojos no veo.');

/* TRAINER_CLIENT */

INSERT INTO trainers_clients(trainer_id,clients_id) VALUES (3,2);

/* CLIENT_TRAINING */

INSERT INTO clients_trainings(client_id,trainings_id) VALUES(2,1);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES(2,2);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES(2,3);
INSERT INTO clients_trainings(client_id,trainings_id) VALUES(2,4);




