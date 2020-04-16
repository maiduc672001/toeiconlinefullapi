<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>

<c:url var="ajaxUrl" value="/ajax-listenguideline-edit.html"/>
<html>
<head>
    <title><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/></title>
    <style>
        .error {
            color: red;
        }
    </style>
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
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form  id="formEdit">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message
                                    key="label.guideline.title" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="text" name="title" id="title" value="${item.pojo.title}"/>
                            </div>
                        </div>
                        < <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message
                                    key="label.guideline.content" bundle="${lang}"/></label>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty item.pojo.content}">
                                    <c:set var="content" value="${item.pojo.content}"/>
                                </c:if>
                                <textarea name="content" cols="80" rows="10"
                                          id="ListenGuidelineContent" value="${content}"></textarea>
                            </div>
                        </div>
                        <c:if test="${not empty item.pojo.listenGuidelineId}">
                            <input type="hidden" name="listenGuidelineId" value="${item.pojo.listenGuidelineId}"/>
                        </c:if>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="submit" class="btn btn-white btn-warning btn-bold" id="btnUpdate"
                                       value="<fmt:message key="label.done" bundle="${lang}"/>"/>
                            </div>
                        </div>
                        <c:if test="${not empty item.exerciseListenId}">
                            <input type="hidden" name="exerciseListenId" value="${item.exerciseListenId}">
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var listenGuidelineId = '';
    var editor="";
    <c:if test="${not empty item.pojo.listenGuidelineId}">
    listenGuidelineId = ${item.pojo.listenGuidelineId}
        </c:if>
        $(document).ready(function () {
           editor= CKEDITOR.replace('ListenGuidelineContent');
            validateData();
            $('#uploadImage').on('change', function () {
                readUrl(this, "viewImage");
            })
        })

    function validateData() {
        $('#formEdit').validate({
            ignore: [],
            rules: [],
            messages: [],
        });
        $('#title').rules("add", {
            required: true,
            messages: {
                required: '<fmt:message key="label.empty" bundle="${lang}"/>',
            }
        })
        $('#ListenGuidelineContent').rules("add", {
            required: function() {
                CKEDITOR.instances.ListenGuidelineContent.updateElement();
            },
            messages: {
                required: '<fmt:message key="label.empty" bundle="${lang}"/>',
            }
        });
        if (listenGuidelineId == '') {
            $('#uploadImage').rules("add", {
                required: true,
                messages: {
                    required: '<fmt:message key="label.empty" bundle="${lang}"/>',
                }
            })
        }
    }

    function readUrl(input, imageId) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' + imageId).attr("src", reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $('#formEdit').submit(function (e) {
        e.preventDefault();
        var $form=$(this);
        if(!$form.valid()) return false;
        var data={};
        var formData=$('#formEdit').serializeArray();
        $.each(formData,function (i,v) {
            data["" + v.name + ""] = v.value;
        });
        data["content"]=editor.getData();
        if (listenGuidelineId == '') {
            addNew(data);
        } else {
            updateNew(data);
        }
    })
    function addNew(data) {
$.ajax({
    url:'${ajaxUrl}',
    type:'POST',
    contentType: 'application/json',
    data:JSON.stringify(data),
    dataType:'json',
    success:function (result) {
console.log(result);
    },
    error:function (error) {
console.log(error);
    }
})
    }
    function updateNew(data) {
        $.ajax({
            url:'${ajaxUrl}',
            type:'PUT',
            contentType:'application/json',
            data:JSON.stringify(data),
            dataType:'json',
            success:function (result) {
                console.log(result);
                },
            error:function (error) {
                console.log(error);
            }
        })
    }
</script>
</body>
</html>
