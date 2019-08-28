package com.sudnae.liteorm.utils;

import java.lang.annotation.Annotation;

/**
 * 2019/8/27
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
public class AnnotationUtil {

    public static <A extends Annotation>Annotation getClassAnnotation(Class clz, Class<A> annotationClz){
        try{
            return clz.getAnnotation(annotationClz);
        }catch (NullPointerException e){
            return null;
        }
    }

}
