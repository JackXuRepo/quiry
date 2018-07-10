package cscc01.summer2018.team11;

import cscc01.summer2018.team11.file.Parser;

public class ParserTest {

	public static void main(String[] args) {
		// print file content
		if (args.length == 1) {
			System.out.println(Parser.getContent(args[0]));
		} else {
			System.out.println("Only one file at a time.");
		}
	}
	
}
