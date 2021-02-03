package dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import cn.hy.cloud_note.dao.RelationDao;
import cn.hy.cloud_note.entity.Book;
import cn.hy.cloud_note.entity.User;
import test.TestBase;

public class TestRelationDao extends TestBase{
	
	private RelationDao dao;
	
	@Before
	public void init() {
		ApplicationContext ac = getContext();
		dao = ac.getBean("relationDao",RelationDao.class);
	}
	
	@Test
	public void test() {
		String userId = "48595f52-b22c-4485-9244-f4004255b972";
		User user = dao.findUserAndBooks(userId);
		System.out.println("=========用户信息==========");
		System.out.println("名字："+user.getCn_user_name());
		System.out.println("昵称："+user.getCn_user_desc());
		System.out.println("笔记本数量："+user.getBooks().size());
		System.out.println("=========笔记列表========");
		for(Book book : user.getBooks()) {
			System.out.println(book.getCn_notebook_name());
		}
		
	}
	@Test
	public void test1() {
		String userId = "48595f52-b22c-4485-9244-f4004255b972";
		User user = dao.findUserAndBooks1(userId);
		System.out.println("=========用户信息==========");
		System.out.println("名字："+user.getCn_user_name());
		System.out.println("昵称："+user.getCn_user_desc());
		System.out.println("笔记本数量："+user.getBooks().size());
		System.out.println("=========笔记列表========");
		for(Book book : user.getBooks()) {
			System.out.println(book.getCn_notebook_name());
		}
		
	}
	//上面多个对象关联
	//下面单个对象关联
	@Test
	public void testOne1() {
		List<Book> list = dao.findBookAndUser();
		System.out.println(list.size());
		System.out.println("=========笔记信息==========");
		for(Book book :list) {
			System.out.println("笔记名字："+book.getCn_notebook_name());
			System.out.println("创建时间："+book.getCn_notebook_createtime());
			if(book.getUser() != null) {
				System.out.println("###用户名："+book.getUser().getCn_user_name());
				
			}
		}
		System.out.println("=========笔记列表========");
		
	}
	
	@Test
	public void testOne2() {
		List<Book> list = dao.findBookAndUser1();
		System.out.println(list.size());
		System.out.println("=========笔记信息==========");
		for(Book book :list) {
			System.out.println("笔记名字："+book.getCn_notebook_name());
			System.out.println("创建时间："+book.getCn_notebook_createtime());
			if(book.getUser() != null) {
				System.out.println("###用户名："+book.getUser().getCn_user_name());
				
			}
		}
		System.out.println("=========笔记列表========");
		
	}
}
