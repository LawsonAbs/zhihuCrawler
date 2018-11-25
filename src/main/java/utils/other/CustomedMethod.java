package utils.other;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomedMethod {
    public static void printSuffix(String string){
        System.out.println(string+"    --> add by lawson");
    }


    public static void printDelimiter(){
        printDelimiter("");
    }
    public static void printDelimiter(String str){
        System.out.println();
        System.out.println();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        System.out.println(str+"=======================" + sdf.format(date)+"======================\n\n");
    }
}
