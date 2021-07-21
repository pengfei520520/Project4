import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import static org.junit.Assert.*;
import static org.junit.Assert.*;
public class RunLocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }
    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;
        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;
        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }
        private String getOutput() {
            return testOut.toString();
        }
        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);

        }
        String first = "Choose 1 to sign up, 2 to sign in, or 3 to exit: ";
        String singError = "Please choose option 1, 2, or 3!";
        String exit = "Exiting. Have a good day!";
        String insertNameUp = "Insert name for profile: ";
        String insertNameIn = "Insert name of profile: ";
        String insertAge = "Enter your age: ";
        String negAgeError = "Please enter a non negative integer for your age!";
        String ageError = "Please enter your age as an integer!";
        String genderString = "Please input your gender (Male, Female, or your chosen gender):";
        String passwordUp = "Please create a password for your profile";
        String passwordWeak = "Your password is weak. There are no numbers or special characters";
        String passwordMed = "Your password is medium. There are no special characters in your password";
        String passwordStrong = "Your password is strong";
        String redoPassword = "Would you like to create a stronger password (Yes/No)?";
        String passwordYesNo = "Please choose Yes or No!";
        String passwordIn = "Please type in your password for your profile";
        String oldAccount = "The username and/or password you have used does not correlate to any account";
        String newName = "Enter new name of profile";


        String logOut = "Logging out";

        String menu = "Choose an option:\n1. Change name of your profile\n2. Change age on your profile\n" +
                "3. Change your gender on your profile\n4. Change your password of your profile\n" +
                "5. Access Social Media App\n6. Delete your account\n7. Exit";

        @Test(timeout = 1000)
        public void testExpectedTwo() {
            String input = "1\nFAR\n19\nMale\nMikie27\nNo\n7";

            String expected = first + "\n" + insertNameUp + "\n" + insertAge + "\n" + genderString + "\n" + passwordUp +
                    "\n" + passwordMed + "\n" + redoPassword + "\n" + menu + "\n" + logOut + "\n";

            receiveInput(input);
            ProfileUsage.main(new String[0]);

            String output = getOutput();

            output = output.replace("\r\n", "\n");

            assertEquals("Make sure your output matches the expected case", expected.trim(), output.trim());
        }

        @Test(timeout = 1000)
        public void testExpectedThree() {
            String input = "1\nDerek\n12\nMale\nTw3lv!\n1\nDerekck\n7";

            String expected = first + "\n" + insertNameUp + "\n" + insertAge + "\n" + genderString + "\n" + passwordUp +
                    "\n" + passwordStrong + "\n" + menu + "\n" + newName + "\n" + menu + "\n" + logOut;

            receiveInput(input);
            ProfileUsage.main(new String[0]);

            String output = getOutput();

            output = output.replace("\r\n", "\n");

            assertEquals("Make sure your output matches the expected case", expected.trim(), output.trim());
        }

        @Test(timeout = 1000)
        public void testExpectedFour() {
            String input = "3";
            String expected = first + "\n" + exit;

            receiveInput(input);
            ProfileUsage.main(new String[0]);

            String output = getOutput();

            output = output.replace("\r\n", "\n");

            assertEquals("Make sure your output matches the expected case", expected.trim(), output.trim());
        }

        @Test(timeout = 1000)
        public void testExpectedFive() {
            String input = "1\nOliver\n-1\naba\n2\nNonBinary\nILUV!Mikie\nNo\n2\n27\n1\nOllie\n7";
            String expected = first + "\n" + insertNameUp + "\n" + insertAge + "\n" + negAgeError + "\n" + insertAge +
                    "\n" + ageError + "\n" + insertAge + "\n" + genderString + "\n" + passwordUp + "\n" + passwordMed +
                    "\n" + redoPassword + "\n" + menu + "\n" + newName + "\n" + menu + "\n" + logOut;

            receiveInput(input);
            ProfileUsage.main(new String[0]);

            String output = getOutput();

            output = output.replace("\r\n", "\n");

            assertEquals("Make sure your output matches the expected case", expected.trim(), output.trim());
        }
    }
}
