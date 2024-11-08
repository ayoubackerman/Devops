package tn.esprit.spring.kaddem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Departement;

import tn.esprit.spring.kaddem.entities.DepartementDto;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DepartementServiceImpl implements IDepartementService{

private final DepartementRepository departementRepository;



	public List<DepartementDto> retrieveAllDepartements(){
		List<Departement> departements = (List<Departement>) departementRepository.findAll();
		return departements.stream().map(Departement::getDto).collect(Collectors.toList());
	}

	public DepartementDto addDepartement (DepartementDto d){
		Departement departement = new Departement();
		departement.setIdDepart(d.getIdDepart());
		departement.setNomDepart(d.getNomDepart());
		return departementRepository.save(departement).getDto();
	}




	public DepartementDto retrieveDepartement(Integer idDepart){
		Optional<Departement> optionalItems = departementRepository.findById(idDepart);
		if (optionalItems.isPresent()){
			return optionalItems.get().getDto();
		}else {
			return null;
		}

	}

	public DepartementDto updateDepartement(Integer id , DepartementDto departementDto)  {
		Optional<Departement> optionalItems = departementRepository.findById(id);
		if (optionalItems.isPresent() ){
			Departement departement = optionalItems.get();
			departement.setNomDepart(departementDto.getNomDepart());
			return departementRepository.save(departement).getDto();
		}else {
			return null;
		}
	}


	public boolean deleteDepartement(Integer idDepartement){
		Optional<Departement> optionalItems= departementRepository.findById(idDepartement);
		if(optionalItems.isPresent()){
			departementRepository.deleteById(idDepartement);
			return true;
		}else {
			return false;
		}
	}

	public List<Departement> retrieve() {
		return (List<Departement>) departementRepository.findAll();
	}

	public Departement add(Departement d) {
		return departementRepository.save(d);
	}

	@Override
	public Departement update(Departement d) {
		return departementRepository.save(d);
	}

	@Override
	public Departement retrieveOne(Integer idDepart) {
		return departementRepository.findById(idDepart).get();
	}



	public void delete(Integer idDepartement) {
		Departement d=retrieveOne(idDepartement);
		departementRepository.delete(d);
	}


}
