package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FavoriteMapper {
    /**
     *
     * @param favorite
     */
    void insertFavoriteByObject(Favorite favorite);

    /**
     *
     * @param openId
     * @return
     */
    List<Favorite> selectFavoritesByOpenId(String openId);

    /**
     *
     * @param favorite
     */
    void deleteFavoriteByObject(Favorite favorite);
}
