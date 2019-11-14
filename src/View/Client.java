/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;
import javax.swing.WindowConstants;
import java.util.Vector;
import Action.ClientThread;
import java.io.IOException;
/**
 *
 * @author Administrator
 */
public class Client extends JFrame {
     private JPanel JP1,JP2,JP3;
     private JLabel name,list,who;
     private JTree l;
     private JButton send,clear;
     private JTextArea chat,write,content;
     private String n,m; 
     private JScrollPane chat1,chat2;
     private ClientThread ct;
     private String ip;
     private int port;
     private String ID;
     private Start start;
     public Client(String id,Start start){
     this.ID=id;
     this.start=start;
     start.setVisible(false);
     connect();
     this.setTitle("JAVA简易聊天系统1.0");
           this.setBounds(200, 300, 700, 500); 
           JP1=new JPanel();
           JP2=new JPanel();
           JP3=new JPanel();
           JP1.setLayout(new BorderLayout());
           JP2.setLayout(new BorderLayout());
           JP3.setLayout(new BorderLayout());
           JP1.setPreferredSize(new Dimension(370,500));
           JP2.setPreferredSize(new Dimension(200,500));
           JP2.setVisible(true);
           JP1.setVisible(true);
            this.add(JP1,BorderLayout.EAST);
            this.add(JP2,BorderLayout.WEST);
            //------
            this.n="欢迎登录";
            this.m="联系人1";
            send=new JButton("发送");
            clear=new JButton("获取聊天信息并启动");
            send.setPreferredSize(new Dimension(70,50));
            name=new JLabel(n,JLabel.CENTER);
            who=new JLabel(m,JLabel.LEFT);
            list=new JLabel("获取聊天记录:",JLabel.LEFT);
            JP3.add(list, BorderLayout.NORTH);
             JP3.add(clear, BorderLayout.SOUTH);
            content=new JTextArea(50,30);
             JScrollPane jsp=new JScrollPane(content);
            jsp.setPreferredSize(new Dimension(250,380));
            chat=new JTextArea(20,30);
            chat.setLineWrap(true);
            chat1=new JScrollPane(chat); 
            chat1.setPreferredSize(new Dimension(350,280));
            write=new JTextArea("请输入内容",7,30);
            write.setLineWrap(true);
            chat2=new JScrollPane(write);
            chat2.setPreferredSize(new Dimension(350,100));
             JP2.add(jsp, BorderLayout.SOUTH);
             JP2.add(name, BorderLayout.NORTH);
             JP2.add(JP3, BorderLayout.CENTER);
             JP1.add(chat1, BorderLayout.NORTH);
             JP1.add(chat2, BorderLayout.CENTER);
             JP1.add(send,BorderLayout.SOUTH);
             this.setVisible(true); 
             send.addActionListener(new ActionListener()
            {
            @Override
                public void actionPerformed(ActionEvent e)
                { 
                   try{                      
                       ct.send(write.getText());
                       write.setText("");
                   }catch(IOException e1){
                       
                   }
                }
            });
             
             clear.addActionListener(new ActionListener()
            {
            @Override
                public void actionPerformed(ActionEvent e)
                { 
                   try{                      
                       ct.readtext();
                      clear.setText("启动中......");
                   }catch(IOException e1){
                       
                   }
                }
            });
              this.addWindowListener(new WindowAdapter() {  
                 public void windowClosing(WindowEvent e) {  
                    super.windowClosing(e);  
                    try{
                        ct.close(content.getText());
                    }catch(IOException e1){
                       
                   }
                }  
            });  
        }
            public void ShowMessage(String message){               
                    chat.append(message+"\r\n");
                    content.append(message+"\r\n");
             }
            public void ShowContent(String message){
                content.append(message+"\r\n");
            }
            private void connect(){
                   try{
                       this.ip="127.0.0.1";
                       this.port=8888;
                       this.ct=new ClientThread(ip, port, ID,Client.this); 
                       ct.start();
                     }catch(IOException e1){
                       
                   }
            }
}
