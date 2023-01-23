package api.Endpoints;



/*Swagger Petstore---->https://petstore.swagger.io
 Create user(POST): https://petstore.swagger.io/v2/user
 Get user (GET): https://petstore.swagger.io/v2/user/{userName}
 Update user(PUT) : https://petstore.swagger.io/v2/user/{userName}
 Delete user(DELETE) : https://petstore.swagger.io/v2/user/{userName}
 
 */

public class Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	//User Model
	
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+"/user/{userName}";
	public static String put_url = base_url+"/user/{userName}";
	public static String delete_url = base_url+"user/{userName}";

}
