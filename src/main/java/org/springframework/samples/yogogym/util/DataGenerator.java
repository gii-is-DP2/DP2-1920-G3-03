package org.springframework.samples.yogogym.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DataGenerator {
	
	static Random rd = new Random();
	
	//size of 26 -> 0 - 25
	static char letters[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	static Integer  dniBaseNumber = 10000000;
	static int charIndex = 0;
	
	static int initAdminId = 1, adminId = 1;
	static int initClientId = 1, clientId = 1;
	static int initTrainerId= 1, trainerId = 1;
	static int initAuthId = 1, authId = 1;
	static int initUserId = 1, userId = 1;
	static int initGuildId = 1, guildId = 1;
	static int initForumId = 1, forumId = 1;
	static int initChallengeId = 1, challengeId = 1;
	static int initInscription = 1, inscriptionId = 1;
	static int initDietId = 1, dietId = 1;
	static int initFoodId = 1, foodId = 1;
	static int initTrainingId = 1, trainingId = 1;
	static int initRoutineId = 1, routineId = 1;
	static int initRoutineLineId = 1, routineLineId = 1;
	static int initMessageID = 1, messageId = 1;
	
	private static void GenerateData(String filePath) throws IOException
	{

		FileWriter fileWriter = new FileWriter(filePath);
		PrintWriter print = new PrintWriter(fileWriter);
		
		PrepareIndex();
		
		AddEquipmentAndExercises(print);
		
		GenerateAdmins(print, 5);
		
		GenerateTrainers(print,100);
		
		GenerateGuildsAndForum(print,10);
		
		GenerateClients(print,800);
		
		LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1);
		GenerateChallenges(print,startDate,7,100,20,65,100);
		
		//porcentajeQueHayaChallenge, porcentajeQueHagaIscription, porcentajeStatusInscription
		GenerateInscriptions(print,0.6f,0.3f,0.4f);
		
		GenerateDiets(print,1000);
		
		GenerateFoods(print,200);
		
		//porcentajeQueHayaFoodEnDieta,porcentajeDeExistaUnaFoodEnEsaDieta
		GenerateDietsFoods(print,0.5f,0.1f);
		
		startDate = LocalDate.now().plusYears(1);
		//pTrainerCreatesTraining,pHasDiet
		Map<String,List<Integer>> creatorTrainings = GenerateTrainings(print,startDate,0.7f,0.1f,5,500);
		
		GenerateRoutines(print,0.2f,20);
		
		GenerateRoutineLines(print,65,0.2f,10);
		
		Map<Integer,List<Integer>> trainersClients = GenerateTrainerClient(print, 0.5f);
		
		GenerateClientsTrainings(print,trainersClients,creatorTrainings);
		
		//MaxMsgSize, MaxAnswerToMsg
		GenerateMessages(print,0.4f,0.4f,800,500);
		
		print.close();
	}
	
	public static String getDni()
	{
		return String.valueOf(dniBaseNumber) + letters[charIndex];
	}
	
	public static void addOneToDni()
	{
		dniBaseNumber += 1;
		
		if(dniBaseNumber >= 99999999)
		{
			dniBaseNumber = 10000000;
			charIndex++;
		}
	}
	
	public static void PrepareIndex()
	{
		adminId = initAdminId;
		clientId = initClientId;
		trainerId = initTrainerId;
		authId = initAuthId;
		userId = initUserId;
		guildId = initGuildId;
		challengeId = initChallengeId;
		inscriptionId = initInscription;
		dietId = initDietId;
		foodId = initFoodId;
		trainingId = initTrainingId;
		routineId = initRoutineId;
		routineLineId = initRoutineLineId;
		messageId = initMessageID; 
	}
	
	public static void AddEquipmentAndExercises(PrintWriter print)
	{
        print.print("/* EQUIPMENT */\n");
        print.print("\n");
		print.print("INSERT INTO equipments(id,name,location) VALUES (1,'Squat-Calf','Muscle and Tonification');\n");
		print.print("INSERT INTO equipments(id,name,location) VALUES (2,'Bars 30-50 mm','Muscle and Tonification');\n");
		print.print("INSERT INTO equipments(id,name,location) VALUES (3,'Weight-Disc 30-50 mm','Muscle and Tonification');\n");
		print.print("INSERT INTO equipments(id,name,location) VALUES (4,'Dumbbells','Muscle and Tonification');\n");
		print.print("INSERT INTO equipments(id,name,location) VALUES (5,'Kettlebells','Muscle and Tonification');\n");
		print.print("INSERT INTO equipments(id,name,location) VALUES (6,'Treadmill','Cardio');\n");
		print.print("INSERT INTO equipments(id,name,location) VALUES (7,'Elliptical','Cardio');\n");
		print.print("INSERT INTO equipments(id,name,location) VALUES (8,'Spin-Bike','Cardio');\n");
		print.print("INSERT INTO equipments(id,name,location) VALUES (9,'Stair-Mill','Cardio');\n");
		print.print("INSERT INTO equipments(id,name,location) VALUES (10,'Rowing Machine','Cardio');\n");
        print.print("\n");
		print.print("/* EXERCISE */\n");
        print.print("\n");
		print.print("/* Equipment 1 */\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (1,'Squat','Crouch and stand up', 100,1,1,2,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (2,'Pistol Squat','Crouch and stand up with one leg. Alter legs', 100,3,1,2,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (3,'Calf Raises','Stand on your tiptoes. Support weight with both legs',100,1,1,3,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (4,'Single Leg Calf Raises','Stand on your tiptoes with one leg. Just support your weight with one log. Alter legs', 100,2,1,3,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (5,'Lunges','Take a step behind with one leg, then shift your weight down to kneel. Alter legs',100,3,1,2,2);\n");
        print.print("\n");
		print.print("/* Equipment 2 */\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (6,'Squat','Crouch and stand up', 100, 2, 2,4,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (7,'Pistol Squat','Crouch and stand up with one leg. Alter legs', 100, 2, 2,4,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (8,'Calf Raises','Stand on your tiptoes. Support weight with both legs', 100, 2, 2,4,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (9,'Single Leg Calf Raises','Stand on your tiptoes with one leg. Just support your weight with one log. Alter legs', 100, 2, 2, 4,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (10,'Lunges','Take a step behind with one leg, then shift your weight down to kneel. Alter legs', 100, 2, 2, 4, 2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (11,'Curtsy Lunges','Normal Lunge but crossing legs', 100, 2,2,4, 2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (12,'Dead Lift','Normal Lunge but crossing legs', 100, 2, 2, 4, 2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (13,'Bent-Over Row','Bend your back forward. Bend knees a little bit and hold the bar horizontally at your hips. Extend your arms straight down and then up again', 100, 2, 2,1,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (14,'Single Arm Row','Like Bent-Over Row, but positioning varies. Back bend forward and one knee forward. One arm hold the forward knee, the other hold the bar vertically.  Extend your arm straight down and then up to the hip', 100, 2, 2,1,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (15,'Biceps Curl','Hold bar horizontally with both hands. Bend your arms to your chest', 100, 2, 2,1,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (16,'Shoulder Press','Knees a bit bend and your a bit separated. Hold bar horizontally up your chest. Extends your arms right above your head', 100, 2, 2,0,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (17,'Chest Press','Lie on the ground. Hold bar horizontally to your chest. Kness bent and feet planted on the floor. Extends your arms straight up and then down', 100, 2, 2,0,2);\n");
        print.print("\n");
		print.print("/* Equipment 3 */\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (18,'Plank Disc Switch','Start by doing a plank. Place one disc next to one arm. Then move the disc next to the grabbing arm. Now move the disc to the starting place an repeat',100,2,3,0,0);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (19,'Disc Push Up','Make a Push up having under your hands discs. The more disc the difficultier',100,2,3,0,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (20,'Front Shoulder Raise','Hold the disc with both hands with your arms straight forward and the disc at chest height. Like a pendulum raise down the disc until your reach your heap. Then go up and repeat',100,2,3,0,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (21,'Lateral Raise','Grab with each hand a disc. Start with them at your hips, then rise up your arms until they are horizontal to the floor. Return to starting position and repeat',100,2,3,0,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (22,'Halo','Hold one disc with both hands with your arms slightly bend in fron of you at head height. Move the disc all around your head and return to starting position, then repeat',100,2,3,0,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (23,'Squat Press','Start at a crouching position holding a disc with both hands and support your hands with your knees. Stand up and hold the disc above your head. Return to starting position and repeat',100,2,3,4,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (24,'Squat Reach','Start at a crouching position holding a disc with both hands having you arms straighten and the disc at chest height. Stand up and bend arms until the disc touches your chest. Return to starting position and repeat',100,2,3,0,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (25,'Side Bend','Hold a disc with one hand, the other at your hip. Slightly bend your upper trunk towards the disc side and then straighten up. Repeat and alternate the arm supporting the disc',100,2,3,0,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (26,'Overhead Press','Hold a disc with both hands and horizontal to the floor. Disc should be at neck height, then move it above your head with extended arms. Return to starting position and repeat',100,2,3,1,2);\n");
        print.print("\n");
		print.print("/* Equipment 4 */\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (27,'Goblet Squat','Standing up, with your feet separated widely (distance should be wider than shoulders distance). Grab a dumbbell with both hands and make a squat. Return to starting position and repeat',100,2,4,3,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (28,'Dumbbell Clean','Start crouching. Both hands hold each a dumbell. Stand up and flip your wrist so now your holding the dumbbells at shoulder height. Then procede to make a squat and flip wrists again. Repeat',100,2,4,4,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (29,'Bent-Over Row','Grab a dumbbell with each hand. Bend knees a bit and bend your back forward. Extend your hands to hip height, then bend arms until you reach your chest. Return to starting position and repeat',100,2,4,1,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (30,'One Arm Swing','One hand holds a dumbell. Start with knees bend, feet separated at shoulder width and arm extended between legs. Stand up straight and pull your arm up like a pendulum untill you reach head height. Return to starting position and repeat',100,2,4,1,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (31,'Dumbbell Bench Press','Lie on a flat bench holding two dumbbells over your chest with an overhand grip. Push up until your arms are straight, then lower under control',100,2,4,1,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (32,'Cross Body Hammer Curl','Stand up with two dumbbells, one at each hand. One at a time, curl each weight up towards your opposing shoulder. Return under control to the start position and repeat on the other side',100,2,4,1,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (33,'Steps-ups','Stand still with each hand grabbing a dumbbell. Stand with one feet on the elevated source, then move the other feet up and stand on it. Return to starting position one feet at a time and repeat',100,2,4,3,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (34,'Dumbbell Scaption','Standing up grab a dumbbell with each hand and raise your arms along the side untill both dumbbells are above shoulder height. Return to starting position and repeat',100,2,4,1,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (35,'Dumbell Lunge','Grab with each hand a dumbbell. Make one step, and then squat. Return to starting position, alter stepping feet and repeat',100,2,4,2,2);\n");
        print.print("\n");
		print.print("/* Equipment 5 */\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (36,'Kettlebell Swing','Knees a bit bend forward. Feet separated a bit more then shoulder distance. Grab kettle with both hands and like a pendulum movemente move the kettle to chest height. Return to starting position and repeat',100,2,5,1,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (37,'Kettlebell One Arm Swing','Same as Kettlebell Swing but with one arm',100,2,5,1,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (38,'Kettlebell Thrusters','Each hand with a kettle. Squat and when your all standing, extend your arm fully up above your head. Return to starting position and repeat',100,2,5,1,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (39,'Kettlebell Clean and Press','Stand holding two kettlebells by your thighs, knees slightly bent and legs shoulder-width apart. In one swift movement, slightly jump off the ground and raise your arms to extend above your head',100,2,5,4,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (40,'Kettlebell Snatch','Holding a kettlebell in one hand between your legs, squat down. Drive upwards through your hips and knees as the kettlebell rises to shoulder height. Return to starting position and repeat',100,2,5,4,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (41,'Kettlebell Pistol Squad','Hold one kettlebell with both hands just under your chin. Lift one leg off the floor and squat down with the other. Return to starting position and repeat',100,2,5,3,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (42,'Kettlebell Gobel Squat','Stand with your legs slightly wider than shoulder-width apart, clasping a kettlebell in each hand in front of your chest with palms facing each other. Bend your knees and lower yourself into a squat. Return to starting position and repeat.',100,2,5,3,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (43,'Kettlebell Shoulder Press','Stand with your feet shoulder-width apart holding two kettlebells at shoulder height. Press one of the weights above your head until your arm is fully extended. Lower and repeat with the other arm.',100,2,5,1,2);\n");
        print.print("\n");
		print.print("/* Equipment 6 */\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (44,'Sprint','Run at full speed',100,2,6,4,0);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (45,'Jogging','Run at a speed that you could maintain forever',100,2,6,4,0);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (46,'Walking','Walk at a suitable speed',100,2,6,4,0);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (47,'Course-Navette','Alternate between high intensity running (85%) and medium-low intensity (60%). 1:30 min at 85% and 3:30 at 60% ',100,2,6,4,0);\n");
        print.print("\n");
		print.print("/* Equipment 7 */\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (48,'Elliptical Max Speed','Use the Ellipitical at the higuest pace you can keep',100,2,7,4,0);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (49,'Elliptical Med Speed','Use the Ellipitical at a pace you can keep forever',100,2,7,4,0);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (50,'Elliptical Min Speed','Use the Ellipitical at a pace sutiable for you and fairly slow',100,2,7,4,0);\n");
        print.print("\n");
		print.print("/* Equipment 8 */\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (51,'Sprint','Bike at the maximum speed you can handle',100,2,8,4,0);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (52,'Standing','Bike standing up and keep it up.',100,2,8,4,0);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (53,'Normal','Bike at a pace you could keep forever',100,2,8,4,0);\n");
        print.print("\n");
		print.print("/* Equipment 9 */\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (54,'Normal Steps','Step on step at a time',100,2,9,4,0);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (55,'Skipping Steps','Step skipping one or two at a time',100,2,9,4,0);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (56,'Side Steps','Like the name says rotate yourself 90º and then step the steps',100,2,9,4,0);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (57,'Speed Steps','Step the fastest you can without skipping steps',100,2,9,4,0);\n");
        print.print("\n");
		print.print("/* Equipment 10 */\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (58,'Rowing Max Intesity','Row at the fastest pace you can keeping a controlled movement. In this exercise technique is key, more than speed',100,2,10,4,0);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (59,'Rowing Med Intesity','Row at a pace you can keep forever without sacrificing technique',100,2,10,4,0);\n");
        print.print("\n");
		print.print("/* No equipment*/\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (60,'Abs','Lie on the floor looking upwards. Bend knees and rotate your upper trunk to your knees. Return to starting position and repeat',100,2,null,0,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (61,'Plank','Lie on the floor looking downwards. Support yourself only with your bend forearms and feet. Keep a straight position',100,2,null,0,0);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (62,'Push ups','Lying with the face down by raising and lowering the body with the straightening and bending of the arms',100,2,null,0,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (63,'Squats','Crouch and stand up',100,2,null,3,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (64,'Burpees','First make a squat then place the palms of the hands on the floor in front of the feet and finally jumps back into a push-up position',100,2,null,4,2);\n");
		print.print("INSERT INTO exercises(id,name,description,kcal,intensity,equipment_id,body_part,repetition_type) VALUES (65,'Jumping Jacks','First start by jumping to a position with legs spread and arms raised and then return to the original position',100,2,null,4,2);\n");
		print.print("\n");
	}
	
	public static void GenerateAdmins(PrintWriter print, int size)
	{
		print.print("/* Administrators */\n");
		for(int i = 0; i < size; i++)
		{
			print.print("INSERT INTO users(username, password, enabled) VALUES ('admin"+ userId +"', 'admin1999', TRUE);\n");
			userId++;
			print.print("INSERT INTO authorities(id, authority, username) VALUES ("+ authId +", 'admin', 'admin"+ adminId +"');\n");
			authId++;
			print.print("INSERT INTO authorities(id, authority, username) VALUES ("+ authId +", 'client', 'admin"+ adminId +"');\n");
			authId++;
			print.print("INSERT INTO admins(id, email, first_name, last_name, nif, username) VALUES ("+ adminId +", 'admin"+ i +"@yogoym.com','AdminName"+ adminId +"', 'AdminSurname', '"+ getDni() +"','admin"+ adminId +"');\n");
			print.print("INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES ("+ clientId +", 'client"+ clientId +"@yogoym.com','AdminClientName"+ clientId  +"', 'AdminClientSurname', '"+ getDni() +"', "+ (rd.nextInt(50-25)+25) +","+ (rd.nextFloat()) +",1.80, "+ (rd.nextInt(2) == 0 ? "true":"false") +", 80, null,'admin"+ adminId +"');\n");
			adminId++;
			clientId++;
			addOneToDni();
			print.print("\n");
		}
	
	}
	
	public static void GenerateTrainers(PrintWriter print,int size)
	{
		print.print("/* Trainers */\n");
		for(int i = 0; i < size; i++)
		{
			print.print("INSERT INTO users(username, password, enabled) VALUES ('trainer"+ trainerId +"', 'trainer1999', TRUE);\n");
			userId++;
			print.print("INSERT INTO authorities(id, authority, username) VALUES ("+ authId +", 'trainer', 'trainer"+ trainerId +"');\n");
			authId++;
			print.print("INSERT INTO trainers(id,email,first_name,last_name,nif,username) VALUES ("+ trainerId +", 'trainer"+ trainerId +"@yogoym.com','TrainerName"+ trainerId  +"', 'TrainerSurname','"+ getDni() + "', 'trainer"+ trainerId +"');\n");
			trainerId++;
			print.print("\n");
		}
		print.print("\n");
	}
	
	public static void GenerateGuildsAndForum(PrintWriter print, int size)
	{
		print.print("/* Guilds */\n");
		for(int i = 0; i < size; i++)
		{
			print.print("INSERT INTO guilds(id, creator, description, name, logo) VALUES ("+ guildId +", 'client"+ clientId +"', 'Description Guild"+ guildId +"', 'Guild Name"+ guildId +"','https://');\n");
			print.print("INSERT INTO forums(id,guild_id) VALUES ("+ forumId +","+ guildId +");\n");
			clientId++;
			guildId++;
			forumId++;
		}
		clientId -= guildId-1;
		print.print("\n");
	}
	
	public static void GenerateClients(PrintWriter print, final int size)
	{
		int auxGuildCount = 1;
		
		print.print("/* Clients */\n");
		for(int i = -(guildId-1); i < size; i++)
		{
			if(i < 0)
				print.print("/* Client Creator of Guild */\n");
			
			print.print("INSERT INTO users(username, password, enabled) VALUES ('client"+ clientId +"', 'client1999', TRUE);\n");
			userId++;
			print.print("INSERT INTO authorities(id, authority, username) VALUES ("+ authId +", 'client', 'client"+ clientId +"');\n");
			authId++;
			
			if(auxGuildCount % guildId == 1)
				auxGuildCount = 1;
			
			if(auxGuildCount != guildId)
				print.print("INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES ("+ clientId +", 'client"+ clientId +"@yogoym.com','ClientName"+ clientId  +"', 'ClientSurname','"+ getDni() + "', "+ (rd.nextInt(50-25)+25) +","+ (rd.nextFloat()) +",1.80, "+ (rd.nextInt(2) == 0 ? "true":"false") + ", 80,"+ auxGuildCount +", 'client"+ clientId +"');\n");
			else
				print.print("INSERT INTO clients(id, email, first_name, last_name, nif, age, fat_percentage, height, is_public, weight, guild_id, username) VALUES ("+ clientId +", 'client"+ clientId +"@yogoym.com','ClientName"+ clientId  +"', 'ClientSurname','"+ getDni() + "', "+ (rd.nextInt(50-25)+25) +","+ (rd.nextFloat()) +", 1.80, "+ (rd.nextInt(2) == 0 ? "true":"false") + ", 80, null, 'client"+ clientId +"');\n");
			
			clientId++;
			auxGuildCount++;
			addOneToDni();
			print.print("\n");
		}
	}
	
	public static void GenerateChallenges(PrintWriter print, LocalDate startDate, int duration, int maxPoints, int minPoints, int exerciseNum, final int size)
	{		
		LocalDate endDate = startDate.plusDays(duration-1);
		
		print.print("/* Challenges */\n");
		for(int i = 0; i < size; i++)
		{
			String sDate = startDate.getYear() + "-" + startDate.getMonthValue() + "-" + startDate.getDayOfMonth();
			String eDate = endDate.getYear() + "-" + endDate.getMonthValue() + "-" + endDate.getDayOfMonth();
			
			print.print("INSERT INTO challenges(id,name,description,initial_date,end_date,reward,points,reps,weight,exercise_id) VALUES ("+ challengeId +",'Challenge"+ challengeId +"','Desc challenge "+ challengeId +"','"+ sDate +"','"+ eDate +"','Reward"+ challengeId +"', "+ (rd.nextInt(maxPoints - minPoints) + minPoints) +", 4, 40, "+ (rd.nextInt(exerciseNum - 1 ) + 1)+");");
			
			startDate = endDate.plusDays(1);
			endDate = endDate.plusDays(duration-1);
			
			challengeId++;
			print.print("\n");
		}
	
		print.print("\n");
	}
	
	public static void GenerateInscriptions(PrintWriter print, float pChallengeInscription, float pInscriptionCompleted, float pClientParticipation)
	{
		Random rd = new Random();
		int status = 2;
		
		print.print("/* Inscriptions */\n");
		for(int chId = initChallengeId; chId < challengeId; chId++)
		{
			print.print("/* Inscriptions Challenge "+ chId +" */\n");
			if(rd.nextFloat() < pChallengeInscription)
			{
				for(int clId = 1; clId < clientId; clId++)
				{
					if(rd.nextFloat() < pClientParticipation)
					{
						if(rd.nextFloat() < pInscriptionCompleted)
							status = rd.nextInt(4);
						
						print.print("INSERT INTO inscriptions(id,status,url,challenge_id) VALUES ("+ inscriptionId +","+ status +",'https://',"+ chId +");\n");
						print.print("INSERT INTO clients_inscriptions(client_id,inscriptions_id) VALUES ("+ clId +","+ inscriptionId +");\n");
						
						inscriptionId++;
					}
				}
			}
			print.print("\n");
		}
		print.print("\n");							
	}
	
	public static void GenerateDiets(PrintWriter print, int size)
	{
		print.print("/* Diets */\n");
		int j = 1;
		
		for (int i = 0; i < size; i++)
		{
			print.print("INSERT INTO diets(id,name,description,kcal,protein,fat,carb) VALUES ("+ dietId +", 'Dieta " + dietId + "','Desc " + dietId + "'," + (rd.nextInt(500-200)+200) + "," + (rd.nextInt(5-1)+1) + "," + (rd.nextInt(5-1)+1) + "," + (rd.nextInt(5-1)+1) + ");\n");
			
			if(j > 3)
				j = 1;
			else
				j++;
			
			dietId++;
		}
		
		print.print("\n");
	}
	
	public static void GenerateFoods(PrintWriter print, int size)
	{
        print.print("/* Foods */\n");
		
        int j = 1;
		
        for (int i = 0; i < size; i++)
        {
        	print.print("INSERT INTO foods(id,name,kcal,protein,fat,carb,weight,food_type) VALUES ("+ foodId + ", 'Food " + foodId + "'," + (rd.nextInt(500-200)+200) + "," + (rd.nextInt(5-1)+1) + "," +  (rd.nextInt(5-1)+1) + "," +  (rd.nextInt(5-1)+1) + "," +  (rd.nextInt(5-1)+1) + "," + j + ");\n");
		
			if(j > 3)
				j = 1;
			else
				j++;
				
			foodId++;
        }
		print.print("\n");
	}
	
	public static void GenerateDietsFoods(PrintWriter print, float pFoodInDiet, float pInsertFoodInDiet)
	{
		for (int dId = initDietId; dId < dietId; dId++)
        {
			print.print("/* Foods in Diet "+ dId + "*/\n");
			
			if(rd.nextFloat() < pFoodInDiet)
        	{  
				for (int fId = 1; fId < foodId; fId++)
        		{
					if(rd.nextFloat() < pInsertFoodInDiet)
						print.print("INSERT INTO diets_foods(diet_id,foods_id) VALUES ("+ dId +","+ fId +");\n");
        		}
        	}
			
			print.print("\n");
        }
	}
	
	public static Map<String,List<Integer>> GenerateTrainings(PrintWriter print, LocalDate startDate, float pTrainerCreatesTraining, float pHasDiet, int interval, int maxTrainings)
	{
		
		 Map<String,List<Integer>>creatorTrainings = new TreeMap<>();
			
		  print.print("/* Trainings */\n");
		  
		  LocalDate startDate_aux = startDate;
		  LocalDate endDate = startDate.plusDays(interval);
		  
	      int permisos = 0;
	      int dietId_aux = 1;
	  
	      print.print("/* Trainings by Trainers*/\n");
	      for(int tId = initTrainerId; tId < trainerId; tId++)
	      {
	    	  print.print("/* Trainer"+ tId +"*/\n");
	    	  
	    	  if(rd.nextFloat() < pTrainerCreatesTraining)
	    	  {
	    		  List<Integer> trainingIds = new ArrayList<>();
	    		  
	    		  for(int i = 0; i < rd.nextInt(maxTrainings-1)+1; i++)
	    		  {
	    			  String sDate = startDate_aux.getYear() + "-" + startDate_aux.getMonthValue() + "-" + startDate_aux.getDayOfMonth() + " 00:00:00.0";
	    			  String eDate = endDate.getYear() + "-" + endDate.getMonthValue() + "-" + endDate.getDayOfMonth() + " 00:00:00.0";
	    			  
	    			  permisos = rd.nextInt(2)== 1 ? 2 : 0;
	    			  
	    			  if(rd.nextFloat() < pHasDiet && dietId_aux < dietId)
	    			  {
	    				  print.print("INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES " + "(" + trainingId + ", '" + sDate +"','"+ eDate +"','Training" + trainingId + "', " + permisos + ", 'trainer" + tId + "', " + dietId_aux + ");\n");
	    				  dietId_aux++;
	    			  }
	    			  else
	    			  {
	    				  print.print("INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES " + "(" + trainingId + ", '" + sDate +"','"+ eDate +"','Training" + trainingId + "', " + permisos + ", 'trainer" + tId + "', null);\n");
	    			  }
	    			  startDate_aux = endDate.plusDays(1);
	    			  endDate = startDate_aux.plusDays(interval);
	    			  
	    			  trainingIds.add(trainingId);
	    			  trainingId++;	    			  
	    		  }
	    		  
	    		  startDate_aux = startDate;
	    		  endDate = startDate.plusDays(interval);
	    		  
	    		  creatorTrainings.put("trainer" + tId , trainingIds);
	    	  }
	    	  
	    	  print.print("\n");
	      }
	      
	      print.print("/* Trainings by Clients*/\n");
	      for(int cId = initClientId; cId < clientId; cId++)
	      {
	    	  print.print("/* Client"+ cId +"*/\n");
	    	 	    	  
	    	  if(rd.nextFloat() < pTrainerCreatesTraining)
	    	  {
	    		  List<Integer> trainingIds = new ArrayList<>();
	    		  
	    		  for(int i = 0; i < rd.nextInt(maxTrainings-1)+1; i++)
	    		  {
	    			  String sDate = startDate_aux.getYear() + "-" + startDate_aux.getMonthValue() + "-" + startDate_aux.getDayOfMonth() + " 00:00:00.0";
	    			  String eDate = endDate.getYear() + "-" + endDate.getMonthValue() + "-" + endDate.getDayOfMonth() + " 00:00:00.0";
	    			  
	    			  if(rd.nextFloat() < pHasDiet && dietId_aux < dietId)
	    			  {
	    				  print.print("INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES " + "(" + trainingId + ", '" + sDate +"','"+ eDate +"','Training" + trainingId + "', " + (rd.nextInt(2)+1) + ", 'client" + cId + "', " + dietId_aux + ");\n");
	    				  dietId_aux++;
	    			  }
	    			  else
	    			  {
	    				  print.print("INSERT INTO trainings(id,initial_date,end_date,name,editing_permission,author,diet_id) VALUES " + "(" + trainingId + ", '" + sDate +"','"+ eDate +"','Training" + trainingId + "', " + (rd.nextInt(2)+1) + ", 'client" + cId + "', null);\n");
	    			  }
	    			  startDate_aux = endDate.plusDays(1);
	    			  endDate = startDate_aux.plusDays(interval);
	    			  
	    			  trainingIds.add(trainingId);
	    			  trainingId++;	    			  
	    		  }
	    		  
	    		  startDate_aux = startDate;
	    		  endDate = startDate.plusDays(interval);
	    		  
	    		  creatorTrainings.put("client" + cId , trainingIds);
	    	  }
	    	  
	    	  print.print("\n");
	      }
	      
	      print.print("\n");
	      return creatorTrainings;
	}
	
	public static void GenerateRoutines(PrintWriter print, float pHasRoutines, int maxRoutinesPerTraining)
	{
		print.print("/* Routines */\n");
		for(int tId = 1;  tId < trainingId; tId++)
		{
			print.print("/* Routines of Training"+ tId +" */\n");
			
			if(rd.nextFloat() < pHasRoutines)
			{
				for(int i = 0; i < rd.nextInt(maxRoutinesPerTraining-1)+1; i++)
				{
					
					print.print("INSERT INTO routines(id,name,description,reps_per_week,training_id) VALUES (" + routineId + ",'Routine " + routineId + "', 'Desc Routine"+ routineId +"',"+ (rd.nextInt(20-1)+1) +"," + tId + ");\n");
					routineId++;
				}
			}
			print.print("\n");
		}
	}
	
	public static void GenerateRoutineLines(PrintWriter print, int exerciseSize, float pHasRoutineLines, int maxSize)
	{
		print.print("/* Routine Lines */\n");
		for(int rId = initRoutineId; rId < routineId; rId++)
		{
			print.print("/* Routine Line from Routine"+ rId +"*/\n");
			if(rd.nextFloat() < pHasRoutineLines)
			{
				for(int i = 0;  i < rd.nextInt(maxSize-1)+1; i++)
				{
					print.print("INSERT INTO routines_lines(id,reps,time,series,weight,exercise_id,routine_id) VALUES ("+ routineLineId + ","+ (rd.nextInt(10-1)+1) +", "+ (rd.nextInt(10-1)+1) +", "+ (rd.nextInt(20-1)+1) +", "+ (rd.nextInt(50-1)+1) +"," + (rd.nextInt(exerciseSize-1)+1) + "," + rId + ");\n");
					routineLineId++;
				}
			}
			print.print("\n");
		}
		print.print("\n");
	}
	
	public static Map<Integer,List<Integer>> GenerateTrainerClient(PrintWriter print, float pClientIsTrained)
	{
		Map<Integer,List<Integer>> res = new TreeMap<>();
		
		int trainerId_aux = 1;
		print.print("/* Trainer_Clients*/\n");
		for(int cId = initClientId; cId < clientId; cId++)
		{	
			if(rd.nextFloat() < pClientIsTrained)
			{
				trainerId_aux = cId%(trainerId-1) + 1;
				print.print("INSERT INTO trainers_clients(trainer_id,clients_id) VALUES ("+ trainerId_aux +", "+ cId +");\n");
				
				if(res.containsKey(trainerId_aux))
				{
					res.get(trainerId_aux).add(cId);
				}
				else
				{
					List<Integer> aux = new ArrayList<>();
					aux.add(cId);
					res.put(trainerId_aux, aux);
				}
			}
		}
		print.print("\n");
		
		return res;
	}
	
	public static void GenerateClientsTrainings(PrintWriter print, Map<Integer,List<Integer>> trainerClients, Map<String,List<Integer>> creatorTrainings)
	{
		List<String> index = creatorTrainings.keySet().stream().collect(Collectors.toList());
		
		print.print("/* Client_Trainings */\n");
		
		for(int i = 0; i < creatorTrainings.size(); i++)
		{
			
			String clientId = index.get(i);
			
			if(clientId.contains("client"))
			{	
				clientId = index.get(i).split("client")[1];
				
				for(int j = 0;  j < creatorTrainings.get(index.get(i)).size(); j++)
				{		
					int trainingId_aux = creatorTrainings.get(index.get(i)).get(j);
					print.print("INSERT INTO clients_trainings(client_id,trainings_id) VALUES ("+ clientId +", "+ trainingId_aux +");\n");
					
				}
			}
		}
		
		print.print("\n");
	}
	
	public static void GenerateMessages(PrintWriter print, float pForumWithMsg, float pMessageWithAnswer, int maxMsgSize, int maxAnswerSizeFromMsg)
	{
		
		print.print("/* Messages */\n");
		
		int clientId_aux = 0;
		int parentMsgId = 0;
		String msgDate = LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-" + LocalDate.now().getDayOfMonth();
		
		for(int fId = initForumId; fId < forumId; fId++)
		{			
			if(rd.nextFloat() < pForumWithMsg)
			{	
				for(int i = 0;  i < rd.nextInt(maxMsgSize-1)+1; i++)
				{
					print.print("/* Messages Forum "+ fId +"*/\n");
					
					clientId_aux = rd.nextInt(clientId-adminId)+adminId;
					print.print("INSERT INTO messages(id,forum_id,message_id,user_id,content,created_at,edited,is_parent) VALUES (" + messageId + "," + fId + ",null,'client" + clientId_aux + "','Mensaje de prueba del cliente " + clientId_aux + "','" + msgDate + "',false,true);\n");
					
					parentMsgId = messageId;
					
					messageId++;
					
					print.print("\n");
					print.print("/* Answer to Message"+ parentMsgId +"*/\n");
					
					for(int j = 0; j < rd.nextInt(maxAnswerSizeFromMsg-1)+1; j++)
					{
						if(rd.nextFloat() < pMessageWithAnswer)
						{
							clientId_aux = rd.nextInt(clientId-adminId)+adminId;
							print.print("INSERT INTO messages(id,forum_id,message_id,user_id,content,created_at,edited,is_parent) VALUES (" + messageId + "," + fId + ","+ parentMsgId +",'client" + clientId_aux + "','Mensaje de Respuesta del cliente " + clientId_aux + " al mensaje "+ parentMsgId +"','" + msgDate + "',false,false);\n");
							messageId++;
						}
					}
					
					print.print("\n");
				}
			}
			
			print.print("\n");
		}
		
		print.print("\n");
	}
	
	public static void main(String[] args)
	{
		String filePath = "GenerateData.sql";
		
		try
		{
			GenerateData(filePath);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
