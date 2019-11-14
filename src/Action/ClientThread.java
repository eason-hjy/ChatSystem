/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import View.Client;
/**
 *
 * @author Administrator
 */
public class ClientThread extends Thread {
       private BufferedReader br,br1;
       private BufferedWriter bw,bw1;
       private File file;
       private String id,path;
       private Client c;
       public  ClientThread(String ip,int port,String id,Client client)throws IOException {
           Socket socket=new Socket(ip,port);
           this.path=id+".txt";
           this.file=new File(path);
           bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
           br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
           // br1=new BufferedReader(new FileReader(file));
           //readtext();
           //bw1=new BufferedWriter(new FileWriter(file));
           this.id=id;
           this.c=client;
           send(id);
           
       }
       public void send(String message)throws IOException{
           bw.write(message);
           bw.newLine();
           bw.flush();
       }
       public void writeTotext(String message)throws IOException{
           bw1=new BufferedWriter(new FileWriter(file));
           bw1.write(message);
           bw1.newLine();
           bw1.flush();
           bw1.close();
       }
       public void readtext()throws IOException{
           br1=new BufferedReader(new FileReader(file));
           String a=null;
           while((a=br1.readLine()) != null) {
              c.ShowContent(a);
           }
           br1.close();
       }
       public void close(String Message)throws IOException{
           writeTotext(Message);
           bw.close();
           br.close();
           
       }
       public void run(){
           try{
               while(true){
                 String a =br.readLine()+"\r\n";
                 c.ShowMessage(a);
                }
           } catch (IOException e) {
			e.printStackTrace();
		}	
           
       }
}
