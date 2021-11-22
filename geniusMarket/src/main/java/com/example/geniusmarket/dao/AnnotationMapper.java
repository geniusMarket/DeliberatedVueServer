package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.Annotation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AnnotationMapper {
    /**
     *
     * @param annotation
     */
    void insertAnnotationByObject(Annotation annotation);

    /**
     *
     * @param id
     * @return
     */
    Annotation selectAnnotationById(int id);

    /**
     *
     * @return
     */
    List<Annotation> selectAllAnnotation();

    /**
     *
     * @param moduleName
     * @return
     */
    List<Annotation> selectAnnotationBy(String moduleName);
    /**
     *
     * @param annotation
     */
    void updateAnnotationByObject(Annotation annotation);

    /**
     *
     * @param status
     * @return
     */
    List<Annotation> selectAnnotationByStatus(int status);
    /**
     *
     * @param id
     */
    void deleteAnnotationById(int id);

}
