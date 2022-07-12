package armahttp.server.Parser;

import java.util.HashMap;

public class RequestBody extends Request{
    HashMap<String,String> params;
    public RequestBody(){
        super();
    }
    @Override
    public void parse(String strheaders){
        params = new HashMap<String, String>();
        String[] arr = strheaders.split("&");
        for (int i=0;i<arr.length;i++){
            String paramKey=arr[i].split("=")[0];
            String paramValue=arr[i].split("=")[1];
            paramValue = paramValue.replaceAll("\\+"," ");
            params.put(paramKey,paramValue);
        }
    }
    public String getValue(String key){
        return this.params.get(key);
    }
    public static void main(String[] args){
        RequestBody rb=new RequestBody();
        rb.init("book=fdssd&arg=sdfds");
        System.out.println(rb.getValue("book"));
    }

}
