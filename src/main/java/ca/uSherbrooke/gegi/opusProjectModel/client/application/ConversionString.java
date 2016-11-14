package ca.uSherbrooke.gegi.opusProjectModel.client.application;

/**
 * Created by Justin on 2016-07-31.
 */
public class ConversionString {

    public static String ConversionDateInFrench(String date)
    {
        String dateInFrench = date;

        dateInFrench = dateInFrench.replaceAll("January","Janvier");
        dateInFrench = dateInFrench.replaceAll("February","février");
        dateInFrench = dateInFrench.replaceAll("March","mars");
        dateInFrench = dateInFrench.replaceAll("April","avril");
        dateInFrench = dateInFrench.replaceAll("May","mai");
        dateInFrench = dateInFrench.replaceAll("June","juin");
        dateInFrench = dateInFrench.replaceAll("July","juillet");
        dateInFrench = dateInFrench.replaceAll("August","août");
        dateInFrench = dateInFrench.replaceAll("September","septembre");
        dateInFrench = dateInFrench.replaceAll("October","octobre");
        dateInFrench = dateInFrench.replaceAll("November","novembre");
        dateInFrench = dateInFrench.replaceAll("December","décembre");

        dateInFrench = dateInFrench.replaceAll("Monday","Lundi");
        dateInFrench = dateInFrench.replaceAll("Tuesday","Mardi");
        dateInFrench = dateInFrench.replaceAll("Wednesday","Mercredi");
        dateInFrench = dateInFrench.replaceAll("Thursday","Jeudi");
        dateInFrench = dateInFrench.replaceAll("Friday","Vendredi");
        dateInFrench = dateInFrench.replaceAll("Saturday","Samedi");
        dateInFrench = dateInFrench.replaceAll("Sunday","Dimanche");

        return dateInFrench;
    }
}
