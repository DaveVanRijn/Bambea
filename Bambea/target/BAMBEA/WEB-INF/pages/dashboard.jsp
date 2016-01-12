<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<jsp:include page="../template/head.jsp" />

<div class="main-content">
    <div class="no-padding col-md-8" id="widget_gmaps">
        <div class="widget widget-blue">
            <div class="widget-title">
                <h3><i class="fa fa-map"></i>Map</h3>
            </div>
            <div type="hidden" id="mapLang" 
                 majid="<spring:message code="map.majid"/>" 
                 minid="<spring:message code="map.minid"/>" 
                 name="<spring:message code="map.name"/>" 
                 lat="<spring:message code="map.lat"/>" 
                 lng="<spring:message code="map.lng"/>" 
                 save="<spring:message code="general.save"/>" 
                 delete="<spring:message code="general.delete"/>" 
                 formAddError="<spring:message code="map.addError"/>" 
                 formEditError="<spring:message code="map.editError"/>" 
                 deleteConfirm="<spring:message code="map.deleteConfirm"/>"
                 editAccessDenied="<spring:message code= "map.accessDenied"/>"></div>
            <div class="widget-content no-padding">
                <div id="gmap-default" style="height: 450px;"></div>
                <input type='hidden' id='csrfToken' value='${_csrf.token}'/><input type='hidden' id='csrfHeader' value='${_csrf.headerName}'/>
            </div>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/rules/add" class="btn btn-action btn-lg btn-info"><i class="fa fa-file-text-o"></i> <spring:message code="dashboard.button.rule"/></a>
            <a href="${pageContext.request.contextPath}/groups/list" class="btn btn-action btn-lg btn-info"><i class="fa fa fa-group"></i> <spring:message code="dashboard.button.group"/></a>
            <a href="${pageContext.request.contextPath}/action/list" class="btn btn-action btn-lg btn-info"><i class="fa fa-flash"></i> <spring:message code="dashboard.button.action"/></a>
        </div>
    </div>

    <div class="no-padding col-md-4">
        <div class="widget widget-blue">
            <div class="widget-title">

                <h3><i class="fa fa-bullseye"></i>Beacons</h3>
            </div>

            <div class="widget-content" >              
                <div class="table-responsive" style="height: 500px; overflow: auto;">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Major ID</th>
                                <th>Minor ID</th>
                                <th><spring:message code="dashboard.beaconlist.name"/></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr></tr>
                            <c:forEach var="beacon" items="${beaconsList}">
                                <tr>
                                    <td>${beacon.major}</td>
                                    <td>${beacon.minor}</td>
                                    <td>${beacon.title}</td>                                      
                                    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MOD')">
                                        <td class="text-right">
                                            <a href="${pageContext.request.contextPath}/beacon/delete/${beacon.id}" onclick="return confirm(<spring:message code="dashboard.beaconlist.delete.confirm"/>)" class="btn btn-danger btn-xs"><center><i class="fa fa-trash"></i></center></a>
                                        </td>
                                    </sec:authorize>                                    
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../template/foot.jsp" />