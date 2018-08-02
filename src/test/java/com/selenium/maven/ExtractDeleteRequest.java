package com.selenium.maven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.selenium.maven.*;

public class ExtractDeleteRequest {

	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		FileInputStream  str = new FileInputStream(System.getProperty("user.dir")+"/env.properties");
		prop.load(str);
	}	
	
	@Test
	public void extractDelete() {		
		RestAssured.baseURI = prop.getProperty("Host");
		Response resp = given().
		queryParam("key", prop.getProperty("KEY")).
		body(PayLoad.getStringBody()).
		when().
		post(Source.postUrl()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status", equalTo("OK")).
		//Extracting the place id
		extract().response();
		String responseString = resp.asString();
		System.out.println(responseString);
		JsonPath js = new JsonPath(responseString);
		String id = js.get("place_id");
		System.out.println("Place_id is: " + id);
		
		//Deleting the generated place id.
		given().
		queryParam("key", "AIzaSyBopd8sHB-iiqHUFr1lLljrkvqjg-Z--rs").
		body("{" + 
				"\"place_id\": \"" +id+ "\"" + 
				"}").
		when().
		post("/maps/api/place/delete/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status", equalTo("OK"));
	}
}
