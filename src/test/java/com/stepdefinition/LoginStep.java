package com.stepdefinition;

import java.io.FileNotFoundException;

import java.io.IOException;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.CommonVariables;
import com.pojo.Login_Output_pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;


/**
 * 
 * @author siren
 * @Description To login in application in API Module
 * @CreationDate 30/06/2022
 *
 */


public class LoginStep extends BaseClass{
	public static CommonVariables commonVariables = new CommonVariables();
	String logtoken;
	Response response;
	
	
	/**
	 * @description Used to add header
	 * @CreationDate 30/06/2022
	 * 
	 */

	@Given("User should add header")
	public void userShouldAddHeader() {
		addHeader("Content-Type", "application/json");
		
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @Dsecription Used to add basic authentication
	 * @CreationDate 30/06/2022
	 * 
	 */

	@Given("User should add basic authentication for login")
	public void userShouldAddBasicAuthenticationForLogin() throws FileNotFoundException, IOException {
		basicAuth(getPropertyFileValue("username"), getPropertyFileValue("password"));

	}
	
	/**
	 * 
	 * @param post
	 * @Description Used to send request type to login
	 * @CreationDate 30/06/2022
	 * 
	 */

	@When("User should {string} request for login endpoint")
	public void userShouldRequestForLoginEndpoint(String post) {
		 response = requestType(post, Endpoints.LOGIN);
		 int statusCode = getStatusCode(response);
		 System.out.println("Actual request for login endpoint verification message:"+statusCode);
		 commonVariables.setStatusCode(statusCode);
		 
	}
	
	/**
	 * 
	 * @param first_name
	 * @Descrioption Used to verify login response message and to get log token
	 * @CreationDate 30/06/2022
	 * 
	 */

	@Then("User verify the login response body firstName present as {string} and get the logtoken")
	public void userVerifyTheLoginResponseBodyFirstNamePresentAsAndGetTheLogtoken(String string) {
		Login_Output_pojo output_pojo = response.as(Login_Output_pojo.class);
		String logtoken = output_pojo.getData().getLogtoken();
		//System.out.println(logtoken);
		commonVariables.setLogToken(logtoken);
	String first_name = output_pojo.getData().getFirst_name();
	System.out.println("Actual verify the logtoken  message:"+first_name);
	Assert.assertEquals("Verify FirstName","saran",first_name);
	}

}
