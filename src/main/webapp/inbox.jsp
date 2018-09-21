<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <table border=1>
            <thead>
                    <tr>
                            <th>ID NO</th>
                            <th>DATE</th>
                            <th>FROM</th> 
                            <th>TO</th> 
                            <th>SUB</th> 
                            <th>MESSAGE</th> 
                            <th colspan=2>Action</th>
                        </tr>
                       
              </thead>
                
                <tbody>
                <c:forEach items="${inbox1}" var="inbx">
                    <tr>
                        <td><c:out value="${inbx.userid}" /></td>
                        <td><c:out value="${inbx.date}" /></td>
                        <td><c:out value="${inbx.from}" /></td>
                        <td><c:out value="${inbx.to}" /></td>
                        <td><c:out value="${inbx.subject}" /></td>
                        <td><c:out value="${inbx.message}" /></td>
                        <td><a href="InboxController?action=edit&userid=<c:out value="${inbx.userid}"/>">Update</a></td>
                        
                        <td><a href="InboxController?action=delete&userid=<c:out value="${inbx.userid}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
                
            
    </table>
    
    <p><a href="InboxController?action=insert">Add User</a></p>
        
      
    
</body>
</html>

