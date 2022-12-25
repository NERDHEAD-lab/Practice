package kr.nerdlab.practice.parser;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class NotStringTest {
	private static final Logger logger = LoggerFactory.getLogger(NotStringTest.class);

	@Test
	public void notStringWithMap() {
		String source = "hello ${data} and $data";
		NotString<String> nStr = new DefaultNotString(source, new HashMap<>() {{
			put("data", "world");
		}});

		Assert.assertEquals(nStr.toString(), source.replaceAll("\\$\\{data}", "world"));
	}

	@Test
	public void notStringWithMapAndConfig() {
		String source = "hello ${{data}} and ${data}";
		NotString<String> nStr = new DefaultNotString(source, new HashMap<>() {{
			put("data", "world");
		}});
		nStr.setConfig(
				NotStringConfig.create(new NotTypeReference<String>() {
						})
						.surroundWith("${{", "}}")
		);

		Assert.assertEquals(nStr.toString(), source.replaceAll("\\$\\{\\{data}}", "world"));
	}
}
