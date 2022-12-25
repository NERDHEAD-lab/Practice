package kr.nerdlab.practice.parser;

import java.util.function.Function;

public class NotStringConfig<T> {
	protected String startWith = "${";
	protected String endWith = "}";
	protected Function<String, String> replaceEmptyValue;

	private NotStringConfig() {
	}

	public static <TYPE> NotStringConfig<TYPE> create(NotTypeReference<TYPE> ref) {
		return new NotStringConfig<>();
	}


	public NotStringConfig<T> surroundWith(String startWith, String endWith) {
		this.startWith = startWith;
		this.endWith = endWith;

		return this;
	}

	public NotStringConfig<T> replaceEmptyValue(Function<String, String> idToWhat) {
		this.replaceEmptyValue = idToWhat;
		return this;
	}
}
