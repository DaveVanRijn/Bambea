<%-- 
    Document   : login
    Created on : Nov 26, 2015, 11:18:35 AM
    Author     : hugod
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page session="false"%>
<html>

    <head charset="utf-8">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
        <link rel='stylesheet' href='${pageContext.request.contextPath}/static/assets/css/style.css'>
        <link href='http://fonts.googleapis.com/css?family=Roboto:100,300,400,700%7CRoboto+Condensed:300,400,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link href="${pageContext.request.contextPath}/static/assets/favicon.ico" rel="shortcut icon">
        <link href="${pageContext.request.contextPath}/static/assets/images/bambea-logo.png" rel="apple-touch-icon">
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          @javascript html5shiv respond.min
        <![endif]-->

        <title>Bambea Login</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/assets/images/bambea-logo.png" />
    </head>

    <body class="glossed" onload='document.loginForm.username.focus();'>

        <div class="all-wrapper no-menu-wrapper light-bg">
            <div class="login-logo-w">
                <a class="logo hidden-xs" href="${pageContext.request.contextPath}"><img  src="${pageContext.request.contextPath}/static/assets/images/bambea-logo.png" alt=""/></a>
            </div>
            <div class="row">
                <div class="col-md-4 col-md-offset-4">

                    <div class="widget widget-blue" id='login-box'>
                        <div class="widget-title">
                            <h3 class="text-center"><i class="fa fa-lock"></i> <spring:message code="login.headermessage"/> </h3>
                            <h3 class="text-right"></h3>
                        </div>
                        <div class="widget-content">
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger alert-dismissable bottom-margin">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                    <i class="fa fa-times-circle"></i> <spring:message code="login.wrongcredentials"/>
                                </div>                              
                            </c:if>
                            <c:if test="${not empty msg}">
                                <div class="alert alert-success alert-dismissable bottom-margin">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                    <i class="fa fa-check-circle"></i> <spring:message code="login.loggedout"/>
                                </div>                               
                            </c:if>
                            <form action="<c:url value='/j_spring_security_check'/>" role="form" method="post" name="loginForm" accept-charset='UTF-8'>                                                              
                                <div class="form-group relative-w">
                                    <input type="text" class="form-control" placeholder="<spring:message code="login.usernameplaceholder"/>" id="username" name='username'>
                                    <i class="fa fa-user input-abs-icon"></i>
                                </div>
                                <div class="form-group relative-w">
                                    <input type="password" class="form-control" placeholder="<spring:message code="login.passwordplaceholder"/>" id="password" name='password'/>
                                    <i class="fa fa-lock input-abs-icon"></i>
                                </div>
                                <div class="form-group">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="remember-me" id="remember-me"><spring:message code="login.rememberme"/></input>
                                        </label>
                                    </div>
                                </div>
                                <button class="btn btn-primary btn-rounded btn-iconed" id="submit" name='submit' type="submit">
                                    <span><spring:message code="login.loginbutton"/></span>
                                    <i class="fa fa-arrow-right"/></i>
                                </button>

                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
        <script src='${pageContext.request.contextPath}/static/assets/js/ad67372f4b8b70896e8a596720082ac6.js'></script>
        <script src='${pageContext.request.contextPath}/static/assets/js/d7dfc13379a397356e42ab8bd98901a0.js'></script>
    </body>
</html>
