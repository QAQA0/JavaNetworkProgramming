package exam02;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoClientExample {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 50001);
            System.out.println("[클라이언트] 연결 완료");

            //send data
            String sendMessage = "나는 자바를 했다";
            OutputStream os = socket.getOutputStream();
            byte[] bytes = sendMessage.getBytes(StandardCharsets.UTF_8);
            os.write(bytes);
            os.flush();
            System.out.println("[클라이언트] 보낸 데이터: " + sendMessage);


            //receive data 1
            InputStream is = socket.getInputStream();
            bytes = new byte[2048];
            int readByteCount = is.read(bytes);
            String receiveMessage = new String(bytes, 0, readByteCount, StandardCharsets.UTF_8);
            System.out.println("[클라이언트] 받은 데이터: " + receiveMessage);


            /*
            //receive data 2
            DataInputStream dts = new DataInputStream(socket.getInputStream());
            String message = dts.readUTF();
            System.out.println("[클라이언트] 받은 데이터: " + message);
            */

            socket.close();
            System.out.println("[클라이언트] 연결 종료");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
