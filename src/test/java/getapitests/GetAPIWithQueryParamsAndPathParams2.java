package getapitests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetAPIWithQueryParamsAndPathParams2 {
	
	@Test
	public void getUserWith_QueryParamsTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
		  .header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		   
		     .queryParam("name", "trivedi")
		      .queryParam("status", "active")
		       .when().log().all()
		         .get("/public/v2/users")
		           .then().log().all()
		             .assertThat()
		               .statusCode(200)
                         .and()
                           .contentType(ContentType.JSON);
		
	}
	
	@Test
	public void getUserWith_QueryParams_WithHashMap() {
		RestAssured.baseURI = "https://gorest.co.in";
		
//		Map<String, String>queryMap = new HashMap<String, String>();
//		queryMap.put("name", "trivedi");
//		queryMap.put("status", "active");
//		queryMap.put("gender", "male");
		
		//enhancement in jdk 9: .of()
		Map<String, String>queryMap = Map.of(
				"name", "trivedi",
				"status", "active"
				);
				
		
		given().log().all()
		  .header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		   
		    .queryParams(queryMap)
		      .when().log().all()
		        .get("/public/v2/users")
		          .then().log().all()
		            .assertThat()
		              .and()
		                .contentType(ContentType.JSON);
		  
	}

}
