package com.strendent.tutorsu.Utilities;

/**
 * Created by user on 7/3/2015.
 */
public class Constants_MixPannel {

    public static final String mixpannelProjectToken ="2d2762c1c496afca016c988b0f17f5b5";
    public static String MAIN_ACTIVITY="MainActivity - onCreate called";
    public static String MENU_OPEN="MenuOpened";
    public static String TRUSTED_TUTORS="Trusted Tutorz opened";
    public static String MY_FAMILY=    "My Family opened";

    public static String FAVOURITE_LOCATIONS=    "FavLocation opened";
    public static String TUTOINS_OPENED=    "Tutions opened";
    public static String PROFILE=    "Profile opened";
    public static String PAYMENTS=    "Payments Opened";
    public static String SHARE="Share Opened";
    public static String PROMOTIONS="Promotions Opened";
    public static String ABOUT="About Opened";

//
   // Payments Opened


 //TODO: This code is used to get pakage info. It gives you key hash
       /* try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.strendent.tutorsu",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/


}
