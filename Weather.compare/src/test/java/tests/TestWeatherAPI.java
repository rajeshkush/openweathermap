package tests;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class TestWeatherAPI {

	@Test
	public void postive_test() {
		
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI="http://api.openweathermap.org/data/2.5/weather";

		 // Get the RequestSpecification of the request that you want to sent
		 // to the server. The server is specified by the BaseURI that we have
		 // specified in the above step.
//		using ValidatableResponse
		ValidatableResponse  response = RestAssured.given().param("q", "Kanpur")
				.param("appid", "7fe67bf08c80ded756e598d6f8fedaea").
				when().get().then();
		response.statusCode(200);
		
		Reporter.log("verified the status code  is : "+response.extract().asString() , true);
//		ValidatableResponse statusCode = response.statusCode(200);
//		Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
////		
		
					
	}
	
	@Test
	public void Invalid_state() {
		
		
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI="http://api.openweathermap.org/data/2.5/weather";
		ValidatableResponse resp_st= RestAssured.given().param("q", "Kanpura")
				.param("appid", "7fe67bf08c80ded756e598d6f8fedaea").
				when().get().then();
		
	Reporter.log("verified the status code  is : "+resp_st.extract().asString() , true);
	resp_st.statusCode(404);
	resp_st.body("message", Matchers.notNullValue());
	resp_st.body("message",Matchers.equalToIgnoringCase("city not found"));
	
	Reporter.log("Verified the error message successfully",true);

	 // Assert that correct status code is returned.
	ValidatableResponse statusCode = resp_st.statusCode(404);
	Assert.assertEquals(statusCode /*actual value*/, 404 /*expected value*/, "city not found");
	
			
	}
	
	@Test
	public void Invalid_appid() {
		
		RestAssured.baseURI="http://api.openweathermap.org/data/2.5/weather";
		ValidatableResponse resp_st= RestAssured.given().param("q", "Kanpur")
				.param("appid", "7fe67bf08c80ded756e598d6f8fedaeazz").
				when().get().then();
		
	Reporter.log("verified the status code  is : "+resp_st.extract().asString() , true);
	resp_st.statusCode(401);
	resp_st.body("message", Matchers.notNullValue());
	resp_st.body("message",Matchers.equalToIgnoringCase("Invalid API key"));
	
	Reporter.log("Verified the error message successfully",true);

	 // Assert that correct status code is returned.
	ValidatableResponse statusCode = resp_st.statusCode(401);
	Assert.assertEquals(statusCode /*actual value*/, 401 /*expected value*/, "Invalid API key");
	
			
	}
	
	
	
}


//http://api.openweathermap.org/data/2.5/weather?q=Kanpur&appid=7fe67bf08c80ded756e598d6f8fedaea  