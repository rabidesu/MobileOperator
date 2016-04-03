<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container">
        <a id="logo-container" href="#" class="brand-logo"><c:out value="${loggedUser.name} ${loggedUser.surname}"/> </a>
        <ul class="right hide-on-med-and-down">
            <li><a class='dropdown-button btn-flat' href='#' data-activates='locale'>
                <c:if test="${pageContext.response.locale == 'en'}"><img src="/images/united_states_flag.png"></c:if>
                <c:if test="${pageContext.response.locale== 'ru'}"><img src="/images/russia_flag.png"></c:if>
            </a>
                <ul id='locale' class='dropdown-content'>
                    <li>
                        <a href="/pages/client/index?lang=en"><img src="/images/united_states_flag.png"></a>
                    </li>
                    <li>
                        <a href="/pages/client/index?lang=ru"><img src="/images/russia_flag.png"></a>
                    </li>
                </ul>
            </li>
            <li><a href="/pages/clientProfile"><spring:message code="lbl.profile"/></a></li>
            <li><a href="/pages/clientContracts"><spring:message code="lbl.contracts"/></a></li>
            <li><a href="/pages/searchTariffs"><spring:message code="lbl.tariffs"/></a></li>
            <li><a href="/logout"><spring:message code="lbl.logout"/></a></li>
        </ul>
    </div>
</nav>