<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Organization</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1 id="title">Edit Organization</h1>
            <p>If you wish to edit the address of an organization, please do so under the <a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a> tab.</p>
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
            <sf:form class="form-horizontal" role="form" modelAttribute="organization" action="editOrganization" method="POST">
                <div class="form-group">
                    <label for="add-organization-name" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-organization-name" maxlength="45"
                                  path="name" placeholder="Name"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-organization-description" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-organization-description" maxlength="240"
                                  path="description" placeholder="Brief Description"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-phone" class="col-md-4 control-label">Phone:</label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-phone" maxlength="10"
                                  path="phone" placeholder="4125551234"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-email" class="col-md-4 control-label">Email:</label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="add-email" maxlength="30"
                                  path="email" placeholder="example@me.com"/>
                        <sf:hidden path="organizationId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Organization"/>
                    </div>
                </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
