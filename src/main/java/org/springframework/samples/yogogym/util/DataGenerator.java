package org.springframework.samples.yogogym.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
	
	static Random rd = new Random();
	
	//size of 26 -> 0 - 25
	static char letters[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	static Integer  dniBaseNumber = 10000000;
	static int charIndex = 0;
	
	static int adminId = 1;
	static int clientId = 1;
	static int trainerId = 1;
	static int authId = 1;
	static int userId = 1;
	static int guildId = 1;
	static int challengeId = 1;
	static int inscriptionId = 1;
	
	private static void GenerateData(String filePath) throws IOException
	{

		FileWriter fileWriter = new FileWriter(filePath);
		PrintWriter print = new PrintWriter(fileWriter);
		
		GenerateAdmins(print, 5);
		
		GenerateTrainers(print,5);
		
		GenerateGuilds(print,3);
		
		GenerateClients(print,10);
		
		LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1);
		GenerateChallenges(print,startDate,7,100,20,65,200);
		
		//porcentajeQueHayaChallenge, porcentajeQueHagaIscription, porcentajeStatusInscription
		GenerateInscriptions(print,0.6f,0.3f,0.4f);
		
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
	
	public static void GenerateGuilds(PrintWriter print, int size)
	{
		print.print("/* Guilds */\n");
		for(int i = 0; i < size; i++)
		{
			print.print("INSERT INTO guilds(id, creator, description, name, logo) VALUES ("+ guildId +", 'client"+ clientId +"', 'Description Guild"+ guildId +"', 'Guild Name"+ guildId +"','https://');\n");
			clientId++;
			guildId++;
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
		for(int chId = 1; chId < challengeId; chId++)
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
