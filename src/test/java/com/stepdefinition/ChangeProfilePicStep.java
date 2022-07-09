package com.stepdefinition;

import java.util.ArrayList;

import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.ChangeProfilePic_Output_pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

/**
 * 
 * @author siren
 * @Description To Change profilePicStep of the apply through API Module
 * @CreationDate 30/06/2022
 *
 */

public class ChangeProfilePicStep extends BaseClass {
	

	/**
	 * @Description Used to add headers and bearer  authourization
	 * @CreationDate 30/06/2022
	 * 
	 */

	@Given("User should add headers and bearer authourization for accessing change profile endpoint")
	public void userShouldAddHeadersAndBearerAuthourizationForAccessingChangeProfileEndpoint() {
		
		List<Header> h = new ArrayList<Header>();

		Header h1 = new Header("Content-Type","multipart/form-data");
		Header h2 = new Header("Authorization", "Bearer " +  LoginStep.commonVariables.getLogToken());
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
	}
	
	/**
	 * @Description Used to perform multipart
	 * @CreationDate 30/06/2022
	 * 
	 */

	@When("user should perform multiPart to change the profilePic")
	public void userShouldPerformMultiPartToChangeTheProfilePic() throws Exception {
		multipart();
	}
	/**
	 * @Description Used to send request type
	 * @CreationDate 30/06/2022
	 * 
	 */

	@When("User should {string} request for change profilePic endpoint")
	public void userShouldRequestForChangeProfilePicEndpoint(String string) {
		Response response=requestType("POST", Endpoints.CHANGEPROFILEPIC);

	}
	
	/**
	 * @Description Used to verify profilePic response body
	 * @CreationDate 30/06/2022
	 * 
	 */

	@Then("User verify the change profilePic response body message matches as {string}")
	public void userVerifyTheChangeProfilePicResponseBodyMessageMatchesAs(String message) {
		
		ChangeProfilePic_Output_pojo changeProfilePic_Output_pojo=response.as(ChangeProfilePic_Output_pojo.class);
		
		String message1 = changeProfilePic_Output_pojo.getMessage();
		System.out.println("Actual update change profilePic responce verification message:"+message1);
		System.out.println(message);
		Assert.assertEquals("Verify Profile updated Successfully", message,message1);
	}

}
