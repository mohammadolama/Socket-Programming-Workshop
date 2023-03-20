package UDP;

import java.io.IOException;
import java.net.*;

public class Client {

    static int clientPort = 19000;
    static String serverIp = "localhost";
    static int serverPort = 18000;
    static int maxLength = 50;

    public static void main(String[] args) throws IOException {

        SocketAddress serverAddress = new InetSocketAddress(serverIp , serverPort);
        DatagramSocket datagramSocket = new DatagramSocket(clientPort);

        String message = "hello world!";
        DatagramPacket datagramPacket = new DatagramPacket(
                message.getBytes() , message.getBytes().length , serverAddress);

        datagramSocket.send(datagramPacket);


        DatagramPacket response = new DatagramPacket(new byte[maxLength], maxLength);

        datagramSocket.receive(response);

        String s = new String(response.getData());

        System.out.println(s);



    }
}
