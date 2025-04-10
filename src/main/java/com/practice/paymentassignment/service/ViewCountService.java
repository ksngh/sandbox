package com.practice.paymentassignment.service;

public interface ViewCountService {

    /**
     * 사용자의 조회를 기록
     * @param articleId 게시글 ID
     * @param userId 유저의 고유 식별자 (IP, UUID 등 가능)
     */
    void recordView(String articleId, String userId);

    /**
     * 해당 게시글의 유니크 조회수 추정값 반환
     * @param articleId 게시글 ID
     * @return 유니크 조회 수 (근사값)
     */
    long getUniqueViews(String articleId);
}
