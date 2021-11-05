
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.UserService;

/**
 *
 * @author Rafael Garcia Oporto
 */
public class UserServlet extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService us = new UserService();
        //check if we're editing/deleting a value from our manage users table
        String editParam = request.getParameter("editEmail");
        String deleteParam = request.getParameter("deleteEmail");
        User tempUser;
        
        //do the appropriate actions if the edit or delete parameters have been passed 
        //edit will load the values onto the edit window of the jsp
        if(editParam != null){
        tempUser = us.getUser(editParam);
        request.setAttribute("toUpdateEmail",tempUser.getEmail());
        request.setAttribute("toUpdateFirstName",tempUser.getFirstName());
        request.setAttribute("toUpdateLastName",tempUser.getLastName());   
        request.setAttribute("toUpdateRole", tempUser.getRole());
        }
        //delete will remove the entry from the database
        if(deleteParam != null){
        tempUser = us.getUser(deleteParam);
        us.deleteUser(tempUser);
        }
        
        //load the values from the database onto an array we can display on the jsp
        ArrayList<User> userList = us.getAll();
        request.setAttribute("userList", userList);
       getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //setup variables needed to parse which POST action we are taking
        UserService us;
        String action = request.getParameter("action");
        String email, firstName, lastName, password, roleValue, statusValue;
        boolean status;
        int role = 0;
        User tempUser;
        ArrayList<User> userList;
        //perform appropriate response to the action parameter
         if(action != null){    
        switch(action){
                //add a new user to the database
                case "add":
                    us = new UserService();
                    //initialize the variables with values from the jsp
                    email = request.getParameter("email");
                    firstName = request.getParameter("firstName");
                    lastName = request.getParameter("lastName");
                    password = request.getParameter("password");
                    roleValue = request.getParameter("role");
                    //convert  roleValue into an int based on the jsp input 
                    if(roleValue != null){
                    role = Integer.parseInt(roleValue);
                    }
                    statusValue = request.getParameter("status");
                    
                    //if all values are not null or empty, create user and add it to the .sql file. Otherwise display an error message.'
                    if(email != null && email.length() > 0 && statusValue != null && firstName != null && firstName.length() > 0 
                            && lastName != null && lastName.length() > 0 && password != null && password.length() > 0 && role != 0){
                        //convert statusValue into a boolean based on the jsp input
                        if (statusValue.equals("T")){
                            status = true; 
                        }
                        else{
                            status = false;
                         }
                        tempUser = new User(email, status, firstName, lastName, password, role);
                        us.addUser(tempUser);
                        //load jsp
                        userList = us.getAll();
                        request.setAttribute("userList", userList);
                        request.setAttribute("message", "User successfully added!");
                        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                    }
                    else{
                        userList = us.getAll();
                        request.setAttribute("userList", userList);
                        request.setAttribute("message", "At least one of the fields wasn't filled properly, please ensure every field is filled before adding a user.");
                        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                    }
                    break;
                //update a user in the database   
                case "update":
                    us = new UserService();
                    //initialize the variables with values from the jsp
                    email = request.getParameter("updateEmail");
                    if(email != null && email.length() > 0){
                    User editUser = us.getUser(email);
                    firstName = request.getParameter("editFirstName");
                    if(firstName != null && firstName.length() > 0)
                        editUser.setFirstName(firstName);
                    lastName = request.getParameter("editLastName");
                    if(lastName != null && lastName.length() > 0)
                        editUser.setLastName(lastName);
                    roleValue = request.getParameter("editRole");
                    if(roleValue != null)
                        editUser.setRole(Integer.parseInt(roleValue));
                    statusValue = request.getParameter("editStatus");
                    //convert statusValue into a boolean based on the jsp input. Only do this if a value was selected.
                    if(statusValue != null) {
                    if (statusValue.equals("T")){
                       status = true; 
                    }
                    else{
                        status = false;
                    }
                     editUser.setStatus(status);
                    }
                    //update the sql file
                    us.updateUser(editUser);
                    //load jsp after successful update
                    userList = us.getAll();
                    request.setAttribute("userList", userList);
                    request.setAttribute("message", "User successfully updated!");
                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                    }
                    else{
                    //load jsp with an error message
                    userList = us.getAll();
                    request.setAttribute("userList", userList);
                    request.setAttribute("message", "Please select a user to update from the Manage Users table.");
                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                    }
                    break;
                //in case a button doesn't work/something goes wrong, reload the page.
                default:
                    doGet(request,response);
                    break;
            }
        }
    }

}
