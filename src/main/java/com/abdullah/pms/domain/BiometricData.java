package com.abdullah.pms.domain;

import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BiometricData {
	private int id;
	@Transient
	private MultipartFile biometricImage;

}
