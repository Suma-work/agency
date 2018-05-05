package com.sumainfo.agency.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.agency.dao.BankMapper;

@Service
public class BankServiceImpl implements Serializable{
private static final long serialVersionUID = 1L;
	
	Logger log=LoggerFactory.getLogger(BankServiceImpl.class);
	
	@Autowired
	BankMapper bankMapper;
	
	public List<Map<String,Object>>getBankList(Map<String,Object>params){
		return bankMapper.getBankList(params);
	}
}
