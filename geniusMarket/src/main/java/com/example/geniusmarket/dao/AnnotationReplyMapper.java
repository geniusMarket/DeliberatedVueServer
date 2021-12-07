package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.Annotation;
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
     * @param id
     * @return
     */
    List<AnnotationReply> selectAnnotationsReplyByAnoId(int id);

    /**
     * @param status
     * @return
     */
    List<AnnotationReply> selectAnnotationsReplyByStatus(int status);
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

    /**
     *
     * @param id
     * @param status
     */
    void setStatus(int id,int status);
    /**
     *
     * @return
     */
    List<AnnotationReply> verify();
}
