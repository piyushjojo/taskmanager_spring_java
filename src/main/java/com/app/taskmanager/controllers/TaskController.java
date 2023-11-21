package com.app.taskmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.taskmanager.dto.CreateNoteDTO;
import com.app.taskmanager.dto.CreateTaskDTO;
import com.app.taskmanager.entities.TaskEntity;
import com.app.taskmanager.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService  ;
	
	@GetMapping
	public ResponseEntity<List<TaskEntity>> getTasks(){
		var tasks =  taskService.getTasks();
		
		return ResponseEntity.ok(tasks);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id){
		var task = taskService.getTaskById(id);
		
		if(task == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(task); 
	}
	
	@PostMapping("")
	public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body){
		var task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());
		return ResponseEntity.ok(task);
	}
	
	@PostMapping("/addNotes/{id}")
	public ResponseEntity<TaskEntity> addNotes( @RequestBody CreateNoteDTO body , @PathVariable("id") Integer id){
		var task = taskService.addNotes(id, body.getTitle(), body.getBody());
		if(task == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(task); 
	}
}
