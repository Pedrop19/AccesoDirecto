/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.albarregas.beans.Ave;

/**
 *
 * @author Pedro Lazaro
 */
@WebServlet(name = "AveController", urlPatterns = { "/AveController" })
public class AveController extends HttpServlet {

    @Override
    public void init(ServletConfig config) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        String boton = request.getParameter("button");
        String anilla = request.getParameter("anilla");
        String url = "";
        Ave ave;
        LinkedList<Ave> aves = new LinkedList<>();
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;
        String error = "";
        try {
            conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/pruebasjava",
                            "java2024", "Java*2024");
            sentencia = conexion.createStatement();
             switch (boton) {
                case "una":
                try{
                    String sql = "SELECT * FROM aves WHERE anilla = '" + anilla + "'";
                    resultado = sentencia.executeQuery(sql);
                    ave = new Ave();
                    if (resultado.next()) {
                        ave.setAnilla(resultado.getString("anilla"));
                        ave.setEspecie(resultado.getString("especie"));
                        ave.setFecha(resultado.getString("fecha"));
                        ave.setLugar(resultado.getString("lugar"));
                        aves.add(ave);
                        url = "JSP/vistaFinal.jsp";
                    } else if (anilla == null || anilla.equals("")) {
                        error = "No se ha introducido ninguna anilla";
                        url = "JSP/error.jsp";
                        request.setAttribute("error", error);
                    } else {
                        error = "No se ha encontrado ninguna ave con la anilla " + anilla + "";
                        url = "JSP/error.jsp";
                        request.setAttribute("error", error);
                    }
                    break;
                }catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    resultado.close();
                }
                    
                break;
                case "todas":
                try {
                    String sql = "SELECT * FROM aves";
                    resultado = sentencia.executeQuery(sql);
                    while (resultado.next()) {
                        ave = new Ave();
                        ave.setAnilla(resultado.getString("anilla"));
                        ave.setEspecie(resultado.getString(2));
                        ave.setFecha(resultado.getString("fecha"));
                        ave.setLugar(resultado.getString("lugar"));
                        aves.add(ave);
                    }
                    url = "JSP/vistaFinal.jsp";
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    resultado.close();
                }
            }
            request.setAttribute("aves", aves);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           if(conexion != null && sentencia != null) {
               try {
                   conexion.close();
                   sentencia.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
        }
        request.getRequestDispatcher(url).forward(request, response);
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
