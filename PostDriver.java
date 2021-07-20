import java.io.*;
import java.util.*;
import java.sql.Timestamp;

/**
 * Runs all of the programs for posts and comments
 *
 * <p>Purdue University -- CS18000 -- Summer 2021</p>
 *
 * @author Henry Emmert
 * @version July 17, 2021
 */

public class PostDriver {

    private ArrayList<Post> posts = new ArrayList<Post>(); //array of all posts

    //runs all of the code related to posts
    public PostDriver(Profile profile) {
        Scanner scan = new Scanner(System.in);
        boolean quit = true; //quit loop switch

        this.getAllPosts();

        do {

            //menu
            System.out.println("""
                    What would you like to do?
                    (1) New Post
                    (2) Edit post
                    (3) Delete post
                    (4) View All Posts
                    (5) Search for all of a user's posts and comments
                    (6) Create, edit, or delete a comment
                    (7) Import post
                    (8) Export post
                    (9) Exit""");
            String input = scan.nextLine(); //input from user

            switch (input) {

                //new post
                case "1":
                    //gets title
                    System.out.println("Enter the title of your post:");
                    String title = scan.nextLine();

                    //gets author
                    String author = profile.getName();

                    //gets content
                    System.out.println("Enter the content of your post:");
                    String content = scan.nextLine();

                    //gets time
                    Date date = new Date();
                    long time = date.getTime();
                    Timestamp timeStamp = new Timestamp(time);
                    String timeStampString = "" + timeStamp;

                    posts.add(new Post(title, author, content, timeStampString));

                    break;

                //edit post
                case "2":
                    int counter; //counter variable
                    int input1 = 0; //input from user for post selection
                    ArrayList<Post> userPosts = new ArrayList<>();
                    ArrayList<Integer> userPostNum = new ArrayList<>();

                    //gets a list of posts that the user is allowed to edit
                    System.out.println("Select a post to edit:");
                    do {
                        counter = 0;
                        for (int i = 0; i < posts.size(); i++) {
                            if (posts.get(i).getAuthor().equals(profile.getName())) {
                                counter++;
                                System.out.println("\n(" + counter + ") " + formatPost(posts.get(i)));
                                userPosts.add(posts.get(i));
                                userPostNum.add(i);
                            }
                        }

                        if (counter == 0) {
                            System.out.println("You have no posts to edit.");
                        } else {
                            input1 = scan.nextInt();
                            scan.nextLine();
                        }

                        if (counter != 0 && (input1 < 1 || input1 > counter)) {
                            System.out.println("Invalid input. Try again.");
                            counter = -1;
                        }
                    } while (counter == -1);

                    if (input1 != 0) {
                        input1--;
                        boolean quit2 = true; //quit loop switch

                        //lets the user enter the edited post information
                        do {
                            System.out.println("""
                                    What would you like to edit?
                                    (1) Title
                                    (2) Content
                                    (3) Cancel""");
                            String input2 = scan.nextLine(); //input from user for editing selection

                            switch (input2) {

                                //edit title
                                case "1":
                                    System.out.println("Enter the new title:");
                                    userPosts.get(input1).setTitle(scan.nextLine());
                                    quit2 = false;
                                    break;

                                //edit content
                                case "2":
                                    System.out.println("Enter the new content");
                                    userPosts.get(input1).setContent(scan.nextLine());
                                    quit2 = false;
                                    break;

                                //quit
                                case "3":
                                    quit2 = false;
                                    break;

                                //invalid
                                default:
                                    System.out.println("Invalid input. Try again.");
                                    break;
                            }

                            //sets all edited posts back into the main post list
                            for (int i = 0; i < userPosts.size(); i++) {
                                posts.set(userPostNum.get(i), userPosts.get(i));
                            }
                        } while (quit2);
                    }
                    break;

                //delete post
                case "3":
                    int counter2; //counter variable
                    int input2 = 0; //input from user for post selection
                    ArrayList<Integer> userPostNum2 = new ArrayList<>();

                    //gets a list of posts that the user is allowed to delete
                    System.out.println("Select a post to delete:");
                    do {
                        counter2 = 0;
                        for (int i = 0; i < posts.size(); i++) {
                            if (posts.get(i).getAuthor().equals(profile.getName())) {
                                counter2++;
                                System.out.println("\n(" + counter2 + ") " + formatPost(posts.get(i)));
                                userPostNum2.add(i);
                            }
                        }

                        if (counter2 == 0) {
                            System.out.println("You have no posts to delete.");
                        } else {
                            input2 = scan.nextInt();
                            scan.nextLine();
                        }

                        if (counter2 != 0 && (input2 < 1 || input2 > counter2)) {
                            System.out.println("Invalid input. Try again.");
                            counter2 = -1;
                        }
                    } while (counter2 == -1);

                    //deletes post from list
                    if (input2 != 0) {
                        input2--;
                        int deleteIndex = userPostNum2.get(input2); //index of the post to be deleted.
                        posts.remove(deleteIndex);
                    }
                    break;

                //view all posts
                case "4":
                    Collections.sort(posts, new SortByDate());

                    for (int i = 0; i < posts.size(); i++) {
                        System.out.println("\n" + formatPost(posts.get(i)));
                    }
                    break;

                //search for all of a user's comments and posts
                case "5":
                    SearchCommentPost search = new SearchCommentPost(posts);
                    search.searchPosts();
                    break;

                //create, edit, or delete a comment
                case "6":
                    new CommentEditor(profile, posts);
                    break;

                //import post
                case "7":
                    posts.addAll(PostCSVHandler.getInstance().importFromCSV(scan));
                    break;

                //export post
                case "8":
                	PostCSVHandler.getInstance().saveToCSV(posts, scan);
                    break;

                //quit
                case "9":
                    quit = false;
                    break;

                //invalid input
                default:
                    System.out.println("Invalid input. Try again.");
                    break;
            }
        } while (quit);

        this.saveAllPosts();
    }

    /**
     * gets all of the previous posts from a text file
     */
    public void getAllPosts() {
        try {
            FileReader reader = new FileReader("posts.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null) {
                posts.add(new Post(line, bufferedReader.readLine(), bufferedReader.readLine(),
                        bufferedReader.readLine()));
                line = bufferedReader.readLine();
                while (line != null && line.equals("Comment:")) {
                    posts.get(posts.size() - 1).addComment(new Comment(bufferedReader.readLine(),
                            bufferedReader.readLine(), bufferedReader.readLine()));
                    line = bufferedReader.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            try {
                PrintWriter printWriter = new PrintWriter("posts.txt");
            } catch (IOException e2) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * writes all posts to a text file
     */
    public void saveAllPosts() {
        try {
            PrintWriter printWriter = new PrintWriter("posts.txt");
            printWriter.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("posts.txt"));

            for (int i = 0; i < posts.size(); i++) {
                bufferedWriter.write(formatPost(posts.get(i)));
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * formats the post to be saved or printed
     * @param post the post to be formatted
     * @return the formatted string
     */
    public String formatPost(Post post) {
        String output = post.getTitle() + "\n" + post.getAuthor() + "\n" + post.getContent() + "\n" +
                post.getTime();
        String outputComments = "";
        for (int i = 0; i < post.getComments().size(); i++) {
            outputComments += "\nComment:\n";
            outputComments += post.getComments().get(i).getAuthor() + "\n" + post.getComments().get(i).getContent() +
            "\n" + post.getComments().get(i).getTime();
        }
        output += outputComments;
        return output;
    }
}
