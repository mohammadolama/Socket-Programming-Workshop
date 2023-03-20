package TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


class ClientHandler extends Thread {


    Socket socket;

    Scanner scanner;
    PrintWriter printWriter;
    Scanner terminal;


    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        scanner = new Scanner(socket.getInputStream());
        printWriter = new PrintWriter(socket.getOutputStream());
        terminal = new Scanner(System.in);

    }

    @Override
    public void run() {
        Thread input = new Thread(() -> {
            while (true) {
                String s = scanner.nextLine();
                System.out.println("TCP.Client sent: " + s);
            }
        });

        Thread output = new Thread(() -> {
            while (true) {
                String s = terminal.nextLine();
                printWriter.println(s);
                printWriter.flush();
            }
        });

        input.start();
        output.start();
    }
}

public class Server {

    public static void main(String[] args) throws IOException {


//        Scanner input = new Scanner(System.in);
//        System.out.println("Enter desstaired port for server: ");
        int port = 10322;

        ServerSocket serverSocket = new ServerSocket(port);

        while (true){
            System.out.println("waiting for client...");

            Socket socket = serverSocket.accept();

            System.out.println("TCP.Client connected");

            ClientHandler clientHandler = new ClientHandler(socket);
            clientHandler.start();

        }



        // ****************************************************************************************************
        // video2: send and receive data using dataStreams

//
//        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
//        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
//
//        char c = inputStream.readChar();
//        int length = inputStream.readInt();
//        byte[] content = new byte[length];
//        inputStream.readFully(content);
//        if (c == 'T') {
//            System.out.println(new String(content));
//        } else if (c == 'P') {
//            // save file as png!!!
//        }
//
//        File file = new File("image7.png");
//        byte[] bytes = Files.readAllBytes(file.toPath());
//        outputStream.writeChar('P');
//        outputStream.writeInt(bytes.length);
//        outputStream.write(bytes);
//        outputStream.flush();
//
//        new Scanner(System.in).nextLine();




    }


}