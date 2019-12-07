package com.abdullah.pms.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class LoanPaidDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // add this
	private int id;
	private Date installmentDate;
	private float paidAmount;
	private float installment;
	
	@ManyToOne
    @JoinColumn(name="loan_id", nullable=false)
    private Loan loan;
}
