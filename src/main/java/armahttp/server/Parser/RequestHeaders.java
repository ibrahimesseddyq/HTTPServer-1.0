package armahttp.server.Parser;

import java.util.HashMap;

public class RequestHeaders {
    HashMap<String,String> headers;
    public RequestHeaders(String strheaders){
        headers = new HashMap<String, String>();
        String[] arr = strheaders.split("[\r\n]+");
        for (int i=0;i<arr.length;i++){
            String headerKey=arr[i].split("=")[0];
            String headerValue=arr[i].split("=")[1];
            headers.put(headerKey,headerValue);
        }
    }
    public String getHeader(String key){
        return headers.get(key);
    }
    public static void main(String[] args){
        RequestHeaders rh = new RequestHeaders("Agent=sdskdjs" +"\n"+
                "age=5");
        System.out.println(rh.getHeader("Agent"));
    }

}
