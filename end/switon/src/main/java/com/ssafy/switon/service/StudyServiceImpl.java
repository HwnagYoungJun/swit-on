package com.ssafy.switon.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.ArticleDAO;
import com.ssafy.switon.dao.ArticleLikeDAO;
import com.ssafy.switon.dao.CategoryDAO;
import com.ssafy.switon.dao.JoinDAO;
import com.ssafy.switon.dao.StudyDAO;
import com.ssafy.switon.dao.StudyLikeDAO;
import com.ssafy.switon.dao.UserDAO;
import com.ssafy.switon.dto.BestLikeReturnDTO;
import com.ssafy.switon.dto.BestUsersReturnDTO;
import com.ssafy.switon.dto.BestWriterReturnDTO;
import com.ssafy.switon.dto.Join;
import com.ssafy.switon.dto.JoinGiveDTO;
import com.ssafy.switon.dto.Like;
import com.ssafy.switon.dto.LowerCategory;
import com.ssafy.switon.dto.LowerCategorySimpleDTO;
import com.ssafy.switon.dto.LowerCategoryWithUpperName;
import com.ssafy.switon.dto.Ranker;
import com.ssafy.switon.dto.SearchReturnDTO;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.StudyCardDTO;
import com.ssafy.switon.dto.StudyReturnDTO;
import com.ssafy.switon.dto.StudySimple;
import com.ssafy.switon.dto.StudyWithRate;
import com.ssafy.switon.dto.UpperCategory;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserRate;
import com.ssafy.switon.dto.UserSimpleDTO;
import com.ssafy.switon.dto.UserStudyInfoDTO;

@Service
public class StudyServiceImpl implements StudyService {

	@Autowired
	StudyLikeDAO likeDao;
	
	@Autowired
	StudyDAO studyDao;
	
	@Autowired
	JoinDAO joinDao;
	
	@Autowired
	UserDAO userDao;
	
	@Autowired
	CategoryDAO categoryDao;

	@Autowired
	ArticleLikeDAO articleLikeDao;
	
	@Autowired
	ArticleDAO articleDao;
	
	@Autowired
	JoinService joinService;
	
	@Autowired
	UserScheduleService userScheduleService;
	
	@Override
	public List<Study> searchAll() {
		return studyDao.selectStudies();
	}

	@Override
	public Study search(int id) {
		return studyDao.selectStudyById(id);
	}
	
	@Override
	public StudyReturnDTO search(int id, boolean isJoined, boolean isLeader, int userId) {
		Study study = studyDao.selectStudyById(id);
		Like like = new Like(likeDao.selectLikeCount(id), 
				likeDao.selectStudyLikeByUser_Study(userId, id) != null);				
		LowerCategory lowerCategory = categoryDao.selectLowOne(study.getLowercategory_id());
		UpperCategory upperCategory = categoryDao.selectUpOne(lowerCategory.getUppercategory_id());
		study.setUppercategory_id(upperCategory.getId());
		study.setUppercategory_name(upperCategory.getName());
		StudyReturnDTO studyReturnDTO = new StudyReturnDTO(study, isJoined, isLeader, like);
		
		String leaderName = userDao.selectUserById(study.getUser_id()).getName();
		studyReturnDTO.setLeaderName(leaderName);
		
		List<UserSimpleDTO> members = joinService.searchMembers(id);
		studyReturnDTO.setMembers(members);
		
		List<Ranker> rankers = joinDao.selectRankers(id);
		studyReturnDTO.setRankers(rankers);
		
		return studyReturnDTO;
	}

	@Override
	public int create(Study study, int userId) {
		if(studyDao.insertStudy(study) == 1) {
			int studyId = studyDao.getRecentStudyIdByUserId(userId);
			return studyId;
		}
		return 0;
	}

	@Override
	public boolean modify(Study study) {
		return studyDao.updateStudy(study) == 1;
	}

	@Override
	public boolean delete(int id) {
		return studyDao.deleteStudy(id) == 1;
	}

	@Override
	public List<UserStudyInfoDTO> searchUserStudies(int userId) {
		List<UserStudyInfoDTO> list = new ArrayList<>();
		List<Join> joins = joinDao.selectJoinsByUserId(userId);
		for(Join join : joins) {
			UserStudyInfoDTO dto = new UserStudyInfoDTO();
			int studyId = join.getStudy_id();
			Study study = studyDao.selectStudyById(studyId);
			dto.setStudy(study);
			dto.setLeaderInfo(userDao.selectUserById(study.getUser_id()));
			dto.setUserCntCurrent(joinDao.countUsersByStudyId(studyId));
			dto.setMedal(join.getMedal());
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<Study> searchStudiesByLowercategory(int lowercategory_id) {
		return studyDao.selectStudiesByLowercategoryId(lowercategory_id);
	}

	@Override
	public List<StudyCardDTO> searchStudyCardsByLowercategoryId(int lowercategory_id) {
		List<Study> studies = studyDao.selectStudiesByLowercategoryId(lowercategory_id);
		List<StudyCardDTO> studyCards = new ArrayList<StudyCardDTO>();
		for(Study study : studies) {
			studyCards.add(new StudyCardDTO(study.getId(), study.getName(), 
					study.getStart_time(), study.getEnd_time(), study.getWeek(), 
					study.getEnd_term(), study.getUsers_current(), study.getUsers_limit()));
		}
		return studyCards;
	}

	@Override
	public List<StudyCardDTO> searchStudyCards() {
		List<Study> studies = studyDao.selectStudies();
		List<StudyCardDTO> studyCards = new ArrayList<StudyCardDTO>();
		for(Study study : studies) {
			studyCards.add(new StudyCardDTO(study.getId(), study.getName(), 
					study.getStart_time(), study.getEnd_time(), study.getWeek(), 
					study.getEnd_term(), study.getUsers_current(), study.getUsers_limit()));
		}
		return studyCards;
	}

	@Override
	public List<Study> searchStudiesByUppercategory(int uppercategory_id) {
		List<Study> studies = new ArrayList<Study>();
		List<LowerCategory> categories = categoryDao.selectUp_Low(uppercategory_id);
		for(LowerCategory category : categories) {
			studies.addAll(studyDao.selectStudiesByLowercategoryId(category.getId()));
		}
		return studies;
	}

	@Override
	public List<StudyCardDTO> searchStudyCardsByUppercategory(int uppercategory_id) {
		List<Study> studies = new ArrayList<Study>();
		List<LowerCategory> categories = categoryDao.selectUp_Low(uppercategory_id);
		for(LowerCategory category : categories) {
			studies.addAll(studyDao.selectStudiesByLowercategoryId(category.getId()));
		}
		
		List<StudyCardDTO> studyCards = new ArrayList<StudyCardDTO>();
		for(Study study : studies) {
			studyCards.add(new StudyCardDTO(study.getId(), study.getName(), 
					study.getStart_time(), study.getEnd_time(), study.getWeek(), 
					study.getEnd_term(), study.getUsers_current(), study.getUsers_limit()));
		}
		return studyCards;
	}
	
	@Override
	public SearchReturnDTO searchStudyByKeyword(String keyword) {
		List<Study> originalStudies = studyDao.selectStudyByKeyword(keyword);
		List<StudySimple> studies = new ArrayList<StudySimple>();
		for(Study originalStudy : originalStudies) {
			StudySimple study = new StudySimple(originalStudy);
			studies.add(study);
		}
		List<LowerCategorySimpleDTO> originalLowercategories = categoryDao.selectLowByKeyword(keyword);
		List<LowerCategoryWithUpperName> lowercategories = new ArrayList<LowerCategoryWithUpperName>();
		for(LowerCategorySimpleDTO dto : originalLowercategories) {
			String upperCategoryName = categoryDao.selectUpperNameByLowerCategoryId(dto.getId());
			LowerCategoryWithUpperName lowerCategory = new LowerCategoryWithUpperName(dto, upperCategoryName);
			lowercategories.add(lowerCategory);
		}
		List<UpperCategory> uppercategories = categoryDao.selectUpByKeyword(keyword);
		
		return new SearchReturnDTO(studies, lowercategories, uppercategories);
	}

	@Override
	public String finishStudies(Timestamp timestamp) {
		List<Integer> ids = studyDao.selectNotFinishedStudyIds(timestamp);
		String result;
		if(ids.size() != 0) {
			result = "스터디 완료 작업 실행!! : ";
			for(int id : ids) {
				result += id + "번 스터디 작업 ";
				List<Ranker> rankers = joinDao.selectRankers(id);
				int medal = 1;
				// 랭커들에게 메달을 부여
				for(Ranker ranker : rankers) {
					JoinGiveDTO dto = new JoinGiveDTO(ranker.getUser_id(), id);
					dto.setMedal(medal);
					joinDao.giveMedal(dto);
				}
				// 완료 처리
				joinDao.updateJoinComplete(id);
				studyDao.updateStudyFinish(id);
				result += "완료 | ";
			}
			return result;
		}
		return null;
	}

	@Override
	public BestUsersReturnDTO findBestUsers(int studyId) {
		List<Integer> ids = joinDao.selectMemberIds(studyId);
		List<BestWriterReturnDTO> writers = new ArrayList<BestWriterReturnDTO>();
		List<BestLikeReturnDTO> likers = new ArrayList<BestLikeReturnDTO>();
		for(int id : ids) {
			UserInfoDTO user = userDao.selectUserById(id);
			int userId = user.getId();
			
			int writeCnt = articleDao.cntUserArticlesByStudyId(userId, studyId);
			BestWriterReturnDTO writer = new BestWriterReturnDTO(userId, user.getName(), writeCnt);
			writers.add(writer);
			
			int likeCnt = articleLikeDao.cntLikesUserGot(userId, studyId);
			BestLikeReturnDTO liker = new BestLikeReturnDTO(userId, user.getName(), likeCnt);
			likers.add(liker);
		}
		
		if(writers.size() > 1) {
			Collections.sort(writers, new Comparator<BestWriterReturnDTO>() {
				@Override
				public int compare(BestWriterReturnDTO o1, BestWriterReturnDTO o2) {
					int result = o2.getArticle_cnt() - o1.getArticle_cnt();
					if(result != 0) {
						return result;
					}
					int result2 = articleDao.selectRecentUserArticleId(o2.getUser_id(), studyId)
							- articleDao.selectRecentUserArticleId(o1.getUser_id(), studyId);
					return result2;
				}
			});
		}
		
		if(likers.size() > 1) {
			Collections.sort(likers, new Comparator<BestLikeReturnDTO>() {
				@Override
				public int compare(BestLikeReturnDTO o1, BestLikeReturnDTO o2) {
					int result = o2.getLike_cnt() - o1.getLike_cnt();
					if(result != 0) {
						return result;
					}
					int result2 = articleLikeDao.selectRecentLikeId(o2.getUser_id(), studyId)
							- articleLikeDao.selectRecentLikeId(o1.getUser_id(), studyId);
					return result2;
				}
			});
		}
		
		List<BestWriterReturnDTO> bestWriters = new ArrayList<BestWriterReturnDTO>();
		List<BestLikeReturnDTO> bestLikers = new ArrayList<BestLikeReturnDTO>();
		
		for(int i = 0; i < 3; i++) {
			if(writers.size() > i) {
				bestWriters.add(writers.get(i));
			}
			if(likers.size() > i) {
				bestLikers.add(likers.get(i));
			}
		}
		
		List<Ranker> rankers = joinDao.selectRankers(studyId);
		return new BestUsersReturnDTO(bestWriters, bestLikers, rankers);
	}

	@Override
	public List<Study> searchStudiesByKeywordEnter(String keyword) {
		keyword = "%" + keyword + "%";
		List<Study> studies = studyDao.selectStudyByKeywordEnter(keyword);
		return studies;
	}
	
	@Override
	public List<StudyWithRate> searchEndStudyByUserId(int user_id) {
		List<Study> endStudiesOriginals = studyDao.selectEndStudyByUserId(user_id);
		List<StudyWithRate> list1 = new ArrayList<StudyWithRate>();
		if(endStudiesOriginals.size() != 0) {
			for(Study endStudy : endStudiesOriginals) {
				int studyId = endStudy.getId();
				UserRate rate = userScheduleService.getUserParticipateRate(user_id, studyId);
				StudyWithRate studyWithRate = new StudyWithRate(endStudy, rate);
				list1.add(studyWithRate);
			}
		}
		return list1;
	}
	@Override
	public List<StudyWithRate> searchNotEndStudyByUserId(int user_id) {
		List<Study> notEndStudiesOriginals = studyDao.selectNotEndStudyByUserId(user_id);
		List<StudyWithRate> list2 = new ArrayList<StudyWithRate>();
		if(notEndStudiesOriginals.size() != 0) {
			for(Study notEndStudy : notEndStudiesOriginals) {
				int studyId = notEndStudy.getId();
				UserRate rate = userScheduleService.getUserParticipateRate(user_id, studyId);
				StudyWithRate studyWithRate = new StudyWithRate(notEndStudy, rate);
				list2.add(studyWithRate);
			}
		}
		return list2;
	}
}
