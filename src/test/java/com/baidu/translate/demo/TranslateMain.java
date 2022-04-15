package com.baidu.translate.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;

public class TranslateMain {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20211215001029018";
    private static final String SECURITY_KEY = "_9LinLvpt0WJ4hia9zgL";

    public static void main(String[] args) throws Exception {
        String query = "Unused local variables should be removed";
        String result = translate("en", "zh", query);
        System.out.println(result);
    }

    public static String translate(String from, String to, String query) throws Exception {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        String data = api.getTransResult(query, from, to);
        JSONObject jsonObject = JSON.parseObject(data);
        JSONArray jsonArray = jsonObject.getJSONArray("trans_result");
        if(jsonArray != null && jsonArray.size() > 0){
             String dst = jsonArray.getJSONObject(0).getString("dst");
             return dst;
        }
        throw new Exception("无数据");
    }

//    private static String convertTransResult(String inputData){
//        int a =inputData.lastIndexOf("\"");
//        int b=inputData.lastIndexOf("\"", a-1);
//        String w= inputData.substring(b+1, a);//提取结果字符串
//        String unicode = w;
//        String ret = null;
//        String[] rets = unicode.split("\\\\u"); //分解成一个个16进制Unicode编码
//        char[] chars = new char[rets.length];
//        int i = 0;
//        for(String ss:rets){
//            if(ss.equals(""))continue;
//            chars[i] = (char)Integer.parseInt(ss,16);//将16进制转换为10进制，再强转为char
//            i++;
//        }
//        ret = new String(chars);
//        try {
//            ret = new String(ret.getBytes("utf-8"),"utf-8");//得到结果utf-8编码格式的结果
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        ret = ret.trim();//去除末尾空格
//        //System.out.println(ret);//输出
//        return ret;
//    }

}
