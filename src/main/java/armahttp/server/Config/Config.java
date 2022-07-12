package armahttp.server.Config;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import  org.apache.commons.configuration.PropertiesConfiguration;

public class Config {
    private Class<?> startClass;
      static Configuration props;
    public Config(){
        try {
            props = new PropertiesConfiguration("config.properties");

        }catch (ConfigurationException e){
            System.out.println(e);
        }
    }
//    public void loadConfig() throws Exception {
//        try {
//            props = new PropertiesConfiguration("config.properties");
//
//        }catch (ConfigurationException e){
//System.out.println(e);
//        }
//    }
//    private static InputStream getRessource(String file) throws Exception{
//
//        ClassLoader cls = Config.class.getClassLoader();
//        InputStream is = cls.getResourceAsStream(file);
//        return is;
//
//    }
    public static String getConfig(String key, String defaultValue)  {
        try {
            String value = (String) props.getProperty(key);
            return value;
        }
        catch (Exception e) {
            System.out.println(e);
            return defaultValue;
        }
        }



}
