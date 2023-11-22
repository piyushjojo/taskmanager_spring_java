package com.app.taskmanager.controllers;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.taskmanager.dto.CreateTaskDTO;
import com.app.taskmanager.dto.ErrorResponseDTO;
import com.app.taskmanager.dto.TaskResponseDTO;
import com.app.taskmanager.dto.UpdateTaskDTO;
import com.app.taskmanager.entities.TaskEntity;
import com.app.taskmanager.service.NotesService;
import com.app.taskmanager.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskService taskService;

	private final NotesService notesService;

	private ModelMapper modelmapper = new ModelMapper();

	public TaskController(TaskService taskService, NotesService notesService) {
		super();
		this.taskService = taskService;
		this.notesService = notesService;
	}

	@GetMapping
	public ResponseEntity<List<TaskEntity>> getTasks() {
		var tasks = taskService.getTasks();

		return ResponseEntity.ok(tasks);
	}

	@PostMapping("")
	public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
		var task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());
		return ResponseEntity.ok(task);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Integer id) {
		var task = taskService.getTaskById(id);
		var notes = notesService.getNotesForTask(id);

		if (task == null) {
			return ResponseEntity.notFound().build();
		}
		var taskResponse = modelmapper.map(task, TaskResponseDTO.class);
		taskResponse.setNotes(notes);
		return ResponseEntity.ok(taskResponse);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body)
			throws ParseException {
		var task = taskService.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());
		if (task == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(task);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleError(Exception e) {
		if (e instanceof ParseException) {
			return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format."));

		}
		e.printStackTrace();
		return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));
	}
}
