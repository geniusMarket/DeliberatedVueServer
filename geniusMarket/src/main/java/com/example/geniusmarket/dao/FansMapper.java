package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.Fans;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FansMapper {
    /**
     *
     * @param fans
     */
    void insertFansByObject(Fans fans);

    /**
     *
     * @param fans
     */
    void deleteFansByObject(Fans fans);

    /**
     *
     * @param openId
     * @return
     */
    List<Fans> selectFans(String openId);
}
