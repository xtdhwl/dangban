package net.shenru.note;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shenru.database.NotesData;
import net.shenru.mould.Note;

public class NoteListPageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setHeader("Content-Type", "text/html;charset=utf-8");
		NotesData notesData = new NotesData();
		List<Note> notes = notesData.getNoteList(-1, -1);
		req.setAttribute("notelist", notes);
		req.getRequestDispatcher("/WEB-INF/jsp/note/notelist.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
