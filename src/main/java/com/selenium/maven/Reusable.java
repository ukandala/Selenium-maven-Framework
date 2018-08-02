package com.selenium.maven;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Reusable {

	public static JsonPath rawToJson(Response resp) {
		String responseString = resp.asString();
		System.out.println(responseString);
		JsonPath js = new JsonPath(responseString);
		return js;
	}
	
	public static XmlPath rawToXml(Response resp) {
		String responseString = resp.asString();
		XmlPath xml = new XmlPath(responseString);
		return xml;
	}
	
	public static String getSessionId() {
		Response rep = given().header("Content-Type", "application/json").
				body("{ \"username\": \"amiteshshukla999\", \r\n" + 
						"  \"password\": \"amitesh90\" \r\n" + 
						"}").
				when().
				post("/rest/auth/1/session").then().statusCode(200).
				extract().response();
				JsonPath js = Reusable.rawToJson(rep);
				String op = js.get("session.value");
				System.out.println(op);
				return op;
	}
}
