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

INSERT INTO equipments(id,name,location) VALUES (1,'Gemelos_10000','Musculación');
INSERT INTO equipments(id,name,location) VALUES (2,'Biceps_9000','Musculación');
INSERT INTO equipments(id,name,location) VALUES (3,'Cinta de Correr','Cardio');

/* EXERCISE */

INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (1,'Gemelos', 'Arriba abajo', 50, 2, 1);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (2,'Biceps Normales', 'Arriba abajo', 50, 2, 2);
INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id) VALUES (3,'Correr', '20 minutos v=12', 200, 3, 3);

/* ROUTINE LINE */

INSERT INTO routines_lines(id,reps,series,weight,exercise_id,routine_id) VALUES (1,10,3,25.0, 1, 1);
INSERT INTO routines_lines(id,reps,series,weight,exercise_id,routine_id) VALUES (2,5,3,0.0, 2, 1);
INSERT INTO routines_lines(id,reps,series,weight,exercise_id,routine_id) VALUES (3,5,3,50.0, 1, 2);

/* CHALLENGE */

INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (1,'Challenge1','Desc challenge 1','2020-01-01','2020-01-02','Reward1',10,10,10,1);
INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES (2,'Challenge2','Desc challenge 2','2020-10-01','2020-10-02','Reward2',20,5,20,2);

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




