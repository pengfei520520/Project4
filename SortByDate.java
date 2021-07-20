import java.util.Comparator;

/**
 * Sorts posts by date
 *
 * <p>Purdue University -- CS18000 -- Summer 2021</p>
 *
 * @author Henry Emmert
 * @version July 20, 2021
 */

public class SortByDate implements Comparator<Post> {

    /**
     * Compares the time of two posts
     * @param post1 first post to be compared
     * @param post2 second post to be compared
     * @return comparison between both posts
     */
    public int compare(Post post1, Post post2) {
        int output;

        if (post1.getTimeSeconds() <= post2.getTimeSeconds()) {
            output = 1;
        } else if (post1.getTimeSeconds() > post2.getTimeSeconds()) {
            output = -1;
        } else {
            output = 0;
        }

        return output;
    }
}
