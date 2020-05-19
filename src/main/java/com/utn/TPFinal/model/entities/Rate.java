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
@Table(name = "rates")
public class Rate {
	@Id
	@Column(name="id")
	private Integer id;
	@Column(name="price")
	private double price;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cityid_from")
	private City cityFrom;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cityid_to")
	private City cityTo;

	@OneToMany(mappedBy = "rate")
	@JsonBackReference(value="rate-call")
	private List<Call> calls;
}
