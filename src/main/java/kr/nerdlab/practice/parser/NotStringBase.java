package kr.nerdlab.practice.parser;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Map;
import java.util.function.Function;

public abstract class NotStringBase<T> implements Serializable, NotString<T> {
	private String source;
	private Map<String, T> dictionary;
	private NotStringConfig<T> config;

	public NotStringBase(String source, Map<String, T> dictionary) {
		this.source = source;
		this.dictionary = dictionary;
		this.config = config();
	}

	private NotStringConfig<T> config() {
		NotStringConfig<T> tNotStringConfig = NotStringConfig.create(new NotTypeReference<>() {});

		return tNotStringConfig.replaceEmptyValue(initReplaceEmptyValue());
	}

	//TODO : if null, throw.
	@Override
	public void setConfig(NotStringConfig<T> config) {
		this.config = config;
	}

	@Override
	public String toNotString() {
		return source;
	}

	@Override
	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public void setDictionary(Map<String, T> dictionary) {
		this.dictionary = dictionary;
	}

	public NotStringBase<T> addDictionary(String id, T value) {
		this.dictionary.put(id, value);

		return this;
	}

	abstract String replaceId(String id, T value);

	abstract Function<String, String> initReplaceEmptyValue();

	/* 정규표현식? */
	private String replace(ReplaceHandler<T> handler) {
		final StringBuilder result = new StringBuilder();

		NotId idCheck = new NotId();
		char[] charArray = source.toCharArray();

		for (char ch : charArray) {
			//시작 했으면 닫힐때 까지 id에 append 한다.
			if (idCheck.isStarted()) {
				if (idCheck.checkEnd(ch)) {
					if (idCheck.isEnded()) {
						//닫히는 순간 id를 반환한다.
						String id = idCheck.getId();
						String apply = handler.handle(id, dictionary.get(id));
						Function<String, String> emptyAction = config.replaceEmptyValue;
						result.append(StringUtils.isNotEmpty(apply) ? apply : emptyAction.apply(id));
						idCheck.reset();
					}
				}
			} else if (!idCheck.checkStart(ch)) {
				//시작 체크는 실패하면 쌓여있던 temp를 같이 result에 append한다.
				result.append(idCheck.temp);
				//리셋
				idCheck.reset();
			}
		}
		return result.append(idCheck.temp).toString();
	}

	@Override
	public String toString() {
		return getString();
	}

	@Override
	public String getString() {
		return replace(this::replaceId);
	}

	private class NotId {
		boolean[] startIndex;
		boolean[] endIndex;
		StringBuilder temp;
		int cursor;

		public NotId() {
			init();
		}

		private void init() {
			startIndex = new boolean[config.startWith.length()];
			endIndex = new boolean[config.endWith.length()];

			temp = new StringBuilder();
		}

		public void reset() {
			init();
		}

		public String getId() {
			return temp.substring(startIndex.length, temp.length() - endIndex.length);
		}

		public boolean checkStart(char ch) {
			return check(ch, startIndex, config.startWith.toCharArray());
		}

		public boolean checkEnd(char ch) {
			return check(ch, endIndex, config.endWith.toCharArray());

		}

		private boolean check(char ch, boolean[] indexArray, char[] with) {
			temp.append(ch);
			for (int i = 0; i < indexArray.length; i++) {
				boolean index = indexArray[i];
				if (index) continue;

				if (with[i] == ch) {
					indexArray[i] = true;
					return true;
				} else {
					return false;
				}
			}
			return false;
		}

		public boolean isStarted() {
			for (boolean index : startIndex) {
				if (!index) return false;
			}
			return true;
		}

		public boolean isEnded() {
			for (boolean index : endIndex) {
				if (!index) return false;
			}
			return true;
		}

	}

}
