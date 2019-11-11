/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Controller.EquipmentController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Equipment;
import model.Student;

/**
 *
 * @author Top
 */
public class BorrowServlet extends HttpServlet {

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
             
        String msg = null;
        String id = request.getParameter("id");
        
        if (id == null || id.isEmpty()) {
            this.setAllEquipment(request);
            getServletContext().getRequestDispatcher("/WEB-INF/BorrowEquipment.jsp").forward(request, response);
        } else {

            int idEquipment = Integer.valueOf(request.getParameter("id"));
            EquipmentController ec = new EquipmentController();
            Equipment e = ec.findEquipmentByid(idEquipment);

            if (e != null) {
                if (e.getBorrower() != null) {
                    
                    msg = "Cannot Borrow";
                    request.setAttribute("msg", msg);
                    this.setAllEquipment(request);
                    getServletContext().getRequestDispatcher("/WEB-INF/BorrowEquipment.jsp").forward(request, response);
                    
                } else {

                    Student s = (Student) request.getSession().getAttribute("user");
                    e.setBorrower(s);
                    ec.editBorrower(e);
                    
                    msg = "Borrow Success!";
                    request.setAttribute("msg", msg);
                    this.setAllEquipment(request);
                    getServletContext().getRequestDispatcher("/WEB-INF/BorrowEquipment.jsp").forward(request, response);

                }
            }
        }

    }

    private void setAllEquipment(HttpServletRequest request) {
        
        EquipmentController ec = new EquipmentController();
        ArrayList<Equipment> equipments = ec.findEquipmentEntities();
        request.setAttribute("equipments", equipments);
        
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
