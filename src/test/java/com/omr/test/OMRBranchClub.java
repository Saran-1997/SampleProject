package com.omr.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.AddAddress_Input_pojo;
import com.pojo.AddAddress_Output_Pojo;
import com.pojo.ChangeProfilePic_Output_pojo;
import com.pojo.DeleteAddress_Input_pojo;
import com.pojo.DeleteAddress_Output_pojo;
import com.pojo.GetAddress_Output_pojo;
import com.pojo.Login_Output_pojo;
import com.pojo.UpdateAddress_Input_pojo;
import com.pojo.UpdateAddress_Output_pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.apache.hc.core5.http.Message;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.*;

/**
 * 
 * @author siren
 * @Description API Testing Automation
 * @createDate 22/6/2022 
 */

public class OMRBranchClub extends BaseClass {
	String logtoken;
	String address;
	
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @Description API Login Module
	 * @createDate 22/6/2022 
	 */

	@Test(priority = 1)
	public void login() throws FileNotFoundException, IOException {

		addHeader("Content-Type", "application/json");
		basicAuth(getPropertyFileValue("username"), getPropertyFileValue("password"));

		Response response = requestType("POST", Endpoints.LOGIN);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);
		Login_Output_pojo output_pojo = response.as(Login_Output_pojo.class);
		String first_name = output_pojo.getData().getFirst_name();
		System.out.println(first_name);
		Assert.assertEquals(first_name, "saran", "Verify FirstName");
		logtoken = output_pojo.getData().getLogtoken();

	}
	/**
	 * @Description API Add Address Module
	 * @createDate 22/6/2022  
	 */

	@Test(priority = 2)
	public void addAddress() {

		List<Header> h = new ArrayList<Header>();

		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);

		AddAddress_Input_pojo addAddress_Input_pojo = new AddAddress_Input_pojo("saran", "kumaran", "9962005457",
				"apartment", 33, 3378, 101, "202020", "33/24ECR Chennai", "Apartment");

		addBody(addAddress_Input_pojo);
		Response response = requestType("POST", Endpoints.ADDADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "verify status code");
		AddAddress_Output_Pojo addAddress_Output_Pojo = response.as(AddAddress_Output_Pojo.class);
		String message = addAddress_Output_Pojo.getMessage();
		System.out.println(message);
		int id = addAddress_Output_Pojo.getAddress_id();
		address = String.valueOf(id);
		System.out.println(address);
		Assert.assertEquals(message, "Address added successfully", "verify address Added Successfully");
	}
	

	/**
	 * @Description API Update Address Module
	 * @createDate 22/6/2022   
	 */


	@Test(priority = 3)

	private void updateAddress() {

		List<Header> h = new ArrayList<Header>();

		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);

		UpdateAddress_Input_pojo updateAddress_Input_pojo = new UpdateAddress_Input_pojo(address, "saran", "kumaran",
				"9962005457", "apartment", 33, 3378, 101, "202020", "33/24ECR Chennai", "Apartment");
		addBody(updateAddress_Input_pojo);

		Response response = requestType("PUT", Endpoints.UPDATEADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		UpdateAddress_Output_pojo updateAddress_Output_pojo = response.as(UpdateAddress_Output_pojo.class);
		String message = updateAddress_Output_pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(message, "Address updated successfully", "verify address Added Successfully");

	}
	
	/**
	 * @Description API get Address Module
	 * @createDate 22/6/2022   
	 */



	@Test(priority = 4)
	public void GetAddress() {
		List<Header> h = new ArrayList<Header>();

		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
		Response response = requestType("GET", Endpoints.GETADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "verifycode");

		GetAddress_Output_pojo getAddress_Output_pojo = response.as(GetAddress_Output_pojo.class);
		String message = getAddress_Output_pojo.getMessage();
		Assert.assertEquals(message, "OK", "verify ok");
		String asPrettyString = getResBodyAsPrettyString(response);
		System.out.println(message);

	}
	
	/**
	 * @Description API Delete Address Module
	 * @createDate 22/6/2022   
	 */

	@Test(priority = 5)

	public void deleteAddresss() {

		List<Header> h = new ArrayList<Header>();

		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
		DeleteAddress_Input_pojo deleteAddress_Input_pojo=new DeleteAddress_Input_pojo(address);
		addBody(deleteAddress_Input_pojo);
		Response response = requestType("DELETE", Endpoints.DELETEADDRESS);
		System.out.println(getStatusCode(response));
		Assert.assertEquals(getStatusCode(response), 200, "verifycode");
		DeleteAddress_Output_pojo deleteAddress_Output_pojo = response.as(DeleteAddress_Output_pojo.class);

		String message = deleteAddress_Output_pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(message, "Address deleted successfully", "Verify Address deleted successfully");

	}
	
	/**
	 * @Description API Change Profile Picture Module
	 * @createDate 22/6/2022   
	 */
	@Test(priority = 6)
	public void changeProfilePic() throws Exception {
		
		List<Header> h = new ArrayList<Header>();

		Header h1 = new Header("Content-Type","multipart/form-data");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
//		System.out.println(headers);
		multipart();
	
		Response response=requestType("POST", Endpoints.CHANGEPROFILEPIC);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		
		Assert.assertEquals(statusCode, 200, "verifycode");
		ChangeProfilePic_Output_pojo changeProfilePic_Output_pojo=response.as(ChangeProfilePic_Output_pojo.class);
		String message = changeProfilePic_Output_pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(message, "Profile updated Successfully", "Verify update successfully");

	}	
}
