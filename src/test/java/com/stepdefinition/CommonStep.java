package com.stepdefinition;

import org.junit.Assert;


import com.base.BaseClass;

import cucumber.api.java.en.Then;

/**
 * 
 * @author siren
 * @Description To verify status code
 * @CreationDate 30/06/2022
 *
 */

public class CommonStep extends BaseClass {
	
	/**
	 * 
	 * @param expValue
	 * @Description Used to get and verify status code
	 * @CreationDate 30/06/2022
	 * 
	 */
	@Then("User verify the status code is {int}")
	public void userVerifyTheStatusCodeIs(int int1) {
		System.out.println(getStatusCode(response));
//		Assert.assertEquals("Verify Status code",int1,LoginStep.commonVariables.getStatusCode());
		Assert.assertEquals("Verify Status code",int1,LoginStep.commonVariables.getStatusCode());
	}


}
//"Verify Status code",int1,LoginStep.commonVariables.getStatusCode());