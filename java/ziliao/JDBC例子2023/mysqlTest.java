import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.Statement;

import java.sql.PreparedStatement;

import java.util.Scanner;//引入这个类用于数据输入


class DBConnection{

//驱动类名
//先将mysql-connector-java-8.0.19.jar放在你使用的jdk中的jre\lib\ext文件夹中

String driver="com.mysql.cj.jdbc.Driver";

//jdbc连接串格式,最后为数据库名，JavaTest为你的数据库名称

String url="jdbc:mysql://localhost:3306/book?useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT%2B8";

//如果你不是连接本地上的数据库，localhost换成数据库运行计算机的IP或域名

//用户名和密码按照你自己数据库的设置
String user="root";

String password="123456";  

Connection  coon=null;



//构造方法中进行数据库连接

public DBConnection(){

try{

//加载驱动程序

Class.forName(driver);

coon=(Connection)DriverManager.getConnection(url,user,password);

if(!coon.isClosed()){

System.out.println("成功连接数据库！");

} 

}catch (Exception e){

e.printStackTrace();

}

}



//关闭数据库连接的方法

public void close(){

try{

this.coon.close();

System.out.println("数据库连接已关闭。");

}catch(Exception e){

e.printStackTrace();

}

}



//增加记录的方法，这一段学习使用PreparedStatement（有时称为：预编译语句。对频繁使用的相同SQL语句，可提高性能；以参数匹配方式代替Statement使用的语句拼接方式，使可读性和可维护性增强）

 public void add(String name,int age,String gender){

//向usrInfo表中插入数据

 String sql="insert into usrInfo(age,gender,username) values('"+age+"','"+gender+"','"+name+"')";  //按列名称的
 try{

PreparedStatement preStmt=(PreparedStatement)this.coon.prepareStatement(sql);

//也可以使用下面语句进行组成另外一种插入方法，按列序号的

//preStmt.setString(1, name);

//preStmt.setInt(3, age);

//preStmt.setString(2, gender);　　

    preStmt.executeUpdate();

    System.out.println("插入数据成功！");

    preStmt.close();

    }catch(Exception e){

            e.printStackTrace();

         }

  }

     

//查询记录的方法，这一段学习使用Statement

public void select(){

//查询usrInfo表中的信息

String sql="select * from usrInfo";

         try{

             Statement stmt=(Statement)this.coon.createStatement();

             ResultSet rs=(ResultSet)stmt.executeQuery(sql);//得到的是结果的集合

             System.out.println("--------------------------------");

             System.out.println("姓名"+"\t"+"年龄"+"\t"+"性别");

             System.out.println("--------------------------------");

             while(rs.next()){

                 String name=rs.getString("username");

                 int age=rs.getInt("age");

                 String gender=rs.getString("gender");

                 System.out.println(name+"\t"+age+"\t"+gender);

             }

             stmt.close();

         }catch(Exception e){

             e.printStackTrace();

         }

     }

     



//更改记录的方法 ，这一段学习使用PreparedStatement    

public void update(String name,int age){

//推荐使用这种组合方式

String sql="update usrInfo set age=? where username=?";

//String sql="update usrInfo set age="+age+" where username='"+name+"'";

try{

    PreparedStatement prestmt=(PreparedStatement)this.coon.prepareStatement(sql);

    prestmt.setInt(1,age);

    prestmt.setString(2,name);

    prestmt.executeUpdate();

             

//当然也可以使用这个组合，这一段学习使用Statement

//String sql="update usrInfo set age="+age+" where username='"+name+"'";             

//Statement stmt=(Statement)this.coon.createStatement();

//stmt.executeUpdate(sql);

 

   System.out.println("更改数据成功！");

   prestmt.close();

         }catch(Exception e){

             e.printStackTrace();

         }

     }

     

 // 删除记录的方法，这一段学习使用PreparedStatement

     public void del(String name){

         String sql="delete from usrInfo where username=?";

         try{

             PreparedStatement prestmt=(PreparedStatement)this.coon.prepareStatement(sql);

             prestmt.setString(1,name);

             prestmt.executeUpdate();

             System.out.println("删除数据成功！");

             prestmt.close();

         }catch(Exception e){

            e.printStackTrace();

         }

     }

 }

 







//在主类中对其创建实例，实例中构造方法完成数据库的连接操作，实例调用增删改查方法进行对数据库信息的操作

public class mysqlTest {

public static void main(String args[]){

Scanner in=new Scanner(System.in);

DBConnection db=new DBConnection();

  

//先查询一下表内记录

System.out.println("目前表usrInfo记录情况如下：");

db.select();

         

//插入数据

System.out.println("下面插入一条记录：");

System.out.println("输入要插入记录的姓名、年龄、性别：(输入时用回车作为结束符)");

String name=in.next();

int age=in.nextInt();

String gender=in.next();

db.add(name, age, gender);         

//再查询一下表内记录

System.out.println("目前表usrInfo记录情况如下：");

db.select();

         

//修改数据

System.out.println("下面更新一条记录：");

System.out.println("输入要更新的记录的姓名，并给出新的年龄：(输入时用回车作为结束符)");

name=in.next();

age=in.nextInt();

db.update(name,age);

//再查询一下表内记录

System.out.println("目前表usrInfo记录情况如下：");

db.select();

         

         

//删除数据

System.out.println("下面删除一条记录");

System.out.println("输入要删除的记录中的姓名：(输入时用回车作为结束符)");

name=in.next();

db.del(name); 

//再查询一下表内记录

System.out.println("目前表usrInfo记录情况如下：");

db.select();





//关闭数据库

db.close();

 }

 }