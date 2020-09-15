

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

        <!-- Los iconos tipo Solid de Fontawesome-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
        <script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>

        <!-- Nuestro css-->
        <link rel="stylesheet" type="text/css" href="" th:href="@{/css/index.css}">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-info" >



            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto" >
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none; color: white"  class="btn btn-outline-dark" href="Controlador?nemu=Principal">home </a>
                    </li>
                    <li class="nav-item">
                        <a  style="margin-left: 10px; border: none; color: white" class="btn btn-outline-dark"href="Controlador?menu=Cliente" target="myframe">cliente</a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none; color: white"class="btn btn-outline-dark" href="Controlador?menu=Producto" target="myframe">Producto</a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none ;color: white"class="btn btn-outline-dark" href="Controlador?menu=Empleado&accion=Listar" target="myframe">Empleado</a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none; color: white" class="btn btn-outline-dark" href="Controlador?menu=NuevaVenta&accion=default" target="myframe">Nueva venta</a>
                    </li>


                </ul>
            </div>
            <div class="dropdown">
                <button style="border: none" class="btn btn-outline-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${usuario.getNom()}
                </button>
                <div class="dropdown-menu text-center">

                    <a class="dropdown-item" href="#">
                        <img src="img/avatar-10.jpg" alt="60" width="60"/>
                    </a>
                    <a class="dropdown-item" href="#">${usuario.getUser()}</a>
                    <a class="dropdown-item" href="#">${usuario.getDni()}</a>
                    <div class="dropdown-divider"></div>
                    <form action="Validar" method="POST" >
                        <button name="accion" value="salir"class="dropdown-item" href="#" class="btn btn-danger text-white"  >Salir</button>
                    </form>
                </div>

            </div>
        </nav>
        <div class="m-4" style="height: 550px">
            <iframe name="myframe" style="height:100% ; width: 100%"> </iframe>      
        </div>            
        <div class="embed-responsives m-4" style="height: 530px;">
            <iframe class="embed-responsive-item" name="myFrame"  style="height: 100%; width: 100%; border:navy"></iframe>
        </div>   
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
