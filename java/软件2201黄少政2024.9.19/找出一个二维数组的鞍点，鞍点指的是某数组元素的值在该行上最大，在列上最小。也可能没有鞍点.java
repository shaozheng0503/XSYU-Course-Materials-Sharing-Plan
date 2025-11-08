public class Main {
    public static void main(String[] args) {
        int[][] a = new int[][] {{6,8,9},{7,4,3},{5,3,4}};
        andian.Andian(a);
    }
}

class andian {
    static int max_x;
    static int max_y;
    static void Andian(int[][] a) {
        int p = 0, q = 0, o = 0;	//p,q为鞍点的行列坐标,o为鞍点值
        for(int i=0; i<=a.length-1; i++) {	//i为行的控制变量
            o = a[i][0];
            p = i;
            q = 0;
            for(int n=1; n<=a[i].length-1; n++) {	//n为列的控制变量,此循环找出每行最大值
                if(o<a[i][n])
                    if(o!=a[i][n]){
                        o = a[i][n];
                        p = i;
                        q = n;
                    }
            }
            for(int j=0; j<=a.length-1; j++) {	//j为行的控制变量,此循环找出这行最大值是否为此列最小值
                if(o>a[j][q]) break;
                else if(j==a.length-1) {
                    System.out.println("这个二维数组的鞍点在["+p+"]["+q+"],值为:"+o);
                    return;
                }
            }
        }
        System.out.println("这个二维数组没有鞍点!");
    }
}