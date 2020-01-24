
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="steward.title" >
    <s:layout-component name="body" >
        <%@include file="flightslist.jsp" %>
    </s:layout-component>
</s:layout-render>
