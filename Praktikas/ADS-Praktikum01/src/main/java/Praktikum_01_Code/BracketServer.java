package Praktikum_01_Code;

public class BracketServer implements CommandExecutor {
	private char[] legalChars = {'{', '}', '(', ')', '[', ']'};

	@Override
	public String execute(String command) throws Exception {
		return Boolean.toString(checkBrackets(command));
	}

	public boolean checkBrackets(String arg) {
		ListStack listStack = new ListStack();
		for (char singleChar : arg.toCharArray()) {
			if (nextChar(singleChar) != Character.MIN_VALUE) {
				switch (singleChar) {
					case '{':
					case '(':
					case '[':
						listStack.push(singleChar);
						break;
					case ')':
						if (checkList(listStack, '(')) return false;
						break;
					case ']':
						if (checkList(listStack, '[')) return false;
						break;
					case '}':
						if (checkList(listStack, '{')) return false;
						break;
				}
			}
		}

		return true;
	}

	private boolean checkList(ListStack listStack, char character) {
		return (!listStack.isEmpty()) && !listStack.pop().equals(character);
	}

	private char nextChar(char singleChar) {
		for (char legalChar : legalChars) {
			if (singleChar == legalChar) {
				return singleChar;
			}
		}
		return Character.MIN_VALUE;
	}
}
