package com.garagediary.garagediary.Repository;

import com.garagediary.garagediary.entity.Booking;
import com.garagediary.garagediary.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByServiceCenterId(UUID serviceCenterId);

    List<Booking> findByServiceCenterIdAndStatus(UUID serviceCenterId, Status status);

}
