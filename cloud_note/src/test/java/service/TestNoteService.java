package service;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.hy.cloud_note.entity.Note;
import cn.hy.cloud_note.service.NoteService;
import cn.hy.cloud_note.util.NoteResult;
import test.TestBase;

public class TestNoteService extends TestBase{
	private NoteService service;
	@Before
	public void init() {
		service = super.getContext().getBean("noteService",NoteService.class);
	}
	
	@Test //测试用例-1：预期结果：笔记本为空
	public void test1() {
		String bookId = "1d46f5db-f569-4c05-bdba-75106108fcba";
		String statusId = "1";
		NoteResult<List<Map>> result = service.loadBookNotes(bookId);
		System.out.println(result);
	}
	@Test //测试用例-2：预期结果：找到所有笔记本
	public void test2() {
		String bookId = "1db556b9-d1dc-4ed9-8274-45cf0afbe859";
		NoteResult<List<Map>> result = service.loadBookNotes(bookId);
		System.out.println(result);
		for(Map map : result.getData()) {	
			System.out.println(map.get("cn_note_title"));
		}
	}
	
	@Test
	public void test3() {
		//String noteId = "046b0110-67f9-48c3-bef3-b0b23bda9d4e";
		String noteId = "4dcb2b359b0e424ebee02bb5537c48dc";
		NoteResult<Note> result = service.loadNote(noteId);
		System.out.println(result);
		System.out.println(result.getData());
		System.out.println(result.getData().getCn_note_title());
	}
	
	@Test //笔记修改成功
	public void test4() {
		String noteId = "046b0110-67f9-48c3-bef3-b0b23bda9d4e";
		String noteTitle = "java笔记";
		String noteBody = "hahah ";
		NoteResult<Object> result2 = service.updateNote(noteId, noteTitle, noteBody);
		System.out.println(result2);
	}

	@Test
	public void test5() {
		String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		String bookId = "6d763ac9-dca3-42d7-a2a7-a08053095c08";
		String noteName = "哈哈哈哈";
		NoteResult<Note> result = service.addNote(userId, bookId, noteName);
		System.out.println(result);
	}
}
