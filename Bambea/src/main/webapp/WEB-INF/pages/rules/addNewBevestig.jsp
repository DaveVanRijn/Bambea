<%-- 
    Document   : addNew
    Created on : 8-okt-2015, 13:53:23
    Author     : Rick
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../../template/head.jsp" />

<script>
    function startSummer(){
        var curYear = new Date().getFullYear();
        document.getElementById("startdate").value="21-06-" + curYear;
    }
    
    function endSummer() {
        var curYear = new Date().getFullYear();
        document.getElementById("enddate").value="20-09-" + curYear;
    }
    
    function startFall() {
        var curYear = new Date().getFullYear();
        document.getElementById("startdate").value="21-09-" + curYear;
    }
    
    function endFall() {
        var curYear = new Date().getFullYear();
        document.getElementById("enddate").value="20-12-" + curYear;
    }
    
    function startWinter() {
        var curYear = new Date().getFullYear();
        document.getElementById("startdate").value="21-12-" + curYear;
    }
    
    function endWinter() {
        var curYear = new Date().getFullYear();
        document.getElementById("enddate").value="20-03-" + curYear;
    }
    
    function startSpring() {
        var curYear = new Date().getFullYear();
        document.getElementById("startdate").value="21-03-" + curYear;
    }
    
    function endSpring() {
        var curYear = new Date().getFullYear();
        document.getElementById("enddate").value="20-06-" + curYear;
    }
</script>

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
                    <h3><i class="fa fa-bullseye"></i> <spring:message code="rules.add.step2"/>n</h3>
                </div>
                <div class="widget-content">
                    <b><spring:message code="rules.add.step2.chosen"/></b><br>
                    ${gekozenBeacon.title}
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">

                    </div>
                    <h3><i class="fa fa-flash"></i> <spring:message code="rules.add.step3"/></h3>
                </div>
                <div class="widget-content">
                    <b><spring:message code="rules.add.step3.chosen"/></b><br>
                    ${gekozenAction.name}
                </div>
            </div>
        </div>

    </div>
    <form:form method="POST" modelAttribute="rule">
    <div class="row">
        <div class="col-md-4">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">

                    </div>
                    <h3><i class="fa fa-calendar"></i> <spring:message code="rules.add.confirm.datehead"/></h3>
                </div>
                <div class="widget-content" style="height: 250px;">
                    <b><spring:message code="rules.add.confirm.datelabel"/></b><br><i><spring:message code="rules.add.confirm.optional"/></i><br><br>
                    <div class="input-group">
                        <input type="text" name="startdate" id="startdate" placeholder="<spring:message code="rules.add.confirm.date.start"/>" class="form-control input-datepicker mask_date" max="12">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><spring:message code="rules.add.confirm.periods"/> <span class="caret"></span></button>
                        <ul class="dropdown-menu pull-right" role="menu">
                          <li><a href="#" onclick="startSummer()"><spring:message code="rules.add.confirm.startsummer"/></a></li>
                          <li><a href="#" onclick="startFall()"><spring:message code="rules.add.confirm.startfall"/></a></li>
                          <li><a href="#" onclick="startWinter()"><spring:message code="rules.add.confirm.startwinter"/></a></li>
                          <li><a href="#" onclick="startSpring()"><spring:message code="rules.add.confirm.startspring"/></a></li>
                          <li class="divider"></li>
                        </ul>
                      </div>
                    </div>
                    <br>
                    <div class="input-group">
                        <input type="text" name="enddate" id="enddate" placeholder="<spring:message code="rules.add.confirm.date.end"/>" class="form-control input-datepicker mask_date" max="12">
                        <div class="input-group-btn">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><spring:message code="rules.add.confirm.periods"/> <span class="caret"></span></button>
                        <ul class="dropdown-menu pull-right" role="menu">
                          <li><a href="#" onclick="endSummer()"><spring:message code="rules.add.confirm.endsummer"/></a></li>
                          <li><a href="#" onclick="endFall()"><spring:message code="rules.add.confirm.endfall"/></a></li>
                          <li><a href="#" onclick="endWinter()"><spring:message code="rules.add.confirm.endwinter"/></a></li>
                          <li><a href="#" onclick="endSpring()"><spring:message code="rules.add.confirm.endspring"/></a></li>
                          <li class="divider"></li>
                        </ul>
                      </div>
                    </div>
                    <br>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">

                    </div>
                    <h3><i class="fa fa-clock-o"></i> <spring:message code="rules.add.confirm.timehead"/></h3>
                </div>
                <div class="widget-content" style="height: 250px;">
                    <b><spring:message code="rules.add.confirm.timelabel"/></b><br><i><spring:message code="rules.add.confirm.optional"/></i><br><br>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
                        <input type="text" name="starttime" placeholder="<spring:message code="rules.add.confirm.time.start"/>" id="tijd" class="form-control input-timepicker ui-timepicker-input mask_time" autocomplete="on" max="5">
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
                        <input type="text" name="endtime" placeholder="<spring:message code="rules.add.confirm.time.end"/>" id="tijd" class="form-control input-timepicker ui-timepicker-input mask_time" autocomplete="on" max="5">
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">

                    </div>
                    <h3><i class="fa fa-check"></i> <spring:message code="rules.add.confirm"/></h3>
                </div>
                <div class="widget-content" style="height: 220px;">
                    <br><br>
                    <!--<a href="${pageContext.request.contextPath}/rules/addSave/${gekozenGroup.id}/${gekozenBeacon.id}/${gekozenAction.id}" class="btn  btn-success" style="width: 100%; height: 50%"><br><i class="fa fa-save"></i><br><spring:message code="general.save"/></a>-->
                    <button type="submit"><i class="fa fa-save"></i><br><spring:message code="general.save"/></button>
                </div>
            </div>
        </div>
    </div>
    </form:form>
</div>

<jsp:include page="../../template/foot.jsp" />
