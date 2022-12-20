package kr.nerdlab.practice.parser;

import java.util.Map;

public interface NotString<T> {
	void setConfig(NotConfig<T> config);
	String getString();

	String toNotString();

	void setDictionary(Map<String, T> dictionary);

	void setSource(String source);

}
