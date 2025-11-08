import javax.swing.*;
public class Circle implements ShapeInterface {
    private double radius;
    public Circle() {
        setRadius(0);
}
public Circle(double r){
        setRadius(r);
    }
    public void setRadius(double r){
        this.radius=r<0?-r:r;
    }
    public double area(){
        return Math.PI*radius*radius;
    }
public double volume(){
        return 0;
    }
    public String getName(){
        return "圆";
    }
public static void main(String args[]){
        Circle circle=new Circle(22);
        String output="\n";
        output+=circle.getName()+"\n"
                +circle.toString()+"\n面积="+circle.area()
                +"\n体积="+circle.volume();
        JOptionPane.showMessageDialog
(null,output,"形状",
JOptionPane.INFORMATION_MESSAGE);
    }
}
