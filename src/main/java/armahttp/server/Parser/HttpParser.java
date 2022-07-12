package armahttp.server.Parser;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpParser {
    private String stLine;
    private List<String> headers;
    private HashMap<String, String> headersProps;
    private String body;
    private SelectionKey key;
    private Map<SocketChannel, HttpParser> httpmap;
    private String content;


}
