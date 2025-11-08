import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JTableExample extends JFrame{
	private DefaultTableModel model;
	private JTable table;

    public JTableExample(){
    	super("表格示例");
    	
    	model=new DefaultTableModel();
    	model.addColumn("姓名");
    	model.addColumn("性别");
    	model.addColumn("年龄");
    	model.addColumn("籍贯");
    	
    	String[] record1={"张三","男","23","四川重庆"};
    	model.addRow(record1);
    	String[] record2={"李四","男","21","浙江杭州"};
    	model.addRow(record2);
    	String[] record3={"黄五","男","17","湖南长沙"};
    	model.addRow(record3);
    	String[] record4={"刘九","女","23","新疆乌鲁木齐"};
    	model.addRow(record4);
    	table=new JTable(model);
    	getContentPane().add(new JScrollPane(table),BorderLayout.CENTER);
    	
    	setSize(300,140);
    	setVisible(true);
    }	
    public static void main(String args[]){
    	JTableExample jte=new JTableExample();
    	jte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}