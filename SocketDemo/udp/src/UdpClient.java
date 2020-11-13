import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
    public static final int MY_PORT = 10001;
    public static final int DEST_PORT = 10000;
    public static final String DEST_IP = "127.0.0.1";
    private static final int DATA_LEN = 4096;
    byte[] inBuff = new byte[DATA_LEN];
    private DatagramPacket inPacket = new DatagramPacket(inBuff,inBuff.length);
    private DatagramPacket outPacket;

    public void init()throws IOException {
        try(DatagramSocket socket = new DatagramSocket(MY_PORT)){
            //先将buff设为零长数组。后面通过setData设置buff对象
            outPacket = new DatagramPacket(new byte[0],0, InetAddress.getByName(DEST_IP),DEST_PORT);
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextLine()){
                byte[] buff = scanner.nextLine().getBytes();
                outPacket.setData(buff);
                socket.send(outPacket);
                socket.receive(inPacket);
                System.out.println(new String(inBuff,0,inPacket.getLength()));
            }
        }
    }

    public static void main(String[] args) {
        try {
            new UdpClient().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
