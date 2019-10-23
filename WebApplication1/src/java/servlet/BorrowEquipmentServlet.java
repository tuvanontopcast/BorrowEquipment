/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
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
public class BorrowEquipmentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs localhost:8080/
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String msg = "";

        if (id == null || id.isEmpty()) {
            this.setAllEquipment(request);
            getServletContext().getRequestDispatcher("/WEB-INF/BorrowEquipment.jsp").forward(request, response);
        } else {

            int idEquipment = Integer.valueOf(request.getParameter("id"));
            Equipmentdao edao = new Equipmentdao();
            Equipment e = edao.getEquipmentByid(idEquipment);

            if (e != null) {
                if (e.getBorrower() != null) {
                    msg = "Cannot Borrow";
                    request.setAttribute("msg", msg);
                    this.setAllEquipment(request);
                    getServletContext().getRequestDispatcher("/WEB-INF/BorrowEquipment.jsp").forward(request, response);
                } else {
                    HttpSession session = request.getSession();
                    Student s = (Student) session.getAttribute("user");
                    e.setBorrower(s);
                    edao.UpdateEquipmentBorrower(e);
                    msg = "Borrow Success!";
                    request.setAttribute("msg", msg);
                    //request.setAttribute("equipments", edao);
                    this.setAllEquipment(request);
                    //response.sendRedirect("/PracticeBorrow/BorrowEquipment");
                    getServletContext().getRequestDispatcher("/WEB-INF/BorrowEquipment.jsp").forward(request, response);

                }
            }
        }

    }

    private void setAllEquipment(HttpServletRequest request) {
        Equipmentdao edao = new Equipmentdao();
        ArrayList<Equipment> equipments = edao.getAllEquipment();
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
