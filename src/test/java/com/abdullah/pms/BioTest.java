package com.abdullah.pms;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.junit.Test;

public class BioTest {

	@Test
	public void att() throws IOException {

		Path path = FileSystems.getDefault().getPath("/src/main/resources/static/image/biometric/1.png");
		String[] paths = path.toString().split("/");
		String res = paths[paths.length-1].split("\\.")[0];
		System.out.println(res);
	}
}
