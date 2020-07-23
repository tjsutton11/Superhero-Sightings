<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Locations</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1 id="title">Locations</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayHeroesPage">Heroes & Villains</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">
                    <h2>Locations</h2>
                    
                    <table id="locationTable" class="table table-hover">
                        <tr>
                            <th width="30%">Name</th>
                            <th width="40%">Address</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentLocation" items="${locationList}">
                            <tr>
                                <td>
                                    <a href="displayLocationDetails?locationId=${currentLocation.locationId}">
                                        <c:out value="${currentLocation.name}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.street}"/> 
                                    <c:out value="${currentLocation.city}"/>, 
                                    <c:out value="${currentLocation.state}"/>
                                    <c:out value="${currentLocation.zip}"/>
                                </td>
                                <td><a href="displayEditLocationForm?locationId=${currentLocation.locationId}">Edit</a></td>
                                <td><a href="deleteLocation?locationId=${currentLocation.locationId}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                
                <div class="col-md-6">
                    <h2>Add New Location</h2>

                    <form class="form-horizontal" role="form" method="POST" action="createLocation">
                        <div class="form-group">
                            <label for="add-location-name" class="col-md-4 control-label">Name:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="locationName" placeholder="Name" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="locationDescription" maxlength="100" placeholder="Brief Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-street" class="col-md-4 control-label">Street:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="streetName" maxlength="40" placeholder="123 Main Street" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-city" class="col-md-4 control-label">City:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="cityName" maxlength="40" placeholder="Pittsburgh" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-state" class="col-md-4 control-label">State:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="stateName" maxlength="20" placeholder="Pennsylvania" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-zip" class="col-md-4 control-label">Zip:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="zipCode" maxlength="15" placeholder="12345" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="latitude" maxlength="16" placeholder="22.836231" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-longitude" class="col-md-4 control-label">Longitude</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="longitude" maxlength="16" placeholder="-41.962338" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-4">
                                <input type="submit" class="btn btn-default" value="Create Location"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>


        </div>
    </body>
</html>
