
package com.googlepages.smallnest.facet;

import javax.jws.WebService;

@WebService(serviceName = "HelloService", targetNamespace = "http://smallnest.googlepages.com/HelloService", endpointInterface = "com.googlepages.smallnest.facet.HelloServicePortType")
public class HelloServiceImpl
    implements HelloServicePortType
{


    public String hello(String in0) {
        throw new UnsupportedOperationException();
    }

}
