/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Karim
 */
public class PeerThread extends Thread {

	private QueueingModule Q = new QueueingModule();
    private BufferedReader bufferedReader;
    private String recievedData ;
    static int count = 0;
    
    public PeerThread(Socket socket) throws IOException {
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {

    	boolean flag = true , full  ;
        while (flag) {
            try {
                JsonObject jsonObject = Json.createReader(bufferedReader).readObject();
                if (jsonObject.containsKey("username")) {
                		
                	recievedData = "[" + jsonObject.getString("username") + "]:" + jsonObject.getString("message");
                	Q.addToQueue(recievedData);
                	full = Q.getStatus();
                	System.out.println(recievedData);
                	
                	while (Q.getStatus() == false ) {
                		sleep(5000);
                    }
                }
                
            } catch (Exception e) {
                flag = false;
                interrupt();
            }
        }
    }
    
    
}
