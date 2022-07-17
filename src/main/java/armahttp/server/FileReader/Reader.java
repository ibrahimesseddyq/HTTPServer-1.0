package armahttp.server.FileReader;

import armahttp.server.Config.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    private String basePath;
    private Config configs;
    public Reader(){
        configs = new Config();
        basePath = configs.getConfig("basePath","C:\\\\Users\\\\Youcode\\\\Desktop\\\\java\\\\mineserver\\\\src\\\\main\\\\resources\\\\");


    }
    public String readFile(String filepath){
        File file= new File(basePath+filepath);
        String fullcontent="";
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                fullcontent += sc.nextLine();
            }
            return fullcontent;
        }catch (FileNotFoundException e){
            System.out.println(e);
            return null;
        }

    }
    public static void main(String[] args){
        Reader reader = new Reader();
        String s =reader.readFile("arma.html");
        System.out.println(s);
    }
}
