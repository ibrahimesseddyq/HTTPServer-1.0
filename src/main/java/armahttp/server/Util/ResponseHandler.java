package armahttp.server.Util;

import armahttp.server.Config.Config;
import armahttp.server.FileReader.Reader;

public class ResponseHandler {
    private String responseLine;
    private String[] responseHeaders;
    private String responseBody;
    private Status status = new Status();
    private Boolean succed=false;
    private Reader fileReader;
    private Config config;
    public ResponseHandler(){
        fileReader = new Reader();
        config = new Config();
    }

    public String prepareResponseHeaders(){
    return "Server: "+config.getConfig("name","arma");
    }
    public String prepareResponseLine(String protocol,Float version){
        String statuscode = "";
        if(succed) statuscode = "OK " + status.st.get("OK");
        else statuscode = "NotFound "+ status.st.get("Not Found");
        return protocol+"/"+version.toString()+" "+statuscode;
    }
    public String prepareResponseBody(String filePath){
        String fileContent = fileReader.readFile(filePath);
        if(fileContent != null) succed=true;
        return fileContent;
    }
    public String joinResponse( String filePath,String protocol,Float version){
        String body = prepareResponseBody(filePath);
        String line = prepareResponseLine(protocol,version);
        String headers = prepareResponseHeaders();
        return line+"\n"+headers+"\n\n"+body;
    }

}
