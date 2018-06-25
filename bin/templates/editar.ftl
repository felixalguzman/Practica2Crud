<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Inicio - Practica 2 Crud</title>

    <!-- Bootstrap core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="../css/simple-sidebar.css" rel="stylesheet">
    <link href="../css/menu.css" rel="stylesheet">


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

                <div id="tituloMenu">

                    <a href="#menu-toggle"  style="text-decoration: none" class="fas fa-bars" id="menu-toggle"></a>

                    <h1 id="titulo">Editar estudiante</h1>

                </div>
            </div>





        </div>
    </div>
    <!-- /#page-content-wrapper -->

    <div>


    <form method="POST" action="/editarP">
        <div class="form-group">
            </br></br>
            <label style="float: left" for="matricula">Matricula</label>
            <input type="number" class="form-control" placeholder="Introduce la matricula"  name="matricula" value="${estudiante.matricula?string["0"]}" required>
        </div>
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input type="text" class="form-control" placeholder="Introduce el nombre"  name="nombre" value="${estudiante.nombre}" required>
        </div>
        <div class="form-group">
            <label for="apellido">Apellido</label>
            <input type="text" class="form-control" placeholder="Introduce el apellido" name="apellido" value="${estudiante.apellido}" required>
        </div>
        <div class="form-group">
            <label for="telefono">Teléfono</label>
            <input type="text" class="form-control" placeholder="Introduce el telefono" name="telefono" value="${estudiante.telefono}" required>
        </div>


        <div style="float: right">
            <button type="submit" class="btn btn-default">Actualizar</button>
        </div>


    </form>

    </div>

</div>
<!-- /#wrapper -->

<!-- Bootstrap core JavaScript -->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>

</body>

</html>
