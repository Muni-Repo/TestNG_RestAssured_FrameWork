package com.pets.store;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.pets.common.Common_Utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class Petstore {

	public Response Get_Resp;
	public Response Post_Resp;
	public Response Put_Resp;
	public Response Delete_Resp;
    
	//====================================================================================================================
	
    @Test(priority=1)
	public void Get_Method() throws IOException {
    	
    	String Get_URL = Common_Utilities.getpropertyvalue("GET_URL", "Petstore_config.properties");
    	String Expected_StatusCode = Common_Utilities.getpropertyvalue("Status_Code", "Petstore_config.properties");
    	String Expected_StatusDesc = Common_Utilities.getpropertyvalue("Status_Description", "Petstore_config.properties");
    	
    	String Actual_Status = "Available Pets";
		String Expected_Status = "Available Pets";
		
		RestAssured.baseURI = Get_URL;
		try {
			Get_Resp = RestAssured.given()
					.queryParam("status", "available")
					.when()
					.get();

			Reporter.log("Get-Response: "+Get_Resp.jsonPath().prettyPrint());
		
			String resp = Get_Resp.getStatusLine();
			String[] resp1 = resp.split(" ");

			String Actual_StatusCode = resp1[1];
			String Actual_StatusDesc = resp1[2];
	
			Reporter.log("Get Response Id: "+Get_Resp.jsonPath().getString("id[0]"));
			Reporter.log("Get Response Name: "+Get_Resp.jsonPath().getString("name[0]"));
			Reporter.log("Get Response Status: "+Get_Resp.jsonPath().getString("status[0]"));

			if(Actual_StatusCode.contentEquals(Expected_StatusCode)) {
				Assert.assertEquals(Actual_StatusCode, Expected_StatusCode);
			}else {
				Assert.assertTrue(false,"Expected StatusCode: "+Expected_StatusCode+" ---> Not Equal To ---> "+"Expected StatusCode: "+Expected_StatusCode);
			}

			if(Actual_StatusDesc.contentEquals(Expected_StatusDesc)) {
				Assert.assertEquals(Actual_StatusDesc, Expected_StatusDesc);				
			}else {
				Assert.assertTrue(false,"Expected StatusDesc: "+Expected_StatusDesc+" ---> Not Equal To ---> "+"Expected StatusDesc: "+Expected_StatusDesc);
			}

			if(Actual_Status.contentEquals(Expected_Status)) {
				Assert.assertEquals(Actual_Status, Expected_Status);
			}else {
				Assert.assertTrue(false,"Actual Status: "+Actual_Status+" ---> Not Equal To ---> "+"Expected Status: "+Expected_Status);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//===============================================================================================

     @Test(priority=2)
	 public void Post_Method() throws IOException, InterruptedException {
		 
		String Json_Body = Common_Utilities.generateStringFromResource("src\\test\\resources\\json_files\\Petstore_post.json");		
		String Post_URL = Common_Utilities.getpropertyvalue("Post_URL", "Petstore_config.properties");
    	String Expected_StatusCode = Common_Utilities.getpropertyvalue("Status_Code", "Petstore_config.properties");
    	String Expected_StatusDesc = Common_Utilities.getpropertyvalue("Status_Description", "Petstore_config.properties");
    	
    	String Actual_Status = "New pet added";
		String Expected_Status = "New pet added";

		try {
			Post_Resp = RestAssured.
					given()
					.contentType("application/json")
					.body(Json_Body)
					.post(Post_URL);

			Reporter.log("Post-Response: "+Post_Resp.jsonPath().prettyPrint());

			String resp = Post_Resp.getStatusLine();
			String[] resp1 = resp.split(" ");

			String Actual_StatusCode = resp1[1];
			String Actual_StatusDesc = resp1[2];

			Reporter.log("Post-Response Id: "+Post_Resp.jsonPath().getString("id"));
			Reporter.log("Post-Response Name: "+Post_Resp.jsonPath().getString("name"));
			Reporter.log("Post-Response Status: "+Post_Resp.jsonPath().getString("status"));

			if(Actual_StatusCode.contentEquals(Expected_StatusCode)) {
				Assert.assertEquals(Actual_StatusCode, Expected_StatusCode);				
			}else {
				Assert.assertTrue(false,"Expected StatusCode: "+Expected_StatusCode+" ---> Not Equal To ---> "+"Expected StatusCode: "+Expected_StatusCode);
			}

			if(Actual_StatusDesc.contentEquals(Expected_StatusDesc)) {
				Assert.assertEquals(Actual_StatusDesc, Expected_StatusDesc);				
			}else {
				Assert.assertTrue(false,"Expected StatusDesc: "+Expected_StatusDesc+" ---> Not Equal To ---> "+"Expected StatusDesc: "+Expected_StatusDesc);
			}

			if(Actual_Status.contentEquals(Expected_Status)) {
				Assert.assertEquals(Actual_Status, Expected_Status);
			}else {
				Assert.assertTrue(false,"Actual Status: "+Actual_Status+" ---> Not Equal To ---> "+"Expected Status: "+Expected_Status);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//===============================================================================================
	
	@Test(priority=3)
	public void Put_Method() throws IOException {

		String Json_Body = Common_Utilities.generateStringFromResource("src\\test\\resources\\json_files\\Petstore_update.json");
		String Put_URL = Common_Utilities.getpropertyvalue("Put_URL", "Petstore_config.properties");
    	String Expected_StatusCode = Common_Utilities.getpropertyvalue("Status_Code", "Petstore_config.properties");
    	String Expected_StatusDesc = Common_Utilities.getpropertyvalue("Status_Description", "Petstore_config.properties");
    	
    	String Actual_Status = "Status updated";
		String Expected_Status = "Status updated";
		
		try {
			Put_Resp = RestAssured.
					given()
					.contentType("application/json")
					.body(Json_Body)
					.put(Put_URL);

			Reporter.log("Put-Response: "+Put_Resp.jsonPath().prettyPrint());

			String resp = Put_Resp.getStatusLine();
			String[] resp1 = resp.split(" ");

			String Actual_StatusCode = resp1[1];
			String Actual_StatusDesc = resp1[2];

			String getId = Put_Resp.jsonPath().getString("id");
			Common_Utilities.updateTestData("src\\test\\resources\\Testdata\\Petstore_Runtime_Values.properties", "PetStoreID", getId);
			
			Reporter.log("Put-Response Id: "+Put_Resp.jsonPath().getString("id"));
			Reporter.log("Put-Response Name: "+Put_Resp.jsonPath().getString("name"));
			Reporter.log("Put-Response Status: "+Put_Resp.jsonPath().getString("status"));
			
			if(Actual_StatusCode.contentEquals(Expected_StatusCode)) {
				Assert.assertEquals(Actual_StatusCode, Expected_StatusCode);
			}else {
				Assert.assertTrue(false,"Expected StatusCode: "+Expected_StatusCode+" ---> Not Equal To ---> "+"Expected StatusCode: "+Expected_StatusCode);
			}

			if(Actual_StatusDesc.contentEquals(Expected_StatusDesc)) {
				Assert.assertEquals(Actual_StatusDesc, Expected_StatusDesc);				
			}else {
				Assert.assertTrue(false,"Expected StatusDesc: "+Expected_StatusDesc+" ---> Not Equal To ---> "+"Expected StatusDesc: "+Expected_StatusDesc);
			}

			if(Actual_Status.contentEquals(Expected_Status)) {
				Assert.assertEquals(Actual_Status, Expected_Status);				
			}else {
				Assert.assertTrue(false,"Actual Status: "+Actual_Status+" ---> Not Equal To ---> "+"Expected Status: "+Expected_Status);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	//===============================================================================================
	
	@Test(priority=4)
	public void Delete_Method() throws IOException {
			
		String  PetStoreID = Common_Utilities.getpropertyvalue("PetStoreID", "src\\test\\resources\\Testdata\\Petstore_Runtime_Values.properties");	
		String DeleteURL = Common_Utilities.getpropertyvalue("Delete_URL", "Petstore_config.properties");
		String Delete_URL = DeleteURL+PetStoreID;
		String Expected_StatusCode = Common_Utilities.getpropertyvalue("Status_Code", "Petstore_config.properties");
    	String Expected_StatusDesc = Common_Utilities.getpropertyvalue("Status_Description", "Petstore_config.properties");
    	
    	String Actual_Status = "PetId Deleted";
		String Expected_Status = "PetId Deleted";
		
		try {
			
			Delete_Resp = RestAssured.
					given()
					.delete(Delete_URL);

			Reporter.log("Delete-Response: "+Delete_Resp.jsonPath().prettyPrint());

			String resp = Delete_Resp.getStatusLine();
			String[] resp1 = resp.split(" ");

			String Actual_StatusCode = resp1[1];
			String Actual_StatusDesc = resp1[2];

			Reporter.log("Delete-Response Id: "+Delete_Resp.jsonPath().getString("code"));
			Reporter.log("Delete-Response Message: "+Delete_Resp.jsonPath().getString("message"));
	
			if(Actual_StatusCode.contentEquals(Expected_StatusCode)) {
				Assert.assertEquals(Actual_StatusCode, Expected_StatusCode);				
			}else {
				Assert.assertTrue(false,"Expected StatusCode: "+Expected_StatusCode+" ---> Not Equal To ---> "+"Expected StatusCode: "+Expected_StatusCode);
			}

			if(Actual_StatusDesc.contentEquals(Expected_StatusDesc)) {
				Assert.assertEquals(Actual_StatusDesc, Expected_StatusDesc);				
			}else {
				Assert.assertTrue(false,"Expected StatusDesc: "+Expected_StatusDesc+" ---> Not Equal To ---> "+"Expected StatusDesc: "+Expected_StatusDesc);
			}

			if(Actual_Status.contentEquals(Expected_Status)) {
				Assert.assertEquals(Actual_Status, Expected_Status);				
			}else {
				Assert.assertTrue(false,"Actual Status: "+Actual_Status+" ---> Not Equal To ---> "+"Expected Status: "+Expected_Status);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}


