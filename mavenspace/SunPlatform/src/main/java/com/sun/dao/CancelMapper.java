package com.sun.dao;

import java.util.List;

import com.sun.entity.CancelEntity;

public interface CancelMapper {
	List<CancelEntity> getWhereEntity(CancelEntity cancelEntity);
}
