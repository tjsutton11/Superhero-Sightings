<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Hero or Villain</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1 id="title">Edit Hero or Villain</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayHeroesPage">Heroes & Villains</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                </ul>    
            </div>
            <sf:form class="form-horizontal" role="form" modelAttribute="hero" action="editHero" method="POST">
                <div class="form-group">
                    <label for="add-hero-name" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-hero-name"
                                  path="name" placeholder="Name"/>
                        <sf:errors path="name" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-hero-description" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-hero-description" maxlength="100"
                                  path="description" placeholder="Brief Description"/>
                        <sf:errors path="description" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-powers" class="col-md-4 control-label">Power(s):</label>
                        <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-powers" maxlength="50"
                                  path="powers" placeholder="Flight, strength, etc."/>
                        <sf:errors path="powers" cssclass="error"></sf:errors>
                        <sf:hidden path="heroId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Hero/Villain"/>
                    </div>
                </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
