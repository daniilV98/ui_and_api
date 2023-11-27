package API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.api.Register;
import ru.api.SuccessRegister;
import ru.api.UserData;
import ru.api.helpers.Specifications;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserDataTest {

    private final static String url = "https://reqres.in/";

    @Test
    public void getUsersData() {
        Specifications.installSpecification(Specifications.requestSpecification(url), Specifications.responseSpecificationOK200());

        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        List<String> firsName = users.stream().map(UserData::getFirst_name).toList();
        System.out.println(String.join(", ", firsName));
    }

    @Test //без pojo класса
    public void getUsersDataNew() {
        Specifications.installSpecification(Specifications.requestSpecification(url), Specifications.responseSpecificationOK200());

        Response response = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> users = jsonPath.get("data.first_name");
        System.out.println(String.join(", ", users));
    }

    @Test
    public void postPosts() {
        Specifications.installSpecification(Specifications.requestSpecification(url), Specifications.responseSpecificationOK200());

        Integer expectedId = 4;
        String expectedToken = "QpwL5tke4Pnpja7X4";

        Register user = new Register("eve.holt@reqres.in", "pistol");

        SuccessRegister successReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessRegister.class);

        Assertions.assertEquals(expectedId, successReg.getId());
        Assertions.assertEquals(expectedToken, successReg.getToken());
    }

    @Test //без pojo класса
    public void postPostsNew() {
        Specifications.installSpecification(Specifications.requestSpecification(url), Specifications.responseSpecificationOK200());

        Map<String, String> user = new HashMap<>();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");

        Integer expectedId = 4;
        String expectedToken = "QpwL5tke4Pnpja7X4";

        given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .body("id", equalTo(expectedId)) // сразу проверка ответа
                .body("token", equalTo(expectedToken)); // сразу проверка ответа
    }

    @Test
    public void deleteUser() {
        Specifications.installSpecification(Specifications.requestSpecification(url), Specifications.responseSpecificationStatus(204));

        given()
                .when()
                .delete("api/users/2")
                .then().log().all();
    }

    public static void main(String[] args) {
        Set set = new HashSet();
        set.add("dsdsds");
        set.add("s");
        set.add("dsddsdsds");

        Iterator f = set.iterator();
        boolean v = f.next().equals("dsddsdsds");
    }
}