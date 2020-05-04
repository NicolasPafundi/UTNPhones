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
@Table(name = "phonelines")
public class PhoneLine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer number;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	@ManyToOne(fetch = FetchType.EAGER)
	private City city;
	@ManyToOne(fetch = FetchType.EAGER)
	private PhoneLineType phoneLineType;

	@OneToMany(mappedBy = "phoneLine")
	@JsonBackReference
	private List<Bill> bills;
	@OneToMany(mappedBy = "lineFrom")
	@JsonBackReference
	private List<Call> callsFrom;
	@OneToMany(mappedBy = "lineTo")
	@JsonBackReference
	private List<Call> callsTo;
}
