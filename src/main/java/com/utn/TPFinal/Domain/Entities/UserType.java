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
@Table(name = "usertypes")
public class UserType {
	@Id
	@Column(name="id")
	private Integer id;
	@Column(name="name")
	private String name;

	@OneToMany(mappedBy = "userType")
	@JsonBackReference(value="userType-user")
	private List<User> users;
}
