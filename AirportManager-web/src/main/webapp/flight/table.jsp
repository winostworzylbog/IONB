<%-- 
    Document   : table
    Created on : 22.11.2013, 13:59:26
    Author     : Samo
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:useActionBean beanclass="pl.edu.uj.ii.ionb.airportmanager.web.beans.FlightsActionBean"
                 var="actionBean"/>

<table class="basic">
    <tr>
        <th><f:message key="id"/></th>
        <th><f:message key="flight.departureTime"/></th>
        <th><f:message key="flight.arrivalTime"/></th>
        <th><f:message key="flight.origin"/></th>
        <th><f:message key="flight.target"/></th>
        <th><f:message key="flight.airplane"/></th>
        <th><f:message key="flight.stewards"/></th>
        <sec:authorize url="/admin">
            <th colspan="2"><f:message key="operations"/></th>
        </sec:authorize>
    </tr>
    
    <c:forEach items="${actionBean.flights}" var="flight">
        <tr>
            <td>${flight.id}</td>
            <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${flight.departureTime}"/></td>
            <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${flight.arrivalTime}"/></td>
            <td><c:out value="${flight.origin.city}"/></td>
            <td><c:out value="${flight.target.city}"/></td>
            <td><c:out value="${flight.airplaneTO.name}"/></td>
            <td>
                <s:form beanclass="pl.edu.uj.ii.ionb.airportmanager.web.beans.FlightsActionBean">
                    <c:choose>
                        <c:when test="${fn:length(flight.stewList) gt 0}">
                            <s:select id="stews" name="flight.stewList">
                                <c:forEach items="${flight.stewList}" var="steward">
                                    <s:option value="steward">
                                        ${steward.firstName} ${steward.lastName}
                                    </s:option>
                                </c:forEach>
                            </s:select>   
                        </c:when>
                        <c:otherwise>
                            <f:message key="flight.noStewards"/>
                        </c:otherwise>
                    </c:choose>   
                </s:form>
            </td>
            <sec:authorize url="/admin">
                <td>
                    <s:link beanclass="pl.edu.uj.ii.ionb.airportmanager.web.beans.FlightsActionBean"
                            event="editflight">
                        <s:param name="flight.id" value="${flight.id}"/>
                        <img src="${pageContext.request.contextPath}/images/edit.png" class="icon"/>
                    </s:link>
                </td>
                <td>
                    <s:link beanclass="pl.edu.uj.ii.ionb.airportmanager.web.beans.FlightsActionBean"
                            event="deleteflight">
                        <s:param name="flight.id" value="${flight.id}"/>
                        <img src="${pageContext.request.contextPath}/images/delete.png" class="icon"/>
                    </s:link>
                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
</table>