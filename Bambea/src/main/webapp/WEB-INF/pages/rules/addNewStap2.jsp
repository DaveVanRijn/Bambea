<%-- 
    Document   : addNew
    Created on : 8-okt-2015, 13:53:23
    Author     : Rick
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="../../template/head.jsp" />

<div class="main-content">
    <ol class="breadcrumb">
        <h1><spring:message code="rules.add.head"/></h1>
    </ol>
    <div class="row">
        <div class="col-md-4">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">

                    </div>
                    <h3><i class="fa fa-users"></i> <spring:message code="rules.add.step1"/></h3>
                </div>
                <div class="widget-content">
                    <div class="table-responsive">
                        <b><spring:message code="rules.add.step1.chosen"/></b><br>
                        ${gekozenGroup.name}
                    </div>
                </div>
            </div>

        </div>

        <div class="col-md-4">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">

                    </div>
                    <h3><i class="fa fa-bullseye"></i> <spring:message code="rules.add.step2"/></h3>
                </div>
                <div class="widget-content">
                    <div class="table-responsive" style="height: 500px; overflow: auto;">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th><spring:message code="rules.add.step2.list.name"/></th>
                                    <th> </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="beacon" items="${beaconList}">
                                    <tr>
                                        <td>${beacon.id}</td>
                                        <td>${beacon.title}</td>
                                        <td><a href="${gekozenGroup.id}/${beacon.id}"><button type="button" class="btn btn-default btn-xs"><spring:message code="general.choose"/></button></a></td>
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