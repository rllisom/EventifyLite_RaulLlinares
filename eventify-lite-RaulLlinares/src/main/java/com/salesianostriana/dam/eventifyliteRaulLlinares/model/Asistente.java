package com.salesianostriana.dam.eventifyliteRaulLlinares.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Asistente {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String email;

    @OneToMany( mappedBy = "asistente",fetch = FetchType.LAZY)
            @Builder.Default
    Set<Entrada> entradas = new HashSet<>();

    //Helper
    public void addEntrada(Entrada entrada){
        this.entradas.add(entrada);
        entrada.setAsistente(this);
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Asistente asistente = (Asistente) o;
        return getId() != null && Objects.equals(getId(), asistente.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
