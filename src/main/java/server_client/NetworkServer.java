package server_client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NetworkServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println(
                "Connected witch client! Local address: "
                + socketChannel.getLocalAddress() +
                        "\nRemote address: " + socketChannel.getRemoteAddress());

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(byteBuffer);
        String message = new String(byteBuffer.array()).trim();
        System.out.println("Message: " + message);

        serverSocketChannel.close();
        socketChannel.close();
    }
}
