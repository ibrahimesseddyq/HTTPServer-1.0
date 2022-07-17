package armahttp.server.Parser;

public class RequestParserHandler {
    private RequestLine line;
    private RequestHeaders headers;
    private RequestBody body;

    public static class RequestHandlerBuilder {
        private RequestLine line;
        private RequestHeaders headers;
        private RequestBody body;

        public RequestHandlerBuilder makeLine(RequestLine rl) {
            this.line = rl;
            return this;
        }

        public RequestHandlerBuilder makeHeaders(RequestHeaders rh) {
            this.headers = rh;
            return this;
        }

        public RequestHandlerBuilder makeBody(RequestBody rb) {
            this.body = rb;
            return this;
        }

        public RequestParserHandler build() {
            RequestParserHandler handler = new RequestParserHandler(this);
            return handler;
        }
    }

    public RequestParserHandler(RequestHandlerBuilder requestHandlerBuilder) {
        this.line = requestHandlerBuilder.line;
        this.headers = requestHandlerBuilder.headers;
        this.body = requestHandlerBuilder.body;


    }

    public void handle(String content) {
        String content1 = content.split("\n")[0];
//        System.out.println(content1);

        content = content.substring(content.indexOf("\n") + 1);
        String content2 = content.split("\r\n")[0];
        System.out.println(content2);

        String content3 = content.split("\r\n")[1];
//        System.out.println(content3);


        this.line.init(content1);
        System.out.println(this.line.toString());

        this.headers.init(content2);
        System.out.println(this.headers.toString());

        this.body.init(content3);
        System.out.println(this.body.toString());


        //        line = new RequestLine()


    }
    public RequestLine getLine(){
        return this.line;
    }
    public RequestHeaders getHeaders(){
        return this.headers;
    }
    public RequestBody getBody(){
        return this.body;
    }
    public static void main(String[] args) {
        RequestHandlerBuilder requestHandlerBuilder = new RequestHandlerBuilder();
        RequestLine lineTest = new RequestLine();
        RequestParserHandler rh = requestHandlerBuilder
                .makeLine(new RequestLine())
                .makeHeaders(new RequestHeaders())
                .makeBody(new RequestBody())
                .build();
        rh.handle("GET /api HTTP/1.1\nUser-agent=chrome\nage=3\n\nbook=gssgf&livre=fgf");
        System.out.println(rh.toString());


    }

    @Override
    public String toString() {
        return "##Line :\n" + this.line.toString() +
                "\n##Headers :\n" + this.headers.toString() +
                "\n##Body :\n" + this.body.toString();

    }
}
