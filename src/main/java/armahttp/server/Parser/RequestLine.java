package armahttp.server.Parser;

public class RequestLine {
    public String method;
    public String requestedURI;
    public Float httpVersion;
    public Boolean https;
    public RequestLine(String stline){
        String[] starray = stline.split(" ");
        this.method = starray[0];
        this.requestedURI = starray[1];
        String[] httpv =starray[2].split("/");
        this.httpVersion = Float.parseFloat( httpv[1]);
        if(httpv[0].equals("HTTP")){
            https=false;
        }else if(httpv[0].equals("HTTPS")){
            https=true;
        }else {
            throw new RuntimeException("This Server support Just HTTP and Theoritcly HTTPS");
        }
    }
    public String getMethod(){
    return this.method;
    }
    public String getRequestedURI(){
        return this.requestedURI;
    }
    public Float getHttpVersion(){
        return this.httpVersion;
    }
    public Boolean isHttps(){
        return this.https;
    }
    public static void main(String[] args){
        // Test
        RequestLine rl = new RequestLine("GET /api HTTPS/1.1");
        System.out.println(rl.getMethod());
        System.out.println(rl.getRequestedURI());
        System.out.println(rl.getHttpVersion());
        System.out.println(rl.isHttps());



    }

}
