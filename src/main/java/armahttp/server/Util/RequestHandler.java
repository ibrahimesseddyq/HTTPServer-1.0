package armahttp.server.Util;

import armahttp.server.Parser.RequestBody;
import armahttp.server.Parser.RequestHeaders;
import armahttp.server.Parser.RequestLine;
import armahttp.server.Parser.RequestParserHandler;

import java.util.List;

public class RequestHandler {
    private RequestParserHandler rpHandler;
    private List<String> queryString;
    public RequestHandler(String rqContent){
        RequestParserHandler.RequestHandlerBuilder requestHandlerBuilder = new RequestParserHandler.RequestHandlerBuilder();
        rpHandler= requestHandlerBuilder
                .makeLine(new RequestLine())
                .makeHeaders(new RequestHeaders())
                .makeBody(new RequestBody())
                .build();

    }
    public RequestParserHandler getRpHandler(){
        return rpHandler;
    }
}
