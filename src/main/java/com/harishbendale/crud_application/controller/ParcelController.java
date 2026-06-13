package com.harishbendale.crud_application.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harishbendale.crud_application.dto.ParcelBookRequestDTO;
import com.harishbendale.crud_application.dto.ParcelBookResponseDTO;
import com.harishbendale.crud_application.service.ParcelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/parcel")
@RequiredArgsConstructor
public class ParcelController {
	private final ParcelService service;
	
	@PostMapping("/book")
	public ResponseEntity<ParcelBookResponseDTO> bookParcel(@RequestBody ParcelBookRequestDTO dto) {
		return new ResponseEntity<>(service.bookParcel(dto), HttpStatus.OK);
	}
	
	@GetMapping("/get/{parcelId}")
	public ResponseEntity<?> getParcel(@PathVariable UUID parcelId) {
		return new ResponseEntity<>(service.getParcel(parcelId), HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public ResponseEntity<?> testController() {
		return new ResponseEntity<>("Hello world", HttpStatus.OK);
	}
}
