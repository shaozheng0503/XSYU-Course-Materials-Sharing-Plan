public class MultiplicationTable {
    public static void main(String[] args) {
        // 使用两个for循环来构建乘法表
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "×" + i + "=" + (i * j) + "\t");
            }
            // 每完成一行的打印后换行
            System.out.println();
        }
    }
}