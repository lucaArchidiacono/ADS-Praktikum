package Praktikum_01_Code;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLServer implements CommandExecutor {
	private String elementRegex = "(?<=<)(\\/?[a-zA-Z0-9-_]+)(?=(\\s[^<]*)?>)";

	@Override
	public String execute(String command) {
		return Boolean.toString(checkWellformed(command));
	}

	private boolean checkWellformed(String arg) {
		return getNextToken(arg).isEmpty();
	}

	private ListStack getNextToken(String arg) {
		ListStack listStack = new ListStack();
		Matcher elementMatcher = Pattern.compile(elementRegex).matcher(arg);
		while (elementMatcher.find()) {
			String tagName = elementMatcher.group();
			String lastTagName = "/" + listStack.peek();
			if (!tagName.contains("/")) {
				listStack.push(tagName);
			} else if (!listStack.isEmpty() && lastTagName.equals(tagName)) {
				listStack.pop();
			}
		}
		return listStack;
	}
}
