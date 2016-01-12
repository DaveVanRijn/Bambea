<%-- 
    Document   : register
    Created on : Jan 5, 2016, 6:28:49 PM
    Author     : hugod
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head charset="utf-8">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/style.css">
        <link href='http://fonts.googleapis.com/css?family=Roboto:100,300,400,700%7CRoboto+Condensed:300,400,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link href="${pageContext.request.contextPath}/static/assets/favicon.ico" rel="shortcut icon">
        <link href="${pageContext.request.contextPath}/static/assets/images/bambea-logo.png" rel="apple-touch-icon">
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          @javascript html5shiv respond.min
        <![endif]-->
        <title>Bambea Registration</title>

    </head>
    <body>
        <div class="all-wrapper no-menu-wrapper light-bg">
            <div class="error-page-wrapper">
                <div class="picture-w">
                    <!--                    <i class="fa fa-question-circle"></i>-->
                    <img src="${pageContext.request.contextPath}/static/assets/images/bambea-logo.png"/>
                </div>
                <h1><spring:message code="register.header"/></h1>


                <form:form class="bottom-margin" id="validateForm" modelAttribute="userRegister" action="${pageContext.request.contextPath}/registration/add/${registrationID}/" method="POST" novalidate="novalidate" accept-charset='UTF-8'>  
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <div class="col-md-4 col-md-offset-4">
                        <% if ("accountExists".equals(request.getParameter("failmsg"))) {%>
                        <div class="alert alert-danger alert-dismissable bottom-margin">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                            <spring:message code="register.accountexists"/>
                        </div>
                        <% } %>
                        <div class="form-group has-iconed" >
                            <label><spring:message code="login.usernameplaceholder"/></label>
                            <div  class="iconed-input">
                                <spring:message code="login.usernameplaceholder" var = "usernameplaceholder"/>
                                <form:input path="username" placeholder="${usernameplaceholder}" name="name" class="form-control" type="text" minlength="5" required=""/>
                            </div>
                        </div>
                        <div class="form-group has-iconed">
                            <label><spring:message code="login.passwordplaceholder"/></label>
                            <div class="iconed-input">
                                <spring:message code="login.passwordplaceholder" var = "passwordplaceholder"/>
                                <form:input path="password" placeholder="${passwordplaceholder}" name="password" class="form-control" type="password" minlength="5" required=""/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:button class="btn btn-default" type="submit"><spring:message code="register.button"/></form:button>
                            </div>
                        </div>
                </form:form>

            </div>
        </div>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
        <script src='${pageContext.request.contextPath}/static/js/ad67372f4b8b70896e8a596720082ac6.js'></script>
        <script src='${pageContext.request.contextPath}/static/js/926fa5705d7bbe688c5b54d9d559f339.js'></script>

    </body>
</html>
