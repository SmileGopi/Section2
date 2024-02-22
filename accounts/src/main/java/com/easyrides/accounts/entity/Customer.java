package com.easyrides.accounts.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity //We can provide @Table if name of the class and table def are different
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id") // generally column is required when table column name and this name are different
    private Long customerId;

    private String name;
    private String email;
    @Column(name="mobile_number")
    private String mobileNumber;

}
