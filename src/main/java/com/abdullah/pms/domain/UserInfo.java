package com.abdullah.pms.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.abdullah.pms.domain.security.Authority;
import com.abdullah.pms.domain.security.UserRole;
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
public class UserInfo implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	Date joiningDate;
	//private int gradeId;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();
	
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorites = new HashSet<>();
		userRoles.forEach(ur -> authorites.add(new Authority(ur.getRole().getName())));
		return authorites;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
}
