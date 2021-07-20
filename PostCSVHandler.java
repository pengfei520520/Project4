import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;
import java.sql.Timestamp;
import java.util.ArrayList;

public class PostCSVHandler {

	private static PostCSVHandler handler = null;

	private PostCSVHandler() {
	}

	public static PostCSVHandler getInstance() {
		if (handler == null) {
			handler = new PostCSVHandler();
		}

		return handler;
	}

	public ArrayList<Post> importFromCSV(Scanner scanner) {
		String csvFile;
		System.out.print("Enter the csv file to import: ");
		csvFile = scanner.nextLine().strip();

		ArrayList<Post> list = new ArrayList<>();

		BufferedReader bfr = null;

		try {
			bfr = new BufferedReader(new FileReader(new File(csvFile)));
			String line;
			while ((line = bfr.readLine()) != null) {
				String[] datas = line.split(",");
				String title = datas[0];
				String author = datas[1];
				String content = datas[2];
				// update time
				String time = new Timestamp(new Date().getTime()).toString(); // datas[3];
				list.add(new Post(title, author, content, time));
			}
			System.out.printf("%s imported!\n", csvFile);
		} catch (FileNotFoundException e) {
			System.out.printf("%s doesn't exist!\n", csvFile);
		} catch (IOException e) {
			System.out.println("Error happens when importing from csv!");
			list.clear();
		}

		return list;
	}

	public void saveToCSV(ArrayList<Post> list, Scanner scanner) {
		String csvFile;
		System.out.print("Enter the csv file to save: ");
		csvFile = scanner.nextLine().strip();

		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(csvFile));
			for (int i = 0; i < list.size(); i++) {
				Post cPost = list.get(i);
				pw.printf("%s,%s,%s,%s\n", cPost.getTitle(), cPost.getAuthor(), cPost.getContent(), cPost.getTime());
			}
			System.out.printf("%s saved!\n", csvFile);
		} catch (FileNotFoundException e) {
			System.out.printf("%s doesn't exist!\n", csvFile);
		} finally {
			pw.close();
		}
	}
}
