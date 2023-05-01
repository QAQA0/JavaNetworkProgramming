package exam03;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 50001);
            System.out.println("[클라이언트] 연결 완료");

            //보내기
            String sendMessage = "나는 자바를 했다";

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(sendMessage);
            dos.flush();
            System.out.println("[클라이언트] 보낸 데이터: " + sendMessage);


            //받기
            DataInputStream dts = new DataInputStream(socket.getInputStream());
            String receiveMessage = dts.readUTF();
            System.out.println("[클라이언트] 받은 데이터: " + receiveMessage);


            socket.close();
            System.out.println("[클라이언트] 연결 종료");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
