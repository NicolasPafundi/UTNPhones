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
@Table(name = "rates")
public class Rate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double price;

	@ManyToOne(fetch = FetchType.EAGER)
	private City cityFrom;
	@ManyToOne(fetch = FetchType.EAGER)
	private City cityTo;

	@OneToMany(mappedBy = "rate")
	@JsonBackReference(value="rate-call")
	private List<Call> calls;
}
