<%-- 
    Document   : index
    Created on : 1-okt-2015, 14:52:36
    Author     : Rick
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../template/head.jsp" />
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="main-content">
    <% if ("verwijderd".equals(request.getParameter("melding"))) { %>
        <div class="alert alert-success alert-dismissable bottom-margin">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <i class="fa fa-check"></i> <spring:message code="rules.deleted"/>
        </div>
    <% } else if ("toegevoegd".equals(request.getParameter("melding"))) { %>
        <div class="alert alert-success alert-dismissable bottom-margin">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <i class="fa fa-check"></i> <spring:message code="rules.added"/>
        </div>
    <% } %>
    <div class="row">
        <div class="col-md-12">
            <div class="widget widget-orange">
                <div class="widget-title">
                    <div class="widget-controls">
                    </div>
                    <h3><i class="fa fa-table"></i> <spring:message code="rules.tablehead"/></h3>
                </div>
                <div class="widget-content">
                    <a href="${pageContext.request.contextPath}/rules/add/"><button type="button" class="btn btn-default btn-sm"><i class="fa fa-plus"></i> <spring:message code="rules.addbutton"/></button></a>
                    <br>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Beacon</th>
                                    <th><spring:message code="rules.list.action"/></th>
                                    <th><spring:message code="rules.list.group"/></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="rule" items="${ruleList}">
                                    <tr>
                                        <td>${rule.id}</td>
                                        <td>${rule.beacon.title}</td>
                                        <td>${rule.action.name}</td>
                                        <td>${rule.group.name}</td>
                                        <td class="text-right">
                                            
                                                
                                                <a class="btn btn-danger btn-xs" href="${pageContext.request.contextPath}/rules/delete/${rule.id}" onclick="return confirm('Are you sure you want to remove this rule?')"><i class="fa fa-trash"></i> <spring:message code="general.delete"/></a>
                                            
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../template/foot.jsp" />