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
import Dao.DataConnect;
//import javax.swing.Action;
/**
 *
 * @author Administrator
 */
public class Start extends JFrame{
    private JPanel JP1,JP2;
    private JLabel na1,na2;
    private JLabel pw1,pw2;
    private JButton JB1;
    private JButton JB2;
    private JPasswordField password1,password2;
    private JTextField name1,name2;
    private JComponent JC;
    private JTabbedPane JT;
    private Container C;
    public Start(){
        C=this.getContentPane();
        this.setVisible(true);
        this.setLayout(new BorderLayout());
         this.setBounds(200, 300, 400, 300);
         this.setTitle("JAVA简易聊天系统1.0");
         this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         JT=new JTabbedPane();
         JP1=new JPanel();
         JP2=new JPanel();
         JB1=new JButton("登录");
         JB2=new JButton("注册");
         JT.addTab("登录",JP1);
         JT.addTab("注册",JP2);
         JP1.setLayout(null);
         JP2.setLayout(null);
         this.add(JT,BorderLayout.CENTER);
         na1=new JLabel("账户:");         
         pw1=new JLabel("密码:");        
         password1=new JPasswordField();
         name1=new JTextField();        
         na1.setBounds(50, 50, 50, 50);         
         name1.setBounds(80,70,200,20);         
         pw1.setBounds(50, 100, 50, 50);
         password1.setBounds(80,120,200,20);
         JB1.setBounds(130,150,80,40);
         //------
         na2=new JLabel("账户:");
         name2=new JTextField();
         pw2=new JLabel("密码:");
         password2=new JPasswordField();
         na2.setBounds(50, 50, 50, 50);
         name2.setBounds(80,70,200,20);
         pw2.setBounds(50, 100, 50, 50);
         password2.setBounds(80,120,200,20);
         JB2.setBounds(130,150,80,40);
         JP1.add(na1);
         JP1.add(name1);
         JP1.add(pw1);
         JP1.add(password1);
         JP1.add(JB1);
         JP2.add(na2);
         JP2.add(name2);
         JP2.add(pw2);
         JP2.add(password2);
         JP2.add(JB2);
         JP2.setVisible(true);
         JP1.setVisible(true);
         JB1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {    
                DataConnect d=new DataConnect();
                if(d.signs(name1.getText(),password1.getText())){
                  JOptionPane.showMessageDialog(JP1,"登录成功","确定 ",0); 
                  Client client=new Client(name1.getText(),Start.this);
                  client.setVisible(true);                    
                }else{
                  JOptionPane.showMessageDialog(JP1,"登录失败","确定 ",0);  
                }
            }
        });
         JB2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {    
                DataConnect d=new DataConnect();
                if(d.logins(name2.getText(),password2.getText())){
                  JOptionPane.showMessageDialog(JP2,"注册成功，请登录！","确定 ",0); 
                }else{
                   JOptionPane.showMessageDialog(JP2,"注册失败！","确定 ",0); 
                }

            }
        });
        
    }
    public static void main(String[] args){
        Start a= new Start();
        
    }
}
