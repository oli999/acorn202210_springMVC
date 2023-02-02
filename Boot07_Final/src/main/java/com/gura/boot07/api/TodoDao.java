package com.gura.boot07.api;

import java.util.List;

public interface TodoDao {	
	public void insert(TodoDto dto);
	public List<TodoDto> getList();
	public void delete(int num);
}
