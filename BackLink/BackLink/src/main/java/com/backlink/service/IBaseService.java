package com.backlink.service;

import java.util.List;

public interface IBaseService<T, ID> {
	
	T getById(ID id);
	
	List<T> findAll();
	
	T saveOne(T entity);
	
	T updateOne(T entity);
	
	List<T> updateMany(List<T> list);
	
	boolean deleteOne(ID id);
	
	boolean deleteMany(ID[] ids);
}
