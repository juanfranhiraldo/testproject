package com.demoqa.test.automatic.main.utils;

import com.demoqa.test.automatic.main.models.Date;

import java.util.Random;

//This class will randomize our data
public class RandomizeData {
    private static final Random randomSeed = new Random(System.currentTimeMillis());
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LETTERS = LOWER_CASE+UPPER_CASE;
    private static final String NUMBERS = "0123456789";
    private  static final String ALPHABET = LOWER_CASE+UPPER_CASE+NUMBERS;

    /** A method returning marital status
     *
     * @return A string representing a marital status
     */
    public static String randomizeMarital(){ //Random marital status
        String[] marital={"single","married","divorced"};
        int value=randomSeed.nextInt(3);
        return marital[value];
    }

    /**
     * A method returning a random combination of hobbies
      * @return A random combination of hobbies
     */
    public static String[] randomizeHobbies(){
        String[] hobbies={"dance","reading","cricket "};
        int value=randomSeed.nextInt(3)+1;
        String finalHobbies[]=new String[value];
        for(int i=0;i<value;i++){
            finalHobbies[i]="";
        }
        for(int i=0;i<value;i++){
            addToRes(hobbies,i,finalHobbies);
        }
        return finalHobbies;
    }

    /**
     * Generate a 3-char string in order to select a random country
     * @return A 3-char string
     */
    public static String randomizeCountry(){
        StringBuilder countryBuilder= new StringBuilder();
        String res;
        for(int i=0;i<3;i++) {
            countryBuilder.append(LOWER_CASE.charAt(randomSeed.nextInt(LOWER_CASE.length())));
        }
        res=countryBuilder.toString();
        return res;
    }

    /**
     * Generate a random Date structure
     * @return An element from customize Date class including day, month and year (after 1949)
     */
    public static Date randomizeDate(){
        int month = randomSeed.nextInt(12)+1;
        int day = randomSeed.nextInt(31)+1;
        int yearIncrement = randomSeed.nextInt(64);
        int year = 1950 + yearIncrement;
        return new Date(day,month,year);
    }

    /**
     * Generate a random phone number
     * @return A string with a random phone number
     */
    public static String randomizePhoneNumber(){
        StringBuilder phoneBuilder = new StringBuilder();
        int phoneLength = randomSeed.nextInt(5) + 11;
        for ( int i = 0;i < phoneLength; i++){
            phoneBuilder.append(NUMBERS.charAt(randomSeed.nextInt(NUMBERS.length())));
        }
        return phoneBuilder.toString();
    }

    /**
     * This method generate a random user name
     * @return Random user name
     */
    public static String randomizeUserName(){
        String letters = LETTERS;
        String lettersAndNumbers = ALPHABET;
        int length = randomSeed.nextInt(10)+5;
        String res = "" + letters.charAt(randomSeed.nextInt(letters.length()));
        StringBuilder userNameBuilder = new StringBuilder();
        for (int i = 0;i < length;i++){
            userNameBuilder.append(lettersAndNumbers.charAt(randomSeed.nextInt(lettersAndNumbers.length())));
        }
        return res+userNameBuilder.toString();
    }
    public static String randomizeEmail(){
        String res,secondPart,termination;
        String firstPart="";
        StringBuilder wordBuilder=new StringBuilder();
        int lengthFirstPart=randomSeed.nextInt(10)+5;
        String letters=LOWER_CASE+LOWER_CASE.toUpperCase();
        String lettersAndNumbers=letters+NUMBERS;
        firstPart=firstPart+letters.charAt(randomSeed.nextInt(letters.length()));
        for(int i=0;i<lengthFirstPart-1;i++){
            wordBuilder.append(lettersAndNumbers.charAt(randomSeed.nextInt(lettersAndNumbers.length())));
        }
        firstPart=firstPart+wordBuilder.toString();
        wordBuilder.setLength(0);
        int lengthLastPart=randomSeed.nextInt(10)+4;
        for(int i=0;i<lengthLastPart;i++){
            wordBuilder.append(letters.charAt(randomSeed.nextInt(letters.length())));
        }
        secondPart=wordBuilder.toString();
        wordBuilder.setLength(0);
        for(int i=0;i<3;i++){
            wordBuilder.append(letters.charAt(randomSeed.nextInt(letters.length())));
        }
        termination="."+wordBuilder.toString();
        res=firstPart+"@"+secondPart+termination;
        return res;
    }

    public static String randomAbout(){
        String letters=UPPER_CASE+" .:@,//\\";
        letters=letters+letters.toUpperCase();
        String numbers="0123456789";
        String lettersAndNumbers=letters+numbers;
        String res;
        int length=randomSeed.nextInt(300);
        StringBuilder resStringBuilder=new StringBuilder();
        for(int i=0;i<length;i++)
            resStringBuilder = resStringBuilder.append(lettersAndNumbers.charAt(randomSeed.nextInt(lettersAndNumbers.length())));
        res=resStringBuilder.toString();
        return res;
    }

    public static String randomPassword(){
        String letters=LOWER_CASE+LOWER_CASE.toUpperCase();
        String numbers="0123456789";
        String lettersAndNumbers=letters+numbers;
        String res;
        int length=randomSeed.nextInt(20)+8;
        StringBuilder resSb=new StringBuilder();
        for(int i=0;i<length;i++){
            resSb=resSb.append(lettersAndNumbers.charAt(randomSeed.nextInt(lettersAndNumbers.length())));
        }
        res=resSb.toString();
        return res;
    }


    private static void addToRes(String[] hobbies, int i, String[] finalHobbies) {
        Random r=new Random(System.currentTimeMillis());
        int value=r.nextInt(3);
        while(containing(hobbies[value],finalHobbies)){
            value=r.nextInt(3);
        }
        finalHobbies[i]=hobbies[value];

    }
    private static boolean containing(String string, String[] finalHobbies) {
        int i=0;
        boolean found=false;
        while(!found&&i<finalHobbies.length){
            if(finalHobbies[i].equals(string)){
                found=true;
            }else
                i++;
        }
        return found;
    }


}

