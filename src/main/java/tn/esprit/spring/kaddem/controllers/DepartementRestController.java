package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.DepartementDto;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/departement")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartementRestController {

	IDepartementService departementService;
	// http://localhost:8089/Kaddem/departement/retrieve-all-departements
	@GetMapping("/retrieve-all-departements")
	public ResponseEntity<List<DepartementDto>> getDepartements() {
		List<DepartementDto> list = departementService.retrieveAllDepartements();
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	// http://localhost:8089/Kaddem/departement/retrieve-departement/8
	@GetMapping("/retrieve-departement/{departement-id}")
	public ResponseEntity<DepartementDto> retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
		DepartementDto itemsDto = departementService.retrieveDepartement(departementId);
		if (itemsDto != null){
			return ResponseEntity.ok(itemsDto);
		}else {
			return ResponseEntity.notFound().build();
		}	}

	// http://localhost:8089/Kaddem/departement/add-departement
	@PostMapping("/add-departement")
	public ResponseEntity<DepartementDto> addDepartement(@ModelAttribute DepartementDto d) {
		DepartementDto departement = departementService.addDepartement(d);
		return ResponseEntity.status(HttpStatus.CREATED).body(departement);
	}

	// http://localhost:8089/Kaddem/departement/remove-departement/1
	@DeleteMapping("/remove-departement/{departement-id}")
	public ResponseEntity<Void> removeDepartement(@PathVariable("departement-id") Integer departementId) {
		boolean delete = departementService.deleteDepartement(departementId);
		if (delete){
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}	}

	// http://localhost:8089/Kaddem/departement/update-departement
	@PutMapping("/update-departement/{departement-id}")
	public ResponseEntity<DepartementDto> updateDepartement(@PathVariable("departement-id") Integer departementId , @ModelAttribute DepartementDto departementDto) {
		DepartementDto itemDto1 = departementService.updateDepartement(departementId,departementDto);
		if (itemDto1 != null){
			return ResponseEntity.ok(itemDto1);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}


