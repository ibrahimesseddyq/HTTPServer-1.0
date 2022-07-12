package armahttp.server.Parser;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;

public class HttpParser {
    private static final byte[] END = {13,10,13,10};
    public HttpParser(SelectionKey key, Map<SocketChannel,HttpParser> httpmap){

    }
}
