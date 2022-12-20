package kr.nerdlab.practice.parser;

public interface ReplaceHandler<T> {
	String handle(String id, T value);
}
