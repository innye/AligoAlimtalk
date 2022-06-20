package com.sms.aligo.repository;
import com.sms.aligo.entity.AligoTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AligoTemplateRepository extends JpaRepository<AligoTemplate, Long> {
    @Query(value = "select m.code from aligotemplate m where m.tag= :tag", nativeQuery = true)
    String findTemplateNumByType(@Param("tag") String tag);
}