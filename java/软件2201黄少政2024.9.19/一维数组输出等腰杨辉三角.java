public class Main {

    public static void main(String[] args) {
        int rows = 9;
        int[] currentRow = new int[rows]; // 初始化一维数组

        for (int i = 0; i < rows; i++) {
            currentRow[0] = 1; // 每一行的第一个元素都是1
            currentRow[i] = 1; // 每一行的最后一个元素也是1

            // 计算中间的元素值
            for (int j = 1; j < i; j++) {
                currentRow[j] = currentRow[j] + currentRow[j - 1];
            }

            // 打印当前行
            int number = 1;
            System.out.format("%" + (rows - i) * 2 + "s", ""); // 打印空格
            for (int j = 0; j <= i; j++) {
                System.out.format("%4d", currentRow[j]);
            }
            System.out.println();
        }
    }
}