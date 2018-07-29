package com.camelsample.filecomponent;

import org.apache.camel.builder.RouteBuilder;

public class CamelFileRouter extends RouteBuilder{
	String srourePath;
	String destinationPath;

	public CamelFileRouter(String srourePath, String destinationPath) {
		this.srourePath = srourePath;
		this.destinationPath = destinationPath;
	}

	@Override
	public void configure() throws Exception {
		from("file:"+srourePath)
		.to("file:"+destinationPath);
	}
}
