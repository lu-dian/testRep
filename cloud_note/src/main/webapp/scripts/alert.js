//弹出新建笔记本对话框
function alertAddBookWindow() {
	$("#can").load("alert/alert_notebook.html");
	//关闭
	//$("#can").html("");
	//显示背景色中间层
	$(".opacity_bg").show();
	//关闭
	//$(".opacity_bg").hide();
};
//关闭弹出框
function closeAlertWindow() {
	//清空div内容
	$("#can").html('');
	
	//隐藏背景色层
	$(".opacity_bg").hide();
}

function alertAddNoteWindow() {
	//判断是否有笔记本被选中
	var $li = $("#book_ul a.checked").parent();
	if($li.length == 0){
		alert("请选择笔记本")
	}else{
		$("#can").load("alert/alert_note.html");
		$(".opacity_bg").show();
	}
};
///弹出删除笔记框
function alertDeleteNoteWindow() {
	//alert("删除按钮绑定");
	$("#can").load("alert/alert_delete_note.html");
	//显示背景色中间层
	$(".opacity_bg").show();
}