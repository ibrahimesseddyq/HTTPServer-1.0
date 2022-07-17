package armahttp.server.Util;

import armahttp.server.Container.HttpContext;

public class Controller {
    private RequestHandler request;
    private ResponseHandler response;
    private String requestContent;
    public Controller(RequestHandler rq,ResponseHandler rp,String rc){
        this.request=rq;
        this.response=rp;
        this.requestContent = rc;
        System.out.println("controller construct");
        this.request.getRpHandler().handle(this.requestContent);

    }
    public RequestHandler getRequest(){
        return this.request;
    }
    public ResponseHandler getResponse(){
        return this.response;
    }
    public String GenerateResponseLine(){
        System.out.println("Genereate res");
         String method = this.request.getRpHandler().getLine().getValue("method");
         String uri = this.request.getRpHandler().getLine().getValue("requestedURI");
//         System.out.println(uri);
         String protocol="HTTP";
         String version = this.request.getRpHandler().getLine().getValue("httpVersion");
//         if (method.equals("GET"))
            return this.response.joinResponse(uri,protocol,Float.parseFloat(version));
//         return "none";
    }
    public static void main(String[] args){
        String content = "GET index.html HTTP/1.1\nsomeheader:dkd\r\nnone=n";
        Controller ct = new Controller(new RequestHandler(content),new ResponseHandler(),content);
        System.out.println(ct.GenerateResponseLine());
    }

}
