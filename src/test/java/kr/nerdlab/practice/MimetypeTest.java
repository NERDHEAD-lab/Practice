package kr.nerdlab.practice;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class Mimetype {
	public static void main(String[] args) throws Throwable {
		Files.probeContentType(Path.of(""));
		File file = new File("");



	}
}
