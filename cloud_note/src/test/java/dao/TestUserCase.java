package dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hy.cloud_note.dao.UserDao;
import cn.hy.cloud_note.entity.User;
import cn.hy.cloud_note.util.NoteUtil;

public class TestUserCase {
	
	private UserDao dao;
	@Before
	public void init() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		dao = ac.getBean("userDao",UserDao.class);
	}
	@Test
	public void test1() {
		String name = "tom";
		User user = dao.findByName(name);
		System.out.println(user);
	}
	
	@Test
	public void testSave() {
		User user = new User();
		user.setCn_user_id(NoteUtil.createId());
		user.setCn_user_name("tom");
		user.setCn_user_password("123456");
		user.setCn_user_desc("mao");
		dao.saveUser(user);
	}
}	
