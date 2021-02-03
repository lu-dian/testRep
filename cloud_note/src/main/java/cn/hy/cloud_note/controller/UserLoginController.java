package cn.hy.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hy.cloud_note.entity.User;
import cn.hy.cloud_note.service.UserService;
import cn.hy.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user") //ƥ������·��
public class UserLoginController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login.do") //ƥ���������
	@ResponseBody //����jackson
	public NoteResult<User> execute(String name ,String password){
		System.out.println(name+" : "+password);
		//�����¼
		NoteResult<User> result = userService.checkLogin(name, password);
		//System.out.println(result);
		return result;
	}
}
