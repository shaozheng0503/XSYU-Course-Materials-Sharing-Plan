public class Main {
    public static void main(String[] args) {
        int i = 1, j = 1;
        while (i <= 9) {
            System.out.print(i + "*" + j + "=" + (i * j) + "\t");
            if (i == j) {
                i++;
                j = 1;       //将 j 重置于 1，保证每换一行后 j 从 1 开始
                System.out.println();
                continue;
            }
            j++;
        }
    }
}