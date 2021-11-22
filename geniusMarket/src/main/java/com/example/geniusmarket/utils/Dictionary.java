package com.example.geniusmarket.utils;

import java.util.HashMap;

public class Dictionary {
    private static  HashMap<String,Integer> hashMap = new HashMap<>();
    public static HashMap<String,Integer> pojoHashMap()
    {
        if(hashMap.size()>=12)return hashMap;
        else
        {
            try
            {
                hashMap.put("Annotation",Integer.valueOf(1));
                hashMap.put("AnnotationReply",Integer.valueOf(2));
                hashMap.put("Answer",Integer.valueOf(3));
                hashMap.put("AnswerReply",Integer.valueOf(4));
                hashMap.put("Article",Integer.valueOf(5));
                hashMap.put("ArticleReply",Integer.valueOf(6));
                hashMap.put("Fans",Integer.valueOf(7));
                hashMap.put("Favorite",Integer.valueOf(8));
                hashMap.put("History",Integer.valueOf(9));
                hashMap.put("Question",Integer.valueOf(10));
                hashMap.put("SourceCode",Integer.valueOf(11));
                hashMap.put("User",Integer.valueOf(12));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return hashMap;
    }
}
