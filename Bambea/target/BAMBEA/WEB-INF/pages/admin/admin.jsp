<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="../../template/head.jsp" />
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
<div class="main-content">
    <div class="row">
        <div class="col-md-8">
            <div class="widget widget-orange">
                <div class="widget-title">
                    <i class="fa fa-table"></i> Userlist
                    <div class="widget-controls"></div>
                </div>
                <div class="widget-content">
                    <div class="table-responsive" style="height: 395px; overflow: auto;">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Username</th>
                                    <th>Role</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${userList}">
                                    <tr>
                                        <td>${user.getUsername()}</td>
                                        <td>${user.getUserRole().role}</td>
                                        <!--<td class="text-right">
                                            <button onClick="edit(${user.username})" class="btn btn-iconed btn-primary btn-xs"><i class="fa fa-pencil"></i><spring:message code="general.edit"/></button>
                                            <button onClick="deleteAction(${user.username})" class="btn btn-danger btn-xs"><i class="fa fa-times"></i><spring:message code="general.delete"/></button>
                                        </td>-->
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
                    <i class="fa fa-flash"></i> Add User
                </div>
                <div class="widget-content">
                    <form:form method="POST" modelAttribute="useradd" action="${pageContext.request.contextPath}/user/add" class="form-horizontal">
                        <input type='hidden' id='csrfToken' value='${_csrf.token}'/><input type='hidden' id='csrfHeader' value='${_csrf.headerName}'/>
                        <div class="form-group">
                            <label class="control-label"><spring:message code="login.usernameplaceholder"/></label>
                            <form:input path="username" type="text" placeholder="" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label class="control-label"><spring:message code="login.passwordplaceholder"/></label>

                            <form:input path="password" type="password" placeholder="" required="true" class="form-control" />

                        </div>

                        <div class="form-group">
                            <label>Rol</label>
                            <div >
                                <select id="userrole" name="userrole" class="form-control">
                                    <c:forEach var="role" items="${roleList}">
                                        <option value="${role.getKey()}">${role.getValue()}</option>
                                    </c:forEach>
                                </select>     
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <form:button type="submit" class="btn btn-primary"><i class="fa fa-plus"></i> <spring:message code="user.formhead"/></form:button>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <a href="${pageContext.request.contextPath}/user/list" class="btn  btn-danger"><i class="fa fa-times"></i> <spring:message code="general.cancel"/></a>
                            </div>
                        </div>
                    </form:form>
                    <legend class="text-center"><spring:message code="admin.or"/></legend>

                    <div class="pre-value-block"><spring:message code="admin.regLinkExplanation"/></div>
                    <form id="regLinkGenForm" class="bottom-margin" method="POST" action="${pageContext.request.contextPath}/registration/linkGen" accept-charset='UTF-8'>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label><spring:message code="admin.uses"/></label>
                                    <input required id="nuOfRegistrations" class="form-control" type="number"/>
                                </div>
                            </div>

                            <div class="col-md-8"
                                 <div class="form-group">
                                    <label class="control-label"><spring:message code="admin.role"/></label>
                                    <select id="userrol" class="form-control">
                                        <option value="ROLE_ADMIN">Admin</option>
                                        <option value="ROLE_MOD">Moderator</option>
                                        <option value="ROLE_PARTNER">Partner</option>
                                    </select>                            

                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label"><spring:message code="admin.customLink"/></label>
                                <input id="regLink" class="form-control" placeholder="Link URL" tpye="text"/>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class=" col-md-3">
                                        <button type="submit" class="btn btn-primary"><spring:message code="admin.generate"/></button>
                                    </div>
                                    <div class="input-group col-md-9">
                                        <span class="input-group-btn">
                                            <button onClick="copy();" class="btn btn-default" type="button"><spring:message code="admin.copy"/></button>
                                        </span>
                                        <input id="linkResult" class="form-control" type="url"/>
                                    </div>
                                </div>
                            </div>
                    </form>
                </div>
            </div>
        </div>
    </div>        
</div>
</div>
<jsp:include page="../../template/foot.jsp" />
