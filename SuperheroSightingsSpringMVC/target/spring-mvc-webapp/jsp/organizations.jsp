<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Organizations</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1 id="title">Hero & Villain Organizations</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayHeroesPage">Heroes & Villains</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                </ul>    
            </div>
            <div class="row">
                <div class="col-md-6">
                    <h2>Hero & Villain Organizations</h2>

                    <table id="organizationTable" class="table table-hover">
                        <tr>
                            <th width="30%">Name</th>
                            <th width="40%">Address</th>
                            <th width="10%"></th>
                            <th width="10%"></th>
                            <th width="10%"></th>
                        </tr>
                        <c:forEach var="currentOrganization" items="${organizationList}">
                            <tr>
                                <td>
                                    <a href="displayOrganizationDetails?organizationId=${currentOrganization.organizationId}">
                                        <c:out value="${currentOrganization.name}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentOrganization.location.street}"/> 
                                    <c:out value="${currentOrganization.location.city}"/>, 
                                    <c:out value="${currentOrganization.location.state}"/>
                                    <c:out value="${currentOrganization.location.zip}"/>
                                </td>
                                <td><a href="displayAddMemberForm?organizationId=${currentOrganization.organizationId}">Add Member</a></td>
                                <td><a href="displayEditOrganizationForm?organizationId=${currentOrganization.organizationId}&locationId=${currentOrganization.location.locationId}">Edit</a></td>
                                <td><a href="deleteOrganization?organizationId=${currentOrganization.organizationId}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-6">
                    <h2>Add New Organization</h2>
                    
                    <form class="form-horizontal" role="form" method="POST" action="createOrganization">
                        <div class="form-group">
                            <label for="add-organization-name" class="col-md-4 control-label">Name:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="orgName" placeholder="Name" maxlength="45" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-organization-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="orgDescription" maxlength="240" placeholder="Brief Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-street" class="col-md-4 control-label">Street:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="street" maxlength="40" placeholder="123 Main Street" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-city" class="col-md-4 control-label">City:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="city" maxlength="40" placeholder="Pittsburgh" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-state" class="col-md-4 control-label">State:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="state" maxlength="20" placeholder="Pennsylvania" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-zip" class="col-md-4 control-label">Zip:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="zip" maxlength="15" placeholder="12345" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="latitude" maxlength="16" placeholder="22.836231" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-longitude" class="col-md-4 control-label">Longitude:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="longitude" maxlength="16" placeholder="-41.962338" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-phone" class="col-md-4 control-label">Phone:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="phone" maxlength="10" placeholder="1234567890" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-email" class="col-md-4 control-label">Email:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="email" maxlength="30" placeholder="example@me.com" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-4">
                                <input type="submit" class="btn btn-default" value="Create Organization"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
