package ejercicio.mutantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ejercicio.mutantes.model.DNA;

public interface DNARepository extends JpaRepository<DNA, Integer> {

}
