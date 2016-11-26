<%-- 
    Document   : getStudent
    Created on : Nov 26, 2016, 3:36:00 PM
    Author     : mohamedk
--%>

<%@page import="controllers.studentEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<div>
    <div>
        <ol class="breadcrumb">
            <li><a href="#"> Home </a></li>
            <li class="active">Add Student</li>
        </ol>
    </div>

    <div>
        <h3>Show Student</h3>
        <hr>
    </div>
</div>

<%
    if((Boolean) request.getAttribute("Error")){
        out.print("Something went Wrong");
    }
    else {
    studentEntity sE = (studentEntity) request.getAttribute("StudentData");
%>

<div class="col-xs-12 col-sm-12 col-md-12">
    <div class="col-xs-2 col-sm-2 col-md-2">
        <label for="fname" >Image:</label>
    </div>
    <div class="col-xs-6 col-sm-6 col-md-6">
        <%=sE.getFile()%>
    </div>
</div>

<div class="col-xs-12 col-sm-12 col-md-12">
    <div class="col-xs-2 col-sm-2 col-md-2">
        <label for="fname" >First Name:</label>
    </div>
    <div class="col-xs-6 col-sm-6 col-md-6">
        <%=sE.getFname()%>
    </div>
</div>

<div class="col-xs-12 col-sm-12 col-md-12">
    <div class="col-xs-2 col-sm-2 col-md-2">
        <label for="fname" >Last Name:</label>
    </div>
    <div class="col-xs-6 col-sm-6 col-md-6">
        <%=sE.getLname()%>
    </div>
</div>


<div class="col-xs-12 col-sm-12 col-md-12">
    <div class="col-xs-2 col-sm-2 col-md-2">
        <label for="fname" >Email:</label>
    </div>
    <div class="col-xs-6 col-sm-6 col-md-6">
        <%=sE.getEmail()%>
    </div>
</div>



<div class="col-xs-12 col-sm-12 col-md-12">
    <div class="col-xs-2 col-sm-2 col-md-2">
        <label for="fname" >Gender:</label>
    </div>
    <div class="col-xs-6 col-sm-6 col-md-6">
        <%=sE.getGender()%>
    </div>
</div>

<div class="col-xs-12 col-sm-12 col-md-12">
    <div class="col-xs-2 col-sm-2 col-md-2">
        <label for="fname" >Age:</label>
    </div>
    <div class="col-xs-6 col-sm-6 col-md-6">
        <%=sE.getAge()%>
    </div>
</div>


<div class="col-xs-12 col-sm-12 col-md-12">
    <div class="col-xs-2 col-sm-2 col-md-2">
        <label for="fname" >Date of Birth:</label>
    </div>
    <div class="col-xs-6 col-sm-6 col-md-6">
        <% out.print(sE.getAge()); %>
        <%=sE.getDob()%>
    </div>
</div>



<div class="col-xs-12 col-sm-12 col-md-12">
    <div class="col-xs-2 col-sm-2 col-md-2">
        <label for="fname" >Interest:</label>
    </div>
    <div class="col-xs-6 col-sm-6 col-md-6">
        <%=sE.getInterest()%>
    </div>
</div>

<% 
}
%>


<%@ include file="footer.jsp" %>