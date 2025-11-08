import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;

public class JTreeExample extends JFrame{
	DefaultMutableTreeNode root;
	DefaultMutableTreeNode node1,node2,node3;
	DefaultMutableTreeNode node11,node12,node13;
	DefaultMutableTreeNode node21,node22;
	DefaultMutableTreeNode node31,node32,node33,node34;
	DefaultTreeModel model; 
	JTree tree;
	
	Container container;
	public JTreeExample(){
		super("树的示例");
		
		root=new DefaultMutableTreeNode("根节点");
		node1=new DefaultMutableTreeNode("节点1");
		node2=new DefaultMutableTreeNode("节点2");
		node3=new DefaultMutableTreeNode("节点3");
		root.add(node1); root.add(node2); root.add(node3);
		
		node11=new DefaultMutableTreeNode("子节点11");
		node12=new DefaultMutableTreeNode("子节点12");
		node13=new DefaultMutableTreeNode("子节点13");
		node1.add(node11);node1.add(node12);node1.add(node13);
		
		node21=new DefaultMutableTreeNode("子节点21");
		node22=new DefaultMutableTreeNode("子节点22");
		node2.add(node21);node2.add(node22);
		
		node31=new DefaultMutableTreeNode("子节点31");
		node32=new DefaultMutableTreeNode("子节点32");
		node33=new DefaultMutableTreeNode("子节点33");
		node3.add(node31); node3.add(node32);node3.add(node33);
		
		model=new DefaultTreeModel(root);
		
		tree=new JTree(model);
		
		container=getContentPane();
		container.add(new JScrollPane(tree));
		pack();
		setVisible(true);
	}
	public static void main(String args[]){
		JTreeExample jte=new JTreeExample();
		jte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}