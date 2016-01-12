<%-- 
    Document   : actionList
    Created on : 1-okt-2015, 12:12:01
    Author     : Dave van Rijn, Student 500714558, Klas IS202
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="../../template/head.jsp" />
<div class="main-content">
    <div class="row">
        <div class="col-md-8">
            <div class="widget widget-orange">
                <div class="widget-title">
                    <i class="fa fa-table"></i> <spring:message code="action.tablehead"/>
                    <div class="widget-controls">
                    </div>
                </div>
                <div class="widget-content">
                    <div class="table-responsive" style="height: 395px; overflow: auto;">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th><spring:message code="action.list.name"/></th>
                                    <th><spring:message code="action.list.vibrate"/></th>
                                    <th>URL</th>
                                    <th>Open App</th>
                                    <th><spring:message code="action.list.notification"/></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            <style type="text/css">
                                .asLabel{
                                    background: rgba(0,0,0,0);
                                    border: 1px solid rgba(0,0,0,0);
                                    width: 100px;
                                }
                                .editField{
                                    width: 100px;
                                }
                            </style>
                            <c:forEach  var="action" items="${actionList}">
                                <tr>
                                    <td>${action.id}</td>
                                    <td><input type="text" id="txtName${action.id}" value="${action.name}"  required class="asLabel" readonly/></td>
                                    <td><input type="text" id="txtVibrate${action.id}" value="${action.vibrate}" class="asLabel" style="width: 50px;" readonly/></td>
                                    <td><input type="text" id="txtUrl${action.id}" value="${action.url}" class="asLabel"style="width: 110px;" readonly/></td>
                                    <td><input type="text" id="txtOpenApp${action.id}" value="${action.openApp}" class="asLabel" readonly/></td>
                                    <td><input type="text" id="txtNoti${action.id}" value="${action.notification}" class="asLabel" readonly/></td>
                                    <td class="text-right">
                                        <button id="edit${action.id}" onClick="edit(${action.id})" class="btn btn-iconed btn-primary btn-xs"><i class="fa fa-pencil"></i><spring:message code="general.edit"/></button>
                                        <button id="delete${action.id}" onClick="deleteAction(${action.id})" class="btn btn-danger btn-xs"><i class="fa fa-times"></i><spring:message code="general.delete"/></button>
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
            <div class="widget widget-orange">
                <div class="widget-title">
                    <i class="fa fa-flash"></i> <spring:message code="action.formhead"/>
                </div>
                <div class="widget-content">
                    <form:form method="POST" modelAttribute="action" action="${pageContext.request.contextPath}/action/add" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-4" control-label>ID</label>
                            <div class="col-md-8">
                                <form:input path="id" type="text" placeholder="${action.id}" class="form-control" readonly="true"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" control-label><spring:message code="action.list.name"/></label>
                            <div class="col-md-8">
                                <form:input path="name" type="text" placeholder="${action.name}" required="true" class="form-control" />
                            </div>
                        </div>
                        <h4 class="widget-header">Triggers</h4>
                        <div class="form-group ">
                            <label class="col-md-4" control-label><spring:message code="action.list.vibrate"/></label>   
                            <div class="col-md-8" checkbox>
                                <form:checkbox path="vibrate"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" control-label>URL</label>
                            <div class="col-md-8">
                                <form:input path="url" type="text" placeholder="${action.url}" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" control-label>Open App</label>
                            <div class="col-md-8">
                                <form:input path="openApp" type="text" placeholder="${action.openApp}" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" control-label><spring:message code="action.list.notification"/></label>
                            <div class="col-md-8">
                                <form:input path="notification" type="text" placeholder="${action.notification}" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <form:button type="submit" class="btn btn-primary"><i class="fa fa-plus"></i> <spring:message code="action.formhead"/></form:button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <a href="${pageContext.request.contextPath}/action/list" class="btn  btn-danger"><i class="fa fa-times"></i> <spring:message code="general.cancel"/></a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>        
    </div>
</div>
<jsp:include page="../../template/foot.jsp" />
