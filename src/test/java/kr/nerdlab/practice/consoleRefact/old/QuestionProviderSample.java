package kr.nerdlab.practice.consoleRefact.old;

import kr.nerdlab.practice.BaseSample;
import org.junit.Test;

import java.util.Scanner;

public class QuestionProviderSample extends BaseSample {

	public static void main(String[] args) {
//		new QuestionProviderSample().reviewCode();
		new QuestionProviderSample().reviewCode2();
	}


	@Override
	public void originalCode() {
		System.out.println("배팅 할 곳을 선택하세요.");
		System.out.println("Player - 1");
		System.out.println("Banker - 2");
		Scanner st = new Scanner(System.in);
		// ...
		int i = 0;
		while (i == 0) {
			System.out.println("계속 하시겠습니까?");
			System.out.println("Yes - 1");
			System.out.println("No  - 2");
			Scanner sc = new Scanner(System.in);
			int stop = sc.nextInt();
			if (stop == 1) {
				//game.play(gamer);
			} else {
				System.out.println("게임을 종료합니다.");
				i++;
			}
		}
	}


	@Test
	@Override
	public void reviewCode() {
//		QuestionProvider provider = QuestionProvider.of(
//				"계속 하시겠습니까?",
//				answers ->
//						answers.answer("Yes", () -> System.out.println("게임을 종료합니다."))
//								.answer("No", () -> System.out.println("게임을 종료합니다."))
//		);


//Case 1
		QuestionProvider.of(
				"질문입니다!",
				answers
						-> answers
							.answer("나는 바보다", () -> System.out.println("바보 ㅇㅈㅇㅈ"))
							.answer("나는 바보가 아닌디유", () -> System.out.println("엥 바보 맞는디유"))
		).run();
//		int result = provider.runWithResult();
	}

	public void reviewCode2() {
		QuestionProviders.of(Questions.Q_Start).run();
	}
}
