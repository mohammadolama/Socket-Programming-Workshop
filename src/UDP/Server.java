package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

public class Server {

    static int serverPort = 18000;
    static int maxLength = 1024;

    public static DatagramPacket readPacket(DatagramSocket datagramSocket) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(new byte[maxLength], maxLength);
        datagramSocket.receive(datagramPacket);
        return datagramPacket;
    }


    /**
     * this method can be used for simple "string" transfer.
     */
    public static void writePacket(DatagramSocket datagramSocket, String message, SocketAddress socketAddress) throws IOException {
        byte[] data = message.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, socketAddress);
        datagramSocket.send(packet);
    }

    /**
     * this method can be used to transfer "packet" .
     */
    public static void writePacket(DatagramSocket datagramSocket, DatagramPacket packet, SocketAddress socketAddress) throws IOException {
        datagramSocket.send(packet);
    }

    public static void main(String[] args) throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket(serverPort);

        DatagramPacket datagramPacket = readPacket(datagramSocket);

        String s = new String(datagramPacket.getData());

        System.out.println(s);
        String us1 = s.toUpperCase();


        DatagramPacket response = new DatagramPacket(us1.getBytes(), us1.getBytes().length, datagramPacket.getSocketAddress());
        datagramSocket.send(response);

//        2 lines above can be replaced by : writePacket(datagramSocket,us1,datagramPacket.getSocketAddress());


    }
}
