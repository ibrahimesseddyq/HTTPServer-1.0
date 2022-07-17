package armahttp.server.Parser;

import java.util.HashMap;
import java.util.Map;

public class RequestBody extends Request{
    HashMap<String,String> params;
    public RequestBody(){
        super();
    }
    /*
    * function that parse the body and load them into hashmap
    * @params strbody String of headers
     */
    @Override
    public void parse(String strbody){
        System.out.println("request body parse");

        if(!strbody.equals("")) {
            params = new HashMap<String, String>();
            String[] arr = strbody.split("&");
            for (int i = 0; i < arr.length; i++) {
                String paramKey = arr[i].split("=")[0];
                String paramValue = arr[i].split("=")[1];
                paramValue = paramValue.replaceAll("\\+", " ");
                this.params.put(paramKey, paramValue);
            }
        }
        System.out.println("request body parse");

    }
    protected void init(String str){
        this.str = str;
        this.parse(this.str);
    }
    public String getValue(String key){
        return this.params.get(key);
    }
    public static void main(String[] args){
        RequestBody rb=new RequestBody();
        rb.init("book=fdssd&arg=sdfds");
        System.out.println(rb.toString());
    }
    @Override
    public String toString() {

        String text = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            text += key + " : " + value + "\n";
        }
        return text;
    }
}
