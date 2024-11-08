package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.DepartementDto;

import java.util.List;

public interface IDepartementService {
    public List<DepartementDto> retrieveAllDepartements();

    public DepartementDto addDepartement (DepartementDto d);

    public   DepartementDto updateDepartement (Integer id , DepartementDto departementDto);

    public  DepartementDto retrieveDepartement (Integer idDepart);

    public  boolean deleteDepartement(Integer idDepartement);

}
