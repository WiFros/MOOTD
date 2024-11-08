package com.bwd4.mootd.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
public class TagSearchResponseDTO {
    private String id; // MongoDB의 ObjectId가 자동으로 할당됨
    private List<String> tag;
    private String originImageUrl;
    private String guideImageUrl;
    private String maskImageUrl;

}