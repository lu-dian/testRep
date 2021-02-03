package service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hy.cloud_note.entity.User;
import cn.hy.cloud_note.service.UserService;
import cn.hy.cloud_note.util.NoteResult;

public class TestUserService {
	
	private UserService service;
	@Before
	public void init() {
		String[] conf = {"conf/spring-mybatis.xml",
						 "conf/spring-mvc.xml",
						 "conf/spring-transaction.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		service = ac.getBean("userService",UserService.class);
	}
	
	@Test //测试用例-1：预期结果：用户名不存在
	public void test1() {
		
		NoteResult<User> result = service.checkLogin("jerry", "1234");
		System.out.println(service.getClass().getName());
		//System.out.println(result);
	}
	@Test //测试用例-2：预期结果：密码错误
	public void test2() {
		NoteResult<User> result = service.checkLogin("demo", "demo");
		System.out.println(result);
	}
	@Test //测试用例-3：预期结果：登陆成功
	public void test3() {
		NoteResult<User> result = service.checkLogin("demo", "123456");
		System.out.println(result);
	}
	
	/**************************************************************/
	
	@Test //测试用例-4：预期结果：用户名已存在
	public void test4() {
		NoteResult<Object> result = service.addUser("demo","123456","demo1");
		System.out.println(result);
	}
	@Test //测试用例-5：预期结果：注册成功
	public void test5() {
		NoteResult<Object> result = service.addUser("jerry","123456","老鼠");
		System.out.println(result);
	}
}
