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
		System.out.println("=========�û���Ϣ==========");
		System.out.println("���֣�"+user.getCn_user_name());
		System.out.println("�ǳƣ�"+user.getCn_user_desc());
		System.out.println("�ʼǱ�������"+user.getBooks().size());
		System.out.println("=========�ʼ��б�========");
		for(Book book : user.getBooks()) {
			System.out.println(book.getCn_notebook_name());
		}
		
	}
	@Test
	public void test1() {
		String userId = "48595f52-b22c-4485-9244-f4004255b972";
		User user = dao.findUserAndBooks1(userId);
		System.out.println("=========�û���Ϣ==========");
		System.out.println("���֣�"+user.getCn_user_name());
		System.out.println("�ǳƣ�"+user.getCn_user_desc());
		System.out.println("�ʼǱ�������"+user.getBooks().size());
		System.out.println("=========�ʼ��б�========");
		for(Book book : user.getBooks()) {
			System.out.println(book.getCn_notebook_name());
		}
		
	}
	//�������������
	//���浥���������
	@Test
	public void testOne1() {
		List<Book> list = dao.findBookAndUser();
		System.out.println(list.size());
		System.out.println("=========�ʼ���Ϣ==========");
		for(Book book :list) {
			System.out.println("�ʼ����֣�"+book.getCn_notebook_name());
			System.out.println("����ʱ�䣺"+book.getCn_notebook_createtime());
			if(book.getUser() != null) {
				System.out.println("###�û�����"+book.getUser().getCn_user_name());
				
			}
		}
		System.out.println("=========�ʼ��б�========");
		
	}
	
	@Test
	public void testOne2() {
		List<Book> list = dao.findBookAndUser1();
		System.out.println(list.size());
		System.out.println("=========�ʼ���Ϣ==========");
		for(Book book :list) {
			System.out.println("�ʼ����֣�"+book.getCn_notebook_name());
			System.out.println("����ʱ�䣺"+book.getCn_notebook_createtime());
			if(book.getUser() != null) {
				System.out.println("###�û�����"+book.getUser().getCn_user_name());
				
			}
		}
		System.out.println("=========�ʼ��б�========");
		
	}
}
