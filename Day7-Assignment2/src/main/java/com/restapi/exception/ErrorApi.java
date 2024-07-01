package com.restapi.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorApi {

	private Integer statusCode;
	private String title;
	private String status;
	private String details;
	private LocalDateTime localDateTime;
}
