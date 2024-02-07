package shop.mtcoding.blog.board;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

public class ReplyResponse {
    @Data
    public static class replyViewDTO{
        private int id;
        private String comment;
        private int boardId;
        private int userId;
        private String username;

    }
}
