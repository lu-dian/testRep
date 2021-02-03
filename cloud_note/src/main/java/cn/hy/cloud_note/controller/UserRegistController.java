package cn.hy.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hy.cloud_note.entity.User;
import cn.hy.cloud_note.service.UserService;
import cn.hy.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user") //∆•≈‰«Î«Û¬∑æ∂
public class UserRegistController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/add.do")
	@ResponseBody //jackson
	public NoteResult<Object> execute(String name ,String password ,String nick){
		NoteResult<Object> result = userService.addUser(name, password, nick);
		//System.out.println(result);
		return result;
	}
}
