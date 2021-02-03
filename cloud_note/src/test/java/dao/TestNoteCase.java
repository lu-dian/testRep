package dao;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hy.cloud_note.dao.NoteDao;
import cn.hy.cloud_note.entity.Note;
import cn.hy.cloud_note.util.NoteUtil;

public class TestNoteCase {
	
	private NoteDao dao;
	@Before
	public void init() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		dao = ac.getBean("noteDao",NoteDao.class);
	}
	@Test
	public void test1() {
		String bookId = "1d46f5db-f569-4c05-bdba-75106108fcba";
		List<Map> list = dao.findByBookId(bookId);
		
		for(Map map : list) {
			System.out.println(map.get("cn_note_title"));
		}
	}	
	@Test
	public void test2() {
		String noteId = "046b0110-67f9-48c3-bef3-b0b23bda9d4e";
		Note note = dao.findByNoteId(noteId);
		System.out.println(note.getCn_note_title());
		System.out.println(note.getCn_note_last_modify_time());
	}
	
	@Test
	public void test3() {
		String noteId = "046b0110-67f9-48c3-bef3-b0b23bda9d4e";
		Note note = dao.findByNoteId(noteId);
		note.setCn_note_title("ºÙºÙºÙ¶îºÚ");
		int i = dao.updateNote(note);
		System.out.println(i);
	}
	
	@Test
	public void test4() {
		String noteId = NoteUtil.createId();
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title("ajax");
		int rows = dao.save(note);
		System.out.println(rows);
	}
}	
