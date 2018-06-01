import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class Main {

    static ArrayList<Estudiante> estudiantes = new ArrayList<>();
    public static void main(String[] args) {


        staticFiles.location("/templates");




        Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(Main.class, "/templates");

        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);



        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Inicio");
            attributes.put("cantEstudiantes", estudiantes.size());
            attributes.put("estudiantes",estudiantes);
            return new ModelAndView(attributes, "inicio.ftl");
        }, freeMarkerEngine);


        get("/agregarEstudiantes", (request, response) -> configuration.getTemplate("formularioAgregar.ftl"));


        post("/agregar",(request, response) -> {
            StringWriter escritor = new StringWriter();
            try {
                String matricula = request.queryParams("matricula");
                String nombre = request.queryParams("nombre");
                String apellido = request.queryParams("apellido");
                String telefono = request.queryParams("telefono");
                estudiantes.add(new Estudiante(Integer.parseInt(matricula), nombre, apellido, telefono));
                response.redirect("/");
            }catch (Exception error){
                System.out.println("Hubo un error agregando un estudiante " + error.toString());
                response.redirect("/agregarestudiante");
            }
            return escritor;
        });

        get("/eliminar/:matricula/:nombre/:apellido/:telefono",(request, response) -> {

            String matricula = request.params("matricula");
            String nombre = request.params("nombre");
            String apellido= request.params("apellido");
            String telefono= request.params("telefono");
            Estudiante estudiante = buscarEstudiante(Integer.parseInt(matricula), nombre, apellido, telefono);

            if (estudiante != null){

                estudiantes.remove(estudiante);
            }

            response.redirect("/");
            return "";
        });

        get("/editar", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Inicio");

            return new ModelAndView(attributes, "formularioAgregar.ftl");
        }, freeMarkerEngine);


    }

    private static Estudiante buscarEstudiante(int matricula, String nombre, String apellido, String telefono){

        for (Estudiante estudiante:estudiantes) {

            if (estudiante.getMatricula() == matricula && estudiante.getNombre().equalsIgnoreCase(nombre) && estudiante.getApellido().equalsIgnoreCase(apellido) && estudiante.getTelefono().equalsIgnoreCase(telefono)){

                return estudiante;
            }
        }
        return null;
    }
}
