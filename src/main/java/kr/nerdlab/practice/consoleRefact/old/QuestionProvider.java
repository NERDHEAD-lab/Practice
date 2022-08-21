package kr.nerdlab.practice.consoleRefact.old;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class QuestionProvider {
	private final String question;
	private final List<Answer> answers;


	private QuestionProvider(String question, Consumer<Answers> answersConsumer) {
		Answers answers = new Answers();
		answersConsumer.accept(answers);

		this.question = question;
		this.answers = answers.getAnswers();

	}

	public static QuestionProvider of(String question, Consumer<Answers> answersConsumer) {
		return new QuestionProvider(question, answersConsumer);
	}


	public static QuestionProvider of(String question, Answers answers) {
		return null;
	}


	public void run() {
		runWithResult();
	}

	//TODO : abstract로 기능 이동 및 질의, 응답에 대한 구현이 가능하도록 변경 필요.
	public int runWithResult() {
		System.out.println(question);

		for (int i = 0; i < answers.size(); i++) {
			System.out.println(i + 1 + ". " + answers.get(i).getAnswer());
		}
		//TODO : outOfRange 예외처리, retry 기능
		int i = new Scanner(System.in).nextInt();

		answers.get(i-1).getAction().run();

		return i;
	}

	static class Answers {
		private final List<Answer> answers;

		private Answers() {
			this.answers = new ArrayList<>();
		}

		public Answers answer(String answer, Runnable action) {
			answers.add(Answer.of(answer, action));
			return this;
		}

		public List<Answer> getAnswers() {
			return answers;
		}
	}

	//Just Entity
	static class Answer {
		private final String answer;
		private final Runnable action;

		private Answer(String answer, Runnable action) {
			this.answer = answer;
			this.action = action;
		}

		public static Answer of(String answer, Runnable action) {
			return new Answer(answer, action);
		}

		public String getAnswer() {
			return answer;
		}

		public Runnable getAction() {
			return action;
		}
	}
}
