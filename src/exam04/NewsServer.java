package exam04;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NewsServer {
    private static DatagramSocket datagramSocket;

    public static void main(String[] args) {
        System.out.println("------------------------------------------");
        System.out.println("[서버] 를 종료하려면 q 또는 Q를 입력한 후 Enter 를 입력하세요");
        System.out.println("------------------------------------------");

        // TCP server start
        startServer();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String key = sc.nextLine();
            if (key.equalsIgnoreCase("q")) {
                break;
            }
        }

        //UDP Server Close
        stopServer();

    }

    private static void startServer() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    datagramSocket = new DatagramSocket(50001);
                    System.out.println("[서버] 시작");

                    while(true) {
                        //클라이언트가 구독할 뉴스 주제 얻기
                        DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
                        System.out.println("클라이언트의 관심 분야를 얻는 중");

                        datagramSocket.receive(receivePacket);
                        String newsKind = new String (receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);

                        //클라이언트의 IP, Port 정보가 있는 SocketAddress 얻기
                        SocketAddress socketAddress = receivePacket.getSocketAddress();

                        //10개의 뉴스 전송
                        for(int i = 1; i < 11; i++) {
                            String data = newsKind + ": 뉴스" + i;
                            byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
                            DatagramPacket sendPacket = new DatagramPacket(bytes, 0, bytes.length, socketAddress);
                            datagramSocket.send(sendPacket);

                            Thread.sleep(1000);
                        }
                    }

                } catch(Exception e) {
                    throw new RuntimeException("[서버] 종료");
                }
            }
        };

        thread.start();
    }

    private static void  stopServer() {
        datagramSocket.close();
        System.out.println("[서버] 종료");
    }

}
