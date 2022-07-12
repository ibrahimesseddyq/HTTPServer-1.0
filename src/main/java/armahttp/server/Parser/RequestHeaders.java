package armahttp.server.Parser;

import java.util.HashMap;

public class RequestHeaders extends Request{
    HashMap<String,String> headers;
    String strheaders;
    public RequestHeaders(){
    super();
    }
    @Override
    public void parse(String strheaders){
        headers = new HashMap<String, String>();
        String[] arr = strheaders.split("[\r\n]+");
        for (int i=0;i<arr.length;i++){
            String headerKey=arr[i].split("=")[0];
            String headerValue=arr[i].split("=")[1];
            headers.put(headerKey,headerValue);
        }
    }
@Override
    public String getValue(String key){
        return headers.get(key);
    }
    public static void main(String[] args){
        RequestHeaders rh = new RequestHeaders();
        rh.init("Agent=sdskdjs" +"\n"+
                "age=5");
        System.out.println(rh.getValue("Agent"));

    }

}
