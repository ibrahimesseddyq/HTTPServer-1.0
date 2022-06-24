package armahttp.server;

import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ArmaHttpServer implements Runnable {
    private static Logger logger = Logger.getLogger(ArmaHttpServer.class);
    private String basePath;
    private int port;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    @Override
    public void run()  {
        while(true){
            try{
            int num = selector.select();
            if(num<= 0)
                continue;
        } catch (IOException e){
                logger.error("Server error");
        }
            Iterator<SelectionKey> iterator= selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(!key.isValid())
                    continue;
                try{
                    if(key.isAcceptable())
                        accept(key);
                    if(key.isReadable())
                        read(key);
                    if(key.isWritable())
                        write(key);
                }catch (Exception e){

                }
            }
        }
    }

    public void init(int port, String basePath) throws IOException {
        this.port = port;
        this.basePath = basePath;
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("localhost",this.port));
        selector = Selector.open();
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        logger.info("Server is boot " + serverSocketChannel.toString());
    }
    public void accept(SelectionKey key) throws IOException{
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel channelSocket= channel.accept();
        channelSocket.configureBlocking(false);
        channelSocket.register(selector,SelectionKey.OP_READ);

    }
    public void read(SelectionKey key){}
    public void write(SelectionKey key){}

}
