<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/admin-lisenquestion-list.html" var="urlEditListen">
</c:url>
<c:url var="urlListListen" value="/admin-lisenquestion-list.html">
    <c:param name="urlType" value="list"/>
    <c:param name="crudaction" value="delete"/>
    <c:param name="exerciseListenId" value="${items.exerciseListenId}"/>
</c:url>
<head>
    <title><fmt:message key="label.exercise.listen.question.list" bundle="${lang}"/></title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#"><fmt:message key="label.home" bundle="${lang}"/></a>
                </li>
                <li class="active"><fmt:message key="label.exercise.listen.question.list" bundle="${lang}"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty message_response}">
                        <div class="alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon"></i>
                            </button>
                                ${message_response}
                        </div>
                    </c:if>
                    <form action="${formUrl}" method="get" id="formUrl">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box table-filter">
                                    <div class="widget-header">
                                        <h4 class="widget-title"><fmt:message key="label.search" bundle="${lang}"/></h4>
                                        <div class="widget-toolbar">
                                            <a href="#" data-action="collapse">
                                                <i class="ace-icon fa fa-chevron-up"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"><fmt:message
                                                            key="label.exercise.listen.question.list"
                                                            bundle="${lang}"/></label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <input type="text" value="${items.pojo.question}"
                                                                   class="form-control input-sm" name="pojo.question" id="textSearch"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-8">
                                                        <button id="btnSearch" class="btn btn-sm btn-success">
                                                            <fmt:message key="label.search" bundle="${lang}"/>
                                                            <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-btn-controls">
                                    <div class="pull-right tableTools-container">
                                        <div class="dt-buttons btn-overlap btn-group">
                                            <c:url value="/admin-lisenquestion-edit.html" var="editListen">
                                                <c:param name="urlType" value="edit"/>
                                                <c:param name="exerciseListenId" value="${items.exerciseListenId}"/>
                                            </c:url>
                                            <a flag="info"
                                               class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                               href="${editListen}"
                                               data-toggle="tooltip"
                                               title="<fmt:message bundle='${lang}' key='label.exercise.listen.question.add'/>"
                                            >
                                                    <span>
                                                        <i class="fa fa-plus-circle bigger-110 purple"></i>
                                                    </span>
                                            </a>
                                            <button type="button"
                                                    class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                    id="deleteAll" onclick="warningBeforeDelete()"
                                                    data-toggle="tooltip"
                                                    title="<fmt:message  key='label.delete.all' bundle='${lang}'/>" disabled>
                                                     <span>
                                                        <i class="fa fa-trash-o bigger-110 pink"></i>
                                                    </span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="table-responsive">
                            <fmt:bundle basename="ResourcesBundle">
                                <display:table id="tableList" list="${items.listResult}" partialList="true" size="${items.totalItems}"
                                               pagesize="${items.maxPageItems}" sort="external" requestURI="${requestUrl}"
                                               class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                               style="margin: 3em 0 1.5em;">
                                    <display:column title="<fieldset class='form-group'>
                                                            <input type='checkbox' id='checkAll' class='check-box-element'>
                                                            </fieldset>" class="center select-cell" headerClass="center select-cell">
                                        <fieldset>
                                            <input type="checkbox" name="checkList" value="${tableList.exerciseListenQuestionId}" class="check-box-element" id="checkbox_${tableList.exerciseListenQuestionId}"/>
                                        </fieldset>
                                    </display:column>
                                    <display:column property="question" titleKey="label.exercise.listen.question.list" sortable="true" sortName="question"></display:column>
                                    <display:column headerClass="col-actions" titleKey="label.action">
                                        <c:url var="editUrl" value="/admin-lisenquestion-edit.html">
                                            <c:param name="urlType" value="edit"/>
                                            <c:param name="pojo.exerciseListenQuestionId" value="${tableList.exerciseListenQuestionId}"/>
                                        </c:url>
                                        <a class="btn btn-sm btn-primary btn-edit" href="${editUrl}" data-toggle="tooltip" title="<fmt:message key='label.exercise.update' bundle='${lang}'/>"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                                    </display:column>
                                </display:table>
                            </fmt:bundle>
                        </div>
                        <c:if test="${not empty items.exerciseListenId}">
                            <input type="hidden" name="exerciseListenId" value="${items.exerciseListenId}">
                        </c:if>
                        <input type="hidden" name="urlType" id="urlType" value="edit"/>
                        <input type="hidden" name="crudaction" id="crudaction"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            $('#crudaction').val('delete');
            var data={};
            var ids=$('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            data['ids']=ids;
            deleteNewData(data);
        })
    }
    function deleteNewData(data) {
        $.ajax({
            url:'${urlEditListen}',
            type:'DELETE',
            contentType:'application/json',
            data:JSON.stringify(data),
            success:function (result) {
                window.location.href="${urlListListen}";
            },
            error:function (error) {
                console.log(error);
            }
        })
    }
    $('#btnSearch').click(function () {
        $('#urlType').val('list');
        $('#formUrl').submit();

    })
</script>
</body>
</html>

