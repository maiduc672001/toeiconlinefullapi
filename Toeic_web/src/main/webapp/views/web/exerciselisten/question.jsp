<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="label.exercise.paractice" bundle="${lang}"/></title>
</head>
<body>
<form method="get" action="" id="formUrl">
    <div class="row">
        <div class="span12">
            <ul class="thumbnails">
                <li class="span12">
                    <div class="thumbnail" id="result">
                        <br/>
                       <c:forEach items="${items.listResult}" var="item">
                           <p>
                               <h>${item.question}</h>
                           </p>
                           <c:if test="${item.image!=null}">
                               <P>
                                   <img src="<c:url value="/repository/${item.image}"/>" width="300px" height="150px">
                               </P>
                           </c:if>
                           <c:if test="${item.audio!=null}">
                               <P>
                                   <audio controls>
                                       <source src="<c:url value="/repository/${item.audio}"/>" type="audio/mpeg">
                                   </audio>
                               </P>
                           </c:if>
                           <p>
                               <input type="radio" name="answerUser" value="A">
                               ${item.option1}
                           </p>
                           <p>
                               <input type="radio" name="answerUser" value="B">
                                   ${item.option2}
                           </p>
                           <p>
                               <input type="radio" name="answerUser" value="C">
                                   ${item.option3}
                           </p>
                           <p>
                               <input type="radio" name="answerUser" value="D">
                                   ${item.option4}
                           </p>
                           <input type="hidden" name="exerciseListenQuestionId" id="exerciseListenQuestionId" value="${item.exerciseListenQuestionId}">
                       </c:forEach>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <!--Pagination-->
    <div class="row">
        <div class="span12">
            <ul id="pagination-demo" class="pagination-sm"></ul>
        </div>
        <input type="button" class="btn btn-info" value="Xem đáp án" id="btnConfirm"/>
        <input type="button" class="btn btn-info" value="Làm lại" id="btnAgain"/>
        <input type="hidden" name="page" id="page" value="${items.page}"/>
    </div>
</form>
<script>
    var exerciseListenQuestionId=$('#exerciseListenQuestionId').val();
    var exerciseListenId=${items.exerciseListenId};
$(document).ready(function () {
$('#btnConfirm').click(function () {
if($('input[name="answerUser"]:checked').length>0){
    $('#formUrl').submit();
}else {
    alert("Bạn chưa chọn đáp án");
}
})
    $('#btnAgain').click(function () {
window.location="/exercise-listen.question.html?page=0"+"&exerciseListenId="+exerciseListenId+"";
    })
})
    var startPage=${items.page};
    var totalPages=${items.totalPages};
$('#pagination-demo').twbsPagination({
    totalPages:totalPages,
    visiblePages:0,
    startPage:startPage,
    onPageClick: function (event,page) {
if(page!=startPage){
    $('#page').val(page);
    window.location="/exercise-listen.question.html?page="+page+"&exerciseListenQuestionId="+exerciseListenId+"";
}
    }
})
    $('#formUrl').submit(function (e) {
e.preventDefault();
$.ajax({
    type:'POST',
    url:'/ajax-exercise-listen-question.html',
    data:$(this).serialize(),
        dataType:'html',
    success:function (result) {
$('#result').html(result);
    },
    error:function (res) {
        console.log(res)

    }

})
    })
</script>
</body>
</html>
