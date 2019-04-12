package com.ahut.service.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.ahut.dao.UserMapper;
import com.ahut.entity.User;
import com.ahut.service.UserService;
@Service
public class ServiceImpl implements UserService{
	@Autowired
	UserMapper mapper;
	@Autowired
	TransactionTemplate template;
	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return mapper.user(id);
	}
	@Override
	public int insert() {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("a","123");
		map.put("b", "abc");
		int insertUser = mapper.insertUser(map);
		return insertUser;
	}

}
