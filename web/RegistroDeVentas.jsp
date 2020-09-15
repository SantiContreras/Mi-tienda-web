<%-- 
    Document   : RegistroDeVentas
    Created on : 25/08/2020, 09:45:06
    Author     : santi
--%>

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
        <title>Registro de ventas</title>
    </head>
    <body>
        <div class="d-flex">
            <!-- Primer columna de la pagina --> 
            <div class="col-sm-5">
                <div class="card">
                    <form action="Controlador?menu=NuevaVenta" method="POST">
                        <div class="card-body">
                            <div class="form-group">
                                <label> Datos del cliente</label>
                            </div>
                            <!-- columna campos codigo cliente y nombre del cliente, boton buscar-->
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="CodigoCliente" value="${cli.getDni()}" class="form-control" placeholder="codigo cliente">
                                    <button type="submit" name="accion" value="BuscarCliente" class=" btn btn-outline-info">Buscar</button>
                                </div>

                                <div class="col-sm-6">
                                    <input type="txt" name="nombredelcliente" value="${cli.getNom()}" class=" form-control"> 
                                </div>

                            </div>
                            <div class="form-group">
                                <label> Datos del productos</label>
                            </div>
                            <!-- columnas campos boton buscar codigo producto nombre producto -->
                            <div class="form-group d-flex"> 
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="codigoproducto" class="form-control" placeholder="codigo producto">
                                    <button type="submit" name="accion" value="BuscarProducto" class=" btn btn-outline-info">Buscar</button>
                                </div>

                                <div class="col-sm-6">
                                    <input type="txt" name="nombreProducto"  value="${pro.getNom()}"class=" form-control" placeholder="nombre del producto"> 
                                </div>
                            </div>
                            <!--columna campos precio y cantidad-->
                            <div class="form-group d-flex"> 
                                <div class="col-sm-6 d-flex">
                                    <label>precio  </label>
                                    <input type="text" name="precio" value="${pro.getPre()}"class="form-control" placeholder="$/ 0.00">
                                </div>
                                <div class="col-sm-3">
                                    <input type="number" name="cant"  class="form-control">
                                </div>

                                <div class="col-sm-3">
                                    <input type="txt" name="stock"  value="${pro.getStock()}"placeholder="stock"class=" form-control"> 
                                </div>
                            </div>
                            <!-- boton agregar-->
                            <div class="form-group">
                                <input type="submit" name="accion" value="Agregar" class="btn btn-success">
                            </div>

                        </div>
                    </form>
                </div>
            </div>
            <!-- segunda columna de la pagina, la tabla con los datos -->
            <div class="col-sm-7">
                <div class="card">
                    <div class="card-body">

                        <div class="col-sm-5 ml-auto">
                            <label>Número de serie</label>
                            <input type="text" name="NroSerie" class="form-control">
                        </div>

                        <table class="table table-hover">
                            <tr>
                                <th> Nro</th>
                                <th> Codigo</th>
                                <th> Descripcion</th>
                                <th> Precio</th>
                                <th> cantidad</th>
                                <th> Sub total</th>
                                <th> Acciones</th>
                            </tr>
                        </table>


                    </div>
                    <div class="card-footer">
                        <input type="submit" value="Generar venta" class="btn btn-success">
                        <input type="submit" value="Eliminar" class="btn btn-danger">
                    </div>


                </div> 

            </div>
        </div>



    </body>
</html>
