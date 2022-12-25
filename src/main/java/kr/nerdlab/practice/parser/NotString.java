package kr.nerdlab.practice.parser;

import java.util.Map;

public interface NotString<T> {
	void setConfig(NotStringConfig<T> config);
	String getString();

	@Override
	String toString();
	String toNotString();

	void setDictionary(Map<String, T> dictionary);

	void setSource(String source);

}
