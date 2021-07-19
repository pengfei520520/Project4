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
    private int age;
    private String gender;

    //Constructor that takes name, age, and gender
    public Profile(String name, int age, String gender) {
        this.name = name;
        this.age = age;

        //Puts the gender to all lower case letters
        String lowerGender = gender.toLowerCase();

        //Selection structure that determines the gender of the profile
        if (lowerGender.equals("boy") || lowerGender.equals("man") || lowerGender.equals("male")) {
            this.gender = "Male";
        } else if (lowerGender.equals("girl") || lowerGender.equals("woman") || lowerGender.equals("female")) {
            this.gender = "Female";
        } else {
            this.gender = "Other";
        }
        this.gender = gender;
    }

    //Method that gets name
    public String getName() {
        return name;
    }

    //Method that gets age
    public int getAge() {
        return age;
    }

    //Method that gets gender
    public String getGender() {
        return gender;
    }
}
