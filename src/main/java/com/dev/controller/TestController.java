package com.dev.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.ResponseDto;
import com.dev.dto.ResultDto;
import com.dev.dto.TestRequestDto;
import com.dev.dto.TestResponseDto;
import com.dev.model.Category;
import com.dev.model.Difficulties;
import com.dev.model.Test;
import com.dev.model.User;
import com.dev.repository.TestRepository;
import com.dev.service.CategoryService;
import com.dev.service.PDFResultsService;
import com.dev.service.TestService;
import com.dev.service.UserService;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/quizes")
public class TestController {
	@Autowired
	private TestService testService;
	@Autowired
	private PDFResultsService pdfService;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TestRepository testRepository;
	@PostMapping
	public ResponseEntity<?> readAll(@Valid @RequestBody TestRequestDto dto, Authentication a){
		Difficulties d=Difficulties.getDifficultie(dto.getDifficulty());
		User u = userService.readByUsername(a.getName());
		Category c = categoryService.readId(dto.getCategoryId());
		Test test=testService.generate(u, c, d);
		TestResponseDto testDto=TestResponseDto.toDto(test);
		return new ResponseEntity<>(new ResponseDto<TestResponseDto>(testDto), HttpStatus.OK);
	}
	
	@GetMapping("/{quizId}/report")
	public ResponseEntity<?> getResults(@PathVariable("quizId") int quizId, Authentication a){
		User u = userService.readByUsername(a.getName());
		if(!testService.wasDoneBy(quizId, u.getId())) {
			return new ResponseEntity<>(new ResponseDto<String>("User not allowed for performing this action"), HttpStatus.FORBIDDEN);
		}
		ResultDto result=testService.getResults(quizId);
		return new ResponseEntity<>(new ResponseDto<ResultDto>(result), HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_PDF_VALUE, path = "/{quizId}/report")
    public ResponseEntity<?> pdfReport(@PathVariable("quizId") int quizId, @RequestParam String type) {
        ByteArrayInputStream bis;
		try {
			
			Test test=testRepository.findById(quizId).orElseThrow(()->new IllegalArgumentException("The requested test does not exist"));

			HttpHeaders headers = new HttpHeaders();
			
			if(type.equalsIgnoreCase("pdf")) {
				bis = pdfService.generatePDF(test);
		        headers.add("Content-Disposition", "inline; filename=test-results.pdf");
		        return ResponseEntity
		                .ok()
		                .headers(headers)
		                .contentType(MediaType.APPLICATION_PDF)
		                .body(new InputStreamResource(bis));
			} else if (type.equalsIgnoreCase("xls")) {
				
			}
			
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
        
    }
}
