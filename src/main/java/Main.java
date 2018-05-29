import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class Main {

    public static void main(String[] args) {

        staticFiles.location("/");


        get("/", ((request, response) -> "Hola"));
    }
}
