package exam01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
//0410 수업 자료
public class Main {

    private static ServerSocket serverSocket;
    public static void main(String[] args) {
        System.out.println("------------------------------------------");
        System.out.println("[서버] 를 종료하려면 q 또는 Q를 입력한 후 Enter를 입력하세요");
        System.out.println("------------------------------------------");

        // TCP server start
        startServer();

        Scanner sc = new Scanner(System.in);

        while(true) {
            String key = sc.nextLine();
            if(key.equalsIgnoreCase("q")) {
                break;
            }
        }

        stopServer();

    }

    private static void startServer() {
        Thread thread = new Thread() {
            @Override
            public void run() {

                try {
                    serverSocket = new ServerSocket(50001);
                    System.out.println("[서버] 시작");

                    while(true) {
                        System.out.println("\n[서버] 연결 중..\n");

                        //연결 수락
                        Socket socket = serverSocket.accept();

                        //연결된 클라이언트 정보 받기
                        InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();

                        String clientIp = isa.getHostString();
                        System.out.println("[서버] " + clientIp + "와 연결 요청 수락");

                        //연결 종료
                        socket.close();
                        System.out.println("[서버] " + clientIp + "와 연결 종료");
                    }

                } catch (IOException e) {
                    throw new RuntimeException("[서버] 종료");
                }
            }
        };
        thread.start();

    }

    private static void stopServer() {
        try {
            serverSocket.close();
        }catch (IOException e) {
            throw new RuntimeException("[서버] 종료");
        }
    }

}