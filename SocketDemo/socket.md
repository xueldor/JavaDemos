## 创建TCP
TCP区分服务端和客户端  
server端: ServerSocket类  
client端: Socket类  
```java
ServerSocket server = new ServerSocket(1234);

Socket socket = new Socket("127.0.0.1",1234);
```
ServerSocket只需要传一个端口,表示侦听此端口;Socket需要指定,连接哪个ip的哪个端口.


## 创建UDP 
相关类：DatagramPacket、DatagramSocket  
DatagramSocket传入PORT，表示监听本地的端口号  
DatagramPacket相当于邮寄信封，设置的是对方的IP和port  
UDP是无连接的，不存在请求连接和受理过程，因此在某种意义上无法明确区分服务器端和客户端，只是因为其提供服务而称为服务器端  
在 UDP 中，不管是服务器端还是客户端都只需要 1 个DatagramSocket,通过地址不同的DatagramPacket发给不同主机  

## 本地socket
相关类：
> android.net.LocalServerSocket  
> android.net.LocalSocket  
> android.net.LocalSocketAddress

LocalSocket是Android里面的。因为是基于`Unix domain scoket` ，而JDK是跨平台的，所以jdk里面不可能提供。  
虽然 Socket 也可以实现同一台主机的跨进程通信，但是 Unix domain socket 避开的不必要的网络协议栈，因此效率高。

LocalSocket 用于 IPC，Socket 用于网络通信。  

下面介绍一下在安卓里使用LocalSocket： 
1. 必须将SELinux关闭，或者设置为宽容模式：  
```shell
generic_x86_64:/dev # getenforce
Enforcing
generic_x86_64:/dev # setenforce 0
generic_x86_64:/dev #
generic_x86_64:/dev # getenforce
Permissive
```
否则会提示Permission denied，SELinux打印：avc: denied。  

2. 必须先运行server端，再运行client，否则client端连接时会报异常。  

3. 代码示例  
创建两个安卓项目，一个用作server，一个作为client
```java
//server端
    LocalServerSocket localServerSocket;

    try {
        localServerSocket = new LocalServerSocket("demo_address");
        LocalSocket localSocketReceiver = localServerSocket.accept();

        InputStream inputStream = localSocketReceiver.getInputStream();
        int ret = inputStream.read();
        Log.i("xxx server receive ","" + ret);

        OutputStream outStream = localSocketReceiver.getOutputStream();
        outStream.write(50);
        Log.i("xxx server send ","" + ret);
    } catch (IOException e) {
        e.printStackTrace();
    }

//client
LocalSocket localSocketSender;
localSocketSender = new LocalSocket();
try {
    localSocketSender.connect(new LocalSocketAddress("demo_address"));

    OutputStream outputStream = localSocketSender.getOutputStream();
    System.out.println("xxx client write " + 20);
    outputStream.write(20);

    InputStream inputStream = localSocketSender.getInputStream();
    int read = inputStream.read();
    System.out.println("xxx client get " + read);
} catch (IOException e) {
    e.printStackTrace();
}

```
输出：  
```shell
130|generic_x86_64:/dev # logcat -c;logcat | grep xxx
01-21 11:50:01.574  8011  8028 I System.out: xxx client write 20
01-21 11:50:01.574  7938  7966 I xxx server receive : 20
01-21 11:50:01.575  7938  7966 I xxx server send : 20
01-21 11:50:01.576  8011  8028 I System.out: xxx client get 50

```
4. LocalSocket**不需要**申请<uses-permission android:name="android.permission.INTERNET"/>。