import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * ProfileUsage
 *
 * Allows user to create their own profile and do several different actions with their file/
 *
 * @author Mikie Kilbourne, L04
 *
 * @version July 19th, 2021
 */
public class ProfileUsage {
    public static void main(String[] args) {
        String signOption; //1 if creating new account 2 if signing in
        String name; //Name on profile
        String age = null; //Age of the user
        String gender = null; //Gender of profile
        String password; //The password of the user
        String newPassword; //A string that states whether the the user wants to remake their password
        Profile userProfile = null; //Profile the user creates or logs into
        ArrayList<Profile> profileList = new ArrayList<Profile>(); //List of profiles on the website
        boolean nameExists; //False if name does not already exist, true if name does already exist
        boolean ageIncorrect; //False if age is possible, true if age is not possible
        boolean accountReal; //True if account is real. False if account does not exist

        Scanner scanner = new Scanner(System.in);
        ProfileUsage usage = new ProfileUsage();

        usage.getAllProfiles(profileList);
        //Continues until user is signed up, signed in to an account that already exists, or exits
        do {
            accountReal = false;
            //Do while loop that continues until an option is taken.
            do {
                System.out.println("Choose 1 to sign up, 2 to sign in, or 3 to exit: ");
                signOption = scanner.nextLine();

                if (!signOption.equals("1") && !signOption.equals("2") && !signOption.equals("3")) {
                    System.out.println("Please choose option 1, 2, or 3!");
                } else if (signOption.equals("3")) {
                    System.out.println("Exiting. Have a good day!");
                    usage.saveAllProfiles(profileList);
                    return;
                }
            } while (!signOption.equals("1") && !signOption.equals("2"));

            //Checks to see if the user signing up creates a name that is already made
            if (signOption.equals("1")) {
                //Do while loop that goes until user creates a name that has not being used by a new account
                do {
                    nameExists = false;
                    System.out.println("Insert name for profile: ");
                    name = scanner.nextLine();

                    //For loop that goes through all the names in the list of profile names
                    for (int ct = 0; ct < profileList.size(); ct++) {
                        if (profileList.get(ct).getName().equals(name)) {
                            System.out.println("The profile name you have creates already exists. You can not use it.");
                            nameExists = true;
                            break;
                        }
                    }
                } while (nameExists);
            } else { //Occurs if the user is signing in
                //Asks user for their name and allows them to input it
                System.out.println("Insert name for profile (no numbers): ");
                name = scanner.nextLine();
            }

            if (signOption.equals("1")) {

                //do while loop that makes sure the user inputs an integer
                do {
                    ageIncorrect = false;
                    //Asks user for age and allows them to input it
                    System.out.println("Enter your age: ");
                    age = scanner.nextLine();

                    //Loop that goes through all characters in age string to make sure it is a positive integer
                    for (int ct = 0; ct < age.length(); ct++) {
                        ageIncorrect = false;
                        //Gets ascii value of each character in age
                        char ageChar = age.charAt(ct);
                        int ascii = ageChar;

                        //Occurs if the number is negative
                        if (ascii == 45) {
                            System.out.println("Please enter a non negative integer for your age!");
                            ageIncorrect = true;
                            break;
                        } else if (ascii < 48 || ascii > 57) { //Occurs if age has a character that is not a number
                            System.out.println("Please enter your age as an integer!");
                            ageIncorrect = true;
                            break;
                        }
                    }
                } while (ageIncorrect);

                //Asks the user to input their gender and allows them to input
                System.out.println("Please input your gender (Male, Female, or your chosen gender):");
                gender = scanner.nextLine();

                //A do while loop that continues until password is strong or the user wants to keep their password
                do {
                    System.out.println("Please create a password for your profile");
                    password = scanner.nextLine();

                    //Counters for amount of numbers or special characters in password
                    int passwordNum = 0;
                    int passwordSpecial = 0;

                    //For loop that goes through all the characters of the password to tell the strength of the password
                    for (int ct = 0; ct < password.length(); ct++) {
                        //Gets ascii value of each character in the password
                        char passwordChar = password.charAt(ct);
                        int ascii = passwordChar;

                        //Increments passwordNum if the password has a number in it
                        if (ascii > 47 && ascii < 58) {
                            passwordNum++;
                        } else if (ascii < 64 || (ascii > 90 && ascii < 97) || ascii > 122) {
                            passwordSpecial++; //Increments passwordSpecial if password has a special character in it
                        }
                    }

                    //Selection structures that state the strength level of the password
                    if (passwordNum == 0 && passwordSpecial == 0) {
                        System.out.println("Your password is weak. There are no numbers or special characters");
                    } else if (passwordNum == 0) {
                        System.out.println("Your password is medium. There are no numbers in your password");
                    } else if (passwordSpecial == 0) {
                        System.out.println("Your password is medium. There are no special characters in your password");
                    } else {
                        System.out.println("Your password is strong");
                        break;
                    }


                    //A do while loop that continues until you choose yes or no
                    do {
                        System.out.println("Would you like to create a stronger password (Yes/No)?");
                        newPassword = scanner.nextLine();

                        //Prints error mesage if yes or no is not chosen
                        if (!newPassword.equals("Yes") && !newPassword.equals("No")) {
                            System.out.println("Please choose Yes or No!");
                        } else {
                            break;
                        }
                    } while (true);

                } while (newPassword.equals("Yes"));
            } else {
                System.out.println("Please type in your password for your profile");
                password = scanner.nextLine();
            }

            //Creates new account if new option is chosen
            if (signOption.equals("1")) {
                userProfile = new Profile(name, age, gender, password);
                profileList.add(userProfile);
            } else {
                int listSize = profileList.size();

                //Goes through all the profiles in the list
                for (int ct = 0; ct < listSize; ct++) {
                    int attributeCt = 0;
                    Profile searchedProfile = profileList.get(ct);

                    //Selection structure to check if name and password equal name and password of searchedProfile
                    if (searchedProfile.getName().equals(name)) {
                        attributeCt++;
                    } if (searchedProfile.getPassword().equals(password)) {
                        attributeCt++;
                    }

                    //Occurs if name and password are correct
                    if (attributeCt == 2) {
                        userProfile = searchedProfile;
                        break;
                    }

                    if (ct == listSize - 1) {
                        System.out.println("The username and/or password you have used does not correlate to any " +
                                "account");
                        accountReal = true;
                    }
                }
            }
        } while (accountReal);

        //A do while loop that continues until exited
        do {
            String optionString; //Action chosen by user as a string
            int option;
            //A do while loop that occurs until an option is chosen
            do {
                //Allows user to pick an action to do on profile
                System.out.println("Choose an option:\n1. Change name of your profile\n2. Change age on your profile");
                System.out.println("3. Change your gender on your profile\n4. Change your password of your profile");
                System.out.println("5. Access Social Media App\n6. Delete your account\n7. Exit");
                optionString = scanner.nextLine();

                //Grabs ASCII value of first character chosen to check if it is a number between 1 and 8.
                char optionChar = optionString.charAt(0);
                int ascii = optionChar;

                //Selection structure that occurs if number 1-8 was not chosen
                if (ascii < 49 || ascii > 55 || optionString.length() < 1) {
                    System.out.println("Choose an option 1-6!");
                } else {
                    break;
                }
            } while (true);

            //Turns the optionString into an integer
            option = Integer.parseInt(optionString);

            if (option == 1) {
                System.out.println("Enter new name of profile");
                String newName = scanner.nextLine();
                int profileMadeCt = 0;

                for (int ct = 0; ct < profileList.size(); ct++) {
                    if (profileList.get(ct).getName().equals(newName)) {
                        profileMadeCt++;
                        System.out.println("The profile name you have creates already exists. Choose a new name");
                        break;
                    }
                }
                if (profileMadeCt == 0) {
                    userProfile.setName(newName);
                }
            } else if (option == 2) {
                //do while loop that makes sure the user inputs an integer
                do {
                    ageIncorrect = false;
                    //Asks user for age and allows them to input it
                    System.out.println("Enter your age: ");
                    age = scanner.nextLine();

                    //Loop that goes through all characters in age string to make sure it is a positive integer
                    for (int ct = 0; ct < age.length(); ct++) {
                        ageIncorrect = false;
                        //Gets ascii value of each character in age
                        char ageChar = age.charAt(ct);
                        int ascii = ageChar;

                        //Occurs if the number is negative
                        if (ascii == 45) {
                            System.out.println("Please enter a non negative integer for your age!");
                            ageIncorrect = true;
                            break;
                        } else if (ascii < 48 || ascii > 57) { //Occurs if age has a character that is not a number
                            System.out.println("Please enter your age as an integer!");
                            ageIncorrect = true;
                            break;
                        }
                    }
                } while (ageIncorrect);
                userProfile.setAge(age);
            } else if (option == 3) {
                System.out.println("Please input your gender (Male, Female, or your chosen gender):");
                gender = scanner.nextLine();
                userProfile.setGender(gender);
            } else if (option == 4) {
                do {
                    System.out.println("Please create a new password for your profile");
                    password = scanner.nextLine();

                    //Counters for amount of numbers or special characters in password
                    int passwordNum = 0;
                    int passwordSpecial = 0;

                    //For loop that goes through all the characters of the password to tell the strength of the password
                    for (int ct = 0; ct < password.length(); ct++) {
                        //Gets ascii value of each character in the password
                        char passwordChar = password.charAt(ct);
                        int ascii = passwordChar;

                        //Increments passwordNum if the password has a number in it
                        if (ascii > 47 && ascii < 58) {
                            passwordNum++;
                        } else if (ascii < 64 || (ascii > 90 && ascii < 97) || ascii > 122) {
                            passwordSpecial++; //Increments passwordSpecial if password has a special character in it
                        }
                    }

                    //Selection structures that state the strength level of the password
                    if (passwordNum == 0 && passwordSpecial == 0) {
                        System.out.println("Your new password is weak. There are no numbers or special characters");
                    } else if (passwordNum == 0) {
                        System.out.println("Your new password is medium. There are no numbers in your password");
                    } else if (passwordSpecial == 0) {
                        System.out.println("Your new password is medium. There are no special characters in your " +
                                "password");
                    } else {
                        System.out.println("Your new password is strong");
                        break;
                    }

                    //A do while loop that continues until you choose yes or no
                    do {
                        System.out.println("Would you like to create a stronger password (Yes/No)?");
                        newPassword = scanner.nextLine();

                        //Prints error message if yes or no is not chosen
                        if (!newPassword.equals("Yes") && !newPassword.equals("No")) {
                            System.out.println("Please choose Yes or No!");
                        } else {
                            break;
                        }
                    } while (true);

                } while (newPassword.equals("Yes"));

                userProfile.setPassword(password);
            } else if (option == 5) {
               PostDriver driver = new PostDriver(userProfile);

            } else if (option == 6) {
                profileList.remove(userProfile);
            } else if (option == 7) {
                System.out.println("Logging out");
                usage.saveAllProfiles(profileList);
                return;
            }
        } while (true);
    }


    //Saves all profiles into a file at the end of the program
    public void getAllProfiles(ArrayList<Profile> profileList) {
        try {
            FileReader reader = new FileReader("profiles.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null) {
                profileList.add(new Profile(line, bufferedReader.readLine(), bufferedReader.readLine(),
                        bufferedReader.readLine()));
                line = bufferedReader.readLine();
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

    //Writes all the profiles into a file at the end of the program
    public void saveAllProfiles(ArrayList<Profile> profileList) {
        try {
            PrintWriter printWriter = new PrintWriter("profiles.txt");
            printWriter.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("profiles.txt"));

            //Writes into file in a loop
            for (int i = 0; i < profileList.size(); i++) {
                bufferedWriter.write(formatProfile(profileList.get(i)));
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //Formats the profile parameters so it can be put into a file
    public String formatProfile(Profile profile) {
        String output = profile.getName() + "\n" + profile.getAge() + "\n" + profile.getGender() + "\n" +
                profile.getPassword();
        return output;
    }
}


