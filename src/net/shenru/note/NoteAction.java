package net.shenru.note;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shenru.database.NotesData;
import net.shenru.mould.Note;

/**
 * 负责笔记的增删改查 增id为null add 改id为日志id update 删除 delete
 * 
 * @author along
 */
public class NoteAction extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// resp.getOutputStream().write("Hello".getBytes());
		req.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-Type", "text/html;charset=utf-8");

		String action = req.getParameter("action");
		String noteId = req.getParameter("id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String filepaths = req.getParameter("filepaths");

		if ("add".equals(action)) {
			Note note = new Note();
			note.setTitle(title);
			note.setContent(content);
			note.setFilepaths(filepaths);

			NotesData data = new NotesData();
			int id = data.addNote(title, content, note.getFilepaths());
			if (id > 0) {
				note.setId(id);
				req.setAttribute("id", id);
				req.setAttribute("title", title);
				req.setAttribute("content", content);
				req.setAttribute("filepaths", note.getFilepaths());
				req.setAttribute("note", note);
				req.getRequestDispatcher("/WEB-INF/jsp/note/note.jsp").forward(req, resp);
			} else {
				req.setAttribute("message", "操作失败id:" + id);
				req.getRequestDispatcher("/message.html").forward(req, resp);
			}
		} else if ("update".equals(action)) {
			Note note = new Note();
			note.setTitle(title);
			note.setContent(content);
			note.setFilepaths(filepaths);
			if (noteId != null) {
				int nid = Integer.valueOf(noteId);
				NotesData data = new NotesData();
				boolean result = data.updateNote(nid, note);
				if (result) {
					// 更新成功
					req.setAttribute("message", "更新成功");
					req.getRequestDispatcher("/message.html").forward(req, resp);
				} else {
					// 更新失败
					req.setAttribute("message", "更新失败");
					req.getRequestDispatcher("/message.html").forward(req, resp);
				}
			} else {
				req.setAttribute("message", "更新id:不能为空");
				req.getRequestDispatcher("/message.html").forward(req, resp);
			}
		} else if ("delete".equals(action)) {
			//TODO 删除日志
		} else {
			req.setAttribute("message", "操作失败为止action");
			req.getRequestDispatcher("/message.html").forward(req, resp);
		}
	}
}
