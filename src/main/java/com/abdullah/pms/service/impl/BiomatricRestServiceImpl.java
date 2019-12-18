package com.abdullah.pms.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abdullah.pms.service.BiomatricRestService;
import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

@Service
public class BiomatricRestServiceImpl implements BiomatricRestService{

	@Override
	public Path checkValidation(MultipartFile file) {

		List<Path> bioList = new ArrayList<>();
		List<Path> filteredResult = new ArrayList<>();
		try {
			MultipartFile biometricImage = file ;
			byte[] candidateImage = biometricImage.getBytes();
			bioList = Files.list(Paths.get(
					"src/main/resources/static/image/biometric/"))
					.collect(Collectors.toList());
		
			filteredResult = bioList.parallelStream().filter(n -> {
				boolean matches = false;
				try {
					byte[] probeImage = Files.readAllBytes(n);
					FingerprintTemplate probe = new FingerprintTemplate(new FingerprintImage().dpi(500).decode(candidateImage));
					FingerprintTemplate candidate = new FingerprintTemplate(new FingerprintImage().dpi(500).decode(probeImage));
					double score = new FingerprintMatcher().index(probe).match(candidate);
					double threshold = 40;
					 matches = score >= threshold;
				} catch (IOException e) {
					e.printStackTrace();
				}
				return matches;
			}).collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Optional<Path> result = filteredResult.stream().findFirst();
		return result.get();
	}

	@Override
	public int decodeUserIdFromPath(Path path) {
		String[] paths = path.toString().split("/");
		String res = paths[paths.length-1].split("\\.")[0];
		return Integer.parseInt(res);
	}

}
