package com.ideatech.org.online_noticeboard;
/**
 * Created by MianGhazanfar on 4/24/2016.
 */
public class AppConfig {
        // Server user login url
        public static String weburl="http://onb.ideatechnology.org/login_api_for_oac/";
        public static String mainUrl="http://192.168.1.103/Notice_Board/";
       // public static String URL_LOGIN = "http://192.168.8.107/Animal/login/login.php";
        public static String URL_LOGIN = "http://onb.ideatechnology.org/login_api_for_oac/login.php?";
        // Server user register url
        public static String URL_REGISTER = "http://onb.ideatechnology.org/login_api_for_oac/register.php?";

        public static String ENTERTAINMENT_URL="http://onb.ideatechnology.org/entertainment.json";
        public static String WORKSHOP_URL="http://onb.ideatechnology.org/workshop.json";
        public static  String EXHIBITION_URL="http://onb.ideatechnology.org/exibition.json";

        public static String getUrlLogin(String email,String pass){
                return URL_LOGIN+"email="+email+"&password="+pass;

        }
        public static String getUrlSignUp(String email,String pass){
                return URL_REGISTER+"email="+email+"&password="+pass;

        }
}
