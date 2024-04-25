package com.riwi.vacants.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "company")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 40, nullable = false)
    private String name;
    private String location;
    @Column(length = 14, nullable = false)
    private String contact;

    /**
     * @OneToMany - uno a muchos: Una empresa puede tener muchas vacantes
     *            mmapedBy: Especificamos donde se guardará la información de la
     *            relacion
     *            fetch EAGER: Join Implicito | fetch LAZY: Join perezoso.
     *            orphanRemoval TRUE-> Remover objeto huerfano (Sin llave foranea)
     */
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Vacant> vacants;
}
