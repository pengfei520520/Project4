# Project4


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
as it needs to access information about the current user and the post prompted by the user

to verify it works we had to test creating multiple profiles and see whether or not we are able to create comments
and properly find the post that we would like to comment on
