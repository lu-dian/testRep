
function loadUserBooks() {
	//测试绑定事件
	//alert("开始笔记本查询");
	//获取userId
	var userId = getCookie("userId");
	//判断是否获取到有效userId
	if(userId == null){
		alert("信息不存在或已经超时");
		//跳转到登录界面
		windows.location.href("login.html");
	}else{
		//发送ajax请求
		$.ajax({
			url : basePath + "/book/loadBooks.do",
			type : "post",
			data : {
				"userId" : userId
			},
			dataType : "json",
			success : function(result) {
				//判断笔记本查询是否成功
				if(result.status == 0){
					//获取笔记本集合
					var books = result.data;
					//遍历
					for(var i=0;i<books.length;i++){
						//获取笔记本id
						var bookId = books[i].cn_notebook_id;
						//获取笔记本名词
						var bookName = books[i].cn_notebook_name;
						//创建一个笔记本列表项的li元素
						createBookLi(bookId,bookName);
						
					}
				}
			},
			error : function() {
				alert("获取笔记本出错");
			}
		});
	}
	
};

//创建笔记本li元素
function createBookLi(bookId,bookName) {
	var sli = "";
	//拼接字符串
	sli += '<li class="online">';
	sli += '<a>';
	sli += '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
	sli += '</i>';
	sli += bookName;
	sli += '</a>';
	sli += '</li>';
	
	//将sli字符串转换成jQuery的对象的li元素
	var $li = $(sli);
	//将bookId值与jQuer对象绑定
	$li.data("bookId",bookId);
	//箱li元素添加到笔记本ul元素列表中
	$("#book_ul").append($li);
};

//动态获取笔记信息
/*
function loadBookNotes() {
	alert("动态绑定");
};
*/


function createUserBooks() {
	//测验是否绑定
	//alert("开始创建笔记");
	//获取参数
	var userId = getCookie("userId");
	//alert(userId);
	var bookName = $("#input_notebook").val().trim();
	//alert(bookName);
	//发送ajax请求
	//检测格式
	var ok = true;
	if(bookName == ""){
		ok = false;
		$("#book_name").html("标题不能为空");
	}
	if(userId == null){
		ok = false;
		window.location.href = "login.html";
	}
	if(ok){
		$.ajax({
			url : basePath + "/book/add.do",
			type : "post",
			data : {
				"userId" : userId,
				"bookName" : bookName
			},
			dataType : "json",
			success : function(result) {
				//alert("开始创建");
				//开刷新列表信息
				//将字符串更新到li的a元素里面
				var bookId = result.data.cn_notebook_id;
				var name = result.data.cn_notebook_name;
				createBookLi(bookId,name);
				alert(result.msg);
			
			},
			error : function(){
				alert("创建失败");
			}
		});
	}
};