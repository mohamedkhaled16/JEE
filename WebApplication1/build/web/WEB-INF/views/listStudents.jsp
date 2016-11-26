<%-- 
    Document   : ListStudents
    Created on : Nov 19, 2016, 7:02:41 PM
    Author     : mohamedk
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="controllers.studentEntity"%>
<%@page import="org.apache.commons.lang.ArrayUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %><!DOCTYPE html>

<% List<studentEntity> list = (List<studentEntity>) request.getAttribute("Students"); %>
<div>
    <div>
        <ol class="breadcrumb">
            <li><a href="#"> Home </a></li>
            <li class="active">List Students</li>
        </ol>
    </div>
    <div>
        <h3>List Students</h3>
        <hr>
    </div>
</div>
<% if (list.isEmpty()) { %>
No Students
<% }  else { %>
<table class="table table-striped">
    <thead>
        <tr>
            <th>Image</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Interest</th>
            <th>Age</th>
            <th>Date of birth</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
<%
        for (Iterator<studentEntity> i = list.iterator(); i.hasNext();) {
            studentEntity item = i.next();
            out.println("<tr>");
            out.println("<td>"+item.getFile()+"</td>");
            out.println("<td>"+item.getFname()+"</td>");
            out.println("<td>"+item.getLname()+"</td>");
            out.println("<td>"+item.getEmail()+"</td>");
            out.println("<td>"+item.getGender()+"</td>");
            out.println("<td>"+item.getInterest()+"</td>");
            out.println("<td>"+item.getAge()+"</td>");
            out.println("<td>"+item.getDob()+"</td>");
            
            out.println("<td>"+"<a href='./UpdateStudent?id="+item.getId()+"'>Edit</a>"+" | "+"<a href='./deleteStudent?id="+item.getId()+"'>Delete</a>"+"</td>");
            out.println("</tr>");
        }
%>
   </tbody>
</table>
<%
    }
%>

<%@ include file="footer.jsp" %>