package cn.hy.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hy.cloud_note.entity.Book;
import cn.hy.cloud_note.service.BookService;
import cn.hy.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
public class AddBookController {
	@Resource
	private BookService service;
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Book> execute(String userId ,String bookName){
		NoteResult<Book> result = service.addBook(userId, bookName);
		return result;
	}
}
