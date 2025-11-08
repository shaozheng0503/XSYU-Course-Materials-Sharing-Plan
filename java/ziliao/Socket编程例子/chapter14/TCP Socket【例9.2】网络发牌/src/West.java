//【例9.2】 网络发牌程序。
//（2）接收客户端

import java.io.IOException;
import java.net.InetAddress;

public class West 
{
    public static void main(String args[]) throws IOException
    {
        new CardReceiveSocketJFrame("西", "127.0.0.1", 10003);
    }
}
