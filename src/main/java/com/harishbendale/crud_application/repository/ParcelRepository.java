package com.harishbendale.crud_application.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harishbendale.crud_application.model.Parcel;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, UUID>{

}
