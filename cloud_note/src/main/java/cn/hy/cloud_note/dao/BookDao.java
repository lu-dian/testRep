package cn.hy.cloud_note.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.hy.cloud_note.entity.Book;

@Repository("bookDao")
public interface BookDao {
	public List<Book> findByUserId(String userId);
	public int save(Book book);
}
