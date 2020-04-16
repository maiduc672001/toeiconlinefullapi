<%@ include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/admin-import-validate-user.html" var="validateExcel">
</c:url>
<c:url value="/admin-import-user.html" var="importUser">
</c:url>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#"><fmt:message key="label.home" bundle="${lang}"/></a>
                </li>
                <li><fmt:message key="label.user.list" bundle="${lang}"/></li>
                <li class="active"><fmt:message key="label.user.import.excel" bundle="${lang}"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">

                    <form action="${validateExcel}" method="post" enctype="multipart/form-data" id="formImport">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="col-sm-12">
                                    <input type="file" name="file"/>
                                    <br/>
                                    <button type="button" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" id="validateData">
                                        <fmt:message key="label.file.validate.import" bundle="${lang}"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <c:if test="${not empty items.userImportDTOS}">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="table-responsive">
                                        <fmt:bundle basename="ResourcesBundle">
                                            <display:table name="items.userImportDTOS" cellspacing="0" cellpadding="0" requestURI="${requestUrl}"
                                                           partialList="true" sort="external" size="${items.totalItems}" id="tableList" excludedParams="checkList"
                                                           pagesize="${items.maxPageItems}" export="false"
                                                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                                           style="margin: 3em 0 1.5em;">
                                                <display:column headerClass="text-left" property="userName" titleKey="label.username"/>
                                                <display:column headerClass="text-left" property="password" titleKey="label.password"/>
                                                <display:column headerClass="text-left" property="fullName" titleKey="label.fullname"/>
                                                <display:column headerClass="text-left" property="roleName" titleKey="label.user.fullname"/>
                                                <display:column headerClass="text-left" property="message" titleKey="label.import.error"/>
                                            </display:table>
                                        </fmt:bundle>
                                    </div>
                                </div>
                            </div>
                            <button type="button" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" id="importData">
                                <fmt:message key="label.user.import" bundle="${lang}"/>
                            </button>
                        </c:if>
                        <input type="hidden" id="urlType" name="urlType"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#validateData').click(function () {
            $('#urlType').val('import');
            $('#formImport').submit();
        })
    })
$('#importData').click(function () {
$('#urlType').val('save_data');
    $('#formImport').prop('enctype',false);
$('#formImport').attr('action','${importUser}');
$('#formImport').submit();
})
</script>
</body>
</html>