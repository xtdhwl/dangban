package net.shenru.note;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shenru.database.NotesData;
import net.shenru.mould.Note;

/**
 * 显示篇日志
 * 
 * @author along
 */
public class NotePageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setHeader("Content-Type", "text/html;charset=utf-8");
		String noteId = req.getParameter("id");
		if (noteId == null) {
			req.setAttribute("message", "日志id为空");
			req.getRequestDispatcher("/message.html").forward(req, resp);
		} else {
			// 日志详情
			try {
				int id = Integer.parseInt(noteId);
				NotesData notesData = new NotesData();
				Note note = notesData.getNote(id);
				if (note != null) {
					req.setAttribute("id", note.getId());
					req.setAttribute("title", note.getTitle());
					req.setAttribute("content", note.getContent());
					req.setAttribute("note", note);
					req.getRequestDispatcher("/WEB-INF/jsp/note/note.jsp").forward(req,
							resp);
				} else {
					req.setAttribute("message", "日志id" + noteId + ",未找到");
					req.getRequestDispatcher("/message.html").forward(req, resp);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
}
