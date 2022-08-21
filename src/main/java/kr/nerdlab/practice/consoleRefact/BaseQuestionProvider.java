package kr.nerdlab.practice.consoleRefact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public abstract class BaseQuestionProvider {
	private final String question;
	private final ConsoleQuestionProvider.QuestionOptions options;

	protected BaseQuestionProvider(String question, Consumer<ConsoleQuestionProvider.QuestionOptions> options) {
		this.question = question;
		this.options = QuestionOptions.of(options);
	}

	protected BaseQuestionProvider(String question, String option, Runnable action) {
		this(question, options -> {
			options.option(option, action, true);
		});
	}

	public void run() {
		printQuestion(question);
		if (options.isSingle) {
			QuestionOption option = options.list.get(0);
			printOptionAsSingle(option.option);
			option.action.run();
		} else {
			for (int i = 0; i < options.list.size(); i++) {
				printOptionWithIndex(i, options.list.get(i).option);
			}
			int index = selectIndex();
			options.list.get(index).action.run();
		}
	}

	protected abstract void printQuestion(String question);

	protected abstract void printOptionAsSingle(String option);

	protected abstract void printOptionWithIndex(int index, String option);

	protected abstract int selectIndex();

	public static class QuestionOptions {
		private List<QuestionOption> list;
		private boolean isSingle = false;

		private QuestionOptions() {
		}

		private static QuestionOptions of(Consumer<QuestionOptions> options) {
			QuestionOptions questionOptions = new QuestionOptions();
			options.accept(questionOptions);
			return questionOptions;
		}

		public QuestionOptions option(String option, Runnable action) {
			return option(option, action, false);
		}

		private QuestionOptions option(String option, Runnable action, boolean isSingle) {
			addOption(option, action, isSingle);
			return this;
		}

		private void addOption(String option, Runnable action, boolean isSingle) {
			if (isSingle) {
				this.isSingle = true;
				list = Collections.singletonList(QuestionOption.of(option, action));
				return;
			} else if (list == null) {
				list = new ArrayList<>();
			}
			list.add(QuestionOption.of(option, action));
		}


	}

	private static class QuestionOption {
		private final String option;
		private final Runnable action;

		private QuestionOption(String option, Runnable action) {
			this.option = option;
			this.action = action;
		}

		private static QuestionOption of(String option, Runnable action) {
			return new QuestionOption(option, action);
		}
	}
}
