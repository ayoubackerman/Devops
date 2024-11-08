package tn.esprit.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Data
public class Departement implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idDepart;
    private String nomDepart;
    @OneToMany(mappedBy="departement")
    @JsonIgnore
    private Set<Etudiant> etudiants;

    public DepartementDto getDto(){
        DepartementDto departementDto =new DepartementDto();
        departementDto.setIdDepart(idDepart);
        departementDto.setNomDepart(nomDepart);
        return departementDto;
    }
//test


}
