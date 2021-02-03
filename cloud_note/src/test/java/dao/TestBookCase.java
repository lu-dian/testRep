package dao;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hy.cloud_note.dao.BookDao;
import cn.hy.cloud_note.entity.Book;
import cn.hy.cloud_note.util.NoteUtil;

public class TestBookCase {
	
	private BookDao dao;
	@Before
	public void init() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		dao = ac.getBean("bookDao",BookDao.class);
	}
	@Test
	public void test1() {
		String userId = "48595f52-b22c-4485-9244-f4004255b972";
		List<Book> list = dao.findByUserId(userId);
		for(Book book : list) {
			System.out.println(book);
		}
	}	
	
	//Ìí¼Ó±Ê¼Ç±¾
	@Test
	public void test2() {
		String userId = "48595f52-b22c-4485-9244-f4004255b972";
		String bookId = NoteUtil.createId();
		Book book = new Book();
		book.setCn_user_id(userId);
		book.setCn_notebook_id(bookId);
		book.setCn_notebook_name("hahahahhahhha");
		book.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		int rows = dao.save(book);
		System.out.println(rows);
	}
}	
