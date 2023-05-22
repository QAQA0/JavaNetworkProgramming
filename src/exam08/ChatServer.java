package exam08;

import org.json.JSONObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {

    ServerSocket serverSocket;
    ExecutorService threadPool = Executors.newFixedThreadPool(100);
    Map<String, SocketClient> chatRoom = Collections.synchronizedMap(new HashMap<>());

    public void start() throws IOException {

        serverSocket = new ServerSocket(50001);
        System.out.println("[서버] 시작");

        Thread thread = new Thread(() -> {
           try{
               while(true) {
                   Socket socket = serverSocket.accept();
                   SocketClient sc = new SocketClient(ChatServer.this, socket);

               }
           } catch (Exception e) {
               throw new RuntimeException("[서버] 종료");
           }
        });

        thread.start();

    }

    public void addSocketClient(SocketClient socketClient) {

        String key = socketClient.chatName + "@" + socketClient.clientIp;
        chatRoom.put(key, socketClient);
        System.out.println("입장 : " + key);
        System.out.println("현재 채팅 인원 수 : " + chatRoom.size() + "\n");
    }

    public void removeSocketClient(SocketClient socketClient) {
        String key = socketClient.chatName + "@" + socketClient.clientIp;
        chatRoom.remove(key);
        System.out.println(key + " 나감");
        System.out.println("현재 채팅 인원 수 : " + chatRoom.size() + "\n");
    }

    public void sendToAll(SocketClient sender, String message) {
        JSONObject root = new JSONObject();
        root.put("clientIp", sender.clientIp);
        root.put("chatName", sender.chatName);
        root.put("message", message);
        String json = root.toString();

        Collection<SocketClient> socketClients = chatRoom.values();
        for(SocketClient sc:socketClients) {
            if(sc == sender) continue;
            sc.send(json);
        }

    }

    public static void main(String[] args) {
        try{
            ChatServer chatServer = new ChatServer();
            chatServer.start();

            System.out.println("-----------------------------------");
            System.out.println("[서버]를 종료하려면 q를 입력하고 Enter");
            System.out.println("-----------------------------------");

            Scanner scanner = new Scanner(System.in);

            while(true) {
                String key = scanner.nextLine();
                if(key.equals("q")) break;
            }

            scanner.close();
            chatServer.stop();

        } catch (Exception e) {
            System.out.println("[서버]" + e.getMessage());
        }
    }

    private void stop() {
        try {
            serverSocket.close();
            threadPool.shutdownNow();
            chatRoom.values().stream().forEach(sc -> sc.close());

        } catch (Exception e) {

        }
    }
}
