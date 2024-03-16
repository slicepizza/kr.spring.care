package kr.spring.care.board.controller;

import kr.spring.care.board.model.Board;
import kr.spring.care.board.model.Comment;
import kr.spring.care.board.service.CommentService;
import kr.spring.care.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 전체 보기
    @GetMapping("/list/{num}")
    public String listComments(@PathVariable long num, Model model) {
        List<Comment> commentList = commentService.list(num);
        long count = commentService.count(num);
        model.addAttribute("commentList", commentList);
        model.addAttribute("count", count);
        return "commentList";
    }

    // 댓글 추가
    @PostMapping("create")
    public ResponseEntity<String> createComment(@RequestBody Comment comment
	/*
	 * @RequestParam("boardNum") long boardNum, @RequestParam("comment") String
	 * commentText,
	 * 
	 * @RequestParam("userId") long userId
	 */
                                            ) {
    	System.out.println("댓글: "+comment.getContent());
    	System.out.println("댓글넘: "+comment.getBoard().getNum());
    	System.out.println("댓글넘2: "+comment.getUser().getUserId());
    	Comment comment2 = new Comment();
    	Board board = new Board();
    	board.setNum(comment.getBoard().getNum());
    	comment.setBoard(board);
    	comment2.setContent(comment.getContent());
       
    	User user = new User();
    	user.setUserId(comment.getUser().getUserId());
        comment2.setUser(user);
        commentService.insert(comment);
        return new ResponseEntity<String>("Comment added successfully", HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/delete/{cnum}")
    public ResponseEntity<String> deleteComment(@PathVariable("cnum") long cnum) {
        commentService.delete(cnum);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}