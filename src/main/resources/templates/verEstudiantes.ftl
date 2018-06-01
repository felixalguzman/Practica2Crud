<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=yes">
    <meta name="description" content="">
    <meta name="author" content="">

    <title> ${titulo}  </title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="css/simple-sidebar.css" rel="stylesheet">
    <link href="css/menu.css" rel="stylesheet">

</head>

<body>

<div id="wrapper">

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="#">
                    Practica 2
                </a>
            </li>
            <li>
                <a href="/">Inicio</a>
            </li>
            <li>
                <a href="/agregarEstudiantes">Agregar estudiante</a>
            </li>
            <li>
                <a href="/verEstudiantes">Ver estudiantes</a>
            </li>

        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div style="float : left">

                    <div id="tituloMenu" style="float: left;">


                        <a href="#menu-toggle"  style="text-decoration: none" class="fas fa-bars" id="menu-toggle"></a>


                        <h3 id="titulo">Cantidad de estudiantes registrados ${cantEstudiantes}</h3>

                        <a href="/generar" class="btn btn">Generar</a>

                    </div>



                </div>




                <table class="table">
                    <thead>
                        <tr>
                            <th>Matricula</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Telefono</th>
                            <th>Eliminar</th>
                            <th>Modificar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#assign x = 0>
                        <#list estudiantes as estudiante>
                            <tr>

                                <td>${estudiante.matricula?string["0"]}</td>
                                <td>${estudiante.nombre}</td>
                                <td>${estudiante.apellido}</td>
                                <td>${estudiante.telefono}</td>

                                <td><a href="/eliminar/${estudiante.matricula}/${estudiante.nombre}/${estudiante.apellido}/${estudiante.telefono}"   class="btn"><i class="far fa-trash-alt"></i></a></td>
                                <td><a href="/editar/${estudiante.matricula}/${estudiante.nombre}/${estudiante.apellido}/${estudiante.telefono}" class="btn"><i class="fas fa-pencil-alt"></i></a></td>

                            </tr>
                        <#assign x++>
                        </#list>
                    </tbody>
                </table>

            </div>
        </div>
        <!-- /#page-content-wrapper -->

</div>
    <!-- /#wrapper -->

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>

</body>

</html>
