package server_client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NetworkClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(8080));
        System.out.println(
                "Connected witch client! Local address: "
                        + socketChannel.getLocalAddress() +
                        "\nRemote address: " + socketChannel.getRemoteAddress());

        String message = new Scanner(System.in).nextLine();
        ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());

        socketChannel.write(byteBuffer);
        socketChannel.close();
    }
}
