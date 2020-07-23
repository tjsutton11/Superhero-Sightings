<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sightings</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1 id="title">Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayHeroesPage">Heroes & Villains</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                </ul>    
            </div>
            <div class="row">
                <div class="col-md-6">
                    <h2>Hero & Villain Sightings</h2>

                    <table id="sightingTable" class="table table-hover">
                        <tr>
                            <th width="25%">Hero/Villain</th>
                            <th width="40%">Address</th>
                            <th width="25%">Date & Time</th>
                            <th width="5%"></th>
                            <th width="5%"></th>
                        </tr>
                        <c:forEach var="currentSighting" items="${sightingList}">
                            <tr>
                                <td>
                                    <a href="displaySightingDetails?sightingId=${currentSighting.sightingId}">
                                        <c:out value="${currentSighting.hero.name}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentSighting.location.street}"/> 
                                    <c:out value="${currentSighting.location.city}"/>, 
                                    <c:out value="${currentSighting.location.state}"/>
                                    <c:out value="${currentSighting.location.zip}"/>
                                </td>
                                <td>
                                    <c:out value="${currentSighting.date}"/>
                                    <c:out value="${currentSighting.time}"/>
                                </td>
                                <td><a href="displayEditSightingForm?sightingId=${currentSighting.sightingId}&date=${currentSighting.date}">Edit</a></td>
                                <td><a href="deleteSighting?sightingId=${currentSighting.sightingId}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-6">
                    <h2>Add New Sighting</h2>

                    <form class="form-horizontal" role="form" method="POST" action="createSighting">
                        <div class="form-group">
                            <label for="add-hero" class="col-md-4 control-label">Hero/Villain ID:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="heroId" placeholder="Hero/Villain ID" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location" class="col-md-4 control-label">Location ID:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="locationId" placeholder="Location ID" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-date" class="col-md-4 control-label">Date:</label> 
                            <div class="col-md-6">
                                <input type="date" class="form-control" name="date" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-time" class="col-md-4 control-label">Time:</label> 
                            <div class="col-md-6">
                                <input type="time" class="form-control" name="time" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-4">
                                <input type="submit" class="btn btn-default" value="Create Sighting"/>
                            </div>
                        </div>
                    </form>
                    <p><b>If you cannot find the location of your sighting, please add it first before continuing.</b></p>
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
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
