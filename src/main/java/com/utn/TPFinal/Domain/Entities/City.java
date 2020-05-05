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
	@Column(name="id")
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="arecode")
	private Integer areaCode;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="state_id")
	private State state;

	@OneToMany(mappedBy = "city")
	@JsonBackReference
	private List<User> users;
	@OneToMany(mappedBy = "city")
	@JsonBackReference(value="city-phoneLine")
	private List<PhoneLine> phoneLines;
	@OneToMany(mappedBy = "cityFrom")
	@JsonBackReference(value="city-rateFrom")
	private List<Rate> ratesFrom;
	@OneToMany(mappedBy = "cityTo")
	@JsonBackReference(value="city-rateTo")
	private List<Rate> ratesTo;
}
