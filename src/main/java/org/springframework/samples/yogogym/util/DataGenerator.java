package org.springframework.samples.yogogym.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

        int clientId = lastClientId + 1;
        int messageId = lastMessageId + 1;
        int forumId = 1;

        FileWriter fileWriter = new FileWriter(path);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for(int i = 0; i < guildNumber*10; i++){

            if(forumId==guildNumber+1){
                forumId=0;
            }

            if(forumId!=0){
            	printWriter.print("//MESSAGE " + messageId + "\n");
            	printWriter.print("INSERT INTO messages(id,forum_id,message_id,user_id,content,created_at,edited,is_parent) VALUES (" + messageId + "," + forumId + ",null,'client" + clientId + "','Mensaje de prueba del cliente " + clientId + "','2020-05-24 21:00:00',false,true);\n\n");

                int parentMessage = messageId;
                int clientIdAnswer = clientId+4;

                messageId++;

                for(int j = 0; j < answerNumber; j++){
                	printWriter.print("INSERT INTO messages(id,forum_id,message_id,user_id,content,created_at,edited,is_parent) VALUES (" + messageId + "," + forumId + "," + parentMessage + ",'client" + clientIdAnswer + "','Respuesta de prueba del cliente " + clientIdAnswer + "','2020-05-25 21:00:00',false,false);\n");

                    messageId++;
                    clientIdAnswer = clientIdAnswer+4;
                }

                printWriter.printf("\n");
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
