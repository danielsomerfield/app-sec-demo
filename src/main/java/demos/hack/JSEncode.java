package demos.hack;

import org.owasp.esapi.ESAPI;

public class JSEncode {
    public static void main(final String [] args){
        System.out.println(ESAPI.encoder().encodeForJavaScript("\"); alert('gotcha');//"));
    }
}
