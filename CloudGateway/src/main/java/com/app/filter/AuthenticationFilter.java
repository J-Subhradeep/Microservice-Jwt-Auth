package com.app.filter;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{
	
	@Autowired
	private RouteValidator validator;
	
	@Autowired
	private RestTemplate template;
	
	public static class Config{
		
	}

	public AuthenticationFilter() {
		super(Config.class);
	}
	@Override
	public GatewayFilter apply(Config config) {
		// TODO Auto-generated method stub
		return ((exchange,chain)->{
			if(validator.isSecured.test(exchange.getRequest())) {
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					System.out.println("Authorization "+exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION));
					throw new RuntimeException("missing auth");
				}
				String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if(authHeaders!=null && authHeaders.startsWith("Bearer")) {
					authHeaders = authHeaders.substring(7);
				
				}
				
				try {
					System.out.println("Hello world");
					String res = template.getForObject("http://AUTH-SERVICE/auth/validate?token="+authHeaders,String.class);
					System.out.println(res);
				}
				catch(Exception e){
					System.out.println("Auth Error "+ e);
					throw new RuntimeException("Unauthorized request");
				}
			}
			
			
			return chain.filter(exchange);
		});
	}
}
