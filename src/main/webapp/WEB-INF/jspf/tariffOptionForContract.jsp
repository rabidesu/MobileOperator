<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab-pane fade" id="tariff">
    <div class="form-group">
        <label for="tariff_select">Выберите тариф:</label>
        <select class="form-control"  id="tariff_select"
                <c:if test="${requestScope.contract.blockedByAdmin ||
                                                          requestScope.contract.blockedByClient}">disabled</c:if>
                name="tariff_id">
            <c:forEach items="${requestScope.tariffs}" var="tariff">
                <option value="${tariff.id}" class="tariff_select_opt"
                        <c:set var="pos_options" value="" scope="page"/>
                        <c:forEach items="${tariff.options}" var="possible_option">
                            <c:set var="pos_options" value="${pos_options} ${possible_option.id}"/>
                        </c:forEach>
                        data-options="${pos_options}"
                        <c:if test="${tariff.id eq requestScope.contract.tariff.id}">
                            selected
                        </c:if>
                >
                    <c:out value="${tariff.name}"/></option>
            </c:forEach>
        </select>
    </div>
</div>

<div class="tab-pane fade" id="options_tab">
    <div class="form-group">
        <c:if test="${not empty requestScope.options}">
            <label>Выберите опции:</label>
            <div id="text-empty-option" class="hidden">
                Для данного тарифа нет доступных опций
            </div>
            <div class="option_chb">
                <c:forEach var="option" items="${requestScope.options}">
                    <div class="checkbox">
                        <label><input type="checkbox"
                            <c:set var="req_options" value="" scope="page"/>

                        <c:forEach var="req_option" items="${option.optionsRequired}">
                            <c:set var="req_options" value="${req_options} ${req_option.id}"/>
                        </c:forEach>
                                      data-req="${req_options}"

                                      <c:if test="${requestScope.contract.blockedByAdmin ||
                                                          requestScope.contract.blockedByClient}">disabled</c:if>
                        <c:forEach var="selected_option" items="${requestScope.contract.options}">
                                      <c:if test="${option.id eq selected_option.id}">checked</c:if>
                        </c:forEach>
                                      class="depended_option"
                                      name="options" value="${option.id}">${option.name}
                            <input name="options" type="hidden"  disabled id="hidden_${option.id}" value="${option.id}"/>
                        </label>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${empty requestScope.options}">
            <div class="panel panel-warning">
                <div class="panel-heading">Информация</div>
                <div class="panel-body"><c:out value="Нет опций, доступных для данного тарифа"/></div>
            </div>
        </c:if>
    </div>
</div>
