package cn.yzw.device.tool;

import java.util.Random;
import java.util.UUID;

public class Strings {

    public static String getString(int len){
        String string = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        Random random = new Random();
        int index;
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < len; i++) {
            index = random.nextInt(string.length());
            code.append(string.charAt(index));
        }
        return code.toString();
    }
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    public static String hashCode(String str){
        return str.hashCode()+"";
    }
}
