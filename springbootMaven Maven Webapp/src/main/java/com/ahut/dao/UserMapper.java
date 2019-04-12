package com.ahut.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;



import com.ahut.entity.User;

@Repository
public interface UserMapper {
	User user(int id);
	int insertUser(Map<String,Object> map);
	int insertUser2();
}
