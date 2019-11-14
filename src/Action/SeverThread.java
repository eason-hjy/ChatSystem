/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;
import Dao.DataConnect;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import View.Sever;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import View.Sever;
/**
 *
 * @author Administrator
 */
public class SeverThread extends Thread{
 
	BufferedWriter bw;
	BufferedReader br;
	Sever sever;
	List<SeverThread> sts;
	String name;
	
	public SeverThread(Socket socket, Sever sever, List<SeverThread> sts) throws IOException {
		bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.sever=sever;
		this.sts=sts;
		name=br.readLine();
		
		
		Vector<String>  vector=new Vector<String>();
		vector.add(socket.getInetAddress().toString());
		vector.add(name);
 
		String format = "hh:mm:ss";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		String format2 = sdf.format(new Date(System.currentTimeMillis()));
		vector.add(format2);
		sever.addUser(vector);	
		MessageToAll("系统消息：用户" + name + "已经进入聊天室！");
		sever.ShowMessage("系统消息：用户" + name + "已经进入聊天室！");
	}
	
	
	private void MessageToAll(String string) {
 
            for (SeverThread severThread : sts){
                severThread.Message(string);
            }	
	}
 
	private void Message(String string) {//发送消息
		
                try {
			bw.write(string);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public void run() {
		while(true)
		{
			String readLine;
			try {
				readLine = br.readLine();
				String format = "hh:mm:ss";
				SimpleDateFormat sdf=new SimpleDateFormat(format);
				String format2 = sdf.format(new Date(System.currentTimeMillis()));
			
				MessageToAll(name +"("+format2+")");
				MessageToAll(readLine);
				sever.ShowMessage(name +"("+format2+")"+readLine);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}	
}
