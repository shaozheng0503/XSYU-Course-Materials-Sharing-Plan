import java.awt.Font;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class CardReceiveSocketJFrame extends JFrame implements Runnable {
    private JTextArea text;                      // 文本区域
    private Socket socket;
    private static final Logger logger = LoggerFactory.getLogger(CardReceiveSocketJFrame.class);

    public CardReceiveSocketJFrame(String name, String host, int port) throws IOException {
        super(name + "  " + InetAddress.getLocalHost().toString());      // 设置窗口标题为客户端名称和本地IP地址
        this.socket = new Socket(host, port);                        // 客户端连接到指定的主机和端口
        this.setTitle(this.getTitle() + " : " + socket.getLocalPort());  // 设置窗口标题显示本地端口号
        this.setBounds(800, 200, 500, 120);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 初始化文本区域并设置属性
        this.text = new JTextArea("");
        this.text.setLineWrap(true);                       // 文本区域自动换行
        this.text.setFont(new Font("Arial", Font.PLAIN, 20));
        this.getContentPane().add(this.text);
        this.setVisible(true);
        new Thread(this, name + " Thread").start();
    }

    @Override
    public void run() {
        try (DataInputStream datain = new DataInputStream(this.socket.getInputStream())) {
            while (true) {
                try {
                    final int data = datain.readInt();  // 读取整数数据
                    SwingUtilities.invokeLater(() -> {
                        text.append(data + "  ");       // 将数据追加到文本区域
                    });
                } catch (EOFException ex) {             // 数据传输结束时抛出EOFException
                    logger.info("End of data transmission");
                    break;
                }
            }
        } catch (IOException ex) {
            logger.error("An error occurred", ex);
        } finally {
            try {
                if (this.socket != null && !this.socket.isClosed()) {
                    this.socket.close();                 // 关闭Socket
                }
            } catch (IOException ex) {
                logger.error("Failed to close socket", ex);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 创建多个客户端，连接到同一服务器的不同端口
        new CardReceiveSocketJFrame("甲", "127.0.0.1", 10001);
        new CardReceiveSocketJFrame("乙", "127.0.0.1", 10002);
        new CardReceiveSocketJFrame("丙", "127.0.0.1", 10003);
        new CardReceiveSocketJFrame("丁", "127.0.0.1", 10004);
    }
}