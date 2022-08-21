package kr.nerdlab.practice.consoleRefact;

import java.util.Scanner;
import java.util.function.Consumer;

public class ConsoleQuestionProvider extends BaseQuestionProvider {


	private ConsoleQuestionProvider(String question, Consumer<QuestionOptions> options) {
		super(question, options);
	}
	private ConsoleQuestionProvider(String question, String option, Runnable action) {
		super(question, option, action);
	}

	public static BaseQuestionProvider of(String question, Consumer<QuestionOptions> options) {
		return new ConsoleQuestionProvider(question, options);
	}

	public static BaseQuestionProvider of(String question, String option, Runnable action) {
		return new ConsoleQuestionProvider(question, option, action);
	}

	@Override
	protected void printQuestion(String question) {
		System.out.println(question);
	}

	@Override
	protected void printOptionAsSingle(String option) {
		System.out.println(option);
	}

	@Override
	protected void printOptionWithIndex(int index, String option) {
		System.out.println( index + 1 + ". " + option);
	}

	@Override
	protected int selectIndex() {
		return new Scanner(System.in).nextInt() - 1;
	}
}
