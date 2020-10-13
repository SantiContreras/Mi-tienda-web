/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import config.GenerarSerie;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import static java.nio.file.Files.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author santi
 */
public class Controlador extends HttpServlet {

    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    Cliente cli = new Cliente();
    ClienteDAO clidao = new ClienteDAO();
    Producto pro = new Producto();
    ProductoDAO pdao = new ProductoDAO();
    Venta v = new Venta();
    VentaDAO vdao = new VentaDAO();

    int ide;
    int idc;
    int idp;
    int item;
    int cod;
    String descripcion;
    Double precio;
    Double subtotal;
    int cant;
    double totalPagar;
    String numeroserie = "";

    List<Venta> lista = new ArrayList<>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }

        if (menu.equals("Empleado")) {
            switch (accion) {
                case "Listar":
                    List lista = edao.listar();
                    request.setAttribute("empleados", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUser");
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    edao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id")); //toma el valor de la ide del empleado
                    Empleado e = edao.ListarId(ide); // a la variable de tipo empleado 
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel");
                    String est1 = request.getParameter("txtEstado");
                    String user1 = request.getParameter("txtUser");
                    em.setDni(dni1);
                    em.setNom(nom1);
                    em.setTel(tel1);
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(ide);
                    edao.actualizar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id")); // toma el valor ide del empleado cuando presiona el boton borrar, 
                    edao.delete(ide); // metodo detele de el empleadoDAO registrado
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);

                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);

        }
        if (menu.equals("NuevaVenta")) {
            switch (accion) {
                case "BuscarCliente":
                    String dni = request.getParameter("codigocliente");
                    cli.setDni(dni);
                    cli = clidao.buscar(dni);
                    request.setAttribute("cli", cli);// el controlador captura  el cliente
                    break;
                case "BuscarProducto":
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));
                    pro = pdao.listarId(id);

                    request.setAttribute("pro", pro);
                    request.setAttribute("cli", cli);
                    request.setAttribute("totalpagar", totalPagar); // captamos el total a pagar
                    request.setAttribute("lista", lista);
                    break;
                case "Agregar":

                    request.setAttribute("cli", cli);
                    totalPagar = 0.0;
                    item = item + 1;
                    cod = pro.getId();
                    descripcion = request.getParameter("nombreproducto"); // nombre del producto capturado en la vista
                    precio = Double.parseDouble(request.getParameter("precio"));// precio caoturado en la vista
                    cant = Integer.parseInt(request.getParameter("cant")); // cantidad capturado en la vista
                    subtotal = precio * cant;
                    v = new Venta(); // instacio un nueva venta para agregar a la lista
                    v.setItem(item);
                    v.setIdproducto(cod);
                    v.setDescripcionP(descripcion);
                    v.setPrecio(precio);
                    v.setCantidad(cant);
                    v.setSubtotal(subtotal);
                    lista.add(v); // agregamos a la coleccion
                    for (int i = 0; i < lista.size(); i++) {  // recorremos la lista para salar los totales a pagar
                        totalPagar = totalPagar + lista.get(i).getSubtotal();
                    }
                    request.setAttribute("totalpagar", totalPagar); // captamos el total a pagar
                    request.setAttribute("lista", lista);  // captamos la lista
                    request.setAttribute("nserie", numeroserie); //captamos el numero de serie
                    break;
                case "GenerarVenta":
                    //Guardar Venta

                    v.setIdcliente(cli.getId()); // guardamos el id cliente
                    v.setIdempleado(2);  // guardamos el id empleado que atendio la venta
                    v.setNumserie(numeroserie); // guardamos el numero de serie
                    v.setFecha("2019-06-14"); // 
                    v.setMonto(totalPagar);
                    v.setEstado("1");
                    vdao.guardarVenta(v);
                    //Guardar Detalle ventas
                    int idv = Integer.parseInt(vdao.IdVentas());
                    for (int i = 0; i < lista.size(); i++) { //recorremos la lista para almacenar en el detalle de venta
                        v = new Venta();
                        v.setId(idv);
                        v.setIdproducto(lista.get(i).getIdproducto());
                        v.setCantidad(lista.get(i).getCantidad());
                        v.setPrecio(lista.get(i).getPrecio());
                        vdao.guardarDetalleventas(v);
                    }
                    //actualiza el stock de productos
                    for (int i = 0; i < lista.size(); i++) {
                        Producto pr = new Producto();  // intanciamos el objeto producto
                        int cantidad = lista.get(i).getCantidad();  // obtenemos la cantidad del producto que se selecciono de la lista antes de generar la factura
                        int idproducto = lista.get(i).getIdproducto(); //obtenemos el id del producto a actualizar
                        ProductoDAO AO = new ProductoDAO(); // instanciamos la clase producto dao que contiene los metodos
                        pr = AO.buscar(idproducto); // buscamos el id de producto
                        int sac = pr.getStock() - cantidad; // restamos la cantidad actual con la cantidad que se va a comprar
                        AO.actualizarstock(idproducto, sac); // actualizamos el stock

                    }
                    lista = new ArrayList<>(); // creamos una nueva lista para desponer la lista anterior.

                    break;
                default:
                    v = new Venta();
                    lista = new ArrayList<>();
                    item = 0;
                    totalPagar = 0.0;
                    numeroserie = vdao.GenerarSerie();
                    if (numeroserie == null) {
                        numeroserie = "000000001";
                        request.setAttribute("nserie", numeroserie);
                    } else {
                        int incrementar = Integer.parseInt(numeroserie);
                        GenerarSerie gs = new GenerarSerie();
                        numeroserie = gs.NumeroSerie(incrementar);

                        request.setAttribute("nserie", numeroserie);
                    }
                    request.getRequestDispatcher("RegistroDeVentas.jsp").forward(request, response);

            }
            request.getRequestDispatcher("RegistroDeVentas.jsp").forward(request, response);

        }
        if (menu.equals("Producto")) {
            request.getRequestDispatcher("Producto.jsp").forward(request, response);

        }
        if (menu.equals("Cliente")) {

            switch (accion) {
                case "Listar":
                    List lista = clidao.listar();
                    request.setAttribute("clientes", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nombre = request.getParameter("txtNombre");
                    String dir = request.getParameter("txtDir");
                    String estado = request.getParameter("txtEstado");
                    cli.setDni(dni);
                    cli.setNom(nombre);
                    cli.setDir(dir);
                    cli.setEs(estado);
                    clidao.Agregar(cli);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    idc = Integer.parseInt(request.getParameter("id"));
                    clidao.Eliminar(idc);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                case "Actualizar" :
                    String dni1 = request.getParameter("txtDni");
                    String nombre1 = request.getParameter("txtNombre");
                    String dir1 = request.getParameter("txtDir");
                    String estado1 = request.getParameter("txtEstado");
                    cli.setDni(dni1);
                    cli.setNom(nombre1);
                    cli.setDir(dir1);
                    cli.setEs(estado1);
                    clidao.Agregar(cli);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                     case "Editar":
                    idc = Integer.parseInt(request.getParameter("id"));
                    Cliente cl = clidao.listarId(idc);
                    request.setAttribute("cliente", cl);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                   
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
