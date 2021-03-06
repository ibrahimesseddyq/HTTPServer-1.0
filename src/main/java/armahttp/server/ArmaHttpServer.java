package armahttp.server;

import armahttp.server.Config.Config;
import armahttp.server.Parser.HttpParser;
import armahttp.server.Util.Controller;
import armahttp.server.Util.RequestHandler;
import armahttp.server.Util.ResponseHandler;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class ArmaHttpServer implements Runnable {
//    private static Logger logger = Logger.getLogger(ArmaHttpServer.class);
    private String basePath;
    private int port;
    private int ThreadCount;
    private String serverName;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private Map<SocketChannel,HttpParser> httpmap;

    @Override
    public  void run()  {
        while(true){
            try{
            int num = selector.select();
            if(num<= 0)
                continue;
        } catch (IOException e){
//                logger.error("Server error");
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
                    if(key!=null&&key.isValid()){
                        key.cancel();
                        try {
                            key.channel().close();
                        } catch (IOException e1) {
                        }
                    }
                }
            }
        }
    }

    public void init(String basePath)  {
        Config config = new Config();
        this.port =
                Integer.parseInt(Config.getConfig("port", "1337"));
        this.basePath =
                Config.getConfig("basePath", basePath);

        this.ThreadCount =
                Integer.parseInt(Config.getConfig("threads","2"));

        this.serverName =
                Config.getConfig("name","ArmaHttp");

        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress("localhost", this.port));
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server "+serverName+"is boot " + serverSocketChannel.toString());
//            logger.info("Server is boot " + serverSocketChannel.toString());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void accept(SelectionKey key) throws IOException{
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel channelSocket= channel.accept();
        channelSocket.configureBlocking(false);
        channelSocket.register(selector,SelectionKey.OP_READ);

    }
    public void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1000);

        while( channel.read(buffer)!=-1){
            buffer.flip();
            System.out.println("Hi");
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);
            String Content = new String(bytes);
            Controller ct = new Controller(new RequestHandler(Content),new ResponseHandler(),Content);
            System.out.println(ct.getRequest().getRpHandler().toString());
            return;

        }
//        HttpParser httpParser = httpmap.get(channel);
//        if(httpParser == null){
//            httpParser = new HttpParser(key,httpmap);
//            httpmap.put(channel,httpParser);
//        }
    }
    public void write(SelectionKey key){}
    public static void main(String[] args){
        ArmaHttpServer mineServer = new ArmaHttpServer();
        mineServer.init("");
        new Thread((mineServer)).start();

    }

}
