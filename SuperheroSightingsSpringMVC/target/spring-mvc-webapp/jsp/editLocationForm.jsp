<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Location</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1 id="title">Edit Location</h1>
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
            <sf:form class="form-horizontal" role="form" modelAttribute="location" action="editLocation" method="POST">
                <div class="form-group">
                    <label for="add-location-name" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-location-name"
                                  path="name" placeholder="Name"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-location-description" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-location-description" maxlength="100"
                                  path="description" placeholder="Brief Description"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-street" class="col-md-4 control-label">Street:</label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-street" maxlength="40"
                                  path="street" placeholder="123 Main Street"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-city" class="col-md-4 control-label">City:</label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-city" maxlength="40"
                                  path="city" placeholder="Pittsburgh"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-state" class="col-md-4 control-label">State:</label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-state" maxlength="20"
                                  path="state" placeholder="Pennsylvania"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-zip" class="col-md-4 control-label">Zip:</label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-zip" maxlength="15"
                                  path="zip" placeholder="12345"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-latitude" maxlength="16"
                                  path="latitude" placeholder="ex. 11.274629"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-longitude" class="col-md-4 control-label">Longitude</label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-longitude" maxlength="16"
                                  path="longitude" placeholder="ex. -41.962338"/>
                        <sf:hidden path="locationId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Location"/>
                    </div>
                </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
