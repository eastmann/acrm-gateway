package com.rtk.acrm.gateway.facade.clients;

import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.rtk.acrm.gateway.facade.BaseRestClient;
import com.rtk.acrm.gateway.facade.dto.AuthUserDto;
import com.rtk.acrm.gateway.facade.dto.RolesDto;
import com.rtk.acrm.gateway.utils.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthServerClient extends BaseRestClient {

    Logger logger = LoggerFactory.getLogger(AuthServerClient.class);

    @Value("${read-time-out}")
    private Integer READ_TIME_OUT;

    @Value("${connection-time-out}")
    private Integer CONNECTION_TIME_OUT;

    @Value("${auth-server.login}")
    private String authServerLogin;

    @Value("${auth-server.password}")
    private String authServerPassword;

    @Value("${auth-server.host}")
    private String AUTH_SERVER_URL;

    @Value("${auth-server.token-path}")
    private String tokenPath;

    @Value("${auth-server.check-token-path}")
    private String checkTokenPath;

    private String TOKEN_URL;
    private String CHECK_TOKEN_URL;

    @Value("${auth-server.get-roles}")
    private String GET_ROLES;

    @Value("${auth-server.get-user}")
    private String GET_USER;

    private String GET_ROLES_URL;

    private String GET_USER_URL;

    public AuthServerClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @PostConstruct
    public void init() {
        TOKEN_URL = UrlUtils.joinUrl(AUTH_SERVER_URL, tokenPath);
        CHECK_TOKEN_URL = UrlUtils.joinUrl(AUTH_SERVER_URL, checkTokenPath);
        GET_ROLES_URL = UrlUtils.joinUrl(AUTH_SERVER_URL, GET_ROLES);
        GET_USER_URL = UrlUtils.joinUrl(AUTH_SERVER_URL, GET_USER);
    }


    public TokenResponse loginUser(String login, String password) throws IOException, ParseException {
        URI uri = URI.create(TOKEN_URL);

        ClientAuthentication authentication = buildClientAuthentication();
        AuthorizationGrant grant = passwordAuthorizationGrant(login, password);
        Scope scope = readWriteScope();

        logger.info("Login request for user "+login + " by url "+TOKEN_URL);
        TokenRequest request = new TokenRequest(uri, authentication, grant, scope);

        TokenResponse response = TokenResponse.parse(request.toHTTPRequest().send());
        logger.info("Response for user "+login+":"+response.toHTTPResponse().getContent());

        return response;
    }

    public RolesDto getAuthorites(String login){
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("login", login);
        return getRequest(GET_ROLES_URL, RolesDto.class, urlParams, READ_TIME_OUT, CONNECTION_TIME_OUT);
    }

    public AuthUserDto getUser(String login){
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("login", login);
        return getRequest(GET_USER_URL, AuthUserDto.class, urlParams, READ_TIME_OUT, CONNECTION_TIME_OUT);
    }

    private ClientAuthentication buildClientAuthentication() {
        ClientID clientID = new ClientID(authServerLogin);
        Secret clientPassword = new Secret(authServerPassword);
        return new ClientSecretBasic(clientID, clientPassword);
    }

    private AuthorizationGrant passwordAuthorizationGrant(String login, String password) {
        return new ResourceOwnerPasswordCredentialsGrant(login, new Secret(password));
    }

    private Scope readWriteScope() {
        return new Scope("READ", "WRITE");
    }
}
