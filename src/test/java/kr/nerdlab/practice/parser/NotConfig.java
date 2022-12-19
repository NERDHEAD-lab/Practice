package kr.nerdlab.practice.parser;

import java.util.function.Function;

public class NotConfig<T> {
	protected String startWith = "${";
	protected String endWith = "}";
	protected Function<String, String> replaceEmptyValue;

	private NotConfig() {
	}

	public static <TYPE> NotConfig<TYPE> create(NotTypeReference<TYPE> ref) {
		return new NotConfig<>();
	}


	public NotConfig<T> surroundWith(String startWith, String endWith) {
		this.startWith = startWith;
		this.endWith = endWith;

		return this;
	}

	public NotConfig<T> replaceEmptyValue(Function<String, String> idToWhat) {
		this.replaceEmptyValue = idToWhat;
		return this;
	}
}
