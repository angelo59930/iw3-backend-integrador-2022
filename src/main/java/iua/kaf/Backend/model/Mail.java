package iua.kaf.Backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	String mail;
}
