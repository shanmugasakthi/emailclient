<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <style>
    
body {
    background-color: linen;
}

h1 {
    color: maroon;
    margin-left: 40px;
} 

    </style>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css"
    href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<title>Add new user</title>
</head>
<body>
    
    <form method="POST" action='/InboxController?action=saveorupdate' name="frmAddUser">
        User ID : <input type="number" style="color:brown" name="userid"
            value="<c:out value="${inbox1.userid}" />" /> <br /> 
        date : <input type="date" name="date"
            value="<c:out value="${inbox1.date}" />" /> <br /> 
            from: <input type="text" name="from"
            value="<c:out value="${inbox1.from}" />" /> <br /> 
            to: <input type="text" name="to"
            value="<c:out value="${inbox1.to}" />" /> <br /> 
            sub: <input type="text" name="subject"
            value="<c:out value="${inbox1.subject}" />" /> <br /> 
        
            message: <input type="text" name="message"
            value="<c:out value="${inbox1.message}" />" /> <br /> 
            

        
        
             <br /> <input
            type="submit" value="Submit" />
    </form>
</body>
</html>