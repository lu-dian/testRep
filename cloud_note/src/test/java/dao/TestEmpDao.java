package dao;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hy.cloud_note.dao.EmpDao;
import cn.hy.cloud_note.entity.Emp;

public class TestEmpDao {
	
	private EmpDao dao;
	@Before
	public void init() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		dao = ac.getBean("empDao",EmpDao.class);
	}
	@Test
	public void test1() {
		Emp emp = new Emp();
		emp.setName("jerry");
		emp.setAge(10);
		dao.save(emp);
		System.out.println(emp);
	}
}	
