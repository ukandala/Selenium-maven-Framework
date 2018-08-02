package com.selenium.maven;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import com.selenium.maven.Source;
import com.selenium.maven.Reusable;

public class XmlExample {	
	
	@Test
	public void extractDelete() throws IOException {	
		String postdata = GenerateStringFromResource("E:\\programs\\PostBody.xml");
		RestAssured.baseURI = "https://maps.googleapis.com";
		Response resp = given().
		queryParam("key", "AIzaSyBopd8sHB-iiqHUFr1lLljrkvqjg-Z--rs").
		body(postdata).
		when().
		post(Source.postXmlUrl()).
		then().assertThat().statusCode(200).and().contentType(ContentType.XML).
		extract().response();
		//calling the rawtoXml function from reusable class
		XmlPath x = Reusable.rawToXml(resp);
		String xmlResponse = x.get("PlaceAddResponse.place_id");
		System.out.println(xmlResponse);
		
		
		//Extracting the place i
	}
	//
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
