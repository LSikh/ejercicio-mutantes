package ejercicio.mutantes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DNA {

	private Integer id;
	
	private String secuence;
	
	private Boolean isMutant;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSecuence() {
		return secuence;
	}

	public void setSecuence(String secuence) {
		this.secuence = secuence;
	}

	public Boolean getIsMutant() {
		return isMutant;
	}

	public void setIsMutant(Boolean isMutant) {
		this.isMutant = isMutant;
	}
}
