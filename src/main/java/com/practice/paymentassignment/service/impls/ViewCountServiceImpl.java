package com.practice.paymentassignment.service.impls;

import com.practice.paymentassignment.service.ViewCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewCountServiceImpl implements ViewCountService {

    private final RedisTemplate<String,Object> redisTemplate;

    private static final String HLL_VIEW_PREFIX = "hll:view:";

    /**
     * 사용자의 조회를 기록
     * @param articleId 게시글 ID
     * @param userId 유저의 고유 식별자 (IP, UUID 등 가능)
     */
    public void recordView(String articleId, String userId) {
        String redisKey = HLL_VIEW_PREFIX + articleId;
        redisTemplate.opsForHyperLogLog().add(redisKey, userId);
    }

    public long getUniqueViews(String articleId) {
        String redisKey = HLL_VIEW_PREFIX + articleId;
        return redisTemplate.opsForHyperLogLog().size(redisKey);
    }
}
