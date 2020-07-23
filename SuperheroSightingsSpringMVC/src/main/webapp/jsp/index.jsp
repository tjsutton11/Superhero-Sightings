<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Superhero Sightings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1 id="title">Superhero Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayHeroesPage">Heroes & Villains</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <h2>Description</h2>
                    <p>
                        This web application is for recording superhero & supervillain sightings! 
                        You can record sightings of heroes (or villains!) at particular locations, 
                        see what organizations they may be a part of, and more!
                    </p>
                    <ul>
                        <li>
                            To add a new hero or villain to the database, click the 
                            <a href="${pageContext.request.contextPath}/displayHeroesPage">Heroes & Villains</a>
                            tab.
                        </li>
                        <li>
                            If you have spotted a hero or villain at a new location, click the 
                            <a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a>
                            tab.
                        </li>
                        <li>
                            Heard of a new hero or villain organization around town? Click the 
                            <a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a>
                            tab.
                        </li>
                        <li>
                            To add a new sighting of a hero or villain, click the
                            <a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a>
                            tab.
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                <h2>Last Ten Sightings:</h2>
                <table id="sightingTable" class="table table-hover">
                        <tr>
                            <th width="30%">Hero/Villain</th>
                            <th width="40%">Address</th>
                            <th width="30%">Date & Time</th>
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
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

