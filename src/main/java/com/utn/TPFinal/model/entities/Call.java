package com.utn.TPFinal.model.entities;

import java.util.Date;

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
	@Column(name="id")
	private Integer id;
	@Column(name="duration")
	private double duration;
	@Column(name="amount")
	private double amount;
	@Column(name="createdon")
	private Date createdOn;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="lineid_from")
	private PhoneLine lineFrom;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="lineid_to")
	private PhoneLine lineTo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="rate_id")
	private Rate rate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="bill_id")
	private Bill bill;
}
