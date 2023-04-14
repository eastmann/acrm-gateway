package com.rtk.acrm.gateway.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;
import java.util.Map;

public class BaseRestClient {

	private Logger logger = LoggerFactory.getLogger(BaseRestClient.class);

	@Value("${zuul.routes.auth.url}")
	private String AUTH_URL;

	@Value("${auth-server.history-put-record}")
	private String HISTORY_PUT_URL;

	@Value("${auth-server.log-requests}")
	private String LOG_REQUESTS;

	private String FULL_URL;

	protected final RestTemplate restTemplate;

	public BaseRestClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private <T> HttpEntity<T> buildHttpHeader(){
		HttpHeaders httpHeaders = new HttpHeaders();
		return new HttpEntity<T>(httpHeaders);
	}

	private RestTemplate restTemplate(Integer readTimeOut, Integer connectionTimeout){
		RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate template = builder
				.setReadTimeout(Duration.ofMillis(readTimeOut))
				.setConnectTimeout(Duration.ofMillis(connectionTimeout)).build();
		return template;
	}

	/**
	 * @param template
	 * @param url
	 * @param httpMethod
	 * @param httpEntity
	 * @param resultClass
	 * @param urlParams
	 * @param <T>
	 * @return
	 */
	@Nullable
	protected <T> T exchangeRequest(RestTemplate template, String url, HttpMethod httpMethod, HttpEntity httpEntity, Class<T> resultClass, Map<String, ?> urlParams) {
		try {
			ResponseEntity<T> responseEntity = template.exchange(
					url, httpMethod, httpEntity, resultClass , urlParams);

			if(responseEntity.getStatusCode() == HttpStatus.OK) {
				return responseEntity.getBody();
			}
		} catch (RestClientException e) {
			logger.error("Can't connect to server by url "+url+", urlParams are "+urlParams, e);
		}
		return null;
	}

	/**
	 * @param template
	 * @param url
	 * @param httpMethod
	 * @param resultClass
	 * @param urlParams
	 * @param <T>
	 * @return
	 */
	@Nullable
	protected <T> T exchangeRequest(RestTemplate template, String url, HttpMethod httpMethod, Class<T> resultClass, Map<String, ?> urlParams) {
		HttpEntity httpEntity = buildHttpHeader();
		return exchangeRequest(template, url, httpMethod, httpEntity, resultClass, urlParams);
	}

	/**
	 * @param url
	 * @param resultClass
	 * @param urlParams
	 * @param <T>
	 * @return
	 */
	@Nullable
	protected <T> T getRequest(String url, Class<T> resultClass, Map<String, ?> urlParams) {
		return exchangeRequest(restTemplate, url, HttpMethod.GET, resultClass, urlParams);
	}

	/**
	 * @param url
	 * @param resultClass
	 * @param urlParams
	 * @param readTimeOut
	 * @param connectionTimeout
	 * @param <T>
	 * @return
	 */
	@Nullable
	protected <T> T getRequest(String url, Class<T> resultClass, Map<String, ?> urlParams, Integer readTimeOut, Integer connectionTimeout) {
		RestTemplate template = restTemplate(readTimeOut, connectionTimeout);
		return exchangeRequest(template, url, HttpMethod.GET, resultClass, urlParams);
	}

	/**
	 * @param url
	 * @param httpEntity
	 * @param resultClass
	 * @param urlParams
	 * @param readTimeOut
	 * @param connectionTimeout
	 * @param <T>
	 * @return
	 */
	@Nullable
	protected <T> T postRequest(String url, HttpEntity httpEntity, Class<T> resultClass, Map<String, ?> urlParams, Integer readTimeOut, Integer connectionTimeout) {
		RestTemplate template = restTemplate(readTimeOut, connectionTimeout);
		return exchangeRequest(template, url, HttpMethod.POST, httpEntity, resultClass, urlParams);
	}


	/**
	 * @param url
	 * @param obj
	 * @param resultClass
	 * @param readTimeOut
	 * @param connectionTimeout
	 * @param <T>
	 * @return
	 */
	@Nullable
	protected <T> ResponseEntity<T> postRequest(String url, Object obj, Class<T> resultClass, Integer readTimeOut, Integer connectionTimeout) {
		RestTemplate template = restTemplate(readTimeOut, connectionTimeout);
		ResponseEntity<T> result = template.postForEntity(url, obj, resultClass);
		return result;
	}

}
