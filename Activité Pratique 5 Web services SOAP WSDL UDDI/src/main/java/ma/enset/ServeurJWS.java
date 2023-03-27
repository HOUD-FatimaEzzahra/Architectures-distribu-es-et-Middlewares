package ma.enset;

import javax.xml.ws.Endpoint;

public class ServeurJWS {
    public static void main(String[] args) {
        String url="http://0.0.0.0:8087/";
        Endpoint.publish(url, new BanqueService());
        System.out.println("Web service deployé sur : "+url);
    }
}
