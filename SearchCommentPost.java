import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs searching based on author names, then return post and comment related
 *
 * <p>Purdue University -- CS18000 -- Summer 2021</p>
 *
 * @author Juntao Shi
 * @version July 18, 2021
 */

public class SearchCommentPost {
    private ArrayList<Post> posts = new ArrayList<Post>();
    Scanner scan = new Scanner(System.in);
    String[] upperCaseCommentsNmaes;
    int size;
    boolean found = false;

    public SearchCommentPost(ArrayList<Post> posts) {
        this.posts = posts;
        size = 0;
        for (int i = 0; i < posts.size(); i++) {
            size += posts.get(i).getComments().size();
        }
        upperCaseCommentsNmaes = new String[size];
    }

    public void searchPosts() {
        int a = 0;
        String[] upperCasePostsNmaes = new String[posts.size()]; //convert everything in author to upper case
        int x = 0;
        for (int i = 0; i < posts.size(); i++) {
            upperCasePostsNmaes[i] = posts.get(i).getAuthor().toUpperCase();
            for (int j = 0; j < posts.get(i).getComments().size(); j++) {
                upperCaseCommentsNmaes[x++] = posts.get(i).getComments().get(j).getAuthor().toUpperCase();
            }
        }
        System.out.println("Enter the name you are searching"); // check valid numbers are entered .
        String authorName = scan.nextLine().toUpperCase();
        for (int i = 0; i < posts.size(); i++) { //print out all the posts related with their title, name, and content.
            // NO timestamps are included.
            if (upperCasePostsNmaes[i].contains(authorName) && !authorName.isBlank()) {
                System.out.println("Author name: " + posts.get(i).getAuthor() + "\nTitle: " +
                        posts.get(i).getTitle() + "\nContent: " + posts.get(i).getContent() + "\n");
                found = true;
            }
            for (int j = 0; j < posts.get(i).getComments().size(); j++) {                       //print comments after
                if (upperCaseCommentsNmaes[a++].contains(authorName) && !authorName.isBlank()) {
                    System.out.println("Comment author: " +
                            posts.get(i).getComments().get(j).getAuthor() + "\nContent: " +
                            posts.get(i).getComments().get(j).getContent() + "\n");
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("This author not found!");
        }
    }


}