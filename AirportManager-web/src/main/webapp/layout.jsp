<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title><f:message key="${titlekey}"/></title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
            <meta equiv="Content-Type" content="text/html; charset=UTF-8">
            <s:layout-component name="header"/>
        </head>

        <sec:authorize url="/admin">
            <body class="admin">
            </sec:authorize>
            <sec:authorize url="/user">
            <body>
            </sec:authorize>

            <div id="header">

                <s:link href="/index.jsp">
                    <div id="logo">
                    </div>
                </s:link>

                <div id="logout">
                    <sec:authorize url="/all">
                        <a href="<c:url value="/j_spring_security_logout" />">
                            <f:message key="logout"/>
                        </a>
                        <br/>
                        <f:message key="username"/>: <%= request.getUserPrincipal().getName()%>
                    </sec:authorize>
                    <sec:authorize url="/admin">
                        <br/>
                        <a href="${pageContext.request.contextPath}/registration.jsp">
                            <f:message key="registration"/>
                        </a>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        <a href="${pageContext.request.contextPath}/login.jsp">
                            <f:message key="login"/>
                        </a><br/>
                    </sec:authorize>
                </div>
            </div>

            <div id="main">
                <div id="navigation">
                    <sec:authorize url="/user">
                        <ul id="menu">
                            <li class="navlink"><f:message key="airplane"/>
                                <ul class="submenu">
                                    <sec:authorize url="/admin">
                                        <s:link href="/airplane/create.jsp">
                                            <li class="navlink"><f:message key="create"/></li>
                                            </s:link>
                                        </sec:authorize>
                                        <s:link beanclass="pl.edu.uj.ii.ionb.airportmanager.web.beans.AirplaneActionBean">
                                        <li class="navlink"><f:message key="list"/></li>
                                        </s:link>
                                </ul>
                            </li>
                            <li class="navlink"><f:message key="destination"/>
                                <ul class="submenu">
                                    <sec:authorize url="/admin">
                                        <s:link href="/destination/create.jsp">
                                            <li class="navlink"><f:message key="create"/></li>
                                            </s:link>
                                        </sec:authorize>
                                        <s:link beanclass="pl.edu.uj.ii.ionb.airportmanager.web.beans.DestinationsActionBean">
                                        <li class="navlink"><f:message key="list"/></li>
                                        </s:link>
                                </ul>
                            </li>
                            <li class="navlink"><f:message key="flight"/>
                                <ul class="submenu">
                                    <sec:authorize url="/admin">
                                        <s:link href="/flight/create.jsp">
                                            <li class="navlink"><f:message key="create"/></li>
                                            </s:link>
                                        </sec:authorize>
                                        <s:link beanclass="pl.edu.uj.ii.ionb.airportmanager.web.beans.FlightsActionBean">
                                        <li class="navlink"><f:message key="list"/></li>
                                        </s:link>
                                </ul>
                            </li>
                            <li class="navlink"><f:message key="steward"/>
                                <ul class="submenu">
                                    <sec:authorize url="/admin">
                                        <s:link href="/steward/edit.jsp">
                                            <li class="navlink"><f:message key="create"/>
                                                <s:param name="createnew" value="true"/>
                                            </li>
                                        </s:link>
                                    </sec:authorize>
                                    <s:link beanclass="pl.edu.uj.ii.ionb.airportmanager.web.beans.StewardsActionBean">
                                        <li class="navlink"><f:message key="list"/></li>
                                        </s:link>
                                </ul>
                            </li>
                        </ul>
                    </sec:authorize>
                </div>
                <div id="wrapper">
                    <s:messages/>
                    <s:errors/>
                    <s:layout-component name="body"/>
                </div>
            </div>

            <div id="footer">
                <f:message key="page.authors"/>: Jakub Kania; <f:message key="page.fimu"/>;
                e-mail: kania.jakub@student.uj.edu.pl
            </div>
        </body>
    </html>
</s:layout-definition>
