package Praktikum_01_Code;

public class KGVServer implements CommandExecutor {

	@Override
	public String execute(String command) throws Exception {
		String[] numbers = command.split(" ");
		int a = Integer.parseInt(numbers[0]);
		int b = Integer.parseInt(numbers[1]);
		return Integer.toString(kgv(a,b));
	}

	public int kgv(int a, int b) {
		int z1temp = a;
		int z2temp = b;

		while (z1temp != z2temp) {
			if (z1temp < z2temp) {
				z1temp += a;
			} else {
				z2temp+= b;
			}
		}
		return z1temp;
	}
}
