        <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
        <script src="http://dev.ibeaconlivinglab.com:1880/beacons/js?ver=4.3.1"></script>
        <script src='${pageContext.request.contextPath}/static/assets/js/ad67372f4b8b70896e8a596720082ac6.js'></script>
        <script src='${pageContext.request.contextPath}/static/assets/js/d7dfc13379a397356e42ab8bd98901a0.js'></script>
        <script src='${pageContext.request.contextPath}/static/assets/js/maps.js'></script>
        <script src='${pageContext.request.contextPath}/static/assets/js/actionlist.js'></script>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <script src='${pageContext.request.contextPath}/static/assets/js/registrationGen.js'></script>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MOD')">
            <script src='${pageContext.request.contextPath}/static/assets/js/beaconAddEdit.js'></script>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_PARTNER')">
            <script src='${pageContext.request.contextPath}/static/assets/js/partnerAccess.js'></script>
        </sec:authorize>
    </body>
</html>