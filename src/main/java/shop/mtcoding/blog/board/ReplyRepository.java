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
    public void replySave(BoardRequest.ReplyDTO requestDTO, int userId, int boardId) {
        Query query = em.createNativeQuery("insert into reply_tb(comment, user_id, board_id, created_at) values(?, ?, ?,now())");
        query.setParameter(1, requestDTO.getComment());
        query.setParameter(2, userId);
        query.setParameter(3, boardId);

        query.executeUpdate();
    }

    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from reply_tb r inner join board_tb b on r.board_id=b.id where", Reply.class);
        return query.getResultList();
    }
}
