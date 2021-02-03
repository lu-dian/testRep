package cn.hy.cloud_note.service;


import java.util.List;

import cn.hy.cloud_note.entity.Book;
import cn.hy.cloud_note.util.NoteResult;



public interface BookService {
	public NoteResult<List<Book>> loadUserBooks(String userId);
	public NoteResult<Book> addBook(String userId ,String bookName);
}
