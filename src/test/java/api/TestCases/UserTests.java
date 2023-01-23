package api.TestCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Endpoints.UserEndPoints;
import api.Payloads.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	public Logger logger;
	@BeforeClass
	public void setupData()
	{
	 faker = new Faker();	
	 userPayload = new User();
	 
	 userPayload.setId(faker.idNumber().hashCode());
	 userPayload.setUsername(faker.name().username());
	 userPayload.setFirstName(faker.name().firstName());
	 userPayload.setLastName(faker.name().lastName());
	 userPayload.setEmail(faker.internet().safeEmailAddress());
	 userPayload.setPassword(faker.internet().password(5,10));
	 userPayload.setLastName(faker.phoneNumber().cellPhone());
	 
	 //logs
	 logger = LogManager.getLogger(this.getClass());
	 
	 
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("**************Create User**********");
	    Response response=	UserEndPoints.createUser(userPayload);
	    response.then().log().all();
	    
	    Assert.assertEquals(response.getStatusCode(),200);
	    
	    logger.info("**************User is created**********");
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("**************Reading User Info**********");
	   Response response = UserEndPoints.readUser(this.userPayload.getUsername());
	   response.then().log().all();
	   
	  Assert.assertEquals(response.getStatusCode(),200);
	  
	  logger.info("*************User Info Displayed**********");
	}
	
	@Test(priority=3)
	public void testUpdateByUser()
	{
		logger.info("**************Updating User Info**********");
		 userPayload.setFirstName(faker.name().firstName());
		 userPayload.setLastName(faker.name().lastName());
		 userPayload.setEmail(faker.internet().safeEmailAddress());
		 		 
		 
		Response response= UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("**************User Info Updated **********");
		
		//checking data after update
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
	}
	
	@Test(priority=4)
	public void testDeleteByUser() 
	{
		logger.info("**************Deleting User**********");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(),405);
		 logger.info("*************User Deleted**********");
	}
}
