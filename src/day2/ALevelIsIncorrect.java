package day2;

public class ALevelIsIncorrect extends Exception {
	public ALevelIsIncorrect() {
		super("Report need a new test without a removed value");
	}
}
