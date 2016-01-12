<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="../../template/head.jsp" />
<div class="main-content">
    <div class="row">
        <div class="col-md-8">
            <div class="widget widget-green">
                <div class="widget-title">
                    <i class="fa fa-table"></i>
                    <spring:message code="groups.tablehead"/>
                    <div class="widget-controls">
                    </div>
                </div>
                <div class="widget-content">
                    <div class="table-responsive" style="height: 500px; overflow: auto;">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th><spring:message code="groups.list.name"/></th>
                                    <th><spring:message code="groups.list.description"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="group" items="${groupList}">
                                    <tr>
                                        <td>${group.id}</td>
                                        <td>${group.name}</td>
                                        <td>${group.description}</td>
                                        <td class="text-right">
                                            <a href="${pageContext.request.contextPath}/groups/edit/${group.id}" class="btn btn-iconed btn-primary btn-xs"><i class="fa fa-pencil"></i><spring:message code="general.edit"/></a>
                                            <a href="${pageContext.request.contextPath}/groups/remove/${group.id}" onclick="return confirm('Weet je zeker dat je deze group wilt verwijderen?')" class="btn btn-danger btn-xs" ><i class="fa fa-times"></i> <spring:message code="general.delete"/></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="widget widget-green">
                <div class="widget-title">
                    <i class="fa fa-group"></i>
                    
                </div>
                <div class="widget-content">
                    <form:form method="POST" modelAttribute="group" action="${pageContext.request.contextPath}/groups/add" class="form-horizontal">
                        <div class="form-group">
                            <label  control-label>ID</label>
                         
                                <form:input path="id" type="text" placeholder="${action.id}" class="form-control" readonly="true"/>
                           
                        </div>
                        <div class="form-group">
                            <label  control-label><spring:message code="groups.list.name"/></label>        
                            
                                <form:input path="name" type="text" placeholder="${group.name}" class="form-control" />
                            
                        </div>
                        <div class="form-group">
                            <label  control-label><spring:message code="groups.list.description"/></label>
                           
                                <form:input path="description" type="text" placeholder="${group.description}" class="form-control" />
                           
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <form:button type="submit" class="btn btn-primary"><spring:message code="groups.form.save"/></form:button>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <a href="${pageContext.request.contextPath}/groups/list" class="btn  btn-warning"><spring:message code="general.cancel"/></a>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../template/foot.jsp" />