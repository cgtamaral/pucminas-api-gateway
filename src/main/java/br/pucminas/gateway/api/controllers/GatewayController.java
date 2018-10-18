package br.pucminas.gateway.api.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController
{
	Logger logger = LoggerFactory.getLogger(GatewayController.class);
	
	@Value("${autenticao.service.host}")
	private String autenticacaoHost;
	
	@Value("${audit.service.host}")
	private String auditHost;
	
	@Value("${payment.service.host}")
	private String paymentHost;
	
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
	    return builder.routes()
	        .route(p -> p.path("/v1/public/authentications").uri(autenticacaoHost))
	        .route(p -> p.path("/v1/public/validations").uri(autenticacaoHost))
	        .route(p -> p.path("/v1/public/audits/:auditId").uri(auditHost))
	        .route(p -> p.path("/v1/public/audits").uri(auditHost))
	        .route(p -> p.path("/api/v1/transactions/{transactionId}").uri(paymentHost))
	        .route(p -> p.path("/api/v1/transactions").uri(paymentHost))
	        .build();
	}
}