# Project4

In order to use this program, compile and run ProfileUsage.java.

Submitted on Vocarium: ???????????????????????
Submitted on Brightspace: ????????????????????????



Post Class, Henry Emmert

Post is a class that is used to create post objects. Each post object stores information such as author,
title, time, post content, and an array of all of comments on each post.

This class interacts with PostDriver, Comment, SearchCommentPost, CommentEditor, and PostCSVHandler to
transfer information about the posts between classes.

This class was tested by setting and getting information to and from Post objects.



Comment Class, Henry Emmert

Comment is a class that is used to create comment objects. Each comment object stores information such
as author, time, and comment content. 

This class interacts with PostDriver,Comment, SearchCommentPost, CommentEditor, and PostCSVHandler to
transfer information about the posts between classes.

This class was tested by getting and setting information to and from Comment objects



PostDriver Class, Henry Emmert

PostDriver is a class used to run various other classes. It is called by ProfileUsage. It starts by displaying
a menu of options for the user to select from. The user can choose to create a post, edit a post, delete
a post, display all posts, search for a user's posts and comments, create, edit, and delete comments, import from csv, export to
csv, and go back to the profile menu. This class also imports and exports all of the post and comment data
to a txt file so that it is saved between uses. This class also contains the logic to create, edit, and delete
posts. Posts can only be editted or deleted by the author of the post.

This class interacts closely with all of the other classes.

This class was tested by running the various other programs from the menu to make sure they are called correctly,
then creating posts and comments to make sure that they are saved between uses. The create, edit, delete, and display
functionalities were tested by using several different profile names to create posts, then trying to edit and delete posts
with both the correct and incorrect profiles. Then the display was tested by making sure that all posts were displayed
with the most recent post being first.



SortByDate Class, Henry Emmert

SortByDate is a very short class used to sort all of the posts by date. It just compares the timestamps and outputs 1 or -1
depending on which timestamp is more recent.

This class interacts close with PostDriver.

This class was tested by making sure all of the posts were displayed in order based on time in PostDriver.



CommentEditor Class, Greg Keller

Comment editor class is called when the user chooses option 6 within the networking app
this means that they would like to create, edit, or delete a comment
to create a comment the post simply has to exist by the author that the current profile inputs
it is then added to the array list of comments under that post
if the user wants to edit a comment we have to make sure the comment exists under the correct post
by the correct author of the post, and we must also check that the current profile is the
author of that comment and then we can change the content of the comment
to delete a comment it is quite similar to editing as the comment must exist
under the correct post by the correct author, and the current profile must be the author
of the comment and then we can delete it.

This class interacts closely with the profile and post classes as they uses getters from both classes
as it needs to access information about the current user and the post prompted by the user.

to verify it works we had to test creating multiple profiles and see whether or not we are able to create comments
and properly find the post that we would like to comment on.




