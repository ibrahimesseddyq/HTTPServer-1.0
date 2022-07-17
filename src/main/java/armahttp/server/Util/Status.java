package armahttp.server.Util;

import java.util.HashMap;

public class Status {
    public static HashMap<String,Integer> st=new HashMap<>(){{
        put("OK",200);
        put("Not Found",404);
    }};
}
