package service;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.hy.cloud_note.entity.Book;
import cn.hy.cloud_note.service.BookService;
import cn.hy.cloud_note.util.NoteResult;
import cn.hy.cloud_note.util.NoteUtil;
import test.TestBase;

public class TestBookService extends TestBase{
	private BookService service;
	@Before
	public void init() {
		service = super.getContext().getBean("bookService",BookService.class);
	}
	
	@Test //测试用例-1：预期结果：笔记本为空
	public void test1() {
		String userId = "0a74930cc0d34652b735a43154b68196";
		NoteResult<List<Book>> result = service.loadUserBooks(userId);
		System.out.println(result);
	}
	@Test //测试用例-2：预期结果：找到所有笔记本
	public void test2() {
		String userId = "48595f52-b22c-4485-9244-f4004255b972";
		NoteResult<List<Book>> result = service.loadUserBooks(userId);
		System.out.println(result);
		for(Book book : result.getData()) {
			System.out.println(book.getCn_notebook_name());
		}
	}
	@Test
	public void test3() {
		String userId = "48595f52-b22c-4485-9244-f4004255b972";
		String bookName = "hahahahhahhha23333333";
		NoteResult<Book> result = service.addBook(userId, bookName);
		System.out.println(result);
		
	}

}
