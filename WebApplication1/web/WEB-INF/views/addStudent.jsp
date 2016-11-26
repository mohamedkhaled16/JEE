<%-- 
    Document   :AddStudent
    Created on : Nov 19, 2016, 7:00:27 PM
    Author     : mohamedk
--%>

<%@page import="org.apache.commons.lang.ArrayUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<%
String fnameError= ((Boolean) request.getAttribute("fname-error"))?"block":"none"; //false;
String lnameError= ((Boolean) request.getAttribute("lname-error"))?"block":"none";
String emailError= ((Boolean) request.getAttribute("email-error"))?"block":"none";
String interestError= ((Boolean) request.getAttribute("interest-error"))?"block":"none";
String genderError= ((Boolean) request.getAttribute("gender-error"))?"block":"none";
String fileError= ((Boolean) request.getAttribute("file-error"))?"block":"none";
String ageError= ((Boolean) request.getAttribute("age-error"))?"block":"none";
String dobError= ((Boolean) request.getAttribute("dob-error"))?"block":"none";

%>
<div>
    <div>
        <ol class="breadcrumb">
            <li><a href="#"> Home </a></li>
            <li class="active">Add Student</li>
        </ol>
    </div>
    
    <div>
        <h3>Add Student</h3>
        <hr>
    </div>
</div>
<form method="post" enctype="multipart/form-data">
    <div class="form-group col-xs-12 col-sm-12 col-md-12" style="display: <% out.print(fnameError);%>">
        <div  style="color: #a94442" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
            Please enter first name
        </div>
    </div>
    <div class="form-group col-xs-12 col-sm-12 col-md-12">

        <div class="col-xs-2 col-sm-2 col-md-2">
            <label for="fname" >First Name:</label>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
            <input type="text" class="form-control" id="fname" name="fname" value="<%= request.getAttribute("fname-value")%>">
        </div>
    </div>
    <br>
    <div class="form-group col-xs-12 col-sm-12 col-md-12" style="display: <% out.print(lnameError);%>">
        <div style="color: #a94442" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
            Please enter last name
        </div>
    </div>
    <br>
    <div class="form-group col-xs-12 col-sm-12 col-md-12">
        <div class="col-xs-2 col-sm-2 col-md-2">
            <label for="lname">Last Name:</label>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
            <input type="text" class="form-control" id="lname" name="lname" value="<%= request.getAttribute("lname-value")%>">
        </div>
    </div>
    <br>
    <div class="form-group col-xs-12 col-sm-12 col-md-12" style="display: <% out.print(emailError);%>">
        <div style="color: #a94442" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
            Please enter a valid email
        </div>
    </div>
    <br>
    <div class="form-group col-xs-12 col-sm-12 col-md-12">
        <div class="col-xs-2 col-sm-2 col-md-2">
            <label for="email">Email address:</label>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
            <input type="email" class="form-control" id="email" name="email" value="<%= request.getAttribute("email-value")%>">
        </div>
    </div>
    <br>
    <div class="form-group col-xs-12 col-sm-12 col-md-12" style="display: <% out.print(interestError);%>">
        <div style="color: #a94442" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
            Please select at least one interest
        </div>
    </div>
    <br>
    <div class="form-group col-xs-12 col-sm-12 col-md-12">
        <div class="col-xs-2 col-sm-2 col-md-2">
            <label for="interest">Interests:</label>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
            <label class="checkbox-inline">
                <input type="checkbox" name="interest" value="Interest1" <%
                    String[] interst = (String[]) request.getAttribute("interest-value");

                    try {
                        if (ArrayUtils.contains(interst, "Interest1")) {
                            out.print("checked");
                        }
                    } catch (Exception e) {
                    }
                       %>>Interest 1
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="interest" value="Interest2"
                       <%
                           try {
                               if (ArrayUtils.contains(interst, "Interest2")) {
                                   out.print("checked");
                               }
                           } catch (Exception e) {
                           }
                       %>
                       >Interest 2
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="interest" value="Interest3" <%
                    try {
                        if (ArrayUtils.contains(interst, "Interest3")) {
                                   out.print("checked");
                               }
                    } catch (Exception e) {
                    }
                       %>>Interest 3
            </label>
        </div>
    </div>
    <br>
    <div class="form-group col-xs-12 col-sm-12 col-md-12" style="display: <% out.print(genderError);%>">
        <div style="color: #a94442" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
            Please select Gender
        </div>
    </div>
    <br>
    <div class="form-group col-xs-12 col-sm-12 col-md-12">
        <div class="col-xs-2 col-sm-2 col-md-2">
            <label for="gender">Gender: </label>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
            <label class="radio-inline"><input type="radio" name="gender" value="0"
                                               <%
                                                   String gender = (String) request.getAttribute("gender-value");
                                                   if (gender.equals("0")) {
                                                       out.print("checked");
                                                   }
                                               %>
                                               >Male</label>
            <label class="radio-inline"><input type="radio" name="gender" value="1"
                                               <%
                                                   if (gender.equals("1")) {
                                                       out.print("checked");
                                                   }
                                               %>
                                               >Female</label>
        </div>
    </div>
    <br>
    <div class="form-group col-xs-12 col-sm-12 col-md-12" style="display: <% out.print(fileError);%>">
        <div style="color: #a94442" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
            Please enter a valid image
        </div>
    </div>
    <br>
    <div class="form-group col-xs-12 col-sm-12 col-md-12">
        <div class="col-xs-2 col-sm-2 col-md-2">
            <label for="password">Image: </label>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
            <input type="file"  class="form-control" id="img" name="file" accept="image/*" onchange="loadFile(event)" required="true"/>
        </div>
        <div class="col-xs-3 col-sm-3 col-md-3">
            <img id="output" />
        </div>

    </div>
    <br>
    <div class="form-group col-xs-12 col-sm-12 col-md-12" style="display: <% out.print(ageError);%>">
        <div style="color: #a94442" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
            Please enter a valid age
        </div>
    </div>
    <br>
    <div class="form-group col-xs-12 col-sm-12 col-md-12">
        <div class="col-xs-2 col-sm-2 col-md-2">
            <label for="age">Age: </label>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
            <input type="text" class="form-control"  name="age" id="age" required="true" value='<%= request.getAttribute("age-value")%>'/>
        </div>
    </div>
    <br>
    <div class="form-group col-xs-12 col-sm-12 col-md-12" style="display: <% out.print(dobError);%>">
        <div style="color: #a94442" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
            Please enter a valid date of birth
        </div>
    </div>
    <br>
    <div class="form-group col-xs-12 col-sm-12 col-md-12">
        <div class="col-xs-2 col-sm-2 col-md-2">
            <label for="dob">Date of birth:</label>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
            <input name="dob" class="form-control" id="dob" type="date" required="true" value="<%= request.getAttribute("dob-value")%>"/>
        </div>
    </div>
    <br>
    <div class="col-xs-6 col-xs-offset-5 col-sm-6 col-sm-offset-5">
        <button type="submit" class="btn btn-default">Submit</button>
    </div>
</form>


<%@ include file="footer.jsp" %>