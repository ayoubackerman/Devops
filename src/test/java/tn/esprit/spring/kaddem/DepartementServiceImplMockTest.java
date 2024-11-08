package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



public class DepartementServiceImplMockTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllDepartements() {
        Departement dep1 = new Departement();
        dep1.setIdDepart(1);
        dep1.setNomDepart("IT");

        Departement dep2 = new Departement();
        dep2.setIdDepart(2);
        dep2.setNomDepart("HR");

        when(departementRepository.findAll()).thenReturn(Arrays.asList(dep1, dep2));

        List<Departement> result = departementService.retrieve();

        assertEquals(2, result.size());
        assertEquals("IT", result.get(0).getNomDepart());
        assertEquals("HR", result.get(1).getNomDepart());
        verify(departementRepository, times(1)).findAll();
    }

    @Test
    void testAddDepartement() {
        Departement departement = new Departement();
        departement.setNomDepart("Finance");

        when(departementRepository.save(any(Departement.class))).thenReturn(departement);

        Departement result = departementService.add(departement);

        assertNotNull(result);
        assertEquals("Finance", result.getNomDepart());
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    void testUpdateDepartement() {
        // Arrange
        Departement existingDepartement = new Departement();
        existingDepartement.setIdDepart(1);
        existingDepartement.setNomDepart("Marketing");

        Departement updatedDepartement = new Departement();
        updatedDepartement.setIdDepart(1);
        updatedDepartement.setNomDepart("Sales");

        // Mocking findById to return the existing departement
        when(departementRepository.findById(1)).thenReturn(Optional.of(existingDepartement));
        // Mocking save to return the updated departement
        when(departementRepository.save(any(Departement.class))).thenReturn(updatedDepartement);

        // Act
        Departement result = departementService.update(updatedDepartement);

        // Assert
        assertNotNull(result, "The updated result should not be null");
        assertEquals("Sales", result.getNomDepart(), "The department name should be updated to 'Sales'");
        verify(departementRepository, times(1)).findById(1);
        verify(departementRepository, times(1)).save(updatedDepartement);
    }


    @Test
    void testRetrieveDepartement() {
        Integer id = 1;
        Departement departement = new Departement();
        departement.setIdDepart(id);
        departement.setNomDepart("Operations");

        when(departementRepository.findById(id)).thenReturn(Optional.of(departement));

        Departement result = departementService.retrieveOne(id);

        assertNotNull(result);
        assertEquals("Operations", result.getNomDepart());
        verify(departementRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteDepartement() {
        Integer id = 1;
        Departement departement = new Departement();
        departement.setIdDepart(id);
        departement.setNomDepart("Logistics");

        when(departementRepository.findById(id)).thenReturn(Optional.of(departement));

        departementService.delete(id);

        verify(departementRepository, times(1)).delete(departement);
    }
}
