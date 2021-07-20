import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Searches for and edits, creates, and deletes comments
 *
 * <p>Purdue University -- CS18000 -- Summer 2021</p>
 *
 * @author Greg Keller
 * @version July 19, 2021
 */
public class CommentEditor {
    Scanner s = new Scanner(System.in);
    boolean quit = false;


    public CommentEditor (Profile profile, ArrayList<Post> allPosts) {

        do {
            // takes in input to choose what action to take
            System.out.println("What would you like to do \n(1) Create a comment\n(2) " +
                    "Delete a comment\n(3) Edit a comment\n(4) Quit");
            String input = s.nextLine();
            String author;
            String title;

            switch (input) {
                case "1":

                    //gets author and title of post
                    System.out.println("What is the Author of the post would you like to create a comment on?");
                    author = s.nextLine();
                    System.out.println("What is the title of the post would you like to create a comment on?");
                    title = s.nextLine();
                    for (Post post : allPosts) {
                        //searches all posts from all users
                        if (title.equalsIgnoreCase(post.getTitle()) && post.getAuthor().equalsIgnoreCase(author)) {
                            //if the title and the author match,
                            // then get the content from user and add to posts comments
                            System.out.println("What would you like the comment to say?");
                            String content = s.nextLine();
                            Date date = new Date();
                            long time = date.getTime();
                            Timestamp timestamp = new Timestamp(time);
                            String timeStampString = "" + timestamp;
                            Comment addedComment = new Comment(profile.getName(), content, timeStampString);
                            post.getComments().add(addedComment);

                        } else {
                            System.out.println("Invalid input, try again");
                        }
                    }
                    break;
                case "2":
                    //get author and title of post to delete pre existing comment
                    System.out.println("What is the Author of the post would you like to delete a comment on?");
                    author = s.nextLine();
                    System.out.println("What is the title of the post would you like to delete a comment on?");
                    title = s.nextLine();

                    for (Post post : allPosts) {
                        if (title.equalsIgnoreCase(post.getTitle()) && post.getAuthor().equalsIgnoreCase(author)) {
                            //search through all posts and if the title matches and the author of the post matches
                            //and the author of the comment is the current user then delete it
                            System.out.println("What is the content of the comment would you like to delete?");
                            String commentContent = s.nextLine();
                            for (int i = 0; i < post.getComments().size(); i++) {
                                if (post.getComments().get(i).getAuthor().equals(profile.getName()) &&
                                        post.getComments().get(i).getContent().equals(commentContent)) {
                                    post.getComments().remove(post.getComments().get(i));

                                }

                            }

                        } else {
                            System.out.println("Invalid input, try again");
                        }

                    }
                    break;

                case "3":
                    System.out.println("What is the title of the post would you like to edit a comment on?");
                    title = s.nextLine();
                    for (Post post : allPosts) {
                        //looks through all posts
                        if (title.equalsIgnoreCase(post.getTitle())) {
                            //if the title matches then
                            System.out.println("What is the content of the comment would you like to edit?");
                            String commentContent = s.nextLine();
                            for (int i = 0; i < post.getComments().size(); i++) {
                                //check if the content of the comment is contained in the list of comments for this post
                                //and the author of the comment is the current user then get new input and set to that
                                if (post.getComments().get(i).getContent().equalsIgnoreCase(commentContent)
                                        && post.getComments().get(i).getAuthor().equals(profile.getName())) {
                                    System.out.println("What would you like the new comment to say?");
                                    String newCommentContent = s.nextLine();
                                    Date date = new Date();
                                    long time = date.getTime();
                                    Timestamp timestamp = new Timestamp(time);
                                    String timeStampString = "" + timestamp;
                                    Comment newComment = new Comment(profile.getName(), newCommentContent,
                                            timeStampString);
                                    post.getComments().add(i, newComment);
                                } else {
                                    System.out.println("Invalid input, try again");
                                }


                            }

                        } else {
                            System.out.println("Invalid input, try again");
                        }

                    }
                    break;

                case "4":
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid input, try again");
            }
        } while (!quit);
    }
}
