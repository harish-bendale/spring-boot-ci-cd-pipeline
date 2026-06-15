package com.harishbendale.crud_application.factory;

import java.math.BigDecimal;
import java.util.UUID;

import com.harishbendale.crud_application.dto.ParcelBookRequestDTO;
import com.harishbendale.crud_application.dto.ParcelBookResponseDTO;
import com.harishbendale.crud_application.model.Parcel;

public class ParcelFactory {
	public static ParcelBookRequestDTO.ParcelBookRequestDTOBuilder createParcelBookRequestDTO() {
		return ParcelBookRequestDTO.builder()
				.description("Table, chair")
				.weight(BigDecimal.valueOf(100.34));
	}
	
	public static ParcelBookResponseDTO.ParcelBookResponseDTOBuilder createParcelBookResponseDTO(Parcel parcel) {
		return ParcelBookResponseDTO.builder()
				.parcelId(parcel.getId())
				.description(parcel.getDescription())
				.weight(parcel.getWeight());
	}
	
	public static Parcel.ParcelBuilder createParcel(ParcelBookRequestDTO dto) {
		return Parcel.builder()
				.id(UUID.randomUUID())
				.description(dto.getDescription())
				.weight(dto.getWeight());
	}
}
