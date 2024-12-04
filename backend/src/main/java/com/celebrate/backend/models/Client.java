package com.celebrate.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "phone", nullable = false, length = 25)
    private String phone;

    @OneToMany(mappedBy = "client")
    private List<Budget> budget;

    @ManyToOne
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private Address address;

    public Client(String name, String email, String password, String cpf, LocalDate birthday, String phone, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
    }
}
