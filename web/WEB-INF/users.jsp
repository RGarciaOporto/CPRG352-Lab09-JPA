<%-- 
    Document   : users
    Created on : Oct 28, 2021, 1:20:11 PM
    Author     : 851649
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
        <style>
        body{
            font-family:'Open Sans', 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
            color: #ffffff;
            background-color:#14213d;
        }
        
        h1{
            text-align: center;
        }
        
        .leftColumn{
            float:left;
            width: 20%;
            padding:10px;
        }
        
        .rightColumn{
            float: right;
            width: 20%;
            padding:10px;
            padding-right: 50px;
        }
        
        .centerColumn{
            float:left;
            width:50%;
            padding:10px;
            padding-left: 3%
        }
        
        #addUsersForm{
            padding-left:30%;
        }
        
        #manageUsers{
            
        }
        
        #editUsersForm{
            padding-left:30%;
        }
        
        table {
            border-collapse: collapse;
             color:#000000;
             margin-left: auto;
             margin-right:auto;
        }

        td, th {
            border: 1.5px solid #fca311;
            text-align: left;
            padding: 8px;
        }

        th{
            background-color:#fca311;
        }

        tr:nth-child(odd){
            background-color: #ffffff;
        }

        tr:nth-child(even) {
            background-color: #e5e5e5;
        }
        
        footer{
            padding: 10px;
            text-align: center;
        }
        </style>
    </head>
    
    <body>
        
        <div id="addUsers" class="leftColumn">
        <h1>Add User</h1>
        <form method="POST" id="addUsersForm">
            <input type="hidden" name="action" value="add">
            <input type="text" name="email" placeholder="Email"><br>
            <label>Status:</label><br>
            <input type="radio"  name="status" value="T">
            <label>Active</label><br>
            <input type="radio" name="status" value="F">
            <label>Inactive</label><br>
            <input type="text" name="firstName" placeholder="First Name"><br>
            <input type="text" name="lastName" placeholder="Last Name"><br>
            <input type="text" name="password" placeholder="Password"><br>
            <select name="role">
                <option value="1">System Administrator</option>
                <option value="3">Company Administrator</option>
                <option value="2">User</option> 
            </select><br>
            <input type="submit" value="Save">
        </form>
        </div>
        
        <div name="manageUsers" class="centerColumn">
            <h1>Manage Users</h1>
            <div name="manageUsers">
                <table>
                    <tr>
                        <th>Email</th>
                        <th>Name</th>
                        <th>Role</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach var="users" items="${userList}">
                        <tr>
                            <td><p>${users.email}</p></td>
                            <td><p>${users.firstName} ${users.lastName}</p></td>
                            <td><p>${users.roleTitle()}</p></td>
                            <td><a href="
                           <c:url value='/users'>
                               <c:param name='editEmail' value='${users.email}' />
                           </c:url>
                           ">Edit</a></td>
                            <td><a href="
                           <c:url value='/users'>
                               <c:param name='deleteEmail' value='${users.email}' />
                           </c:url>
                           ">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        
        <div name="editUsers" class="rightColumn" id="editUsers">
            <h1>Edit Users</h1>
            <form method="POST" id="editUsersForm">
            <input type="hidden" name="action" value="update">
            <input type="text" name="updateEmail" placeholder="Email" readonly="true" value="${toUpdateEmail}"><br>
            <label>Status:</label><br>
            <input type="radio"  name="editStatus" value="T">
            <label>Active</label><br>
            <input type="radio" name="editStatus" value="F">
            <label>Inactive</label><br>
            <input type="text" name="editFirstName" placeholder="First Name" value="${toUpdateFirstName}"><br>
            <input type="text" name="editLastName" placeholder="Last Name" value="${toUpdateLastName}"><br>
            <select name="editRole">
                <option value="1">System Administrator</option>
                <option value="3">Company Administrator</option>
                <option value="2">User</option> 
            </select><br>
            <input type="submit" value="Save">
            <input type="reset" value="Cancel">
        </form>
        </div>
        <footer id="message">
            <p>${message}</p>
        </footer>
    </body>
</html>
