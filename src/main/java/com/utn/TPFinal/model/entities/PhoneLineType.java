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
@Table(name = "phonelinetype")
public class PhoneLineType {
	@Id
	@Column(name="id")
	private Integer id;
	@Column(name="name")
	private String name;

	@OneToMany(mappedBy = "phoneLineType")
	@JsonBackReference(value="phoneLineType-phoneLine")
	private List<PhoneLine> phoneLines;
}
