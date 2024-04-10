package selector_server_clients;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class SelectorClient1 {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(8098));
        String message = "";

        while (!message.equals("exit")) {
            System.out.println("Input message: ");
            message = new Scanner(System.in).nextLine();

            ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
            socketChannel.write(byteBuffer);
        }
        socketChannel.close();
    }
}
