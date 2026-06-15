package com.harishbendale.crud_application.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harishbendale.crud_application.dto.ParcelBookRequestDTO;
import com.harishbendale.crud_application.factory.ParcelFactory;
import com.harishbendale.crud_application.model.Parcel;
import com.harishbendale.crud_application.repository.ParcelRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ParcelIntegrationIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ParcelRepository parcelRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Test
	void bookParcel_shouldSaveParcelInDatabase() throws Exception {
		ParcelBookRequestDTO request = ParcelFactory.createParcelBookRequestDTO().build();
		
		mockMvc.perform(post("/api/v1/parcel/book")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.parcelId").exists())
				.andExpect(jsonPath("$.description").value(request.getDescription()))
				.andExpect(jsonPath("$.weight").value(request.getWeight()));
		
		assertEquals(1, parcelRepository.count());
	}
	
	@Test
	void getParcel_shouldFetchParcelFromDatabase() throws Exception {
		ParcelBookRequestDTO request = ParcelFactory.createParcelBookRequestDTO().build();
		
		Parcel parcel = ParcelFactory.createParcel(request).build();
		
		entityManager.persist(parcel);
		entityManager.flush();
		
		UUID parcelId = parcel.getId();
		
		mockMvc.perform(get("/api/v1/parcel/get/{parcelId}", parcelId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.description").value(request.getDescription()))
			.andExpect(jsonPath("$.weight").value(request.getWeight()));
	}
}
