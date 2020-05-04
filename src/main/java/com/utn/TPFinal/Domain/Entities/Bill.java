package com.utn.TPFinal.Domain.Entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bills")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer callsAmount;
	private double priceCost;
	private double priceFinal;
	private Date payDay;
	private Date createdOn;

	@ManyToOne(fetch = FetchType.EAGER)
	private PhoneLine phoneLine;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@OneToMany(mappedBy = "bill")
	@JsonBackReference
	private List<Call> calls;
}
