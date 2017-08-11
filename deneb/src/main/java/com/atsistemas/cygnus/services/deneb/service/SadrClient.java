package com.atsistemas.cygnus.services.deneb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.client.RestTemplate;

import com.atsistemas.cygnus.model.PingRequest;
import com.atsistemas.cygnus.model.PingResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@ConfigurationProperties(prefix="cygnus.sadr")
public class SadrClient {

	private static Logger logger = LoggerFactory.getLogger(SadrClient.class);

	private String pingUrl;

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod="retrieveFallbackPingSadr")
	public PingResponse pingSadr(PingRequest pingRequest){

		logger.debug("--> [SadrClient] pingSadr received - id: {} - content: {}", pingRequest.getId(), pingRequest.getMessage());
		logger.debug("--> [SadrClient] sadr endpoint: {}",pingUrl);
		PingResponse ping = restTemplate.postForObject(pingUrl, pingRequest, PingResponse.class);
		return ping;
	}

	public PingResponse retrieveFallbackPingSadr(PingRequest pingRequest){
		return new PingResponse("[SadrClient] Error pinging sadr. This is a fallback message");
	}

	public void setPingUrl(String pingUrl) {
		this.pingUrl = pingUrl;
	}
}