package com.abdullah.pms.service;

import java.nio.file.Path;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface BiomatricRestService {

	Path checkValidation(MultipartFile file);

	int decodeUserIdFromPath(Path path);

}
