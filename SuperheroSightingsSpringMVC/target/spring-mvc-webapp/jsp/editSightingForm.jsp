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
            <div class="row">
                <div class="col-md-6">
                    <h2>Heroes & Villains</h2>

                    <table id="heroesTable" class="table table-hover">
                        <tr>
                            <th width="15%">ID</th>
                            <th width="30%">Name</th>
                            <th width="40%">Power(s)</th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentHero" items="${heroList}">
                            <tr>
                                <td>
                                    <c:out value="${currentHero.heroId}"/>
                                </td>
                                <td>
                                    <c:out value="${currentHero.name}"/> 
                                </td>
                                <td>
                                    <c:out value="${currentHero.powers}"/>
                                </td>
                                <td></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-6">
                    <h2>Locations</h2>

                    <table id="locationTable" class="table table-hover">
                        <tr>
                            <th width="10%">ID</th>
                            <th width="40%">Name</th>
                            <th width="40%">Address</th>
                            <th width="10%"></th>
                        </tr>
                        <c:forEach var="currentLocation" items="${locationList}">
                            <tr>
                                <td>
                                    <c:out value="${currentLocation.locationId}"/>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.name}"/>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.street}"/> 
                                    <c:out value="${currentLocation.city}"/>, 
                                    <c:out value="${currentLocation.state}"/>
                                    <c:out value="${currentLocation.zip}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
