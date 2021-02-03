function userLogin() {
	//测试绑定事件
	//alert("绑定好了");
	//获取参数			
	var name = $("#count").val().trim();
	var password = $("#password").val().trim();
	$("#count_span").html("");
	$("#password_span").html("");
	alert(name + " : " + password)
	//格式检测
	var ok = true;
	if (name == "") {
		$("#count_span").html("用户名不能为空");
		ok = false;
	}
	if (password == "") {
		$("#password_span").html("密码不能为空");
		ok = false;
	}
	//发送请求
	if (ok) {//检测格式通过，
		//发送ajax请求
		$.ajax({
			url : basePath + "/user/login.do",
			type : "post",
			data : {
				"name" : name,
				"password" : password
			},
			dataType : "json",
			success : function(result) {
				//result是服务器返回的JSON结果
				//alert("开始验证");
				if (result.status == 0) {
					//登陆成功
					var userId = result.data.cn_user_id;
					//将用户信息保存到cookie中
					addCookie("userId", userId, 2);
					window.location.href = "edit.html";
				} else if (result.status == 1) {//用户名错误
					$("#count_span").html(result.msg);
				} else if (result.status == 2) {//密码错误
					$("#password_span").html(result.msg);
				}
			},
			error : function() {
				alert("登录失败!");
			}
		});
	}
};

function userRegister() {
	//测试绑定事件
	//alert("注册按钮绑定");
	//获取参数
	var name = $("#regist_username").val().trim();
	var nick = $("#nickname").val().trim();
	var password = $("#regist_password").val().trim();
	var final_password = $("#final_password").val().trim();
	//alert(name + nick + password + final_password);
	
	//检查数据格式
	var ok = true;
	//检查用户
	if(name == ""){
		ok = false;
		$("#warning_1 span").html("用户名不能为空");
		$("#warning_1").show();
	}
	//检测密码：1-非空 2-不能小于6位
	if(password == ""){
		ok = false;
		$("#warning_2 span").html("密码不能为空");
		$("#warning_2").show();
	}else if(password.length > 0 && password.length < 6){
		ok = false;
		$("#warning_2 span").html("密码不能小于6位");
		$("#warning_2").show();
	}
	//检测确认密码：1-非空 2-是否一致
	if(final_password != password){
		ok = false;
		$("#warning_3").show();
	}
	//数据校验通过
	if(ok == true){
		//alert("开始请求");
		$.ajax({
			url : basePath + "/user/add.do",
			type : "post",
			data : {
				"name" : name,
				"nick" : nick,
				"password" : password
			},
			dataType : "json",
			success : function(result) {
				//result是服务器返回的JSON结果
				if(result.status == 0){
					//注册成功
					//alert(result.msg);
					//返回到登录页面
					$("#back").click();
				}else if(result.status == 1){
					//注册失败
					$("#warning_1 span").html("用户已存在");
					$("#warning_1").show();
				}
			},
			error : function() {
				alert("注册失败");
			}
				
		});
	}
	
};

