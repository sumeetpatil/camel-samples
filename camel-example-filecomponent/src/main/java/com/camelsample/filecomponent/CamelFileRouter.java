package com.camelsample.filecomponent;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class CamelFileRouter extends RouteBuilder {
	String srourePath;
	String destinationPath;

	public CamelFileRouter(String srourePath, String destinationPath) {
		this.srourePath = srourePath;
		this.destinationPath = destinationPath;
	}

	@Override
	public void configure() throws Exception {
		from("file:" + srourePath).process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				Message in = exchange.getIn();
				String data = in.getBody(String.class);
				/*
				 * if message in a file contains hello, then change the body to
				 * hello world
				 */
				if (data.contains("hello")) {
					in.setBody("hello world");
				}
			}
		}).to("file:" + destinationPath);
	}
}
