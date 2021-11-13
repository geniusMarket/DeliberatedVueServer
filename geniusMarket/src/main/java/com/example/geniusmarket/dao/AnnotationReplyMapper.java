package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.AnnotationReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AnnotationReplyMapper {
    /**
     *
     * @param annotationReply
     */
    void insertAnnotationReplyByObject(AnnotationReply annotationReply);

    /**
     *
     * @param id
     * @return
     */
    AnnotationReply selectAnnotationReplyById(int id);

    /**
     *
     * @param str
     * @return
     */
    List<AnnotationReply> selectAnnotationsReplyByString(String str);

    /**
     *
     * @param annotationReply
     */
    void updateAnnotationReplyByObject(AnnotationReply annotationReply);

    /**
     *
     * @param id
     */
    void deleteAnnotationReplyById(int id);
}
