package kr.nerdlab.practice.consoleRefact;

import kr.nerdlab.practice.BaseSample;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class QuestionProviderTest extends BaseSample {
	public static void main(String[] args) {
		new QuestionProviderTest().reviewCode();
	}

	@Override
	public void originalCode() {
		System.out.println("배팅 할 곳을 선택 하세요.");
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
				System.out.println("게임을 종료 합니다.");
				i++;
			}
		}
	}

	@Override
	public void reviewCode() {
		QuestionTestCode.start();
	}


	static class QuestionTestCode {
		private static final Map<Question, BaseQuestionProvider> questions = new HashMap<>() {{
			AtomicBoolean login = new AtomicBoolean(false);

			put(Question.WELCOME, ConsoleQuestionProvider.of("어서 오세요.", options ->
					options
							.option("로그인", () -> get(Question.LOG_IN).run())
							.option("회원 가입", () -> get(Question.NEW_ACCOUNT).run())
							.option("종료", () -> {})
			));
			put(Question.LOG_IN, ConsoleQuestionProvider.of("아이디와 패스워드를 입력해 주세요.",
					"아이디/패스워드",
					() -> {
						if (!login.get()) {
							System.out.print("id : ");
							String id = new Scanner(System.in).nextLine();
							System.out.print("pw : ");
							String pw = new Scanner(System.in).nextLine();
							System.out.print("id : " + id);
							System.out.println(", pw : " + pw);
							System.out.println("로그인 성공!");
						} else {
							System.out.println("자동 로그인!");
						}
						login.set(true);
						get(Question.GAME_START).run();
					})
			);
			put(Question.NEW_ACCOUNT, ConsoleQuestionProvider.of("회원 가입 할 아이디와 패스워드 및 1억원을 계좌 이체 해 주세요. 기업 97900292201015", options ->
					options
							.option("(미구현)", () -> get(Question.WELCOME).run())
			));
			put(Question.GAME_START, ConsoleQuestionProvider.of("게임이 시작 됩니다.", options ->
					options
							.option("(미구현)", () -> get(Question.GAME_FINISHED).run())
			));
			put(Question.GAME_FINISHED, ConsoleQuestionProvider.of("게임이 끝났습니다. 다시 하시겠습니까?", options ->
					options
							.option("YES (처음으로 돌아 갑니다)", () -> get(Question.WELCOME).run())
							.option("NO (게임을 종료 합니다.)", () -> {})
			));
		}};


		public static void start() {
			questions.get(Question.WELCOME).run();
		}
	}
}
