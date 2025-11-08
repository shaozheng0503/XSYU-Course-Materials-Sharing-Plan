//【例9.2】 网络发牌程序。
//（2）接收客户端

import java.io.IOException;

public class South 
{
    public static void main(String args[]) throws IOException
    {
        new CardReceiveSocketJFrame("南", "127.0.0.1", 10002);
    }
}
