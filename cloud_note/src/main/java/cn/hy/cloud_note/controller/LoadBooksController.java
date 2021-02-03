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
@RequestMapping("/book") //ƥ������·��
public class LoadBooksController {
	
	@Resource
	private BookService bookService;
	
	@RequestMapping("/loadBooks.do") //ƥ���������
	@ResponseBody //����jackson
	public NoteResult<List<Book>> execute(String userId){
		//System.out.println(userId);
		//�����¼
		NoteResult<List<Book>> result = bookService.loadUserBooks(userId);
		//System.out.println(result);
		return result;
	}
}
