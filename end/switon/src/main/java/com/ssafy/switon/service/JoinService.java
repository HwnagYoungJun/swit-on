package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Join;
import com.ssafy.switon.dto.UserSimpleDTO;

public interface JoinService {
	
	List<Join> searchAll();
	Join search(int id);
	boolean join(int studyId, int userId);
	boolean createLeader(Join join);
	boolean leave(int studyId, int userId);
	List<Join> searchAllByUserId(int userId);
	boolean isMember(int studyId, int userId);
	List<UserSimpleDTO> searchMembers(int studyId);
	boolean isFull(int studyId);
	boolean givePoint(int userId, int studyId, int point);
	
}
