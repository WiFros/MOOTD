package com.bwd4.mootd.controller;

import com.bwd4.mootd.common.response.ApiResponse;
import com.bwd4.mootd.dto.request.PhotoUploadRequestDTO;
import com.bwd4.mootd.dto.response.MapResponseDTO;
import com.bwd4.mootd.service.PhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/photos")
@Slf4j
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    //TODO 촬영 기기의 고유정보를 입력받아야함.
    @Operation(summary = "촬영 후 사진을 서버에 업로드하는 api", description = "촬영 사진을 서버로 업로드 합니다. !! 향후에 안드로이드 기기에서 가이드라인까지 생성한다면 가이드라인도 서버로 전송해야합니다.!!")
    @PostMapping(consumes = "multipart/form-data")
    public Mono<ResponseEntity<ApiResponse<String>>> uploadPhoto(PhotoUploadRequestDTO request) {
        //1.일단 "OK"d응답 성공을 반환한다.
        //2.입력받은 이미지를 S3에 업로드한다.
        //3.입력받은 이미지에서 메타정보를 추출하여, 촬영시간, 위치정보(위도,경도)등을 추출한다.
        log.info("file is null = {}", request.originImageFile().isEmpty());
        photoService.uploadPhotoLogics(request).subscribe();
        return Mono.just(new ResponseEntity<>(ApiResponse.success("서버 전송이 완료되었습니다.", null), HttpStatus.OK));
    }

    @Operation(summary = "위도, 경도, 반경(km)를 기반으로 해당 지역에 있는 사진목록을 조회합니다.")
    @GetMapping
    public Mono<ResponseEntity<ApiResponse<List<MapResponseDTO>>>> getImages(
            @Parameter(description = "위도")
            @RequestParam Double latitude,
            @Parameter(description = "경도")
            @RequestParam Double longitude,
            @Parameter(description = "(위도,경도)기반으로 조회할 반경(km)")
            @RequestParam Double radius) {
        return photoService.findPhotosWithinRadius(latitude, longitude, radius)
                .collectList()  // 모든 데이터를 리스트로 변환
                .map(list -> ResponseEntity.ok(ApiResponse.success("지도 이미지 조회 성공", list)));
    }

    @GetMapping("/{id}")
    public Mono<String> getImage(@PathVariable String id) {
        return Mono.just("ok");
    }
}