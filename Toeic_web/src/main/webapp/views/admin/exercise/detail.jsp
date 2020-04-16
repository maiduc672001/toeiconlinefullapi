<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url value="/admin-exercise-listen-edit.html" var="addExe">
</c:url>
<c:url var="listExe" value="/admin-exercise-listen-list.html"></c:url>
<html>
<head>
    <title><fmt:message key="label.exercise.management" bundle="${lang}"/></title>
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
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form  id="formEdit">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.exercise.listen.add" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="text"  id="name">
                                <br/>
                                <input type="submit" id="btnAdd" class="btn btn-white btn-warning btn-bold" value="<fmt:message key="label.done" bundle="${lang}"/>"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
    validateExeListen();


})
    function validateExeListen(){
        $('#formEdit').validate({
            rules:[],
            messages:[],
        });
        $('#name').rules("add",{
            required: true,
            messages:{
                required:'<fmt:message bundle="${lang}" key="label.empty"/>'
            }
        })
    }
$('#formEdit').submit(function (e) {
    e.preventDefault();
    var $form=$(this);
    if(!$form.valid()) return false;
    var data={};
    data["name"]=$('#name').val();
    addNewExe(data);
})
function addNewExe(data) {
    $.ajax({
        url:'${addExe}',
        type:'POST',
        contentType:'application/json',
        data:JSON.stringify(data),
        dataType:'json',
        success:function (result) {
            window.location.href='${listExe}?urlType=list&&crudaction=save';
        },
        error:function (error) {
//window.location.href='${listExe}?urlType=list&&crudaction=error'
        }
    })
}
</script>
</body>
</html>