package com.ssafy.nfti.api.service;

import com.ssafy.nfti.api.request.CommunityReq;
import com.ssafy.nfti.api.response.CommunityRes;
import com.ssafy.nfti.db.entity.Community;
import com.ssafy.nfti.db.repository.CommunityRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("communityService")
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    CommunityRepository communityRepository;

    @Override
    public CommunityRes createCommunity(CommunityReq req, String url) {
        Community community = new Community(
            req.getName(),
            req.getDescription(),
            req.getPayable(),
            url,
            req.getHostAddress()
        );

        Community res = communityRepository.save(community);

        return CommunityRes.of(res);
    }

    @Override
    public List<CommunityRes> getList() {
        List<Community> list = communityRepository.findAll();
        List<CommunityRes> res = new ArrayList<>();

        for (Community c : list) {
            res.add(CommunityRes.of(c));
        }

        return res;
    }
}
