package TCP;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    static Scanner scanner;
    static PrintWriter printWriter;

    public static void main(String[] args) throws IOException {


        // video 1: initialization

        Socket socket=new Socket("localhost" , 10322);

        scanner = new Scanner(socket.getInputStream());
        printWriter = new PrintWriter(socket.getOutputStream());


        Scanner terminal = new Scanner(System.in);




        // ****************************************************************************************************
        // video2: send and receive data using dataStreams


//        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
//        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
//
//        String message = "Hello";
//
//        // Type - Length - Content
//        // text png
//        // T : text  , P : png , J : jpg
//        outputStream.writeChar('T');
//        outputStream.flush();
//
//        outputStream.writeInt(message.length());
//        outputStream.flush();
//
//        outputStream.write(message.getBytes());
//        outputStream.flush();
//
//
//        char c = inputStream.readChar();
//        int length = inputStream.readInt();
//        byte[] content = new byte[length];
//        inputStream.readFully(content);
//        if (c == 'T') {
//            System.out.println(new String(content));
//        } else if (c == 'P') {
//            FileOutputStream fileOutputStream = new FileOutputStream(new File("a.png"));
//            fileOutputStream.write(content);
//            fileOutputStream.flush();
//            fileOutputStream.close();
//            // save file as png!!!
//        }else if (c == 'J')
//
//        new Scanner(System.in).nextLine();











        // ****************************************************************************************************
        // video 3: multiThread

        Thread input = new Thread(() -> {
            while (true){
                String s = scanner.nextLine();
                System.out.println("TCP.Server sent: " + s);
            }
        });

        Thread output = new Thread(() ->{
            while (true){
                String s = terminal.nextLine();
                printWriter.println(s);
                printWriter.flush();
            }
        });

        input.start();
        output.start();

    }
}