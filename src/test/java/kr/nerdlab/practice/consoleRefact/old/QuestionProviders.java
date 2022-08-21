package kr.nerdlab.practice.consoleRefact.old;

public class QuestionProviders {
	static {
		Questions.Q_Start.setProvider(QuestionProvider.of("안녕하세요",
				answers -> answers
						.answer("네 안녕하세요", () -> QuestionProviders.of(Questions.Q_A_1).run())
						.answer("ㄴㄴ 꺼지샘", () -> QuestionProviders.of(Questions.Q_A_2).run())
		));

		Questions.Q_A_1.setProvider(QuestionProvider.of("친절하시네요",
				answers -> answers
						.answer("ㄳㄳ", () -> QuestionProviders.of(Questions.Q_B).run())
						.answer("ㅇㅇ", () -> QuestionProviders.of(Questions.Q_B).run())
		));
		Questions.Q_A_2.setProvider(QuestionProvider.of("불친절하시네요",
				answers -> answers
						.answer("ㅎㅎ", () -> QuestionProviders.of(Questions.Q_B).run())
						.answer("ㅗㅗ", () -> QuestionProviders.of(Questions.Q_B).run())
		));


		Questions.Q_B.setProvider(QuestionProvider.of("오늘 날씨 괜찮았지요",
				answers -> answers
						.answer("맑아서 좋았지요", () -> QuestionProviders.of(Questions.Q_B_1).run())
						.answer("비가와서 슬펏지요", () -> QuestionProviders.of(Questions.Q_B_1).run())
						.answer("ㅗㅗ", () -> QuestionProviders.of(Questions.Q_B_2).run())
						.answer("네?", () -> QuestionProviders.of(Questions.Q_B).run())
		));

		Questions.Q_B_1.setProvider(QuestionProvider.of("오늘하루도 좋은날 되세요",
				answers -> answers
						.answer("ㄳㄳ", () -> System.out.println("ㅃㅇㅇ"))
		));

		Questions.Q_B_2.setProvider(QuestionProvider.of("씹새끼야",
				answers -> answers
						.answer("ㄳㄳ", () -> System.out.println("ㅗㅗㅗㅗㅗㅗㅗ"))
						.answer("다시 물어봐줘", () -> {
							System.out.println("ㅇㅇ");
							QuestionProviders.of(Questions.Q_Start).run();
						})
		));
	}

	public static QuestionProvider of(Questions question) {
		return question.getQuestionProvider();
	}
}
