package exam04;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class NewsClient {
    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket();

            //구독할 뉴스 주제 보내기
            String data = "스포츠";
            byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
            DatagramPacket sendPacket = new DatagramPacket(bytes, 0, bytes.length, new InetSocketAddress("localhost", 50001));
            datagramSocket.send(sendPacket);

            while(true) {
                DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
                datagramSocket.receive(receivePacket);

                String news = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
                System.out.println(news);

                //10번 받았을 때 while 종료
                if(news.contains("뉴스11")) {
                    System.out.println("[클라인언트] 연결 종료");
                    break;
                }

            }

            datagramSocket.close();

        } catch (Exception e) {
            throw new RuntimeException("종료");
        }
    }
}
