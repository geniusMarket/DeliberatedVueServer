package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.SourceCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SourceCodeMapper {
    /**
     *
     * @param id
     * @return
     */
    SourceCode selectSourceCodeById(int id);

    /**
     *
     * @param path
     * @return
     */
    SourceCode selectSourceCodeByPath(String path);

}
