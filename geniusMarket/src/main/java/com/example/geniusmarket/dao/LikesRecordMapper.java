package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.LikesRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LikesRecordMapper {
    /**
     *
     * @param likesRecord
     */
    void insertRecord(LikesRecord likesRecord);

    /**
     *
     * @param likesRecord
     */
    void deleteRecord(LikesRecord likesRecord);
    /**
     *
     * @param likesRecord
     * @return
     */
    boolean recordIsExists(LikesRecord likesRecord);

    /**
     *
     * @param tid
     * @param type
     */
    void deleteRecords(int tid,int type);
}
