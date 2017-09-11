package com.cgm.twitter.repository;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cgm.twitter.domain.Message;
import com.cgm.twitter.domain.User;

@Repository
public class MessageDAO extends AbstractDAO<Message> {
	
	public MessageDAO() {
		super(Message.class);
	}
	

	
}
