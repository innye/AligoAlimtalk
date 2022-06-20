package com.sms.aligo.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="aligomessage")
public class AligoMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String state;

    @Column
    private String tag;

    @Column
    private String sender_name;

    @Column
    private String sender_num;

    @Column
    private String receiver_name;

    @Column
    private String receiver_num;

    @Column
    private String url;

    @CreatedDate
    @Column
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column
    private LocalDateTime updated_on;
}
