package com.snaplogic.portal.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.snaplogic.portal.model.User;

public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	public void addUser(long userId, User newUser, ServiceContext serviceContext) {

		logger.info("Inside service class");

		// Convert object to JSON string
		String jsonNewUser = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonNewUser = mapper.writeValueAsString(newUser);
			logger.info("JSON String: " + jsonNewUser);

			// Convert object to JSON string and pretty print
			//jsonNewUser = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(newUser);
			//logger.info("Pretty JSON :" + jsonNewUser);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		if (jsonNewUser != null && jsonNewUser.length() > 0) {

			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

			String plainCreds = "spunreddy@snaplogic.com:Sh0dh@2oioS";
			byte[] plainCredsBytes = plainCreds.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			// set HTTP connection
			headers.add("Authorization", "Basic " + base64Creds);

			// set your entity to send
			HttpEntity<String> httpEntity = new HttpEntity<String>(jsonNewUser, headers);
//			List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//			messageConverters.add(new MappingJackson2HttpMessageConverter());
//			messageConverters.add(new FormHttpMessageConverter());
//			restTemplate.setMessageConverters(messageConverters);

			restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

			logger.info("HttpEntity Headers: " + httpEntity.getHeaders().toString());
			logger.info("HttpEntity Body: " + httpEntity.getBody().toString());

			ResponseEntity<String> responseEntity = restTemplate.postForEntity(
					"https://uat.elastic.snaplogic.com/api/1/rest/public/users", httpEntity, String.class);

			// send it!
			// ResponseEntity<String> responseEntity =
			// restTemplate.exchange("https://uat.elastic.snaplogic.com/api/1/rest/public/users",
			// HttpMethod.POST, httpEntity, String.class);

			// ResponseEntity<String> responseEntity = restTemplate
			// .postForEntity("https://uat.elastic.snaplogic.com/api/1/rest/public/users",
			// request, String.class);
			logger.info(responseEntity.toString());
			if (responseEntity.getStatusCodeValue() == 200) {
				logger.info("Successfully added the new user");
			} else {
				logger.error(responseEntity.toString());
			}

		}
	}

}
