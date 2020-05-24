package org.springframework.samples.yogogym.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
	
	/**
	 * <p>The clients must have their guilds in the following pattern (1, 2, 3, 0) from the beginning.
	 * With this pattern the guild id for the first client is 1.</p> 
	 */
	public static void DataMessageGenerator(String path, int guildNumber, int clientNumber, int lastMessageId) throws IOException {
		
		int lastClientId = 1;
		int answerNumber = clientNumber/4 - 1;
		
		DataGenerator.DataMessageGenerator(path, guildNumber, answerNumber, lastClientId, lastMessageId);
	}
	
	public static void DataMessageGenerator(String path, int guildNumber, int answerNumber, int lastClientId, int lastMessageId) throws IOException {

		FileWriter fileWriter = new FileWriter(path);
        PrintWriter printWriter = new PrintWriter(fileWriter);
		
		int clientId = lastClientId + 1;
        int messageId = lastMessageId + 1;
        int forumId = 1;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime messageDate = LocalDateTime.now();
        String messageDateStr;
        LocalDateTime answerDate;
        String answerDateStr;

        for(int i = 0; i < guildNumber*10; i++){

            if(forumId==guildNumber+1){
                forumId=0;
            }

            if(forumId!=0){
            	messageDateStr = messageDate.format(formatter);
            	
            	printWriter.print("//MESSAGE " + messageId + "\n");
            	printWriter.print("INSERT INTO messages(id,forum_id,message_id,user_id,content,created_at,edited,is_parent) VALUES (" + messageId + "," + forumId + ",null,'client" + clientId + "','Mensaje de prueba del cliente " + clientId + "','" + messageDateStr + "',false,true);\n\n");
            	
            	answerDate = messageDate;
            	answerDate = answerDate.plusMinutes(10);
            	
                int parentMessage = messageId;
                int clientIdAnswer = clientId+4;

                messageId++;

                for(int j = 0; j < answerNumber; j++){
                	answerDateStr = answerDate.format(formatter);
                	
                	printWriter.print("INSERT INTO messages(id,forum_id,message_id,user_id,content,created_at,edited,is_parent) VALUES (" + messageId + "," + forumId + "," + parentMessage + ",'client" + clientIdAnswer + "','Respuesta de prueba del cliente " + clientIdAnswer + "','" + answerDateStr + "',false,false);\n");

                    messageId++;
                    clientIdAnswer = clientIdAnswer+4;
                    answerDate = answerDate.plusMinutes(10);
                }

                printWriter.printf("\n");
                messageDate = messageDate.plusHours(1);
            }
            else{
                i--;
            }

            clientId++;
            forumId++;
        }
        
        printWriter.close();

    }
	
}
