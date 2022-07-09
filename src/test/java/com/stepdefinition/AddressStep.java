package com.stepdefinition;

import java.util.ArrayList;

import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.AddAddress_Input_pojo;
import com.pojo.AddAddress_Output_Pojo;
import com.pojo.DeleteAddress_Input_pojo;
import com.pojo.DeleteAddress_Output_pojo;
import com.pojo.GetAddress_Output_pojo;
import com.pojo.UpdateAddress_Input_pojo;
import com.pojo.UpdateAddress_Output_pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

/**
 * 
 * @author siren
 * @Description To add, update, get and delete address of an application through
 *              API Module
 * @CreationDate 30/06/2022
 *
 */

public class AddressStep extends BaseClass {
	// Response response;
	static String address;
	
	/**
	 * @Description Used to add headers and bearer authourization
	 * @CreationDate 30/06/2022
	 * 
	 */

	@Given("User should add headers and bearer authourization for accessing address endpoint")
	public void userShouldAddHeadersAndBearerAuthourizationForAccessingAddressEndpoint() {

		List<Header> h = new ArrayList<Header>();

		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + LoginStep.commonVariables.getLogToken());
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
	}
	
	/**
	 * 
	 * @param first_name
	 * @param last_name
	 * @param mobile
	 * @param apartment
	 * @param state
	 * @param city
	 * @param country
	 * @param zipcode
	 * @param address
	 * @param address_type
	 * @Description Used to add request body to add new address
	 * @CreationDate 30/06/2022
	 */

	@When("User should add request body for Add new address {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string}")
	public void userShouldAddRequestBodyForAddNewAddressAnd(String string, String string2, String string3,
			String string4, String string5, String string6, String string7, String string8, String string9,
			String string10) {

		AddAddress_Input_pojo addAddress_Input_pojo = new AddAddress_Input_pojo("saran", "kumaran", "9962005457",
				"apartment", 33, 3378, 101, "202020", "33/24ECR Chennai", "Apartment");

		addBody(addAddress_Input_pojo);

	}

	@When("User should {string} request for add new address")
	public void userShouldRequestForAddNewAddress(String post) {
		response = requestType(post, Endpoints.ADDADDRESS);
	}

	@Then("User verify the create address response message matches {string} and get the address_id")
	public void userVerifyTheCreateAddressResponseMessageMatchesAndGetTheAddress_id(String message) {
		AddAddress_Output_Pojo addAddress_Output_Pojo = response.as(AddAddress_Output_Pojo.class);
		int id = addAddress_Output_Pojo.getAddress_id();
		address = String.valueOf(id);
		System.out.println(address);
		String message1 = addAddress_Output_Pojo.getMessage();
		System.out.println("Expected create address response verification messsage:"+message1);
		System.out.println("Actual create address response verification message:"+message);

		Assert.assertEquals("Verify Success messages", message, message1);
	}

	
	/**
	 * 
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 * @param string5
	 * @param string6
	 * @param string7
	 * @param string8
	 * @param string9
	 * @param string10
	 * @Description Used to send request body to update address
	 * @CreationDate 30/06/2022
	 * 
	 */

	@When("User should add request body for Update new address {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string}")
	public void userShouldAddRequestBodyForUpdateNewAddressAnd(String string, String string2, String string3,
			String string4, String string5, String string6, String string7, String string8, String string9,
			String string10) {

		UpdateAddress_Input_pojo updateAddress_Input_pojo = new UpdateAddress_Input_pojo(address, "saran", "kumaran",
				"9962005457", "apartment", 33, 3378, 101, "202020", "33/24ECR Chennai", "Apartment");
		addBody(updateAddress_Input_pojo);

	}
	/**
	 * 
	 * @param updateDeleteMessage
	 * @Description Used to verify update address response body
	 * @CreationDate 30/06/2022
	 * 
	 */

	@When("User should {string} request for update existing address")
	public void userShouldRequestForUpdateExistingAddress(String put) {
		Response response = requestType(put, Endpoints.UPDATEADDRESS);
	}
	
	/**
	 * 
	 * @param addAddressSuccessMessage
	 * @Description Used to verify the create address response message and to get
	 *              address id
	 * @CreationDate 30/06/2022
	 * 
	 */

	@Then("User verify the update address response message matches {string}")
	public void userVerifyTheUpdateAddressResponseMessageMatches(String message1) {
		UpdateAddress_Output_pojo updateAddress_Output_pojo = response.as(UpdateAddress_Output_pojo.class);
		String message = updateAddress_Output_pojo.getMessage();
		System.out.println("Expected update address response verification messsage:"+message1);
		System.out.println("Actual update address responce verification message:"+message);
		Assert.assertEquals("Verify  Address Update Message", message1, message);

	}
	
	/**
	 * 
	 * @param get
	 * @description Used to send request type to get address
	 * @CreationDate 30/06/2022
	 * 
	 */

	@When("User should {string} request for getting all address")
	public void userShouldRequestForGettingAllAddress(String get) {
		Response response = requestType(get, Endpoints.GETADDRESS);
	}
	
	/**
	 * 
	 * @param getSuccessMessage
	 * @Description Used to verify get address response message
	 * @CreationDate 30/06/2022
	 * 
	 */

	@Then("User verify the get address response message matches {string}")
	public void userVerifyTheGetAddressResponseMessageMatches(String message) {
		GetAddress_Output_pojo getAddress_Output_pojo = response.as(GetAddress_Output_pojo.class);
		String message1 = getAddress_Output_pojo.getMessage();
		System.out.println("Expected get address verification messsage :"+message1);
		System.out.println("Actual get address verification message:"+message);
		Assert.assertEquals("verify ok", message, message1);

	}
	

	/**
	 * 
	 * @param string
	 * @description Used to send request body to delete message
	 * @CreationDate 30/06/2022
	 * 
	 */

	@When("User should add request body for Delete address using {string}")
	public void userShouldAddRequestBodyForDeleteAddressUsing(String string) {
		DeleteAddress_Input_pojo deleteAddress_Input_pojo = new DeleteAddress_Input_pojo(address);
		addBody(deleteAddress_Input_pojo);
	}
	
	/**
	 * 
	 * @param delete
	 * @Description Used to send request type to delete message
	 * @CreationDate 30/06/2022
	 */

	@When("User should {string} request for deleting the generated address")
	public void userShouldRequestForDeletingTheGeneratedAddress(String delete) {
		Response response = requestType(delete, Endpoints.DELETEADDRESS);

	}
	/**
	 * 
	 * @param deleteSuccessMessage
	 * @Description Used to verify delete address response message
	 * @CreationDate 30/06/2022
	 * 
	 */

	@Then("User verify the delete address response message matches {string}")
	public void userVerifyTheDeleteAddressResponseMessageMatches(String message) {
		DeleteAddress_Output_pojo deleteAddress_Output_pojo = response.as(DeleteAddress_Output_pojo.class);
		String message1 = deleteAddress_Output_pojo.getMessage();
		System.out.println("Actual delete address verification message:"+message1);
		System.out.println("Expected delete address verification message:"+message);
		Assert.assertEquals("Verify Address deleted successfully", message, message1);

	}
}
