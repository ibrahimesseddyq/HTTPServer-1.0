package armahttp.server.Parser;

import java.util.HashMap;
import java.util.function.Function;

public class RequestLine extends Request{
    HashMap<String,String> lines;

    public RequestLine(){
        super();
    }
    @Override
    public void parse(String stline){
        lines = new HashMap<>();
        String[] starray = stline.split(" ");
        this.lines.put("method",starray[0]) ;
        this.lines.put("requestedURI",starray[1]);
        String[] httpv =starray[2].split("/");
        this.lines.put("httpVersion",httpv[1]);
        if(httpv[0].equals("HTTP")){
            this.lines.put("isHttps","false");
        }else if(httpv[0].equals("HTTPS")){
            this.lines.put("isHttps","true");
        }else {
            throw new RuntimeException("This Server support Just HTTP and Theoritcly HTTPS");
        }
    }
    @Override
    public String getValue(String key){
            return this.lines.get(key);

    }

    public static void main(String[] args){
        // Test
        RequestLine rl = new RequestLine();
        rl.init("GET /api HTTPS/1.1");
        System.out.println(rl.getValue("method"));
        System.out.println(rl.getValue("requestedURI"));
        System.out.println(rl.getValue("httpVersion"));
        System.out.println(rl.getValue("isHttps"));



    }

}
