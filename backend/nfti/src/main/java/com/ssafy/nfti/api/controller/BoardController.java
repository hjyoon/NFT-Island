package com.ssafy.nfti.api.controller;

import com.ssafy.nfti.api.request.BoardReq;
import com.ssafy.nfti.api.request.DeleteReq;
import com.ssafy.nfti.api.request.ValidReq;
import com.ssafy.nfti.api.response.BoardCreateRes;
import com.ssafy.nfti.api.response.BoardRes;
import com.ssafy.nfti.api.service.BoardService;
import com.ssafy.nfti.api.service.ItemsService;
import com.ssafy.nfti.common.exception.enums.ExceptionEnum;
import com.ssafy.nfti.common.exception.response.ApiException;
import com.ssafy.nfti.common.model.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/board")
@Api(value = "게시글 API", tags = {"Board."})
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping()
    @ApiOperation(value = "게시글 생성", notes = "게시글을 생성한다.")
    public ResponseEntity<BoardCreateRes> postBoard(@RequestBody BoardReq req) {

        BoardCreateRes res = boardService.save(req);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/list")
    @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 불러온다. (사용X)")
    public ResponseEntity<List<BoardRes>> getList(
        @PageableDefault(sort = "createdAt", direction = Direction.DESC, size = 2) Pageable pageable,
        @PathVariable Long id
    ) {
        List<BoardRes> res = boardService.list(pageable, id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "게시글 정보", notes = "게시글의 정보를 불러온다.")
    public ResponseEntity<BoardRes> getBoard(
        @PathVariable Long id,
        @RequestBody ValidReq req
    ) {
        BoardRes res = boardService.getOne(id, req.getCommunityId());
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정한다.")
    public ResponseEntity<BoardRes> updateBoard(
        @PathVariable Long id,
        @RequestBody BoardReq req
    ) {
        BoardRes res = boardService.updateBoard(id, req);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    public ResponseEntity<BaseResponseBody> deleteBoard(
        @PathVariable("id") Long id,
        @RequestBody ValidReq req
    ) {
        boardService.delete(id, req.getUserAddress());
        return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
    }
}
