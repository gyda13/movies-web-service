package com.lulugyda.models.entities;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Phone_Numbers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
public class PhoneNumberEntity {

    @Id
    @Column(name = "id", unique = true)
    private String id;

    @Nullable
    @Column(name = "user_id")
    private String user_id;

    @Nullable
    @Column(name = "mobile_number")
    private String mobileNumber;


}
