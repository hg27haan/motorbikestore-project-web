/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * author: H.M.Duc
 */
package control;

import dao.DAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "AddAmountCartControl", urlPatterns = {"/addAmountCart"})
public class AddAmountCartControl extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if(a == null) {
        	response.sendRedirect("login");
        	return;
        }
        int accountID = 0;
        int productID = 0;
        int amount = 0;
	    try {
	        accountID = a.getMaAccount();
	        productID = Integer.parseInt(request.getParameter("productID"));
	        amount = Integer.parseInt(request.getParameter("amount"));
	        amount+=1;
        }catch(Exception e) {
        	System.out.print("Co loi xay ra trong qua trinh chuyen so");
        	System.out.print(e.getMessage());
        	request.getRequestDispatcher("Error_Account.jsp").forward(request, response);
        }
        try {
            DAO dao = new DAO();
            dao.editAmountCart(accountID, productID, amount);
            request.setAttribute("mess", "Da tang so luong!");
            request.getRequestDispatcher("managerCart").forward(request, response);
        }catch(Exception e) {
        	request.getRequestDispatcher("Error_Account.jsp").forward(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
