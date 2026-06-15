package com.harishbendale.crud_application.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harishbendale.crud_application.controller.ParcelController;
import com.harishbendale.crud_application.dto.ParcelBookRequestDTO;
import com.harishbendale.crud_application.dto.ParcelBookResponseDTO;
import com.harishbendale.crud_application.factory.ParcelFactory;
import com.harishbendale.crud_application.model.Parcel;
import com.harishbendale.crud_application.service.ParcelService;

@WebMvcTest(ParcelController.class)
public class ParcelControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private ParcelService parcelService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void bookParcel_shouldReturnCreatedParcel() throws Exception {
		UUID parcelId =  UUID.randomUUID();
		
		ParcelBookRequestDTO request = ParcelFactory.createParcelBookRequestDTO().build();
		
		Parcel parcel = ParcelFactory.createParcel(request).build();
		
		
		ParcelBookResponseDTO response = ParcelFactory.createParcelBookResponseDTO(parcel).build();
		
		when(parcelService.bookParcel(any(ParcelBookRequestDTO.class))).thenReturn(response);
		
		mockMvc.perform(post("/api/v1/parcel/book")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.parcelId").value(parcel.getId().toString()))
			.andExpect(jsonPath("$.description").value(parcel.getDescription()))
			.andExpect(jsonPath("$.weight").value(parcel.getWeight()));
		
		verify(parcelService, times(1)).bookParcel(any(ParcelBookRequestDTO.class));
	}
	
	@Test
	void getParcel_shouldReeturnParcelByUuid() throws Exception {
		ParcelBookRequestDTO request = ParcelFactory.createParcelBookRequestDTO().build();
		
		Parcel parcel = ParcelFactory.createParcel(request).build();
		
		ParcelBookResponseDTO response = ParcelFactory.createParcelBookResponseDTO(parcel).build();
		
		when(parcelService.getParcel(parcel.getId())).thenReturn(response);
		
		mockMvc.perform(get("/api/v1/parcel/get/{parcelId}", parcel.getId()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.parcelId").value(parcel.getId().toString()))
			.andExpect(jsonPath("$.description").value(parcel.getDescription()))
			.andExpect(jsonPath("$.weight").value(parcel.getWeight()));
		
		verify(parcelService, times(1)).getParcel(parcel.getId());
		
	}
}
