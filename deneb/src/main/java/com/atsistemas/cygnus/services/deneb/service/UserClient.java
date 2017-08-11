package com.atsistemas.cygnus.services.deneb.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.atsistemas.cygnus.services.deneb.model.UserDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@ConfigurationProperties(prefix = "cygnus.user")
public class UserClient {
	private static Logger logger = LoggerFactory.getLogger(UserClient.class);

	private String userUrl;

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "retrieveFallbackUser")
	public List<UserDto> getUsers() {

		ResponseEntity<Resources<Resource<UserDto>>> responseEntity = restTemplate.exchange(userUrl, HttpMethod.GET,
				null, new ParameterizedTypeReference<Resources<Resource<UserDto>>>() {
				}, Collections.emptyMap());

		if (responseEntity.getBody() != null) {
			Resources<Resource<UserDto>> resources = responseEntity.getBody();
			Collection<Resource<UserDto>> content = resources.getContent();
			List<UserDto> list = new ArrayList<UserDto>();
			for (Resource<UserDto> resource : content) {
				list.add(resource.getContent());
			}
			return list;
		}
		return null;
	}

	@HystrixCommand(fallbackMethod = "retrieveFallbackFindUser")
	public UserDto getUser(String userName) {

		ResponseEntity<Resource<UserDto>> responseEntity = restTemplate.exchange(
				userUrl + "/search/by-name?name=" + userName, HttpMethod.GET, null,
				new ParameterizedTypeReference<Resource<UserDto>>() {
				});
		if (responseEntity.getBody() != null) {
			return responseEntity.getBody().getContent();
		} else {
			return null;
		}

		// ResponseEntity<UserDto> usuario = restTemplate.getForEntity(userUrl +
		// "/search/by-name?name=" + userName,
		// UserDto.class);
		// if (usuario.getBody() != null) {
		// return usuario.getBody();
		// } else {
		// return null;
		// }
	}

	public List<UserDto> retrieveFallbackUser() {
		logger.error("[UserClient] Error call to " + userUrl);
		return null;
	}

	public UserDto retrieveFallbackFindUser() {
		logger.error("[UserClient] Error call to " + userUrl);
		return null;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}
}
