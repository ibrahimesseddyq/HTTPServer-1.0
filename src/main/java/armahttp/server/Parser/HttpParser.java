package armahttp.server.Parser;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpParser {
    private String stLine;
    private List<String> headers;
    private HashMap<String,String> headersProps;
    private String body;
    private SelectionKey key;
    private Map<SocketChannel,HttpParser> httpmap;
    private String content;
    public static class Builder{
        private SelectionKey key;
        private Map<SocketChannel,HttpParser> httpmap;
        private String content;
        public  Builder(){

        }
        public void buildKey(SelectionKey bkey){
            this.key=bkey;
        }
        public void buildMap(Map<SocketChannel,HttpParser> bhttpmap){
            this.httpmap=bhttpmap;
        }
        public void buildReq(String bcontent){
            this.content=bcontent;
        }
        public HttpParser build(){
            HttpParser parser= new HttpParser(this);
            return parser;
        }
    }

    public HttpParser(Builder parser){
        this.key = parser.key;
        this.content = parser.content;
        this.httpmap=parser.httpmap;
    }
//    public String processStatusLine(){
//        this.stLine =
//    }

}
