//按回车，加载搜索结果的第一页
function sureSearchShare(event) {
	var code = event.keyCode;
	if (code == 13) {
		// 清空列表
		$("#search_ul").empty();
		// alert("回车事件绑定成功");
		var keyword = $("#search_note").val().trim();
		// alert(searchText);
		//加载获取参数函数
		page = 1;
		loadPageShare(keyword,page);
	}
};
//安更多按钮，加载搜索结果下一页
function moreSearch() {
	//获取参数
	var keyword = $("#search_note").val().trim();
	page = page + 1;
	//发送ajax请求加载列表
	loadPageShare(keyword,page);
};

//发送 获取跟多信息 请求
function loadPageShare(keyword, page) {
	// 发送请求
	$.ajax({
		url : basePath + "/share/search.do",
		type : "post",
		data : {
			"keyword" : keyword,
			"page" : page
		},
		dataType : "json",
		success : function(result) {
			// 获取数据
			var shares = result.data;
			// 遍历数据
			for (var i = 0; i < shares.length; i++) {
				// 记录shareid//绑定到li
				var shareId = shares[i].cn_share_id;
				// 获取shareTitle
				var shareTitle = shares[i].cn_share_title;
				// 获取li元素对象
				var sli = "";
				sli += '<li class="online">';
				sli += '<a>';
				sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
				sli += shareTitle;
				sli += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
				sli += '</a>';
				sli += '</li>';
				// 将sli字符串转换成jQuery的对象的li元素
				var $li = $(sli);
				// 将shareId值与jQuer对象绑定
				$li.data("shareId", shareId);
				// 箱li元素添加到笔记本ul元素列表中
				$("#search_ul").append($li);

				// 切换显示区域
				$("#pc_part_2").hide();// 隐藏
				$("#pc_part_6").show();// 显示
			}
		},
		error : function() {
			alert("查询失败");
		}
	});
}
