import java.util.ArrayList;
import java.util.Comparator;

/**
 * Post class
 *
 * <p>Purdue University -- CS18000 -- Summer 2021</p>
 *
 * @author Henry Emmert
 * @version July 17, 2021
 */

public class Post {

    private String title; //title of post
    private String author; //author of post
    private String content; //content of post
    private String time; //timestamp of post
    private ArrayList<Comment> comments = new ArrayList<>();

    public Post(String title, String author, String content, String time) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.time = time;
    }

    /**
     * gets the title of the post
     * @return post title
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets the title of the post
     * @param title title of the post to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets the author of the post
     * @return post author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * sets the author of the post
     * @param author author of the post to be set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * gets the content of the post
     * @return post content
     */
    public String getContent() {
        return content;
    }

    /**
     * sets the content of the post
     * @param content content of the post to be set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * gets the timestamp of the post
     * @return post timestamp
     */
    public String getTime() {
        return time;
    }

    /**
     * sets the timestamp of the post
     * @param time timestamp of the post to be set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * gets the comment list of the post
     * @return post comments
     */
    public ArrayList<Comment> getComments() {
        return comments;
    }

    /**
     * adds a comment to the list of comments
     * @param comment comment list of the post to be set
     */
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    /**
     * sets comments list
     * @param comments comment list of the post to be set
     */
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    /**
     * gets the time in seconds
     * @return time in seconds
     */
    public double getTimeSeconds() {
        double year = Double.parseDouble(time.substring(0, 4));
        double month = Double.parseDouble(time.substring(5, 7));
        double day = Double.parseDouble(time.substring(8, 10));
        double hour = Double.parseDouble(time.substring(11, 13));
        double minute = Double.parseDouble(time.substring(14, 16));
        double second = Double.parseDouble(time.substring(17, 23));

        year = year * 365 * 24 * 60 * 60;
        month = month * 30 * 24 * 60 * 60;
        day = day * 24 * 60 * 60;
        hour = hour * 60 * 60;
        minute = minute * 60;

        double output = year + month + day + hour + minute + second;
        return output;
    }
}
