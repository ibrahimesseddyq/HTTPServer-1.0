package armahttp.server.Parser;

import java.util.Map;

public class Request {
    String str;
    protected void init(String str){
        this.str = str;
        this.parse(this.str);
    }

    protected Request(){

    }
    public void parse(String str){

    }
    public String getValue(String str){
        return "";
    }


}
