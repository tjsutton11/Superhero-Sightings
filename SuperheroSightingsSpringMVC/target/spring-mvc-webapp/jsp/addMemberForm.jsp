<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Organization Member</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1 id="title">Add Organization Member</h1>
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

            <div class="row">  
                <div class="col-md-6">
                    <h2>Add Organization Member</h2>
                    <p><b>Name: <c:out value="${organization.name}"/></b></p>
                    <p><b>Please choose the hero you would like to add to this organization by entering their Hero ID.</b></p>
                    <sf:form class="form-horizontal" role="form" modelAttribute="organization" method="POST" action="addMember">
                        <div class="form-group">
                            <label for="hero-id" class="col-md-4 control-label">Hero ID:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="heroId" placeholder="Hero ID" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="organization-id" class="col-md-4 control-label">Org ID:</label>
                            <div class="col-md-6">
                                <sf:input type="text" class="form-control"  id="organization-id" path="organizationId" readonly="true"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-4">
                                <input type="submit" class="btn btn-default" value="Add Member"/>
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
            </div>
        </div>
    </body>
</html>
