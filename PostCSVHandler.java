import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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

	public ArrayList<Post> importFromCSV(String csvFile) throws FileNotFoundException {
		ArrayList<Post> list = new ArrayList<>();

		File f = new File(csvFile);
		FileReader fr = new FileReader(f);
		BufferedReader bfr = null;

		try {
			bfr = new BufferedReader(fr);
			String line;
			while ((line = bfr.readLine()) != null) {
				String[] datas = line.split(",");
				String title = datas[0];
				String author = datas[1];
				String content = datas[2];
				String time = datas[3];
				list.add(new Post(title, author, content, time));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bfr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public void saveToCSV(ArrayList<Post> list, String csvFile) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(csvFile));
		for (int i = 0; i < list.size(); i++) {
			Post cPost = list.get(i);
			pw.printf("%s,%s,%s,%s\n", cPost.getTitle(), cPost.getAuthor(), cPost.getContent(), cPost.getTime());
		}
		pw.close();
	}

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Post> list = PostCSVHandler.getInstance().importFromCSV("post.csv");
		PostCSVHandler.getInstance().saveToCSV(list, "post_save.csv");
	}

}
