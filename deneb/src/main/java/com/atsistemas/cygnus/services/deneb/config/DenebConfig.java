package com.atsistemas.cygnus.services.deneb.config;

import java.util.Arrays;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.atsistemas.cygnus.services.deneb.service.SadrClient;
import com.atsistemas.cygnus.services.deneb.service.UserClient;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
class DenebConfig {

	@Bean
	@RefreshScope
	SadrClient sadrClient() {
		return new SadrClient();
	}

	@Bean
	@RefreshScope
	UserClient userClient() {
		return new UserClient();
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(new Jackson2HalModule());

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
		converter.setObjectMapper(mapper);

		return builder.messageConverters(converter).build();

	}

}
