package com.jacekg.reportSystem.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleError2(MaxUploadSizeExceededException e, RedirectAttributes redirectAttributes) {
		System.out.println("My log: in GlobalExceptionHandler");
		redirectAttributes.addFlashAttribute("message", "Przekroczono rozmiar plików (maksymalnie 20MB)");
		return "redirect:/report/showReportForm";

	}

}
