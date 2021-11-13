package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    /**
     *
     * @param user
     */
    void insertUserByObject(User user);
    /**
     *
     * @param openId
     * @return
     */
    User selectUserByOpenId(String openId);

    /**
     *
     * @param user
     */
    /**
     *
     * @param user
     */
    void updateUserByObject(User user);

    /**
     *
     * @param openId
     * @return
     */
    List<User> selectFansByOpenId(String openId);

    /**
     *
     * @param openId
     * @return
     */
    List<User> selectAttentionsByOpenId(String openId);
    /**
     *
     * @param openId
     */
    void deleteUserByOpenId(String openId);
}
