package com.utn.TPFinal.domain.Entities;

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
@Table(name = "states")
public class State {
	@Id
	@Column(name="id")
	private Integer id;
	@Column(name="name")
	private String name;

	@OneToMany(mappedBy = "state")
	@JsonBackReference(value="state-city")
	private List<City> cities;
}
