/**
 * Comment class
 *
 * <p>Purdue University -- CS18000 -- Summer 2021</p>
 *
 * @author Henry Emmert
 * @version July 19, 2021
 */

public class Comment {

    private String author; //author of post
    private String content; //content of post
    private String time; //timestamp of post

    public Comment(String author, String content, String time) {
        this.author = author;
        this.content = content;
        this.time = time;
    }

    /**
     * gets the author of the comment
     *
     * @return comment author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * sets the author of the comment
     *
     * @param author author of the comment to be set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * gets the content of the comment
     *
     * @return comment content
     */
    public String getContent() {
        return content;
    }

    /**
     * sets the content of the comment
     *
     * @param content content of the comment to be set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * gets the timestamp of the comment
     *
     * @return comment timestamp
     */
    public String getTime() {
        return time;
    }

    /**
     * sets the timestamp of the comment
     *
     * @param time timestamp of the comment to be set
     */
    public void setTime(String time) {
        this.time = time;
    }
}

