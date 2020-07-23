<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Heroes & Villains</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1 id="title">Heroes & Villains</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayHeroesPage">Heroes & Villains</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                </ul>    
            </div>

            <div class="row">   

                <div class="col-md-6">
                    <h2>Heroes & Villains</h2>

                    <table id="heroesTable" class="table table-hover">
                        <tr>
                            <th width="30%">Name</th>
                            <th width="40%">Power(s)</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentHero" items="${heroList}">
                            <tr>
                                <td>
                                    <a href="displayHeroDetails?heroId=${currentHero.heroId}">
                                        <c:out value="${currentHero.name}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentHero.powers}"/> 
                                </td>
                                <td><a href="displayEditHeroForm?heroId=${currentHero.heroId}">Edit</a></td>
                                <td><a href="deleteHero?heroId=${currentHero.heroId}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-6">
                    <h2>Add New Hero or Villain</h2>

                    <form class="form-horizontal" role="form" method="POST" action="createHero">
                        <div class="form-group">
                            <label for="add-hero-name" class="col-md-4 control-label">Name:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="name" placeholder="Hero/Villain Name" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-hero-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="description" maxlength="240" placeholder="A brief description (not required)"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-hero-powers" class="col-md-4 control-label">Primary Powers:</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="powers" maxlength="50" placeholder="Flight, Strength, etc." required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-4">
                                <input type="submit" class="btn btn-default" value="Create Hero/Villain"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
