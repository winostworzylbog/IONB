<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="airplane.title">
    <s:layout-component name="body">

        <s:useActionBean beanclass="pl.edu.uj.ii.ionb.airportmanager.web.beans.AirplaneActionBean" var="actionBean"/>
        <div class="text-content">
            <h1><f:message key="airplane.list.allairplanes"/></h1>
            <table class="basic">
                <tr>
                    <th><f:message key="id"/></th>
                    <th><f:message key="airplane.capacity"/></th>
                    <th><f:message key="airplane.name"/></th>
                    <th><f:message key="airplane.type"/></th>
                    <th colspan="3"><f:message key="operations"/></th>
                </tr>
                <c:forEach items="${actionBean.airplanes}" var="airplane">
                    <tr>
                        <td>${airplane.id}</td>
                        <td><c:out value="${airplane.capacity}"/></td>
                        <td><c:out value="${airplane.name}"/></td>
                        <td><c:out value="${airplane.type}"/></td>
                        <td><s:link beanclass="pl.edu.uj.ii.ionb.airportmanager.web.beans.AirplaneActionBean" event="flightsOfPlane">
                                <s:param name="airplane.id" value="${airplane.id}"/>
                                <f:message key="listFlights"/>
                            </s:link>
                        </td>
                        <sec:authorize url="/admin">
                            <td>
                                <s:link beanclass="pl.edu.uj.ii.ionb.airportmanager.web.beans.AirplaneActionBean" event="edit">
                                    <s:param name="airplane.id" value="${airplane.id}"/>
                                    <img class="icon" src="${pageContext.request.contextPath}/images/edit.png"/>
                                </s:link>
                            </td>
                            <td>
                                <s:link beanclass="pl.edu.uj.ii.ionb.airportmanager.web.beans.AirplaneActionBean" event="delete">
                                    <s:param name="airplane.id" value="${airplane.id}"/>
                                    <img class="icon" src="${pageContext.request.contextPath}/images/delete.png"/>
                                </s:link>
                            </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </table>      
        </div>

    </s:layout-component>
</s:layout-render>