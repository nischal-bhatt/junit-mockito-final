package com.appsdeveloperblog.tutorials.junit.ui.controllers;

import com.appsdeveloperblog.tutorials.junit.security.SecurityConstants;
import com.appsdeveloperblog.tutorials.junit.ui.response.UserRest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@SpringBootTest(webEnvironment   = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties", properties = "server.port=9096")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersControllerIntegrationTest {

    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;
    //does not extend regular restTemplate

    private String authorizationToken;

    @Test
    void contextLoads() {

        System.out.println(serverPort);
        System.out.println(localServerPort);
    }

    @Test
    @DisplayName("User can be created")
    @Order(1)
    void testCreateUser_whenValidDetailsProvided_returnUserDetails() throws JSONException {
        //arrange
        JSONObject userDetailsRequestJson = new JSONObject();
        userDetailsRequestJson.put("firstName","Sergey");
        userDetailsRequestJson.put("lastName","Kargopolov");
        userDetailsRequestJson.put("email","test3@test.com");
        userDetailsRequestJson.put("password","12345678");
        userDetailsRequestJson.put("repeatPassword","12345678");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        HttpEntity<String> request= new HttpEntity<>(userDetailsRequestJson.toString(),headers);


        //act

        ResponseEntity<UserRest> createdUserDetailsEntity  = testRestTemplate.postForEntity("/users",request,UserRest.class);

        UserRest createdUserDetails = createdUserDetailsEntity.getBody();



        //assert

        Assertions.assertEquals(HttpStatus.OK, createdUserDetailsEntity.getStatusCode());

        Assertions.assertEquals(userDetailsRequestJson.getString("firstName"), createdUserDetails.getFirstName());


    }

    @Test
    @DisplayName("GET /users requires JWT")
    @Order(2)
    void testGetUsers_whenMissingJWT_returns403() {
        //arrange

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json");

        HttpEntity requestEntity = new HttpEntity(headers);



        //act
       ResponseEntity<List<UserRest>> response = testRestTemplate.exchange("/users", HttpMethod.GET,requestEntity,new ParameterizedTypeReference<List<UserRest>>() {


        });

        //assert
        Assertions.assertEquals(HttpStatus.FORBIDDEN,response.getStatusCode());

    }

    @Test
    @DisplayName("/login works")
    @Order(3)
    void testUserLogin_whenValidCredentialsProvided_returnsJWTinAuthorizationHeader() throws JSONException {
        //arrange
        JSONObject loginCredentials = new JSONObject();
        loginCredentials.put("email","test3@test.com");
        loginCredentials.put("password","12345678");

        HttpEntity<String> request = new HttpEntity<>(loginCredentials.toString());





        //act
        ResponseEntity response = testRestTemplate.postForEntity("/users/login",request, null);

        authorizationToken = response.getHeaders().getValuesAsList(SecurityConstants.HEADER_STRING).get(0);
        //assert
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    @Order(4)
    @DisplayName("GET /users works")
    void testGetUsers_whenValidJWTProvided_returnsUsers() {
        //arrange
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(authorizationToken);

        HttpEntity requestEntity = new HttpEntity(headers);
        //act
        ResponseEntity<List<UserRest>> res = testRestTemplate.exchange("/users",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<UserRest>>() {
                });

        List<UserRest> a = res.getBody();

        for (int i =0; i<a.size(); i++)
        {
            System.out.println(a.get(i).getFirstName());
        }

        //assert
        Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
    }


}
