package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.persistence.BoardDAO;
import org.zerock.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

  @Inject
  private ReplyDAO replyDAO;
  
  @Inject
  private BoardDAO boardDAO;

  @Transactional //이 어노테이션으로 인해 밑에 addReply작업이 아토믹하게 이루어짐 
  @Override
  public void addReply(ReplyVO vo) throws Exception {

    replyDAO.create(vo);//댓글 만들고
    boardDAO.updateReplyCnt(vo.getBno(),1);//댓글 수 +1
  }

  @Transactional //이 어노테이션으로 인해 밑에 removeReply작업이 아토믹하게 이루어짐 
  @Override
  public void removeReply(Integer rno) throws Exception {
  
    int bno = replyDAO.getBno(rno);//삭제하기 전에 게시물 번호 알아내고
    replyDAO.delete(rno);//삭제하고
    boardDAO.updateReplyCnt(bno, -1);//댓글 수 -1
  } 
  
  @Override
  public List<ReplyVO> listReply(Integer bno) throws Exception {

    return replyDAO.list(bno);
  }

  @Override
  public void modifyReply(ReplyVO vo) throws Exception {

	  replyDAO.update(vo);
  }

  @Override
  public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) 
      throws Exception {

    return replyDAO.listPage(bno, cri);
  }

  @Override
  public int count(Integer bno) throws Exception {

    return replyDAO.count(bno);
  }

}