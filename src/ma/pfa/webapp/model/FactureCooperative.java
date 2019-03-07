package ma.pfa.webapp.model;


import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FactureCooperative {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_factureCooperative")
	private int id;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="id_commandeCooperative")
	private CommandeCooperative commandeCooperative;
	
	
	
   

}