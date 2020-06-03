package com.utn.TPFinal.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@Column(name="Id")
	private Integer id;
	@Column(name="username")
	private String userName;
	@Column(name="name")
	private String firstName;
	@Column(name="lastname")
	private String lastName;
	@Column(name="dni")
	private String dni;
	@JsonIgnore
	@Column(name="password")
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="city_id")
	private City city;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="usertype_id")
	private UserType userType;

	@OneToMany(mappedBy = "user")
	@JsonBackReference(value="user-phoneLine")
	private List<PhoneLine> phoneLines;
	@OneToMany(mappedBy = "user")
	@JsonBackReference(value="user-bill")
	private List<Bill> bills;
}
