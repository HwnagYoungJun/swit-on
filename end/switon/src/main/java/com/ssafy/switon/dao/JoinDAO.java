package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Join;
import com.ssafy.switon.dto.JoinGiveDTO;
import com.ssafy.switon.dto.Medals;
import com.ssafy.switon.dto.Ranker;

public interface JoinDAO {
	List<Join> selectJoins();
	Join selectJoin(int id);
	int insertJoin(Join join);
	int insertJoinLeader(Join join);
	int deleteJoin(int id);
	List<Join> selectJoinsByUserId(int userId);
	int countUsersByStudyId(int studyId);
	int isMember(Join join);
	int deleteJoinByIds(Join join);
	List<Integer> selectMemberIds(int studyId);
	List<Join> selectJoinsByStudyId(int studyId);
	int updateJoinComplete(int studyId);
	List<Ranker> selectRankers(int studyId);
	int giveMedal(JoinGiveDTO dto);
	Medals selectMedalsByUserId(int userId);
	int addScore(JoinGiveDTO dto);
}
