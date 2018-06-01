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

    private static ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private static Estudiante estudianteEditar;
    public static void main(String[] args) {


        staticFiles.location("/templates");




        Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(Main.class, "/templates");

        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);



        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Inicio");
            if (estudiantes.size() > 5)
                attributes.put("estudiantes",estudiantes.subList(0,5));
            else
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
                String matriculaParseada = matricula.replace(",", "");
                estudiantes.add(new Estudiante(Integer.parseInt(matriculaParseada), nombre, apellido, telefono));
                response.redirect("/");
            }catch (Exception error){
                System.out.println("Hubo un error agregando un estudiante " + error.toString());
                response.redirect("/agregarestudiante");
            }
            return escritor;
        });

        get("/verEstudiantes", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Inicio");
            attributes.put("cantEstudiantes", estudiantes.size());
            attributes.put("estudiantes",estudiantes);
            return new ModelAndView(attributes, "verEstudiantes.ftl");
        }, freeMarkerEngine);

        get("/generar", (request, response) -> {

            estudiantes.add(new Estudiante(20141234, "feli", "guzman", "654987654"));
            estudiantes.add(new Estudiante(20133122, "ali", "rodriguez", "32165444"));
            estudiantes.add(new Estudiante(20149877, "jose", "pere", "65461654455"));
            estudiantes.add(new Estudiante(20154545, "lui", "martinez", "6546554545"));

            response.redirect("/verEstudiantes");
            return "";
                });


        get("/eliminar/:posicion",(request, response) -> {

            String posicion = request.params("posicion");

            estudiantes.remove(Integer.parseInt(posicion));


            response.redirect("/");
            return "";
        });

        get("/editar/:posicion", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String posicion = request.params("posicion");
            Estudiante estudiante = estudiantes.get(Integer.parseInt(posicion));

            estudianteEditar = estudiante;
            attributes.put("titulo", "Editar estudiante");
            attributes.put("estudiante", estudiante);

            return new ModelAndView(attributes, "editar.ftl");
        }, freeMarkerEngine);

        post("/editarP",(request, response) -> {
            StringWriter escritor = new StringWriter();
            try {
                String matricula = request.queryParams("matricula");
                String nombre = request.queryParams("nombre");
                String apellido = request.queryParams("apellido");
                String telefono = request.queryParams("telefono");
               int pos = posicionEstudiante(estudianteEditar);

               estudianteEditar.setMatricula(Integer.parseInt(matricula));
               estudianteEditar.setNombre(nombre);
               estudianteEditar.setApellido(apellido);
               estudianteEditar.setTelefono(telefono);

               if (pos != -1){

                   estudiantes.set(pos, estudianteEditar);
                   estudianteEditar = null;
               }

                response.redirect("/");
            }catch (Exception error){
                System.out.println("Hubo un error editando un estudiante " + error.toString());

            }
            return escritor;
        });

        get("/ver/:posicion", (request, response) -> {

            String posicion = request.params("posicion");
            Estudiante estudiante = estudiantes.get(Integer.parseInt(posicion));

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("estudiante", estudiante);

            return new ModelAndView(attributes, "ver.ftl");
        }, freeMarkerEngine);


    }


    private static int posicionEstudiante(Estudiante estudiante){

        for (int i =0; i < estudiantes.size(); i++){

            if (estudiantes.get(i) == estudiante)
            {
                return i;
            }
        }

        return  -1;
    }
}
