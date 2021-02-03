package cn.hy.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hy.cloud_note.entity.Book;
import cn.hy.cloud_note.service.BookService;
import cn.hy.cloud_note.util.NoteResult;


@Controller
@RequestMapping("/book") //匹配请求路径
public class LoadBooksController {
	
	@Resource
	private BookService bookService;
	
	@RequestMapping("/loadBooks.do") //匹配具体请求
	@ResponseBody //调用jackson
	public NoteResult<List<Book>> execute(String userId){
		//System.out.println(userId);
		//处理登录
		NoteResult<List<Book>> result = bookService.loadUserBooks(userId);
		//System.out.println(result);
		return result;
	}
}
