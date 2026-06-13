package com.harishbendale.crud_application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.harishbendale.crud_application.dto.ParcelBookRequestDTO;
import com.harishbendale.crud_application.dto.ParcelBookResponseDTO;
import com.harishbendale.crud_application.model.Parcel;
import com.harishbendale.crud_application.repository.ParcelRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParcelService {
	private final ParcelRepository parcelRepo;
	private final EntityManager entityManager;
	
	@Transactional
	public ParcelBookResponseDTO bookParcel(ParcelBookRequestDTO dto) {
		Parcel parcel = Parcel.builder()
				.id(UUID.randomUUID())
				.description(dto.getDescription())
				.weight(dto.getWeight())
				.build();
		
		entityManager.persist(parcel);
		
		return ParcelBookResponseDTO.builder()
				.parcelId(parcel.getId())
				.description(parcel.getDescription())
				.weight(parcel.getWeight())
				.build();
		
	}
	
	public ParcelBookResponseDTO getParcel(UUID id) {
		Parcel parcel = parcelRepo.findById(id).get();
		
		return ParcelBookResponseDTO.builder()
				.parcelId(parcel.getId())
				.description(parcel.getDescription())
				.weight(parcel.getWeight())
				.build();
		
	}
}
