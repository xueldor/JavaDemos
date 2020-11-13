package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerDemo implements Runnable {
    private Socket client;

    public ServerDemo(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            InputStream in = client.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            OutputStream out = client.getOutputStream();
            PrintStream ps = new PrintStream(out);
            String tempstr;
            while ((tempstr = br.readLine()) != null) {
                if (tempstr.equals("bye")) {
                    client.close();
                    break;
                }
                ps.println(tempstr.toUpperCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("server");
        ExecutorService serverPool = Executors.newCachedThreadPool();

        //TCP有server端和client的区别。ServerSocket只需要传一个端口，Socket类表示客户端，需要指定server端的IP和PORT
        try {
            ServerSocket server = new ServerSocket(1234);
            while(true){
                try {
                    Socket client = server.accept();
                    System.out.println("accepted " + client.getInetAddress().getHostAddress());
                    serverPool.execute(new ServerDemo(client));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("server 2");
    }
}
