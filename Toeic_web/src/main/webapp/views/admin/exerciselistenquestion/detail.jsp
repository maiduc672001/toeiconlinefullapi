<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>

<c:url var="ajaxUrl" value="/ajax-exercise-listen-question-edit.html"/>
<c:url var="formEdit" value="/admin-lisenquestion-edit.html"/>
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
                    <form  id="formEdit" method="post" action="${formEdit}" enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message
                                    key="label.guideline.title" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="text" name="question" id="question" value="${item.pojo.question}"/>
                            </div>
                        </div>
                        < <br/>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.grammarguideline.upload.image" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="file" name="file" id="uploadImage"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.grammarguideline.upload.image.view" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <c:if test="${not empty item.pojo.image}">
                                    <c:set var="image" value="/repository/${item.pojo.image}"/>
                                </c:if>
                                <img src="${image}" id="viewImage" width="150px" height="150px"/>
                            </div>
                        </div>
                        < <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.grammarguideline.upload.image" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="file" name="file" id="uploadAudio"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message
                                    key="label.exercise.option" bundle="${lang}"/></label>
                        </div>
                    <br/>
                        <br/>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty item.pojo.option1}">
                                    <c:set var="option1" value="${item.pojo.option1}"/>
                                </c:if>
                                <input name="option1" id="option1" value="${option1}">
                            </div>
                            <br/>
                            <br/>
                            <div class="col-sm-12">
                                <c:if test="${not empty item.pojo.option2}">
                                    <c:set var="option2" value="${item.pojo.option2}"/>
                                </c:if>
                                <input name="option2" id="option2" value="${option2}">
                            </div>
                            <br/>
                            <br/>
                            <div class="col-sm-12">
                                <c:if test="${not empty item.pojo.option3}">
                                    <c:set var="option3" value="${item.pojo.option3}"/>
                                </c:if>
                                <input name="option3" id="option3" value="${option3}">
                            </div>
                            <br/>
                            <br/>
                            <div class="col-sm-12">
                                <c:if test="${not empty item.pojo.option4}">
                                    <c:set var="option4" value="${item.pojo.option4}"/>
                                </c:if>
                                <input name="option4" id="option4" value="${option4}">
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.exercise.question.correct" bundle="${lang}"/></label>
                            <div class="col-sm-12">
                                <c:if test="${not empty item.pojo.correctAnswer}">
                                    <c:set var="correctAnswer" value="${item.pojo.correctAnswer}"/>
                                </c:if>
                                <input name="correctAnswer" id="correctAnswer" value="${correctAnswer}">
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <c:if test="${not empty item.pojo.exerciseListenQuestionId}">
                            <input type="hidden" name="exerciseListenQuestionId" value="${item.pojo.exerciseListenQuestionId}"/>
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
    var exerciseListenQuestionId = '';
    <c:if test="${not empty item.pojo.exerciseListenQuestionId}">
    exerciseListenQuestionId = ${item.pojo.exerciseListenQuestionId}
        </c:if>
        $(document).ready(function () {
            validateData();
            $('#uploadImage').on('change', function () {
                readUrl(this, "viewImage");
            })
        })

    function validateData() {
        $('#formEdit').validate({
            rules: [],
            messages: [],
        });
        $('#question').rules("add",{
            required:true,
            messages: {
                required:'<fmt:message bundle="${lang}" key="label.empty"/>',
            }
        })
        $('#option1').rules("add",{
            required:true,
            messages: {
                required:'<fmt:message bundle="${lang}" key="label.empty"/>',
            }
        })
        $('#option2').rules("add",{
            required:true,
            messages: {
                required:'<fmt:message bundle="${lang}" key="label.empty"/>',
            }
        })
        $('#option3').rules("add",{
            required:true,
            messages: {
                required:'<fmt:message bundle="${lang}" key="label.empty"/>',
            }
        })
        $('#option4').rules("add",{
            required:true,
            messages: {
                required:'<fmt:message bundle="${lang}" key="label.empty"/>',
            }
        })
        $('#correctAnswer').rules("add",{
            required:true,
            messages: {
                required:'<fmt:message bundle="${lang}" key="label.empty"/>',
            }
        })
        if(exerciseListenQuestionId==''){
            $('#uploadAudio').rules("add",{
                required:true,
                messages: {
                    required:'<fmt:message bundle="${lang}" key="label.empty"/>',
                }
            })
            $('#uploadImage').rules("add",{
                required:true,
                messages: {
                    required:'<fmt:message bundle="${lang}" key="label.empty"/>',
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
</script>
</body>
</html>

