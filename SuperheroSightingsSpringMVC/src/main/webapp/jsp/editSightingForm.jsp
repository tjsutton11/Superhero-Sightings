<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Sighting</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1 id="title">Edit Sighting</h1>
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
            <div class="row>">
                <div class="col-md-6 col-md-offset-3">
                    <sf:form class="form-horizontal" role="form" modelAttribute="sighting" action="editSighting" method="POST">
                        <div class="form-group">
                            <label for="edit-hero" class="col-md-4 control-label">Hero/Villain: </label>
                            <div class="col-md-6">
                                <select name="heroId"  value="Select Hero/Villain">
                                    <c:forEach var="currentHero" items="${heroList}">
                                        <option value="${currentHero.heroId}"${currentHero.heroId == sighting.hero.heroId? "selected" : " " }>
                                            <c:out value="${currentHero.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit-location" class="col-md-4 control-label">Location:</label>
                            <div class="col-md-6">
                                <select name="location.locationId"  value="Select Location">
                                    <c:forEach var="currentLocation" items="${locationList}">
                                        <option value="${currentLocation.locationId}"${currentLocation.locationId == sighting.location.locationId? "selected" : " " }>
                                            <c:out value="${currentLocation.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit-date" class="col-md-4 control-label">Date:</label>
                            <div class="col-md-6">
                                <sf:input type="date" class="form-control" id="edit-date" path="date"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit-time" class="col-md-4 control-label">Time:</label>
                            <div class="col-md-6">
                                <sf:input type="time" class="form-control" id="edit-time" path="time"/>
                                <sf:hidden path="sightingId"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Update Sighting"/>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
