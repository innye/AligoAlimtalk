package com.sms.aligo.repository;
import com.sms.aligo.entity.AligoMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AligoMessageRepository extends JpaRepository<AligoMessage, Long>{
    @Query(value = "select * from aligomessage m where m.state= :state", nativeQuery = true)
    List<AligoMessage> findMessageByState(@Param("state") String state);

    @Transactional
    @Modifying
    @Query(value = "UPDATE aligomessage m SET m.state=:state, m.updated_on=:time WHERE m.id =:id", nativeQuery = true)
    void updateMessageStateById(@Param("state") String state, @Param("time") LocalDateTime time, @Param("id") Long id);
}
