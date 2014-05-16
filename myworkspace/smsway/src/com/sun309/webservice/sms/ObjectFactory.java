package com.sun309.webservice.sms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.sun309.webservice.sms package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _Send_QNAME = new QName("http://mas/", "send");
	private final static QName _ReceiveRPTResponse_QNAME = new QName(
			"http://mas/", "receiveRPTResponse");
	private final static QName _SendResponse_QNAME = new QName("http://mas/",
			"sendResponse");
	private final static QName _ReceiveRPT_QNAME = new QName("http://mas/",
			"receiveRPT");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.sun309.webservice.sms
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link RptItem }
	 * 
	 */
	public RptItem createRptItem() {
		return new RptItem();
	}

	/**
	 * Create an instance of {@link ReceiveRPT }
	 * 
	 */
	public ReceiveRPT createReceiveRPT() {
		return new ReceiveRPT();
	}

	/**
	 * Create an instance of {@link ReceiveRPTResponse }
	 * 
	 */
	public ReceiveRPTResponse createReceiveRPTResponse() {
		return new ReceiveRPTResponse();
	}

	/**
	 * Create an instance of {@link Message }
	 * 
	 */
	public Message createMessage() {
		return new Message();
	}

	/**
	 * Create an instance of {@link Send }
	 * 
	 */
	public Send createSend() {
		return new Send();
	}

	/**
	 * Create an instance of {@link SendResponse }
	 * 
	 */
	public SendResponse createSendResponse() {
		return new SendResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Send }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://mas/", name = "send")
	public JAXBElement<Send> createSend(Send value) {
		return new JAXBElement<Send>(_Send_QNAME, Send.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ReceiveRPTResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://mas/", name = "receiveRPTResponse")
	public JAXBElement<ReceiveRPTResponse> createReceiveRPTResponse(
			ReceiveRPTResponse value) {
		return new JAXBElement<ReceiveRPTResponse>(_ReceiveRPTResponse_QNAME,
				ReceiveRPTResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SendResponse }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://mas/", name = "sendResponse")
	public JAXBElement<SendResponse> createSendResponse(SendResponse value) {
		return new JAXBElement<SendResponse>(_SendResponse_QNAME,
				SendResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveRPT }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://mas/", name = "receiveRPT")
	public JAXBElement<ReceiveRPT> createReceiveRPT(ReceiveRPT value) {
		return new JAXBElement<ReceiveRPT>(_ReceiveRPT_QNAME, ReceiveRPT.class,
				null, value);
	}

}
