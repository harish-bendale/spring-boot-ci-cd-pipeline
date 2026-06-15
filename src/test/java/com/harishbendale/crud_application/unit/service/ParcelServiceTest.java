package com.harishbendale.crud_application.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.harishbendale.crud_application.dto.ParcelBookRequestDTO;
import com.harishbendale.crud_application.dto.ParcelBookResponseDTO;
import com.harishbendale.crud_application.factory.ParcelFactory;
import com.harishbendale.crud_application.model.Parcel;
import com.harishbendale.crud_application.repository.ParcelRepository;
import com.harishbendale.crud_application.service.ParcelService;

import jakarta.persistence.EntityManager;

@ExtendWith(MockitoExtension.class)
public class ParcelServiceTest {
	@Mock
	private ParcelRepository parcelRepository;
	
	@Mock
	private EntityManager entityManager;
	
	@InjectMocks
	private ParcelService parcelService;
	
	ParcelBookRequestDTO request;
	Parcel parcel;
	ParcelBookResponseDTO response;
	
	@BeforeEach
	void setup() {
		request = ParcelFactory.createParcelBookRequestDTO().build();
		parcel = ParcelFactory.createParcel(request).build();
		response = ParcelFactory.createParcelBookResponseDTO(parcel).build();
	}
	
	@Test
	void bookParcel_shouldSaveParcelSuccessfully() {
		
		ParcelBookResponseDTO dto = parcelService.bookParcel(request);
		
		assertNotNull(dto);
		assertEquals(request.getDescription(), dto.getDescription());
		assertEquals(request.getWeight(), dto.getWeight());
		assertNotNull(dto.getParcelId());
		
		verify(entityManager, times(1)).persist(any(Parcel.class));
	}
	
	@Test
	void getParcel_shouldReturnParcel_whenParcelExists() {
		when(parcelRepository.findById(parcel.getId())).thenReturn(Optional.of(parcel));
		
		ParcelBookResponseDTO response = parcelService.getParcel(parcel.getId());
		
		assertNotNull(response);
		assertEquals(parcel.getDescription(), response.getDescription());
		assertEquals(parcel.getWeight(), response.getWeight());
		assertNotNull(response.getParcelId());
		
		verify(parcelRepository, times(1)).findById(parcel.getId());
	}
}
