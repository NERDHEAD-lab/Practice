package kr.nerdlab.practice.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * TODO CHECK LIST
 * dictionary의 key값에 대한 규칙을 적용 할 것인가?
 * surroundWith의 자유로운 변경을 허용 할 것인가?
 */
public class DefaultNotString extends NotStringBase<String> {

	public DefaultNotString(String source) {
		super(source, new HashMap<>());
	}

	public DefaultNotString(String source, Map<String, String> dictionary) {
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



}
