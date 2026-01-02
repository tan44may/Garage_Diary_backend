package com.garagediary.garagediary.Repository;

import com.garagediary.garagediary.entity.ServiceOffered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServiceOfferedRepository extends JpaRepository<ServiceOffered, UUID> {
}
