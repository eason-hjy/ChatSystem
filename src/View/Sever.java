/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
纠错：
1.构造函数千万别加修饰
2.两个类型需要互相相互调用时，必须将本对象传参，不可NEW一个新对象
3.List,Vector等集合 在类内和类外操作都互相通用；（因为对于的内存空间都是相同）
4.切记 BufferedReader 和 BufferedWriter 不可同时打开，如果同时打开，源文件的信息就会消失
*/
package View;
import Action.SeverThread;
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
import javax.swing.WindowConstants;
/**
 *
 * @author Administrator
 */
public class Sever extends JFrame{
    	private JButton JB;
	private JTextField JT;
	private JTextArea JTA;
	private JTable JTB;
        private Vector<Vector<String>> userdata;
	private List <SeverThread> sts=new ArrayList<SeverThread>();
        public Sever(){
            	 JLabel jl1=new JLabel("端口号");
		 JLabel jl2=new JLabel("在线用户");
		 JT=new JTextField(10);
		 JT.setText("8888");
                 JT.setEditable(false);
		 JB=new JButton("服务端");
		//表头
		 Vector<String> vector=new Vector<String>();
		 vector.add("IP地址");
		 vector.add("账号");
		 vector.add("登录时间");
		 //数据
		 userdata=new Vector< Vector<String>>();
		 JTB=new JTable(userdata, vector);
		 JScrollPane jsp=new JScrollPane(JTB);
		 //聊天面版 
		 JTA=new JTextArea(10,10);
		 JScrollPane jsp1=new JScrollPane(JTA);
		 jl2.setBounds(20,10,60,30);
		 add(jl2);
		 jl1.setBounds(230,10,60,30);
		 add(jl1);
		 JT.setBounds(280, 14,100,20);
		 add(JT);
		 JB.setBounds(400, 14,80,20);
		 add(JB);
		 jsp.setBounds(20,50,200, 300);
		 add(jsp);
		 jsp1.setBounds(250, 50, 200, 300);
		 add(jsp1);
                 setSize(500, 400);
		 setLayout(null);
                 this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                 this.setTitle("JAVA简易聊天系统1.0");
                 this.setVisible(true);
                 JB.addActionListener(new ActionListener()
                {
                 @Override
                public void actionPerformed(ActionEvent e)
                { 
                    new Thread(){
                        public void run(){
                           
                        try {
			ServerSocket ss=new ServerSocket(Integer.parseInt(JT.getText()));
			JB.setText("连接中...");
			while(true)//监听客户端的连接情况
			{
			Socket socket=ss.accept();
			//sts.add(st);
			SeverThread st=new SeverThread(socket,Sever.this,sts);
			sts.add(st);
			st.start();	
			}
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}		
                        }
                    }.start();
                }
            });
        }
        public void addUser(Vector<String> vector) {//跟新用户列表
        userdata.add(vector);
        JTB.updateUI();	
	}
	public void ShowMessage(String string) {//跟新信息列表
        JTA.append(string+"\r\n");	
	}
        public static void main(String[] args){
        Sever a= new Sever();
    }
}
