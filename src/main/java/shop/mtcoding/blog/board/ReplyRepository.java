package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReplyRepository {
    private final EntityManager em;

    @Transactional
    public void replySave(BoardRequest.ReplyDTO requestDTO, int userId, int boardId, String username) {
        Query query = em.createNativeQuery("insert into reply_tb(comment,  board_id, user_id, username) values(?, ?, ?, ?)");
        query.setParameter(1, requestDTO.getComment());
        query.setParameter(2, boardId);
        query.setParameter(3, userId);
        query.setParameter(4, username);

        query.executeUpdate();
    }

    public List<Reply> findAll(int boardId) {
        Query query = em.createNativeQuery("select t.reply_id as id, t.comment, t.board_id, u.id as user_id, u.username from (select r.id as reply_id, r.comment as comment, r.user_id as user_id, b.id  as board_id from reply_tb r inner join board_tb b on r.board_id=b.id where board_id=?) t inner join user_tb u on t.user_id=user_id", Reply.class);
        query.setParameter(1, boardId);
        return query.getResultList();
    }
}
