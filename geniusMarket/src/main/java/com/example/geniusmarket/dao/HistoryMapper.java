package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.History;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HistoryMapper {
    /**
     *
     * @param history
     */
    void insertHistoryByObject(History history);

    /**
     *
     * @param id
     * @return
     */
    List<History> selectHistoryByOpenId(String id);
}
