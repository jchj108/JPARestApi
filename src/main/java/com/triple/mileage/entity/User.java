package com.triple.mileage.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter @Setter
@javax.persistence.Table(name = "\"User\"") // escape reserved keyword
public class User {

    @Id
    @Column(name = "userId")
    @Type(type="uuid-char")
    private UUID id = UUID.randomUUID();


}
