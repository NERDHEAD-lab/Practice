package kr.nerdlab.practice.file;

import java.io.File;
import java.util.List;

public class TestSample {
	private File file;
	private String data;

	private List<TestData> testData;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<TestData> getTestData() {
		return testData;
	}

	public void setTestData(List<TestData> testData) {
		this.testData = testData;
	}

	public static class TestData {
		private String testData1;
		private String testData2;

		public TestData(String testData1, String testData2) {
			this.testData1 = testData1;
			this.testData2 = testData2;
		}

		public String getTestData1() {
			return testData1;
		}

		public void setTestData1(String testData1) {
			this.testData1 = testData1;
		}

		public String getTestData2() {
			return testData2;
		}

		public void setTestData2(String testData2) {
			this.testData2 = testData2;
		}
	}
}
