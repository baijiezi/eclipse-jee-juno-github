
package com.googlepages.smallnest.facet;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "HelloServicePortType", targetNamespace = "http://smallnest.googlepages.com/HelloService")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface HelloServicePortType {


    @WebMethod(operationName = "hello", action = "")
    @WebResult(name = "out", targetNamespace = "http://smallnest.googlepages.com/HelloService")
    public String hello(
        @WebParam(name = "in0", targetNamespace = "http://smallnest.googlepages.com/HelloService")
        String in0);

}
