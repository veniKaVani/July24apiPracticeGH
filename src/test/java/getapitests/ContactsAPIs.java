package getapitests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;

public class ContactsAPIs {
	
	@Test
	public void getContactsTests_1() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
		  .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmE4YWM4NjFiMjA2NTAwMTM0MmRlZTkiLCJpYXQiOjE3MjU1MDQwOTh9.qK7w_8wzyiScBnZ1VFN_HU_A5ie_iLHTIaP5u5kKEIU")
		    .when().log().all()
		      .get("/contacts")
		        .then().log().all()
                  .assertThat()
                    .statusCode(200)
                      .and()
                       .statusLine("HTTP/1.1 200 OK")
                         .and()
                          .contentType(ContentType.JSON)
		                    .and()
		                      .body("$.size()",equalTo(35));
		       
	}
	
	@Test
	public void getContactsTests_2InvalidToken() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
		  .header("Authorization", "Bearer -Binka24")
		    .when().log().all()
		      .get("/contacts")
		        .then().log().all()
		          .assertThat()
		            .statusCode(401);
	}

}
