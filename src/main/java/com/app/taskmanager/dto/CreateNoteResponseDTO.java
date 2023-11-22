package com.app.taskmanager.dto;

import com.app.taskmanager.entities.NotesEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNoteResponseDTO {
	
	private Integer taskId ; 
	private NotesEntity note ; 

}
