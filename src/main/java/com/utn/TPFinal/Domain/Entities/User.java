package com.utn.TPFinal.Domain.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String userName;
	private String firstName;
	private String lastName;
	private String dni;

	@ManyToOne(fetch = FetchType.EAGER)
	private City city;
	@ManyToOne(fetch = FetchType.EAGER)
	private UserType userType;

	@OneToMany(mappedBy = "user")
	@JsonBackReference(value="user-phoneLine")
	private List<PhoneLine> phoneLines;
	@OneToMany(mappedBy = "user")
	@JsonBackReference(value="user-bill")
	private List<Bill> bills;
}
