package com.abdullah.pms.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Loan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //add this 
	private int id;
	@NotBlank
	private String reason;
	private Date placeDate;
	private Date approveDate;
	private float amount;
//	@NotBlank
//	@Size(min = 3, max = 6)
//	@Pattern(regexp = "[0-9]+")
//	private String amountValidation;
	private int status;
	private String loanType;
	
	@ManyToOne
	@JoinColumn(name="userInfoId")
	private UserInfo userInfo;
	
	@OneToMany(mappedBy="loan")
    private Set<LoanPaidDetails> loanPaidDetails;
	
}
