<%-- 
    Document   : succes
    Created on : Jan 6, 2016, 9:38:21 PM
    Author     : hugod
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="4;url=${pageContext.request.contextPath}/login" />
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

        <title>Success!</title>
    </head>
    <body class="glossed">
        <div class="all-wrapper no-menu-wrapper light-bg">
            <div class="error-page-wrapper">
                <div class="picture-w">
                    <i class="fa fa-check-circle" ></i>
                </div>
                <h1><spring:message code="register.addSuccess"/></h1>
                <h2><spring:message code="register.successText"/> </h2>
                <h2><spring:message code="register.successText2"/> </h2>
                <h3><spring:message code="register.buttonClick"/> </h3>
                <a href="${pageContext.request.contextPath}/login" class="btn btn-primary btn-lg"><spring:message code="register.login"/></a>
            </div>

        </div>
    </body>
</html>