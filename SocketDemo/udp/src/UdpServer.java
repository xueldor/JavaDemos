import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
    public static final int PORT = 10000;
    private static final int DATA_LEN = 4096;

    byte[] inBuff = new byte[DATA_LEN];

    //接收数据
    private DatagramPacket inPacket = new DatagramPacket(inBuff,inBuff.length);
    private DatagramPacket outPacket;

    String[] books = new String[]{"test1","test2","test3","test4","test5"};
    
    //DatagramSocket传入PORT，表示监听此端口
    //DatagramPacket相当于邮寄信封，设置的是对方的IP和port
    //UDP是无连接的，不存在请求连接和受理过程，因此在某种意义上无法明确区分服务器端和客户端，只是因为其提供服务而称为服务器端
    //在 UDP 中，不管是服务器端还是客户端都只需要 1 个DatagramSocket,通过地址不同的DatagramPacket发给不同主机
    public void init()throws IOException {
        try(DatagramSocket socket = new DatagramSocket(PORT)){
            for (int i = 0; i < 1000 ; i++ ){
                System.out.println("receive");
                socket.receive(inPacket);
                System.out.println(inBuff == inPacket.getData());//true
                System.out.println(new String(inBuff,0,inPacket.getLength()));

                byte[] sendData = books[i % 4].getBytes();
                outPacket = new DatagramPacket(sendData,sendData.length,inPacket.getSocketAddress());
                socket.send(outPacket);
            }
        }
    }

    public static void main(String[] args) {
        try {
            new UdpServer().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
