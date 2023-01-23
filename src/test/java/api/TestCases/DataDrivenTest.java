package api.TestCases;
import api.Utilities.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Endpoints.UserEndPoints;
import api.Payloads.User;
import io.restassured.response.Response;

public class DataDrivenTest {
	

	@Test(priority=1, dataProvider ="Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userID, String userName, String fname, String lName, String useremail, String pwd, String ph)
	{
	    User userPayload = new User();
	    
	    userPayload.setId(Integer.parseInt(userID));
	    userPayload.setUsername(userName);
	    userPayload.setFirstName(fname);
	    userPayload.setLastName(lName);
	    userPayload.setEmail(useremail);
	    userPayload.setPassword(pwd);
	    userPayload.setPhone(ph);
	    
	    Response response = UserEndPoints.createUser(userPayload);
	        
	    Assert.assertEquals(response.getStatusCode(), 200);
	    
	}
	
	@Test(priority=2, dataProvider ="UserNames", dataProviderClass= DataProviders.class)
	public void testDeleteUserByName(String UserName)
	{
		Response response=  UserEndPoints.deleteUser(UserName);
		Assert.assertEquals(response.getStatusCode(),405);
	}

}
