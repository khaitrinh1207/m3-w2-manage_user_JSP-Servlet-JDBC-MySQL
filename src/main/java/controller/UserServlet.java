package controller;

import model.User;
import service.userService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    private service.userService userService;

    public void init() {
        userService = new userService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEidForm(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertUser(request, response);
                    break;
                case "edit":
                    updateUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException,ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User book = new User(id,name,email,country);
        userService.updateUser(book);
        RequestDispatcher rq = request.getRequestDispatcher("user/edit.jsp");
        rq.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        RequestDispatcher rq = request.getRequestDispatcher("user/create.jsp");
        rq.forward(request,response);
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> userList = userService.selectAllUser();
        request.setAttribute("userList",userList);
        RequestDispatcher rq = request.getRequestDispatcher("user/list.jsp");
        rq.forward(request,response);

    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(id);
         List<User> userList = userService.selectAllUser();
         request.setAttribute("userList",userList);
         RequestDispatcher rq = request.getRequestDispatcher("user/list.jsp");
         rq.forward(request,response);
    }

    private void showEidForm(HttpServletRequest request, HttpServletResponse response) throws SQLException,ServletException,IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userService.selectUser(id);
        RequestDispatcher rq = request.getRequestDispatcher("user/edit.jsp");
        request.setAttribute("user",existingUser);
        rq.forward(request,response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,SQLException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(name,email,country);
        userService.insertUser(newUser);
        RequestDispatcher rq = request.getRequestDispatcher("user/create.jsp");
        rq.forward(request,response);
    }
}
