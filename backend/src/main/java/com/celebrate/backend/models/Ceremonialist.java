package com.celebrate.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ceremonialist")
@Data
@NoArgsConstructor
public class Ceremonialist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "document", nullable = false, length = 14)
    private String document;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "phone", nullable = false, length = 25)
    private String phone;

    // @OneToMany(mappedBy = "ceremonialist")
    // private List<Budget> budget;

    @OneToMany(mappedBy = "ceremonialist")
    private List<Client> clients;

    @ManyToOne
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private Address address;

    public Ceremonialist(String name, String email, String password, String document, LocalDate birthday, String phone, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.document = document;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
    }
}
