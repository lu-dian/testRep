///笔记菜单
function showNoteMenu() {
	$("#note_ul").on("click",".btn_slide_down",function(){
		//隐藏笔记下拉框
		$("#note_ul div").hide();
		//alert("下拉框绑定");
		//显示点击菜单 //获取 父元素div
		var note_menu = $(this).parents("li").find("div");
		note_menu.slideDown(500);
		
		$("#note_ul a").removeClass("checked");
		$(this).parent().addClass("checked");
		//阻止冒泡事件
		return false;
	});
	//点body隐藏菜单
	$("body").click(function() {
		$("#note_ul div").hide();
	});
};

//加载笔记本
function loadBookNotes() {
	//设置选中效果
	$("#book_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//alert("动态绑定");
	//获取bookId
	var bookId = $(this).data("bookId");
	//alert(bookId);
	//清除原列表信息
	//$("#note_ul").empty();
	$.ajax({
		url : basePath + "/note/loadnotes.do",
		type : "post",
		data : {
			"bookId" : bookId
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				//alert("开始获取笔记");
				//获取笔记信息
				var notes = result.data;
				//清除原列表信息
				$("#note_ul").empty();
				//$("#noet_ul li").remove();
				if (notes != null) {
					//遍历数据，添加li
					for (var i = 0; i < notes.length; i++) {
						//获取笔记id
						var noteId = notes[i].cn_note_id;
						//获取笔记
						var noteTitle = notes[i].cn_note_title;
						//生成笔记li
						createNoteLi(noteId, noteTitle);
					}
				}
			}
		},
		error : function() {
			alert("获取笔记失败");
		}
	});
};

function createNoteLi(noteId, noteTitle) {
	var sli = "";
	sli += '<li class="online">';
	sli += '<a>';
	sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli += noteTitle;
	sli += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	sli += '</a>';
	sli += '<div class="note_menu" tabindex="-1">';
	sli += '<dl>';
	sli += '<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli += '<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli += '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli += '</dl>';
	sli += '</div>';
	sli += '</li>';

	//将sli字符串转换成jQuery的对象的li元素
	var $li = $(sli);
	//将bookId值与jQuer对象绑定
	$li.data("noteId", noteId);
	//箱li元素添加到笔记本ul元素列表中
	$("#note_ul").append($li);
};



//显示笔记信息
function loadNote() {
	um.setContent("");
	//检测是否绑定
	//alert("开始获取笔记的详细信息");
	//设置选中效果
	$("#note_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//获取笔记参数
	var noteId = $(this).data("noteId");
	//alert(noteId);
	//ajax请求处理
	$.ajax({
		url : basePath + "/note/loadnote.do",
		type : "post",
		data : {
			"noteId" : noteId 
		},
		dataType : "json",
		success : function(result) {
			//判断是否查询到笔记
			if(result.status == 0){
				//获取笔记标题
				var noteTitle = result.data.cn_note_title;
				//获取笔记内容
				var noteBody = result.data.cn_note_body;
				//设置笔记标题
				$("#input_note_title").val(noteTitle);
				//设置笔记内容
				um.setContent(noteBody);
				//获取新的内容用于保存								
			}
		},
		error : function() {
			alert("笔记信息加载失败");
		}
	});
	
};

//保存笔记信息
function updataNote() {
	//测试绑定
	//alert("保存按钮已绑定");
	//获取参数 //获取选定li对象
	var $li = $("#note_ul a.checked").parent();
	//获取笔记id
	//var noteId = $("#note_ul li").data("noteId");
	var noteId = $li.data("noteId");
	//获取笔记标题
	var noteTitle = $("#input_note_title").val().trim();
	//获取笔记内容
	var noteBody = um.getContent();
	//ajax请求
	$.ajax({
		url : basePath + "/note/update.do",
		type : "post",
		data : {
			"noteId" : noteId,
			"noteTitle" : noteTitle,
			"noteBody" : noteBody
		},
		dataType : "json",
		success : function(result) {
			//检查是否获取了新的名字
			//alert("进行更新");
			if(result.status == 0){
				var str = "";
				str += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
				str += noteTitle;
				str += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
				//将字符串更新到li的a元素里面
				$li.find("a").html(str);
				//提示修改成功
				alert(result.msg);
			}
		},
		error : function() {
			alert("保存笔记失败");
		}
	});
};


//创建笔记
function createNote(){
	//alert("笔记绑定生效");
	//获取参数
	var noteName = $("#input_note").val().trim();
	var $li = $("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	//alert(bookId);
	var userId = getCookie("userId");
	//alert(userId);
	//数据格式检查
	var ok = true;
	if(noteName == ""){
		ok = false;
		$("#title_span").html("标题不能为空");
	}
	if(userId == null){
		//检查是否失效
		ok = false;
		window.location.href = "login.html";
	}
	if(ok){
		//发送ajax请求
		$.ajax({
			url : basePath + "/note/add.do",
			type : "post",
			data : {
				"userId" : userId,
				"bookId" : bookId,
				"noteName" : noteName
			},
			dataType : "json",
			success : function(result) {
				alert(result.msg);
				if(result.status == 0){
					var noteId = result.data.cn_note_id;
					var title = result.data.cn_note_title;
					//生成笔记li
					createNoteLi(noteId, title);
					alert(result.msg);
				}
			},
			error : function(){
				alert("创建笔记失败");
			}
		});
	}
};

function shareNote(){
	//alert("分享按钮绑定成功");
	//请求参数
	//获取参数 //获取选定li对象
	//var $li = $("#note_ul a.checked").parent();
	//父类元素里找li
	var $li=$(this).parents("li");
	//获取笔记id
	//var noteId = $("#note_ul li").data("noteId");
	var noteId = $li.data("noteId");
	//alert(noteId);
	//var noteTitle = $li.find("span").html();
	//alert(noteTitle);
	//发送ajax请求
	//发送ajax请求
	$.ajax({
		url : basePath + "/share/add.do",
		type : "post",
		data : {
			"noteId" : noteId,
		},
		dataType : "json",
		success : function(result) {
			//获取文本信息
			var noteTitle = $li.text();
			
			//跟新名称，添加分享图标
			var str = "";
			str += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
			str += noteTitle;
			str += '<i class="fa fa-sitemap"></i>';
			str += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
			//将这些添加到 li的a元素里面---替换
			$li.find("a").html(str);
			alert(result.msg);
		},
		error : function(){
			alert("笔记分享失败");
		}
	});
};	
//阐述笔记
function deleteNote() {
	
	//请求参数
	//获取笔记id
	var $li = $("#note_ul a.checked").parent();
	var noteId = $li.data("noteId");
	//alert(noteId);
	//发送请求
	$.ajax({
		url : basePath + "/note/delete.do",
		type : "post",
		data : {
			"noteId" : noteId,
		},
		dataType : "json",
		success : function(result) {
			//刷新列表
			if(result.status == 0){
				$li.find("a").html("");
				alert(result.msg);
			}else{
				alert(result.msg);
			}
		},
		error : function(){
			alert("笔记删除失败");
		}		
	});
};
