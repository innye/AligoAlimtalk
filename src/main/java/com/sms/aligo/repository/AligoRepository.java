package com.sms.aligo.repository;
import com.sms.aligo.entity.AligoTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AligoRepository extends JpaRepository<AligoTemplate, Long> {
}