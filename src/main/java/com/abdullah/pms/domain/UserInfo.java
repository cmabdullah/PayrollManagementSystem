package com.abdullah.pms.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Builder
public class UserInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //add this 
	//@Column(name = "id", unique = true, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	private int id;
//	@NotBlank
//	@Size(min=2  ,max=15)
//	@Pattern(regexp="^\\w{4,}$")
	private String username;
//	@NotBlank
//	@Size(min=3  ,max=15)
//	@Pattern(regexp="^\\S+$")
	private String password;
	//javascript validation perpose
	private String confirmPassword;
	private boolean enabled;
	private String authority;
//	@Size(min=4  ,max=250)
	private String fullname;
//	@Size(min=4  ,max=250)
	private String address;
//	@NotNull
	//@Pattern(regexp=".*\\@.*\\..*", message = "Not a valied email address")
	private String email;
	private int phone;
	LocalDateTime joiningDate;
	//private int gradeId;
	
	@OneToOne
	@JoinColumn(name="gradeId")
	private Grade grade;
	
	@OneToOne
	@JoinColumn(name="shiftId")
	private Shift shift;
	
	@OneToMany
	//(mappedBy = "userInfo")
	@JsonIgnore
	private List<Attendance> attendance;
}
