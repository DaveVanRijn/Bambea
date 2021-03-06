<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%-- 
    Document   : 500
    Created on : Dec 5, 2015, 6:11:34 PM
    Author     : hugod
--%>

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

        <title>Bambea 500</title>
    </head>
    <body class="glossed">
        <div class="all-wrapper no-menu-wrapper light-bg">
            <div class="error-page-wrapper">
                <div class="picture-w">
                    <i class="fa fa-exclamation-circle"></i>
                </div>
                <h1><spring:message code="error.500.header"/></h1>
                <h3><spring:message code="error.500.text1"/> <br/> <spring:message code="error.500.text2"/>  </h3>
                <a href="${pageContext.request.contextPath}/" class="btn btn-primary btn-lg"><spring:message code="error.backButton"/></a>
            </div>
        </div>
    </body>
</html>
