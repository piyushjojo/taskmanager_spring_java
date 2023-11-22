package com.app.taskmanager.dto;

import java.util.Date;
import java.util.List;

import com.app.taskmanager.entities.NotesEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskResponseDTO {
	
	private int id ; 
	private String title ; 
	private String description ;
	private Date deadline ; 
	private boolean completed ; 
	private List<NotesEntity> notes ; 
}
