/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sistemas
 */
@WebServlet(urlPatterns = {"/ServletCarritoCompras"})
public class ServletCarritoCompras extends HttpServlet {

    private final Map<String, String> libros = new HashMap<String, String>();

    public void init() {
        libros.put("Java", "50.60");
        libros.put("C", "70.40");
        libros.put("C++", "35.50");
        //libros.put("Visual Basic", "65.30");
        libros.put("Ruby", "55.70");
        libros.put("Python", "80.20");
        //libros.put("Php", "80.20");
        //libros.put("Html", "55.70");

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Recomendaciones</title>");
            out.println("<h1>Servlet ServletCarritoCompras at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        Cookie cookies[] = request.getCookies();

        Double sum = 0.0;
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">\n");
            out.println("<title>Recomendaciones</title>");
            out.println("<style>\n"
                    + "            span{\n"
                    + "                color: red;\n"
                    + "            }\n"
                    + ".container{"
                    + "padding-top: 5%;" + "}"
                    + ""
                    + "        </style>");
            out.println("</head>");
            out.println("<body>");
            if (cookies != null && cookies.length != 0) {
                out.println("<div class=\"container\">");
                out.println("<h1>Compra total</h1>");
                out.println("<div class=\"table-hover\">\n"
                        + "  <table class=\"table\">\n"
                        + "   <thead>\n"
                        + "<tr>\n"
                        + "  <th>Libro</th>\n"
                        + "  <th>Precio</th>\n"
                        + "</tr>\n"
                        + "</thead>\n"
                        + "<tbody>\n"
                        + " ");
                for (int i = 0; i < cookies.length; i++) {
                    out.println("<tr>");
                    out.println("<td>" + cookies[i].getName() + " en 24 Horas</td>");
                    out.println("<td>" + cookies[i].getValue());
                    out.println("</tr>");
                    sum = sum + Double.parseDouble(cookies[i].getValue());
                }
                out.println("</tbody>"
                        + "  </table>\n"
                        + "</div>");

                out.println("<div class=\"row\">");
                out.println("<div class=\"col-lg-9\">");
                out.println("<h2>Monto Total S/. :" + (double) sum + "</h2>");
                out.println("</div>");
                out.println("<div class=\"col-lg-3\">");
                out.println("<a class=\"btn btn-primary\" href=\"index.html\">Regresar</a>");
                out.println("</div>");
                out.println("<div>");

            } else {
                out.println("<h4 class=\"space\">no hay Recomendaciones </h4>");
            }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }

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
        String libro = request.getParameter("libro");
        String precio = libros.get(libro);
        Cookie cookie = new Cookie(libro, precio);
        /*Cookie cookie = new Cookie("cookiename", "cookievalue");*/
        response.addCookie(cookie);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>LIBRERIA WALTER LETURIA</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">\n"
                    + "");
            out.println("<style>\n"
                    + "            span{\n"
                    + "                color: red;\n"
                    + "            }\n"
                    + ".container{"
                    + "padding-top: 5%;" + "}"
                    + "        </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");
            out.println("<h4>Bienvenido Libreria Atlantida!  Usted Seleccion&oacute; <span>" + libro + "</span></h4>");
            out.println("<p><a href=\"index.html\">Seguir comprando</a></p>");
            out.println("<p><a href=\"ServletCarritoCompras\">Resumen de compra</a></p>");
            out.println("<div>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }

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
