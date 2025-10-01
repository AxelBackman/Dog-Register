import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InputReader {
	
	private static Set<InputStream> inputStreams = new HashSet<>();
	
	private Scanner scanner;
	
	public InputReader() throws IllegalStateException {
		this(System.in);
	}
	
	public InputReader(InputStream inputStream) throws IllegalStateException {
		if (inputStreams.contains(inputStream)) {
			throw new IllegalStateException("Error");
		}
		inputStreams.add(inputStream);
		this.scanner = new Scanner(inputStream);
	}
	
	public int readInt(String string) {
		System.out.println(string + "?> ");
		int input = scanner.nextInt();
		scanner.nextLine();
		return input;
	}
	
	public double readDouble(String string) {
		System.out.println(string + "?> ");
		double input = scanner.nextDouble();
		scanner.nextLine();
		return input;
	}
	
	public String readLine(String string) {
		System.out.println(string + "?> ");
		String input = scanner.nextLine();
		while (input.trim().isEmpty()) {
			System.out.println("ERROR: A blank string is not allowed, try again");
			input = readLine(string);
		}
		return normalizeString(input);
		
	}
	
	private static String normalizeString(String string) {
		return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
	}
}
