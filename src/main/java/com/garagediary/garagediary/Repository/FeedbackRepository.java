package com.garagediary.garagediary.Repository;

import com.garagediary.garagediary.entity.Feedback;
import com.garagediary.garagediary.entity.ServiceCenter;
import com.garagediary.garagediary.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
    List<Feedback> findByServiceCenter(ServiceCenter serviceCenter);

    List<Feedback> findByCustomer(UserEntity customer);

    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.serviceCenter = :serviceCenter")
    Double findAverageRatingByServiceCenter(ServiceCenter serviceCenter);
}
