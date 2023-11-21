package com.app.taskmanager.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.taskmanager.entities.TaskEntity;

@Service
public class TaskService {

	private List<TaskEntity> tasks = new ArrayList<>() ; 
	private int taskId = 1 ; 
	private final SimpleDateFormat deadlineFormatter = new SimpleDateFormat("yyyy-MM-dd");
	
	public TaskEntity addTask(String title , String description , String deadline) {
		TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        try {
			task.setDeadline(deadlineFormatter.parse(deadline));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        task.setCompleted(false);
        tasks.add(task);
        taskId++;
        return task;
	}
	
	public List<TaskEntity> getTasks(){
		return tasks;
	}
	
	public TaskEntity getTaskById(int id) {
		for(TaskEntity task : tasks) {
			if(task.getId() == id) {
				return task ; 
			}
		}
		return null;
	}
}
