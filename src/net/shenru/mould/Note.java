package net.shenru.mould;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 笔记
 * 
 * @author along
 */
public class Note {

	private int id;
	private String title;
	private String content;
	private List<String> filepaths;
	//添加时间
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getFilepaths() {
		return filepaths;
	}

	public void setFilepaths(List<String> filepaths) {
		this.filepaths = filepaths;
	}

	public void setFilepaths(String filepaths) {
		List<String> paths = null;
		if (filepaths != null) {
			paths = Arrays.asList(filepaths.split("[|]"));
		} else {
			paths = new ArrayList<String>();
		}
		this.filepaths = paths;
	}
}
