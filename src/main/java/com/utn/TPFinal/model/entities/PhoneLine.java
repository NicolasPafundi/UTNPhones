package com.utn.TPFinal.model.entities;

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
	@Column(name="id")
	private Integer id;
	@Column(name="numberline")
	private Integer numberLine;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="city_id")
	private City city;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="phonelinetype_id")
	private PhoneLineType phoneLineType;

	@OneToMany(mappedBy = "phoneLine")
	@JsonBackReference(value="phoneLine-bill")
	private List<Bill> bills;
	@OneToMany(mappedBy = "lineFrom")
	@JsonBackReference("phoneLine-callFrom")
	private List<Call> callsFrom;
	@OneToMany(mappedBy = "lineTo")
	@JsonBackReference("phoneLine-callTo")
	private List<Call> callsTo;
}
