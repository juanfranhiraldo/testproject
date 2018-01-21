package com.demoqa.test.automatic.main.utils;

import com.demoqa.test.automatic.main.models.Date;

import java.util.Random;

//This class will randomize our data
public class RandomizeData {
    private static final Random randomSeed = new Random(System.currentTimeMillis());
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LETTERS = LOWER_CASE + UPPER_CASE;
    private static final String NUMBERS = "0123456789";
    private static final String ALPHABET = LOWER_CASE + UPPER_CASE + NUMBERS;

    /**
     * A method returning marital status.
     *
     * @return A string representing a marital status
     */
    public static String randomizeMarital() { //Random marital status
        String[] marital = {"single", "married", "divorced"};
        int value = randomSeed.nextInt(3);
        return marital[value];
    }

    /**
     * A method returning a random combination of hobbies.
     *
     * @return A random combination of hobbies
     */
    public static String[] randomizeHobbies() {
        String[] hobbies = {"dance", "reading", "cricket "};
        int value = randomSeed.nextInt(3) + 1;
        String finalHobbies[] = new String[value];
        for (int i = 0; i < value; i++) {
            finalHobbies[i] = "";
        }
        for (int i = 0; i < value; i++) {
            addToRes(hobbies, i, finalHobbies);
        }
        return finalHobbies;
    }

    /**
     * Generate a 3-char string in order to select a random country.
     *
     * @return A 3-char string
     */
    public static String randomizeCountry() {
        StringBuilder countryBuilder = new StringBuilder();
        String res;
        getARandomString(countryBuilder, LOWER_CASE, 3);
        res = countryBuilder.toString();
        return res;
    }

    /**
     * Generate a random Date structure.
     *
     * @return An element from customize Date class including day, month and year (after 1949)
     */
    public static Date randomizeDate() {
        int month = randomSeed.nextInt(12) + 1;
        int day = randomSeed.nextInt(31) + 1;
        int yearIncrement = randomSeed.nextInt(64);
        int year = 1950 + yearIncrement;
        return new Date(day, month, year);
    }

    /**
     * Generate a random phone number.
     *
     * @return A string with a random phone number
     */
    public static String randomizePhoneNumber() {
        StringBuilder phoneBuilder = new StringBuilder();
        int phoneLength = randomSeed.nextInt(5) + 11;
        getARandomString(phoneBuilder, NUMBERS, phoneLength);
        return phoneBuilder.toString();
    }

    /**
     * This method generate a random user name.
     *
     * @return Random user name
     */
    public static String randomizeUserName() {
        String letters = LETTERS;
        int length = randomSeed.nextInt(10) + 5;
        String res = "" + letters.charAt(randomSeed.nextInt(letters.length()));
        StringBuilder userNameBuilder = new StringBuilder();
        getARandomString(userNameBuilder, ALPHABET, length);
        return res + userNameBuilder.toString();
    }

    /**
     * Return a random email.
     *
     * @return Generate a string with a random email
     */
    public static String randomizeEmail() {
        String res;
        String firstPart = "";
        String secondPart;
        String termination;
        StringBuilder wordBuilder = new StringBuilder();
        int lengthFirstPart = randomSeed.nextInt(10) + 5;
        String letters = LETTERS;
        firstPart = firstPart + letters.charAt(randomSeed.nextInt(letters.length()));
        getARandomString(wordBuilder, ALPHABET, lengthFirstPart - 1);
        firstPart = firstPart + wordBuilder.toString();
        wordBuilder.setLength(0);
        int lengthLastPart = randomSeed.nextInt(10) + 4;
        getARandomString(wordBuilder, letters, lengthLastPart);
        secondPart = wordBuilder.toString();
        wordBuilder.setLength(0);
        getARandomString(wordBuilder, letters, 3);
        termination = "." + wordBuilder.toString();
        res = firstPart + "@" + secondPart + termination;
        return res;
    }

    /**
     * Auxiliar method to obtain a random String
     *
     * @param wordBuilder       StringBuilder to store our String temporally
     * @param lettersAndNumbers Main string to obtain random characters
     * @param i2                Number of characters of the string
     */
    private static void getARandomString(StringBuilder wordBuilder, String lettersAndNumbers, int i2) {
        for (int i = 0; i < i2; i++) {
            wordBuilder.append(lettersAndNumbers.charAt(randomSeed.nextInt(lettersAndNumbers.length())));
        }
    }

    /**
     * Generate a String which will be our about text.
     *
     * @return Random text
     */
    public static String randomAbout() {
        String lettersAndNumbers = ALPHABET + " .:@,\n\\//";
        String res;
        int length = randomSeed.nextInt(300);
        StringBuilder resStringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++)
            resStringBuilder = resStringBuilder.append(lettersAndNumbers.charAt(randomSeed.nextInt(lettersAndNumbers.length())));
        res = resStringBuilder.toString();
        return res;
    }

    /**
     * Generate a random password.
     *
     * @return Random password
     */
    public static String randomPassword() {
        String lettersAndNumbers = ALPHABET;
        String res;
        int length = randomSeed.nextInt(20) + 8;
        StringBuilder resSb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            resSb = resSb.append(lettersAndNumbers.charAt(randomSeed.nextInt(lettersAndNumbers.length())));
        }
        res = resSb.toString();
        return res;
    }

    /**
     * Add a hobbie to the list of hobbies.
     *
     * @param hobbies      List of available hobbies
     * @param i            Position in our hobbies selection array
     * @param finalHobbies Selected hobbies
     */
    private static void addToRes(String[] hobbies, int i, String[] finalHobbies) {
        int value = randomSeed.nextInt(3);
        while (containing(hobbies[value], finalHobbies)) {
            value = randomSeed.nextInt(3);
        }
        finalHobbies[i] = hobbies[value];

    }

    /**
     * Auxiliar function to find a hobby in hobbies list.
     *
     * @param hobby        hobby to be found
     * @param finalHobbies Selected hobbies
     * @return Is hobby into the selected ones?
     */
    private static boolean containing(String hobby, String[] finalHobbies) {
        int i = 0;
        boolean found = false;
        while (!found && i < finalHobbies.length) {
            if (finalHobbies[i].equals(hobby)) {
                found = true;
            } else
                i++;
        }
        return found;
    }


}

