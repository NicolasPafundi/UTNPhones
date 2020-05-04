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
@Table(name="cities")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer areaCode;

	@ManyToOne(fetch = FetchType.EAGER)
	private State state;

	@OneToMany(mappedBy = "city")
	@JsonBackReference
	private List<User> users;
	@OneToMany(mappedBy = "city")
	@JsonBackReference
	private List<PhoneLine> phoneLines;
	@OneToMany(mappedBy = "cityFrom")
	@JsonBackReference
	private List<Rate> ratesFrom;
	@OneToMany(mappedBy = "cityTo")
	@JsonBackReference
	private List<Rate> ratesTo;
}
