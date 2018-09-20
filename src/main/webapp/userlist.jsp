<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
                <title>Show All Users</title>
                <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
                <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
                <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
                <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>
                <link rel="stylesheet" href="style.css">
            </head>

            <body>
                <div class="container">
                    <div class="col-md-10 col-md-offset-1">
                        <div class="panel panel-default panel-table">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col col-xs-6">
                                        <h3 class="panel-title">Panel Heading</h3>
                                    </div>
                                    <div class="col col-xs-6 text-right">
                                        <a href="UserController?action=insert">Add User</a>
                                    </div>
                                </div>
                            </div>
                            <table class="table table-striped table-bordered table-list">
                                <thead>
                                    <tr>
                                        <th>User Id</th>
                                        <th>email</th>
                                        <th colspan=2 class="text-center">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${userList}" var="user">
                                        <tr>
                                            <td>
                                                <c:out value="${user.userid}" />
                                            </td>
                                            <td>
                                                <c:out value="${user.email}" />
                                            </td>

                                            <td class="text-center">
                                                <a class="glyphicon glyphicon-pencil" href="UserController?action=edit&userid=<c:out value=" ${user.userid} "/>"></a>
                                            </td>

                                            <td class="text-center">
                                                <a class="glyphicon glyphicon-trash" href="UserController?action=delete&userid=<c:out value=" ${user.userid} "/>"></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <p>

                    </p>
            </body>

            </html>