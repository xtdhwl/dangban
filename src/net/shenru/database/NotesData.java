package net.shenru.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.shenru.mould.Note;

public class NotesData {

	/**
	 * 添加一条日志
	 * 
	 * @param title
	 * @param content
	 * @param paths
	 * @return
	 */
	public int addNote(String title, String content, List<String> paths) {
		// insert into note(title,content,filepath)
		// values("hha","","");
		// insert into note(title,content,filepath)
		// values("我的第一篇日志","这是我第一篇日志，^_^","");
		// String sql =
		// "insert into note(title,content,filepaths) values(?,?,?);";
		String sql = "insert into note(title,content,filepaths) values(?,?,?);";
		StringBuffer filepaths = new StringBuffer();
		if (paths != null) {
			for (int i = 0, count = paths.size(); i < count; i++) {
				if (i != 0) {
					filepaths.append("|");
				}
				filepaths.append(paths.get(i));
			}
		}

		SQLDatabase dataBase = new SQLDatabase();
		String obj[] = { title, content, filepaths.toString() };

		final ArrayList<Integer> array = new ArrayList<Integer>();
		dataBase.executeUpdate(sql, obj, new GenericResultHandler() {

			@Override
			public void statementHandler(PreparedStatement sta) {
				try {
					ResultSet generatedKeys = sta.getGeneratedKeys();
					if (generatedKeys.next()) {
						int generatedId = generatedKeys.getInt(1);
						array.add(0, generatedId);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		if (array.size() > 0) {
			return array.get(0);
		}
		return -1;
	}

	/**
	 * 获取一条日志
	 * 
	 * @param noteId
	 * @return
	 */
	public Note getNote(int noteId) {
		// select id,title,content,filepaths from note where id =
		// id;
		String sql = " select id,title,content,filepaths  from note where id = ?;";
		try {
			SQLDatabase dataBase = new SQLDatabase();
			Object obj[] = { noteId };
			Note resultNote = (Note) dataBase.executeQuery(sql, obj, new GenericResultHandler() {
				@Override
				public Object resultSetHandler(ResultSet res) {
					Note note = null;
					try {
						if (res.next()) {
							int id = res.getInt("id");
							String title = res.getString("title");
							String content = res.getString("content");
							String filepaths = res.getString("filepaths");
							List<String> fp = null;
							if (filepaths.trim().equals("")) {
								fp = new ArrayList<String>();
							} else {
								fp = Arrays.asList(filepaths
										.split("[|]"));
							}

							note = new Note();
							note.setId(id);
							note.setTitle(title);
							note.setContent(content);
							note.setFilepaths(fp);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return note;
				}
			});
			return resultNote;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取日志列表
	 * 
	 * @param 分页id
	 * @param page
	 *                  分页个数
	 * @return
	 */
	public List<Note> getNoteList(int lastId, int page) {
		// select id,title,content,filepath from note;

		String sql = "select id,title,content,filepaths from note;";
		try {
			SQLDatabase dataBase = new SQLDatabase();
			@SuppressWarnings("unchecked")
			List<Note> resultNote = (List<Note>) dataBase.executeQuery(sql, null,
					new GenericResultHandler() {
						@Override
						public List<Note> resultSetHandler(ResultSet res) {
							List<Note> notes = new ArrayList<Note>();
							try {
								while (res.next()) {
									int id = res.getInt("id");
									String title = res.getString("title");
									String content = res.getString("content");
									String filepaths = res
											.getString("filepaths");
									List<String> fp = null;
									if (filepaths == null
											|| filepaths.trim()
													.equals("")) {
										fp = new ArrayList<String>();
									} else {
										fp = Arrays.asList(filepaths
												.split("[|]"));
									}

									Note note = new Note();
									note.setId(id);
									note.setTitle(title);
									note.setContent(content);
									note.setFilepaths(fp);

									notes.add(note);
								}
							} catch (SQLException e) {
								e.printStackTrace();
								return null;
							}
							return notes;
						}
					});
			return resultNote;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加一条笔记
	 * 
	 * @param note
	 */
	public boolean updateNote(int noteId, Note note) {
		String sql = "update note set title=?,content=?,filepaths=? where id=?";
		try {
			SQLDatabase dataBase = new SQLDatabase();
			StringBuffer sb = new StringBuffer();
			if (note.getFilepaths() != null) {
				for (String path : note.getFilepaths()) {
					sb.append(path + "|");
				}
			}
			Object objParam[] = { note.getTitle(), note.getContent(), sb.toString(), noteId };
			dataBase.executeUpdate(sql, objParam);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
