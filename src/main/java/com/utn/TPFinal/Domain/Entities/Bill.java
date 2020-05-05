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
	@Column(name="id")
	private Integer id;
	@Column(name="calls_amount")
	private Integer callsAmount;
	@Column(name="price_cost")
	private double priceCost;
	@Column(name="price_final")
	private double priceFinal;
	@Column(name="payday")
	private Date payDay;
	@Column(name="createdon")
	private Date createdOn;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="phoneline_id")
	private PhoneLine phoneLine;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;

	@OneToMany(mappedBy = "bill")
	@JsonBackReference(value="bill-call")
	private List<Call> calls;
}
