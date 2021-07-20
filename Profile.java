/**
 *
 * Profile
 *
 * This class creates a profile object and allows the data to be saved into a file whenever the program ends
 *
 * @author Mikie Kilbourne, L04
 *
 * @version July 18th, 2021
 *
 */

public class Profile {
    private String name;
    private String age;
    private String gender;
    private String password;

    //Constructor that takes name, age, and gender
    public Profile(String name, String age, String gender, String password) {
        this.name = name;
        this.age = age;

        //Puts the gender to all lower case letters
        String lowerGender = gender.toLowerCase();

        //Selection structure that determines the gender of the profile
        if (lowerGender.equals("male")) {
            this.gender = "Male";
        } else if (lowerGender.equals("female")) {
            this.gender = "Female";
        } else {
            this.gender = gender;
        }
        this.password = password;
    }

    //Method that gets name
    public String getName() {
        return name;
    }

    //Method that gets age
    public String getAge() {
        return age;
    }

    //Method that gets gender
    public String getGender() {
        return gender;
    }

    //Method that gets the password
    public String getPassword() {
        return password;
    }

    //Able to change name on the account
    public void setName(String name) {
        this.name = name;
    }

    //Able to change age on the account
    public void setAge(String age) {
        this.age = age;
    }

    //Able to change gender on the account
    public void setGender(String gender) {
        this.gender = gender;
    }

    //ABle to change password on the account
    public void setPassword(String password) {
        this.password = password;
    }
}
