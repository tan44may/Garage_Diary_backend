package com.garagediary.garagediary.Repository;

import com.garagediary.garagediary.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, UUID> {
}
