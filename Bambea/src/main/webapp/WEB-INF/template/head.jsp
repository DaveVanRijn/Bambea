<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head meta charset="utf-8">
        <title>BAMBEA.NL</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/style.css">
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:100,300,400,700%7CRoboto+Condensed:300,400,700">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link href="${pageContext.request.contextPath}/static/assets/favicon.ico" rel="shortcut icon">
        <link href="${pageContext.request.contextPath}//static/assets/images/bambea-logo.png" rel="apple-touch-icon">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/assets/images/bambea-logo.png" />
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        @javascript html5shiv respond.min
        <![endif]-->
    </head>
    <body class="glossed">
        <div class="all-wrapper fixed-header left-menu hide-sub-menu">
            <div class="page-header">
                <div class="header-links hidden-xs">

                    <div class="dropdown hidden-sm hidden-xs">
                        <a href="#" data-toggle="dropdown" class="header-link"><i class="fa fa-globe"></i> <spring:message code="menu.language"/> <i class="fa fa-caret-down"></i></a>

                        <ul class="dropdown-menu dropdown-inbar">
                            <li><a href="${pageContext.request.contextPath}/change-language?lang=en"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Flag_of_the_United_Kingdom.svg/120px-Flag_of_the_United_Kingdom.svg.png" height="10"/> English</a></li>
                            <li><a href="${pageContext.request.contextPath}/change-language?lang=nl"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Flag_of_the_Netherlands.svg/125px-Flag_of_the_Netherlands.svg.png" height="10"/> Nederlands</a></li>
                            <li><a href="${pageContext.request.contextPath}/change-language?lang=fr"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Flag_of_France.svg/125px-Flag_of_France.svg.png" height="10"/> Français (Beta)</a></li>
                            <li><a href="${pageContext.request.contextPath}/change-language?lang=de"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Flag_of_Germany.svg/130px-Flag_of_Germany.svg.png" height="10"/> Deutsch (Beta)</a></li>
                        </ul>
                    </div>

                    <div class="dropdown hidden-sm hidden-xs">
                        <a href="#" class="header-link clearfix" data-toggle="dropdown">
                            <div class="user-name-w">
                                <i class="fa fa-user"></i>
                                ${pageContext.request.userPrincipal.name} <i class="fa fa-caret-down"></i>
                            </div>
                        </a>
                        <ul class="dropdown-menu dropdown-inbar">

                            <li><a href="<c:url value='/logout'/>"<i class="fa fa-power-off"></i> <spring:message code="menu.logout"/></a></li>
                        </ul>
                    </div>
                </div>
                <a class="logo hidden-xs" href="${pageContext.request.contextPath}"><img style="width: 45px; height: 45px;" src="${pageContext.request.contextPath}/static/assets/images/bambea-logo.png" alt=""><!--<i class="fa fa-rocket"></i>--></a>
                <h1>Bambea</h1>
            </div>
            <div class="side">
                <div class="sidebar-wrapper">
                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}" data-toggle="tooltip" data-placement="right" title="" data-original-title="Dashboard &amp; Beacons">
                                <i class="fa fa-dashboard"></i>
                            </a>
                        </li>  
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li>
                                <a href="${pageContext.request.contextPath}/user/list" data-toggle="tooltip" data-placement="right" title="" data-original-title="Users">
                                    <i class="fa fa-user"></i>
                                </a>
                            </li>
                        </sec:authorize>                      
                        <li>
                            <a href="${pageContext.request.contextPath}/groups/list" data-toggle="tooltip" data-placement="right" title="" data-original-title="<spring:message code="menu.groups"/>">
                                <i class="fa fa-group"></i>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/action/list" data-toggle="tooltip" data-placement="right" title="" data-original-title="<spring:message code="menu.actions"/>">
                                <i class="fa fa-flash"></i>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/rules" data-toggle="tooltip" data-placement="right" title="" data-original-title="<spring:message code="menu.rules"/>">
                                <i class="fa fa-file-text-o"></i>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>