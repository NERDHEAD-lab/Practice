package kr.nerdlab.practice.file;

import org.junit.Test;

import java.nio.file.Paths;
import java.util.ArrayList;

public class NotFileTest {
	@Test
	public void saveTest() throws Throwable {
		TestSample sample = testSample();
	}

	@Test
	public void loadTest() throws Throwable {

	}

	@SuppressWarnings(value = "ConstantConditions")
	public static TestSample testSample() throws Throwable {
		TestSample sample = new TestSample();
		sample.setFile(
				Paths.get(NotFileTest.class.getResource("/file/testFile.txt").toURI()).toFile()
		);

		sample.setData("This is 테스트 코드");
		sample.setTestData(new ArrayList<>(){{
			add(new TestSample.TestData("data 1-1", "data 1-2"));
			add(new TestSample.TestData("data 2-1", "data 2-2"));
			add(new TestSample.TestData("data 3-1", "data 3-2"));
		}});

		return sample;
	}
}
