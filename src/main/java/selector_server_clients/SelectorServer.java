package selector_server_clients;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorServer {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannelFirst = ServerSocketChannel.open();
        serverSocketChannelFirst.socket().bind(new InetSocketAddress(8098));
        serverSocketChannelFirst.configureBlocking(false);
        serverSocketChannelFirst.register(selector, SelectionKey.OP_ACCEPT);

        ServerSocketChannel serverSocketChannelSecond = ServerSocketChannel.open();
        serverSocketChannelSecond.socket().bind(new InetSocketAddress(8099));
        serverSocketChannelSecond.configureBlocking(false);
        serverSocketChannelSecond.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                System.out.println("Current key \"" + key + "\"");
                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannelDuplicate =
                            (ServerSocketChannel) key.channel();
                    System.out.println("Channel: " + key.channel());
                    SocketChannel socketChannel = serverSocketChannelDuplicate.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel socketChannelDuplicate = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer =  ByteBuffer.allocate(1024);
                    socketChannelDuplicate.read(byteBuffer);
                    String message = new String(byteBuffer.array()).trim();
                    System.out.println("Message form client \"" + message + "\"");
                }
                iterator.remove();
            }
        }
    }
}
