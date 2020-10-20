package Praktikum_02_Code;
public class BracketServer implements CommandExecutor {
	private char[] legalChars = {'{', '}', '(', ')', '[', ']', '<', '>'};
	private static final char SPECIAL_BRACKET = '*';

	@Override
	public String execute(String command) throws Exception {
		return Boolean.toString(checkBrackets(command));
	}

	public boolean checkBrackets(String arg) {
		ListStack listStack = new ListStack();
		char[] allCharacters = arg.toCharArray();
		for (int i = 0; i<allCharacters.length; i++){
			char currentChar = allCharacters[i];
			char nextChar = getNextChar(i, allCharacters);
			char previousChar = getPreviousChar(i, allCharacters);
			if (checkChar(currentChar)) {
				switch (currentChar) {
					case '{':
					case '(':
					case '[':
						listStack.push(currentChar);
						break;
					case '<':
						listStack.push((nextChar == SPECIAL_BRACKET) ? SPECIAL_BRACKET : currentChar );
						break;
					case ')':
						if (checkStack(listStack, '(')) return false;
						break;
					case '>':
						if (previousChar == SPECIAL_BRACKET) {
							if (checkStack(listStack, SPECIAL_BRACKET)){
								return false;
							}
						} else if (checkStack(listStack, '<')){
							return false;
						}
						break;
					case ']':
						if (checkStack(listStack, '[')) return false;
						break;
					case '}':
						if (checkStack(listStack, '{')) return false;
						break;
				}
			}
		}

		return listStack.isEmpty();
	}

	private boolean checkStack(ListStack listStack, char character) {
		if (listStack.isEmpty()){
			return true;
		} else return (!listStack.isEmpty()) && !listStack.pop().equals(character);
	}

	private boolean checkChar(char singleChar) {
		for (char legalChar : legalChars) {
			if (singleChar == legalChar) {
				return true;
			}
		}
		return false;
	}

	private char getPreviousChar(int index, char[] allCharacters) {
		char previousChar;
		if (index == 0){
			previousChar = allCharacters[index];
		} else {
			previousChar = allCharacters[index-1];
		}
		return previousChar;
	}

	private char getNextChar(int index, char[] allCharacters) {
		int nextIndex = index+1;
		if (nextIndex<allCharacters.length){
			return allCharacters[nextIndex];
		} else {
			return Character.MIN_VALUE;
		}
	}
}
