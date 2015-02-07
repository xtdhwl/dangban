package net.shenru.note;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shenru.database.NotesData;
import net.shenru.mould.Note;

/**
 * 日志编辑
 * 
 * @author along
 */
public class NoteEditPageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String noteId = req.getParameter("id");
		if (noteId != null) {
			NotesData notesData = new NotesData();
			Note note = notesData.getNote(Integer.valueOf(noteId));
			if (note != null) {
				req.setAttribute("action", "update");
				req.setAttribute("id", note.getId());
				req.setAttribute("title", note.getTitle());
				req.setAttribute("content", note.getContent());
				req.setAttribute("filepaths", note.getFilepaths());
				req.setAttribute("note", note);
			} else {
				req.setAttribute("message", "日志为找到");
				req.getRequestDispatcher("/message.html").forward(req, resp);
			}
		}else{
			req.setAttribute("action", "add");
		}

		req.getRequestDispatcher("/WEB-INF/jsp/note/noteedit.jsp").forward(req, resp);
	}

}
