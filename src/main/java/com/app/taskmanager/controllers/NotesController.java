package com.app.taskmanager.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.taskmanager.dto.CreateNoteDTO;
import com.app.taskmanager.dto.CreateNoteResponseDTO;
import com.app.taskmanager.entities.NotesEntity;
import com.app.taskmanager.service.NotesService;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {
	
	private NotesService notesService ; 
	
	public NotesController(NotesService notesService ) {
		this.notesService = notesService;
	}
	
	@GetMapping
	public ResponseEntity<List<NotesEntity>> getnotes(@PathVariable("taskId") Integer taskId) {
		var notes = notesService.getNotesForTask(taskId);
		if(notes == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(notes);
	}
	
	@PostMapping
	public ResponseEntity<CreateNoteResponseDTO> addNote(@PathVariable("taskId") Integer taskId, @RequestBody CreateNoteDTO body){
		var notes = notesService.addNotesForTask(taskId, body.getTitle(), body.getBody());
		
		if(notes == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new CreateNoteResponseDTO(taskId, notes));
	}

}
