package cn.hy.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hy.cloud_note.dao.UserDao;
import cn.hy.cloud_note.entity.User;
import cn.hy.cloud_note.util.NoteResult;
import cn.hy.cloud_note.util.NoteUtil;

//扫描到spring容器
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	public NoteResult<User> checkLogin(String name, String password) {
		//接收结果数据
		NoteResult<User> result = new NoteResult<>();
		
		//按参数name查询数据库
		User user = userDao.findByName(name);
		//检测用户名
		if(user == null) {
			result.setStatus(1);
			result.setMsg("用户不存在");
			return result;
		}
		//检测密码
		//解密
		String md5Password = NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5Password)) {
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
		}
		//用户名和密码都正确
		result.setStatus(0);
		result.setMsg("登陆成功");
		result.setData(user);
		return result;
	}

	public NoteResult<Object> addUser(String name,String password,String nick) {
		NoteResult<Object> result = new NoteResult<>();
		//检查用户
		User hasUser = userDao.findByName(name);
		if(hasUser != null) {
			//账号已经存在
			result.setStatus(1);
			result.setMsg("账号已存在");
			return result;
		}
		//添加用户
		User user = new User();
		//设置用户名
		user.setCn_user_name(name);
		//设置密码
		user.setCn_user_password(NoteUtil.md5(password));
		//设置用户ID
		user.setCn_user_id(NoteUtil.createId());
		//设置用户昵称
		user.setCn_user_desc(nick);
		userDao.saveUser(user);
		//构建返回结果
		result.setStatus(0);
		result.setMsg("注册成功");
		return result;
	}
}
