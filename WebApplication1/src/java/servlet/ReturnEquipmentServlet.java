/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Equipment;
import model.Equipmentdao;
import model.Student;

/**
 *
 * @author Top
 */
public class ReturnEquipmentServlet extends HttpServlet {

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
        String id = request.getParameter("id");
        String msg = null;
        if (id == null || id.trim().isEmpty()) {
            this.setAllEquipment(request);
            request.getRequestDispatcher("/WEB-INF/ReturnEquipment.jsp").forward(request, response);
        }
        //String id = request.getParameter("id");
        int ide = Integer.valueOf(id);
        Equipmentdao edao = new Equipmentdao();
        Equipment e = edao.getEquipmentByid(ide);

        if (e == null) {
            msg = "Cannot Return";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/WEB-INF/ReturnEquipment.jsp").forward(request, response);
        } else {
            if (e.getBorrower() != null) {
                
                e.setBorrower(null);
                edao.UpdateEquipmentBorrower(e);
                this.setAllEquipment(request);
                response.sendRedirect("/PracticeBorrow/ReturnEquipment");
                return;
                
            } else {
                
                this.setAllEquipment(request);
                msg = "Cannot Return";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/WEB-INF/ReturnEquipment.jsp").forward(request, response);
                
            }
        }

    }

    private void setAllEquipment(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Student s = (Student) session.getAttribute("user");
        Equipmentdao edao = new Equipmentdao();
        ArrayList<Equipment> le = edao.getEquipmentByBorrower(s);
        request.getSession().setAttribute("equipments", le);
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
