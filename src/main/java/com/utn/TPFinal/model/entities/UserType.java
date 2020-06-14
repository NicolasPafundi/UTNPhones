package com.utn.TPFinal.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.utn.TPFinal.model.Enum.UserTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usertypes")
public class UserType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Enumerated(EnumType.STRING)
	@Column(name="name")
	private UserTypes name;

	@OneToMany(mappedBy = "userType")
	@JsonBackReference(value="userType-user")
	private List<User> users;
}
