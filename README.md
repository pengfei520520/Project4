# Project4

In order to use this program, compile and run ProfileUsage.java.

Submitted on Vocarium: Henry Emmert
Submitted on Brightspace: Mikie Kilbourne



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



Profile Class, Mikie Kilbourne

The Profile class has a simple constructor that sets all the fields to the parameters. It also has get and set methods for all of it's fields. This class is used by the ProfileUsage class to make profiles, check if a profile has already been made, and allow the user to change attributes of their profile. I tested the Profile class by using the constructor in the main of the ProfileUsage, changing attributes of the profiles by using the set methods, and then printed them to make sure that they worked.



ProfileUsage Class, Mikie Kilbourne

The ProfileUsage class is a large class that has the main that calls all other methods and classes in the project. It saves and retrieves all the profiles that have been made. It retrieves them at the beginning of the program and puts it into an arraylist of profiles. It then saves it at the end of the program. It also allow the user to either sign in to their profile or sign up and make a profile. The program prints errors if the user makes a mistake when making a profileand allows them to retry. If the user is signing up it makes sure that an account with that name has already been created. If they are signing up they input their name, age, gender and password. The account tells them if their password is weak, medium, or strong based on whether it has any numbesr or speica characters. If they are signing in they make sure that profile does indeed exist and allows them to access. Once they have either signed in or their account is created they can edit attributes using set methods from the Profile class. They delete their profile by removing it from the arraylist of profiles. If they want to post or comment on a post the main calls the PostDriver. The program exits when the user chooses to log out. I tested the class thoroughly by creating names, ages, and genders to make sure the error messages pop up. I checked that the strength of the password is correct by inputting several different password. I checked to see if all the attributes are changed in the profile when the set methods are called by printing the attributes by calling their get methods. I also tested to make sure that the profiles are saved and properly retrieved when the program starts back up by making several accounts, exitiing the program, and then trying to sign into them. All these tests were succesful.

