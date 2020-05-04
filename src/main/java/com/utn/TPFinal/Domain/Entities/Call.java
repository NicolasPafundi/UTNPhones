package com.utn.TPFinal.Domain.Entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "calls")
public class Call {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double duration;
	private double amount;
	private Date createdOn;

	@ManyToOne(fetch = FetchType.EAGER)
	private PhoneLine lineFrom;
	@ManyToOne(fetch = FetchType.EAGER)
	private PhoneLine lineTo;
	@ManyToOne(fetch = FetchType.EAGER)
	private Rate rate;
	@ManyToOne(fetch = FetchType.EAGER)
	private Bill bill;
}
