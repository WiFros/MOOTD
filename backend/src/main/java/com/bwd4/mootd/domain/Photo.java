package com.bwd4.mootd.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Document(collection = "photo")
public class Photo {
    @Id
    private String id; // MongoDB의 ObjectId가 자동으로 할당됨

    @Field("device_id")
    private Long deviceId;//기계 아이디

    private GeoJsonPoint coordinates; // GeoJSON 포인트 타입 사용

    private List<String> tag;//이미지 분석 후 생성되는 태그

    @Field("created_at")
    private LocalDateTime createdAt;//촬영시간

    @Field("name")
    private String name;

    @Field("origin_image_url")
    private String originImageUrl;

    @Field("guide_image_url")
    private String guideImageUrl;

    @Field("mask_image_url")
    private String maskImageUrl;

    private Boolean flag;

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCoordinates(double longitude, double latitude) {
        this.coordinates = new GeoJsonPoint(longitude, latitude);
    }

    public void setOriginImageUrl(String originImageUrl) {
        this.originImageUrl = originImageUrl;
    }

    public void setGuideImageUrl(String guideImageUrl) {
        this.guideImageUrl = guideImageUrl;
    }

    public void setMaskImageUrl(String maskImageUrl) {
        this.maskImageUrl = maskImageUrl;
    }

    // Getters, Setters, Constructors
}