package com.utn.TPFinal.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.utn.TPFinal.model.Enum.PhoneLineTypes;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Enumerated(EnumType.STRING)
	@Column(name="name")
	private PhoneLineTypes name;

	@OneToMany(mappedBy = "phoneLineType")
	@JsonBackReference(value="phoneLineType-phoneLine")
	private List<PhoneLine> phoneLines;
}
