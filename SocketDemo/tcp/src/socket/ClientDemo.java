package socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;
        try{
            socket = new Socket("127.0.0.1",1234);//server端阻塞在server.accept()，直到这一行
            InputStream in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);

            OutputStream out = socket.getOutputStream();
            PrintStream ps = new PrintStream(out);
            String tempstr = scanner.nextLine();
            while(true){
                ps.println(tempstr);
                System.out.println(br.readLine());
                if(tempstr.equals("bye")){
                    break;
                }
                tempstr = scanner.nextLine();
            }
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        scanner.close();
    }
}
