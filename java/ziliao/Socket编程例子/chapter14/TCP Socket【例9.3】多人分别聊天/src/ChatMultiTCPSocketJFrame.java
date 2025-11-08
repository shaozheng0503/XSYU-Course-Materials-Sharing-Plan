//【例9.3】  多人分别聊天，既是服务端也是客户端。

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

//多人分别聊天框架类，响应动作事件
public class ChatMultiTCPSocketJFrame extends JFrame implements ActionListener
{
    private String name;                         //网名
    private JComboBox<String> combox;            //输入IP地址或域名的组合框
    private JTextField text_conn;                //指定对方端口文本行
    private JTabbedPane tab;                     //选项卡窗格，每页显示与一人的聊天记录
    
    //构造方法，port指定初始等待端口，name指定网名
    public ChatMultiTCPSocketJFrame(int port, String name) throws IOException
    {
        super("聊天室  "+name+"  "+InetAddress.getLocalHost().toString());
        this.setBounds(320,240,580,240);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //以下工具栏，显示本机等待TCP连接端口、请求连接服务端的主机IP地址和端口
        JToolBar toolbar = new JToolBar();
        this.getContentPane().add(toolbar,"North"); 
        toolbar.add(new JLabel("等待端口"));
        JTextField text_local=new JTextField(port+"",4);      //本机等待端口文本行
        text_local.setHorizontalAlignment(JTextField.CENTER); //设置水平对齐方式为居中
        toolbar.add(text_local);
        text_local.setEditable(false);
        toolbar.addSeparator();                               //工具栏添加分隔线，留空
        toolbar.add(new JLabel("主机"));
        String[] address={"","127.0.0.1","202.119.162.123"};  //已知的IP地址
        toolbar.add(this.combox=new JComboBox<String>(address));
        this.combox.setEditable(true);
        toolbar.add(new JLabel("端口"));
        toolbar.add(this.text_conn=new JTextField(6));
        this.text_conn.setHorizontalAlignment(JTextField.CENTER); //设置水平对齐方式为居中
        JButton button = new JButton("请求连接");
        button.addActionListener(this);
        toolbar.add(button);
        this.getContentPane().add(this.tab=new JTabbedPane());  //添加选项卡窗格
        this.setVisible(true);

        //以下作为服务端，在port开始端口等待连接，每建立一条TCP连接，就添加1个Tab页
        this.name = name;
        while(true)
        {
            Socket socket=new ServerSocket(port).accept(); //等待接收客户端的连接申请
            this.tab.addTab(this.name, new TabPageJPanel(socket));   //tab添加新页，页中添加一个内部类面板
            this.tab.setSelectedIndex(this.tab.getTabCount()-1);//tab指定新页为选中状态
            port++;                                        //在下个端口等待下个客户端
            text_local.setText(port+"");
        }//关闭窗口时，while循环停止，关闭所有TCP连接和服务。
    }
    
    public void actionPerformed(ActionEvent event)         //单击"请求连接"按钮
    {
        if(event.getActionCommand().equals("请求连接"))
        {
            String host = (String)this.combox.getSelectedItem();  //获得主机IP地址
            int port = Integer.parseInt(this.text_conn.getText());//获得端口号，未处理数值格式异常
            try
            {
                this.tab.addTab(this.name, new TabPageJPanel(new Socket(host, port))); //tab添加新页
                this.tab.setSelectedIndex(this.tab.getTabCount()-1); //tab指定新页为选中状态
            }
            catch(UnknownHostException ex)                 //未知主机异常
            {
                JOptionPane.showMessageDialog(this, "主机IP地址错误。");
                System.out.println(ex.getClass().getName());
            }
            catch(ConnectException ex)                     //连接异常
            {
                JOptionPane.showMessageDialog(this, "IP地址或端口错误，未建立TCP连接");
                System.out.println(ex.getClass().getName());
            }
            catch(IOException ex)
            {
 //               ex.printStackTrace();
                System.out.println(ex.getClass().getName());
            }
        }
    }

    //选项卡窗格一页的面板内部类，包含一个Socket和一个线程
    private class TabPageJPanel extends JPanel implements Runnable, ActionListener
    {
        JTextArea text_receiver;                 //显示对话内容的文本区
        JTextField text_sender;                  //输入发送内容的文本行
        JButton[] buttons;                       //发送、离线、删除页按钮
        PrintWriter cout;                        //格式化字符输出流
        Socket socket;
    
        TabPageJPanel(Socket socket)             //为每个socket构造一个tab页
        {
            super(new BorderLayout());
            this.add(new JScrollPane(this.text_receiver=new JTextArea()));
            this.text_receiver.setEditable(false);
        
            //以下创建工具栏，输入内容，添加发送等命令按钮
            JToolBar toolbar = new JToolBar();
            this.add(toolbar,"South");
            toolbar.add(this.text_sender=new JTextField(16));
            this.text_sender.addActionListener(this);

            String[] strs={"发送","离线","删除页"};
            this.buttons = new JButton[strs.length];
            for(int i=0; i<this.buttons.length; i++)
            {
            	this.buttons[i] = new JButton(strs[i]);
                toolbar.add(buttons[i]);
                this.buttons[i].addActionListener(this);
            }
            this.buttons[2].setEnabled(false);   //删除页按钮无效
            
            this.socket = socket;
            (new Thread(this)).start();          //启动线程，当前面板作为线程目标对象
        }
        
        public void run()         //线程运行方法，接收对方信息，将对方发来的字符串添加到文本区
        {
            try
            {   //下句从Socket获得字节输出流，再创建格式化字符输出流，立即flush
                this.cout = new PrintWriter(this.socket.getOutputStream(),true);
                this.cout.println(name);         //发送自己网名给对方，访问外部类.this.name

                //以下两句将Socket的字节输入流转换成字符流，默认GBK字符集，再创建缓冲字符输入流
                Reader reader = new InputStreamReader(this.socket.getInputStream());
                BufferedReader bufreader = new BufferedReader(reader);
                String line=bufreader.readLine();          //接收对方网名 
                int index = tab.getSelectedIndex();        //当前页在tab中的序号，外部类.this.tab
                tab.setTitleAt(index, line);               //将对方网名设置为当前页标题 
                while((line= bufreader.readLine())!=null && !line.equals("null"))
                {
                    tab.setSelectedIndex(index);           //收到对方信息时，显示该页
                    this.text_receiver.append(line+"\r\n");
                } 
                bufreader.close();
                reader.close();
                this.cout.close();
                this.socket.close();
                this.buttons[0].setEnabled(false);         //发送按钮无效
                this.buttons[1].setEnabled(false);         //离线按钮无效
                this.buttons[2].setEnabled(true);          //删除页按钮有效
            }
            catch(IOException ex){} 
        }
        
        public void actionPerformed(ActionEvent event)     //单击tab页上的"发送"等按钮
        {
            if(event.getSource()==this.buttons[0])         //发送
            {
                this.cout.println(name+" 说："+this.text_sender.getText());
                this.text_receiver.append("我说："+this.text_sender.getText()+"\n");
                this.text_sender.setText("");
            }
            else if(event.getSource()==buttons[1])         //离线
            {
            	this.text_receiver.append("我离线\n");
                this.cout.println(name+"离线\n"+"null");
            }            
            else if(event.getSource()==this.buttons[2])
                tab.remove(this);//tab.getSelectedIndex());//删除tab当前页
        }
    }                                            //TabPageJPanel内部类结束
    
    public static void main(String[] args) throws IOException
    {
        new ChatMultiTCPSocketJFrame(10001, "花仙子");        //指定初始等待端口和网名
    }
    

    public void finalize()
    {
        //关闭ServerSocket;
    }    
}