package com.ssafy.nfti.api.controller;

import com.ssafy.nfti.api.request.CommunityReq;
import com.ssafy.nfti.api.request.CommunityUpdateReq;
import com.ssafy.nfti.api.request.DeleteReq;
import com.ssafy.nfti.api.request.UserReq;
import com.ssafy.nfti.api.request.ValidReq;
import com.ssafy.nfti.api.response.CommunityCreateRes;
import com.ssafy.nfti.api.response.CommunityListRes;
import com.ssafy.nfti.api.response.CommunityRes;
import com.ssafy.nfti.api.service.AWSS3Service;
import com.ssafy.nfti.api.service.CommunityService;
import com.ssafy.nfti.api.service.ItemsService;
import com.ssafy.nfti.api.service.UserService;
import com.ssafy.nfti.common.exception.enums.ExceptionEnum;
import com.ssafy.nfti.common.exception.response.ApiException;
import com.ssafy.nfti.common.model.response.BaseResponseBody;
import com.ssafy.nfti.db.entity.Community;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/v1/community")
@Api(value = "???????????? API", tags = {"Community."})
public class CommunityController {

    private final CommunityService communityService;
    private final AWSS3Service awss3Service;
    private final UserService userService;
    private final ItemsService itemsService;

    @Autowired
    public CommunityController(
        CommunityService communityService,
        AWSS3Service awss3Service,
        UserService userService,
        ItemsService itemsService
    ) {
        this.communityService = communityService;
        this.awss3Service = awss3Service;
        this.userService = userService;
        this.itemsService = itemsService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @ApiOperation(value = "???????????? ??????", notes = "??????????????? ????????????.", response = CommunityCreateRes.class)
    public ResponseEntity<CommunityCreateRes> createCommunity(
        @ModelAttribute CommunityReq req
    ) {
        log.info("file: " + req.getFile());
        log.info("user address: " + req.getHostAddress());
        String url = awss3Service.uploadFile(req.getFile());

        CommunityCreateRes res = communityService.createCommunity(req, url);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    @ApiOperation(value = "???????????? ??????(?????????)", notes = "???????????? ????????? ??????????????? ????????????.", response = CommunityListRes.class, responseContainer = "List")
    public ResponseEntity<List<CommunityListRes>> getCommunityList(
        @PageableDefault(sort = "createdAt", direction = Direction.DESC, size = 2) Pageable pageable,
        @RequestParam(defaultValue = "") String search
    ) {

        List<CommunityListRes> res = communityService.getList(pageable, search);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/member-sort")
    @ApiOperation(value = "???????????? ??????(?????? ?????????)", notes = "???????????? ????????? ?????? ??????????????? ????????????.", response = CommunityListRes.class, responseContainer = "List")
    public ResponseEntity<List<CommunityListRes>> getCommunityListSortByMember(
        @PageableDefault(sort = "createdAt", direction = Direction.DESC, size = 2) Pageable pageable,
        @RequestParam(defaultValue = "") String search
    ) {

        List<CommunityListRes> res = communityService.getListSortByMember(pageable, search);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/board-sort")
    @ApiOperation(value = "???????????? ??????(????????? ?????????)", notes = "???????????? ????????? ????????? ??????????????? ????????????.", response = CommunityListRes.class, responseContainer = "List")
    public ResponseEntity<List<CommunityListRes>> getCommunityListSortByBoard(
        @PageableDefault(sort = "createdAt", direction = Direction.DESC, size = 2) Pageable pageable,
        @RequestParam(defaultValue = "") String search
    ) {

        List<CommunityListRes> res = communityService.getListSortByBoard(pageable, search);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "???????????? ?????? ????????????", notes = "<strong>???????????? ?????????</strong>??? ???????????? ??????????????? ????????? ????????????.", response = CommunityRes.class)
    public ResponseEntity<CommunityRes> getCommunity(
        @PathVariable Long id,
        @RequestParam(defaultValue = "") String search
    ) {
        CommunityRes res = communityService.getOne(id, search);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "???????????? ?????? ??????", notes = "??????????????? ????????? ????????????. payable??? ?????? x", response = CommunityRes.class)
    public ResponseEntity<CommunityRes> updateCommunity(
        @PathVariable Long id,
        @RequestBody CommunityUpdateReq req
    ) {
//        String url = awss3Service.uploadFile(req.getFile());

        CommunityRes res = communityService.updateCommunity(id, req);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "???????????? ??????", notes = "??????????????? ????????????. Body?????? <strong>host_address</strong>??? ????????????.", response = BaseResponseBody.class)
    public ResponseEntity<BaseResponseBody> deleteCommunity(
        @PathVariable Long id,
        @RequestBody ValidReq req
    ) {
        communityService.deleteCommunity(id, req.getUserAddress());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
