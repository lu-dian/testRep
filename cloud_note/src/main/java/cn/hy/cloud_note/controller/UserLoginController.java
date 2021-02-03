package cn.hy.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hy.cloud_note.entity.User;
import cn.hy.cloud_note.service.UserService;
import cn.hy.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user") //匹配请求路径
public class UserLoginController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login.do") //匹配具体请求
	@ResponseBody //调用jackson
	public NoteResult<User> execute(String name ,String password){
		System.out.println(name+" : "+password);
		//处理登录
		NoteResult<User> result = userService.checkLogin(name, password);
		//System.out.println(result);
		return result;
	}
}
