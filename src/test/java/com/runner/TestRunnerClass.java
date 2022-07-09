package com.runner;

import java.io.FileNotFoundException;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.base.BaseClass;
import com.reports.Reporting;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

/**
 * 
 * @author siren
 * @Description To run the project
 * @CreationDate 30/06/2022
 *
 */

@RunWith(Cucumber.class)
@CucumberOptions(snippets = SnippetType.CAMELCASE, strict = true, dryRun = false, plugin = { "pretty",
		"json:target\\Cucumber.json" }, monochrome = true, features = "src\\test\\resources", glue = "com.stepdefinition")

public class TestRunnerClass extends BaseClass {
	
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @Description Used to call generated JVM Report
	 * @CreationDate 30/06/2022
	 * 
	 */
	@AfterClass
	public static void afterClass() throws FileNotFoundException, IOException {
		Reporting.generateJVMReport(System.getProperty("user.dir") + "\\target\\cucumber.json");

	}
}
