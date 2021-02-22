import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class TesteApi {
    @Test
    public void  cadastro() {
        String url= "https://api.thecatapi.com/v1/user/passwordlesssignup";
        String bodymessage="{\"email\": \"guilhermel_ima@hotmail.com\",\"appDescription\": \"teste api\"}";

        Response response = given().contentType("application/json").body(bodymessage).
                when().post(url);

                response.then().body("message",containsString("SUCCESS")).statusCode(200);

                System.out.println("Retorno =>" + response.body().asString());
    }

    @Test
    public void  votos() {
        String url= "https://api.thecatapi.com/v1/votes/";

        Response response = given().contentType("application/json").body("{\"image_id\": \"rzPxlNge1\", \"value\": \"true\", \"sub_id\": \"demo-cbd3aa\"}").when().post(url);

        response.then().body("message",containsString("SUCCESS")).statusCode(200);

        System.out.println("Retorno =>" + response.body().asString());
        String id = response.jsonPath().getString("id");
            System.out.println("ID =>" + id);
    }
}
