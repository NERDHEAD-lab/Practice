package kr.nerdlab.practice.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * TODO CHECK LIST
 * dictionary의 key값에 대한 규칙을 적용 할 것인가?
 * surroundWith의 자유로운 변경을 허용 할 것인가?
 */
public class NotString extends NotStringBase<String> {

	public NotString(String source) {
		super(source, new HashMap<>());
	}

	public NotString(String source, Map<String, String> dictionary) {
		super(source, dictionary);
	}

	@Override
	String replaceId(String id, String value) {
		return value;
	}

	//TODO FIX : 직관적이지 않은 기능
	@Override
	Function<String, String> initReplaceEmptyValue() {
		return id -> id;
	}


	public static void main(String[] args) {
		NotString nStr = new NotString("hello ${data} and $data", new HashMap<>() {{
			put("data", "world");
		}});

		String x1 = nStr.toString();

		nStr = new NotString("hello ${{data}} and ${data}", new HashMap<>() {{
			put("data", "world");
		}});
		nStr.setConfig(
				NotConfig.create(new NotTypeReference<String>() {
						})
						.surroundWith("${{", "}}")
		);

		return;
	}
}
