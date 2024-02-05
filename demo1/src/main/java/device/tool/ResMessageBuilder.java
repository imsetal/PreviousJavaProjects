package cn.yzw.device.tool;

import cn.yzw.device.vo.ResMessage;

public class ResMessageBuilder {

    private static ResMessage resMessage;

    public static ResMessage success(){
        return resMessage(200,"success",null);
    }

    public static ResMessage success(String message){

        return resMessage(200,message,null);
    }
    public static ResMessage success(String message,Object content){

        return resMessage(200,message,content);
    }


    public static ResMessage resMessage(int code,String message,Object content) {
         resMessage = new ResMessage(code,message,content);
         return resMessage;
    }
}
