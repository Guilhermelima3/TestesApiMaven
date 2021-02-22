import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class TesteApi extends MassaDados{

    @BeforeClass
    public static void urlbase(){
        baseURI = "https://api.thecatapi.com/v1/";
    }
    @Test
    public void  cadastro() {
        Response response = given().contentType("application/json").body(bodyCadastro).
                when().post(urlCadastro);
                validacao(response);
    }

    @Test
    public void  votacao() {
        Response response = given().contentType("application/json")
                .body(corpoVotacao)
                .when().post("votes/");

        response.then().body("message",containsString("SUCCESS")).statusCode(200);
        validacao(response);

        String id = response.jsonPath().getString("id");
        vote_id = id;
        System.out.println("ID =>" + id);
    }
    
    //@Test
    public void  deletavotacao() {
        //erro
        votacao();
        deletaVoto();
    }

    private void deletaVoto() {
        String url= "votes/{vote_id}";
        Response response = given().contentType("application/json").header("x-api-key","a7cf904d-4182-4921-8e35-7e82fb1cc354").pathParam("vote_id", vote_id).when().delete(url);

        validacao(response);
    }

    @Test
    public void  favoritar() {

        favorita();
        desfavorita();
    }

    private void favorita() {
        Response response =
                given()
                .contentType("application/json")
                .header("x-api-key","a7cf904d-4182-4921-8e35-7e82fb1cc354")
                .body(corpoFavorita).when().post("favourites");

                String id = response.jsonPath().getString("id");
                vote_id = id;

                validacao(response);
    }
    private void desfavorita() {
        String url= "favourites/{favourite_id}";
        Response response =
                given().contentType("application/json")
                        .header("x-api-key","a7cf904d-4182-4921-8e35-7e82fb1cc354")
                        .pathParam("favourite_id", vote_id)
                        .when().delete(url);

                        validacao(response);
    }

    public void validacao(Response response){
        response.then().body("message",containsString("SUCCESS")).statusCode(200);
        System.out.println("Retorno Da Api =>" + response.body().asString());
    }
}
