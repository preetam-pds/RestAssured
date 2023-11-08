package com.testng.Restassured;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Requestbody.payload;


public class App 
{
    @Test(dataProvider="booksdata")
	public void Addbook(String isbn,String aisle)
    {
        System.out.println( "Hello World!" );
        RestAssured.baseURI="http://216.10.245.166";
		  String response = given().log().all().header("Content-Type","application/json").body(payload.Addbook(isbn,aisle))
				  .when().post("/Library/Addbook.php")
		.then().log().all().statusCode(200).extract().response().asString();
		
		System.out.println("Response is " +response);
		
		JsonPath js = new JsonPath(response);
		String Id =js.getString("id");
		System.out.println(Id);
    }
    
    @DataProvider(name="booksdata")
    public Object[][] getData() {
    	return new Object[][] {{"aara","1211"},{"base","13745"}};
    }
}
