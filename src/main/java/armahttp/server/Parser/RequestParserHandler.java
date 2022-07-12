package armahttp.server.Parser;

public class RequestParserHandler {
    private RequestLine line;
    private RequestHeaders headers;
    private RequestBody body;
    public static class Builder{
        private RequestLine line;
        private RequestHeaders headers;
        private RequestBody body;
        public Builder makeLine(RequestLine rl){
            this.line = rl;
            return this;
        }
        public  Builder makeHeaders(RequestHeaders rh){
            this.headers = rh;
            return this;
        }
        public Builder makeBody(RequestBody rb){
            this.body=rb;
            return this;
        }
        public RequestParserHandler build(){
            RequestParserHandler handler = new RequestParserHandler(this);
            return handler;
        }
    }
    public RequestParserHandler(Builder builder){
    this.line = builder.line;
    this.headers=builder.headers;
    this.body=builder.body;


    }
    public void handle(String content){
        String content1 = content.split("\n")[0];
        content = content.substring(content.indexOf("\n")+1);
        String content2 = content.split("\n\n")[0];
        String content3 = content.split("\n\n")[1];
        this.line.init(content1);
        this.headers.init(content2);
        this.body.init(content3);
        //        line = new RequestLine()


    }

    public static void main(String[] args){
        Builder builder = new Builder();
        RequestLine lineTest =new RequestLine();
        RequestParserHandler rh = builder
                    .makeLine(new RequestLine())
                    .makeHeaders(new RequestHeaders())
                    .makeBody(new RequestBody())
                    .build();
        rh.handle("GET /api HTTP/1.1\nUser-agent=chrome\nage=3\n\nbook=gssgf&livre=fgf");
        System.out.println(rh.line.getValue("method"));
        System.out.println(rh.line.getValue("requestedURI"));
        System.out.println(rh.headers.getValue("age"));
        System.out.println(rh.body.getValue("book"));



    }

}
