package kr.nerdlab.practice.consoleRefact;

public enum Questions {
	Q_Start(null),
	Q_A_1(null),
	Q_A_2(null),
	Q_B(null),
	Q_B_1(null),
	Q_B_2(null);

	private QuestionProvider provider;

	Questions(QuestionProvider provider) {
		this.provider = provider;
	}

	public void setProvider(QuestionProvider provider) {
		this.provider = provider;
	}

	public QuestionProvider getQuestionProvider() {
		return provider;
	}
}
