$(document).ready(function () {
checkboxAll('checkAll');
    enableOrDisableDeleteAll();
    autoCheckCheckboxAll('checkAll');
})
function checkboxAll(id) {
$('#'+id).on('change',function () {
if((this).checked){
    $(this).closest('table').find('input[type=checkbox]').prop('checked',true);
}else {
    $(this).closest('table').find('input[type=checkbox]').prop('checked',false);
}
})
}
function enableOrDisableDeleteAll() {
$('input[type=checkbox]').on('change',function () {
if($(this).attr('id')=='checkAll'&&this.checked==false){
$(this).closest('table').find('input[type=checkbox]').prop('checked',false);
}
if($(this).closest('table').find('input[type=checkbox]:checked').length>0){
    $('#deleteAll').prop('disabled',false);
}else {
    $('#deleteAll').prop('disabled',true);
}
})
}
function autoCheckCheckboxAll(id) {
var totalBox=$('#'+id).closest('table').find('tbody input[ type=checkbox]').length;
var a=$('#'+id).closest('table');
$('tbody input[type=checkbox]').on('change',function () {
var totalChecked=$('#'+id).closest('table').find(' tbody input[type=checkbox]:checked').length;
if(totalChecked==totalBox){
    $('#'+id).prop('checked',true);
}else {
    $('#'+id).prop('checked',false);
}
})
}