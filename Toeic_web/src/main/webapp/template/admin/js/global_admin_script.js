$(document).ready(function () {
checkBoxAll('checkAll');
});
function checkBoxAll(id) {
$('#'+id).on('change',function () {
if((this).checked()==true){
    $(this).closest('table').find('input[type=checkbox]').prop('checked',true);
}else {
    $(this).closest('table').find('input[type=checkbox]').prop('checked',false);
}
});
}