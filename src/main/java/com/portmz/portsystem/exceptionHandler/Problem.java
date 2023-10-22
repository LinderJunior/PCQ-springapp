package com.portmz.portsystem.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class Problem {
	
	private Integer status;
	private OffsetDateTime dateTimeException;
	private String title;
	private List<Field> field;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public OffsetDateTime getDateTimeException() {
		return dateTimeException;
	}
	public void setDateTimeException(OffsetDateTime dateTimeException) {
		this.dateTimeException = dateTimeException;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
		
	public List<Field> getField() {
		return field;
	}
	public void setField(List<Field> field) {
		this.field = field;
	}

	public static class Field{
		
		private String nameField;
		private String messageField;
		public  String getName() {
			return nameField;
		}

		public String getMessageField() {
			return messageField;
		}

		public Field(String nameField, String messageField) {
			super();
			this.nameField = nameField;
			this.messageField = messageField;
		}

		public void setName(String name) {
			this.nameField = nameField;
		}

		public void setMessageField(String messageField) {
			this.messageField = messageField;
		}
			
	}
}
