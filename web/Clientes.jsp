<%-- 
    Document   : Clientes
    Created on : 25/08/2020, 10:32:14
    Author     : santi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <div class="d-flex">
            <!-- Columna del formulario cliente-->
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <form action="Controlador?menu=Cliente" method="POST">
                            <div class="form-group">
                                <label>Dni:</label>
                                <input type="text"  value="${cliente.getDni()}" name="txtDni" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Nombres:</label>
                                <input type="text" value="${cliente.getNom()}" name="txtNombre"class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Direccion:</label>
                                <input type="text" value="${cliente.getDir()}" name="txtDir"class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Estado:</label>
                                <input type="text" value="${cliente.getEs()}" name="txtEstado"class="form-control">
                            </div>
                            <input type="submit"  name="accion" value="Agregar" class="btn btn-success">
                            <input type="submit"  name="accion" value="Actualizar" class="btn btn-warning">
                        </form>

                    </div>

                </div>    
            </div>

            <!-- Columna de los clientes ya agregados-->
            <div class="col-sm-10">
                <div class="card">
                    <div class="card-body">
                        <table class="table table-hover table-responsive">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>DNI</th>
                                    <th>NOMBRES</th>
                                    <th>DIRECCION</th>
                                    <th>ESTADO</th>
                                    <th>ACCIONES</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="em" items="${clientes}">
                                    <tr>
                                        <td>${em.getId()}</td>
                                        <td>${em.getDni()}</td>
                                        <td>${em.getNom()}</td>
                                        <td>${em.getDir()}</td>
                                        <td>${em.getEs()}</td>
                                        <td>
                                            <a class="btn btn-warning" href="Controlador?menu=Cliente&accion=Editar&id=${em.getId()}">Editar</a>
                                            <a class="btn btn-danger" href="Controlador?menu=Cliente&accion=Eliminar&id=${em.getId()}">Delete</a>
                                        </td>
                                    </tr>

                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
