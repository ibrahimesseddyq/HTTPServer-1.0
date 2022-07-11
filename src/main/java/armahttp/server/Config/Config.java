package armahttp.server.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

public class Config {
     static Properties props;
    Config(){
        props = new Properties();
    }
    void loadConfig() throws Exception {
        props.load(getRessource("app.properties"));

    }
    private static InputStream getRessource(String file) throws Exception{

        ClassLoader cls = Config.class.getClassLoader();
        InputStream is = cls.getResourceAsStream(file);
        return is;

    }
    public static String getConfig(String key,String defaultValue)  {
        try {
            String value = props.getProperty(key, defaultValue);
            return value;
        }
        catch (Exception e) {
            return defaultValue;
        }
        }



}
