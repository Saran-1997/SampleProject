package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author DELL
 * @Description To maintain all reusable methods
 * @createDate 22/6/2022
 */


public class BaseClass {


	RequestSpecification reqSpec;
	public static Response response;
	
	/**
	 * 
	 * @param key
	 * @Discription Used to add Header
	 * @param value
	 * @CreateDate 22/06/2022
	 */

	public void addHeader(String key, String value) {
		reqSpec = RestAssured.given().header(key, value);
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @Description Used to get queryParam response
	 * @createDate 22/6/2022 
	 */
	

	public void queryParam(String key, String value) {
		reqSpec = reqSpec.queryParam(key, value);
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @Description Used to get pathParam response
	 * @createDate 22/6/2022 
	 */

	public void pathParam(String key, String value) {
		reqSpec = reqSpec.param(key, value);

	}
	
	/**
	 * 
	 * @param reqBody
	 * @Description Used to add body
	 * @createDate 22/6/2022
	 */

	public void addBody(String reqBody) {
		reqSpec = reqSpec.body(reqBody);
	}
	
	/**
	 * 
	 * @param reqType
	 * @param endpoint
	 * @return Response
	 * @Description Used to specify requestType
	 * @createDate 22/6/2022 
	 */

	public Response requestType(String reqType, String endpoint) {

		switch (reqType) {
		case "GET":
			response = reqSpec.log().all().get(endpoint);
			break;
		case "POST":
			response = reqSpec.log().all().post(endpoint);
			break;
		case "PUT":
			response = reqSpec.log().all().put(endpoint);
			break;
		case "DELETE":
			response = reqSpec.log().all().delete(endpoint);
			break;

		default:
			break;
		}
		return response;
	}

	/**
	 * 
	 * @param response
	 * @return int
	 * @Description Used to get status Code
	 * @createDate 22/6/2022 
	 */

	public int getStatusCode(Response response) {
		int statuscode = response.getStatusCode();
		return statuscode;

	}
	
	/**
	 * 
	 * @param response
	 * @return ResponseBody
	 * @Description Used to get Response Body
	 * @createDate 22/6/2022 
	 */

	public ResponseBody getResponseBody(Response response) {
		ResponseBody responsebody = response.getBody();
		return responsebody;
	}
	
	/**
	 * 
	 * @param response
	 * @return String
	 * @Description Used to Make Strings Pretty
	 * @createDate 22/6/2022 
	 */

	public String getResBodyAsString(Response response) {
		String asString = getResponseBody(response).asString();
		return asString;
	}

	public String getResBodyAsPrettyString(Response response) {
		String asPrettyString = getResponseBody(response).asString();
		return asPrettyString;

	}
	/**
	 * 
	 * @param username
	 * @param password
	 * @Description Used to Authenticate login
	 * @createDate 22/6/2022 
	 */

	public void basicAuth(String username, String password) {
		reqSpec = reqSpec.auth().preemptive().basic(username, password);

	}
	
	/**
	 * 
	 * @param key
	 * @return String
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @Description Used to Pass location path
	 * @createDate 22/6/2022 
	 */

	public String getPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(System.getProperty("user.dir") + "\\config.properties"));
		Object object = properties.get(key);
		String value = (String) object;
		return value;

	}
	
	/**
	 * 
	 * @param reqBody
	 * @Description Used to add body
	 * @createDate 22/6/2022
	 */
	
	public void addBody(Object reqbody) {
		reqSpec= reqSpec.body(reqbody);

	}
	
	/**
	 * 
	 * @param headers
	 * @Description Used to add Headers
	 * @createDate 22/6/2022 
	 */
	public void addHeaders(Headers headers) {
		reqSpec = RestAssured.given().headers(headers);

	}
	
	/**
	 * 
	 * @Description Used to Get Image Location
	 * @createDate 22/6/2022 
	 */
	
	public void multipart() throws Exception {
		reqSpec = reqSpec.multiPart("profile_picture", new File("C:\\Users\\saran\\Pictures\\Screenshots\\a.png"));

	}

	
	
	
	
	
	
	

}
	


