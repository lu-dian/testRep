package cn.hy.cloud_note.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hy.cloud_note.dao.BookDao;
import cn.hy.cloud_note.entity.Book;
import cn.hy.cloud_note.util.NoteResult;
import cn.hy.cloud_note.util.NoteUtil;

@Service("bookService")
public class BookServiceImpl implements BookService{

	@Resource
	private BookDao dao;
	
	public NoteResult<List<Book>> loadUserBooks(String userId) {
		NoteResult<List<Book>> result = new NoteResult<>();
		List<Book> list = dao.findByUserId(userId);
		//未查询到笔记本
		if(list.size() == 0) {
			result.setStatus(1);
			result.setMsg("没有笔记本");
			return result;
		}
		
		result.setStatus(0);
		result.setMsg("笔记本已找到");
		result.setData(list);
		return result;
	}

	@Override
	public NoteResult<Book> addBook(String userId ,String bookName) {
		NoteResult<Book> result = new NoteResult<>();
		Book book = new Book();
		book.setCn_user_id(userId);
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);
		book.setCn_notebook_name(bookName);
		book.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		int rows = dao.save(book);
		if(rows == 1) {
			result.setStatus(0);
			result.setMsg("创建笔记本成功");
			result.setData(book);
			return result;
		}
		result.setStatus(1);
		result.setMsg("创建笔记本失败");
		return result;
	}

}
