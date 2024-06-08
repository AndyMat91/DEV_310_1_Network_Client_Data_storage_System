package org.example.dev_310_1_network_client_data_storage_system.common.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "client_name", length = 100)
    private String clientName;

    @Column(name = "client_type", length = 20)
    private String clientType;

    @Column(name = "datereg")
    private Instant datereg;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<AddressEntity> addresses = new LinkedHashSet<>();

}