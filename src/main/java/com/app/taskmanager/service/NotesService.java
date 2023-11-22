package com.app.taskmanager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.taskmanager.entities.NotesEntity;
import com.app.taskmanager.entities.TaskEntity;

@Service
public class NotesService {

	private TaskService taskService ; 
	private Map<Integer,TaskNotesHolder> taskNoteHolder = new HashMap<>() ; 
	
	public NotesService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	class TaskNotesHolder{
		protected int noteId  = 1; 
		protected List<NotesEntity> notes = new ArrayList<>() ; 
	}
	
	
	public List<NotesEntity> getNotesForTask(int taskId){
		TaskEntity task = taskService.getTaskById(taskId);
		if(task == null) {
			return null;
		}
		
		if(taskNoteHolder.get(taskId)==null) {
			taskNoteHolder.put(taskId, new TaskNotesHolder());
		}
		
		
		return taskNoteHolder.get(taskId).notes; 
	}
	
	public NotesEntity addNotesForTask(int taskId , String title, String body) {
		TaskEntity task = taskService.getTaskById(taskId);
		if(task == null) {
			return null;
		}
		
		if(taskNoteHolder.get(taskId)==null) {
			taskNoteHolder.put(taskId, new TaskNotesHolder());
		}
		
		TaskNotesHolder taskNotesHolder = taskNoteHolder.get(taskId);
		NotesEntity note = new NotesEntity();
		note.setId(taskNotesHolder.noteId);
		note.setTitle(title);
		note.setBody(body);
		taskNotesHolder.notes.add(note);
		taskNotesHolder.noteId++;
		return note;
	}
	
}
