package armahttp.server.Parser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RequestLine extends Request{
    HashMap<String,String> lines;

    public RequestLine(){
        super();
    }
    protected void init(String str){
        this.str = str;
        this.parse(this.str);
    }
    /*
     * function that parse the lines and load them into hashmap
     * @params strheaders String of headers
     */
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
        System.out.println(this.lines.get("method"));
        System.out.println(this.lines.get("requestedURI"));
        System.out.println(this.lines.get("httpVersion"));
        System.out.println("Reuestline parse");
    }
    @Override
    public String getValue(String key){
            return this.lines.get(key);

    }

    public static void main(String[] args){
        // Test
        RequestLine rl = new RequestLine();
        rl.init("GET /api HTTP/1.1");
        System.out.println(rl.toString());

    }

    @Override

        public String toString() {

            String text = "";
            for (Map.Entry<String, String> entry : lines.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                text += key + " : " + value + "\n";
            }
            return text;
        }
}
