package net.shenru.database.test;

import java.util.ArrayList;
import java.util.List;

import net.shenru.database.NotesData;
import net.shenru.mould.Note;

import org.junit.Assert;
import org.junit.Test;

public class NoteDataTest {

	@Test
	public void getNoteTest() {
		NotesData data = new NotesData();
		Note note = data.getNote(33);
		Assert.assertEquals(note.getTitle(), "测试添加Note");
	}

	@Test
	public void addNoteTest() {
		// insert into note(title,content,filepaths)
		// values('测试添加Note','这是一条测试笔记','1');
		String title = "测试添加Note";
		String content = "这是一条测试笔记";
		List<String> paths = new ArrayList<String>();
		NotesData data = new NotesData();
		int id = data.addNote(title, content, paths);
		System.out.println("id:" + id);
	}

	@Test
	public void getNotesTest() {
		NotesData data = new NotesData();
		List<Note> notes = data.getNoteList(0, 29);
		Assert.assertEquals(notes.size(), 29);
	}
	
	@Test
	public void updateNote(){
		int noteId = 34;
		NotesData data = new NotesData();
		Note note = new Note();
		note.setTitle("这是标题");
		note.setContent("这是内容");
		note.setFilepaths("");
		boolean result = data.updateNote(noteId, note);
		Assert.assertEquals(result, true);
	}
}
