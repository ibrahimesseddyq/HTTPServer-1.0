package armahttp.server.Parser;

import java.util.HashMap;
import java.util.Map;

public class RequestHeaders extends Request {
    HashMap<String, String> headers;
    String strheaders;

    public RequestHeaders() {
        super();
    }
    protected void init(String str){
        this.str = str;
        this.parse(this.str);
    }
    /*
     * function that parse the headers and load them into hashmap
     * @params strheaders String of headers
     */
    @Override
    public void parse(String strheaders) {

        headers = new HashMap<String, String>();
        String[] arr = strheaders.split("[\r\n]+");
        for (int i = 0; i < arr.length; i++) {
            String headerKey = arr[i].split(":")[0];
            String headerValue = arr[i].split(":")[1];
            this.headers.put(headerKey, headerValue);
        }
        System.out.println("request header parse");
    }

    @Override
    public String getValue(String key) {
        return headers.get(key);
    }

    public static void main(String[] args) {
        RequestHeaders rh = new RequestHeaders();
        rh.init("Agent=sdskdjs" + "\n" +
                "age=5");
        System.out.println(rh.toString());

    }

    @Override
    public String toString() {

        String text = "";
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            text += key + " : " + value + "\n";
        }
        return text;
    }
}
