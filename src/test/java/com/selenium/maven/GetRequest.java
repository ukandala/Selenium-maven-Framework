package com.selenium.maven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import org.apache.logging.log4j.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequest {

	private static Logger log = LogManager.getLogger(GetRequest.class.getName());
	@Test
	public void example() {
		//base uri
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		Response resp = given().
		param("key", "AIzaSyBopd8sHB-iiqHUFr1lLljrkvqjg-Z--rs").
		param("location", "-33.8670522,151.1957362").
		param("radius", "500").log().all()./*To print the log*/
		when().
		get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("results[0].name", equalTo("Sydney")).and().
		//giving assertion for header
		header("Server", "scaffolding on HTTPServer2").
		extract().response();
		JsonPath js = Reusable.rawToJson(resp);
		int count = js.get("results.size()");
		System.out.println("===========================");
		System.out.println(count);
		for(int i=0;i<count;i++) {
			String raw = js.get("results["+i+"].name");
			System.out.println(raw);
		}
		
		
	}

}
