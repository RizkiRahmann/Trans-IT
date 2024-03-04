package com.pioneers.transit.controller;

import com.pioneers.transit.dto.request.DestinationRequest;
import com.pioneers.transit.dto.request.UserRequest;
import com.pioneers.transit.dto.response.*;
import com.pioneers.transit.service.UserService;
import com.pioneers.transit.specification.user.UserSearchDTO;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import com.pioneers.transit.utils.constant.ConstMessage;
import com.pioneers.transit.utils.constant.ConstStatus;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.USER)
public class UserController {
    private final UserService userService;
    private final BuildResponse buildResponse;
    private String entity = "User";

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest request){
        UserResponse userResponse = userService.create(request);
        ControllerResponse<UserResponse> response = buildResponse.response(userResponse, ConstStatus.STATUS_CREATE, entity, ConstMessage.M_CREATE);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','CUSTOMER')")
    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                    @RequestParam(name = "size", defaultValue = "5") Integer size,
                                    @RequestParam(name = "sort-by", defaultValue = "name") String sortBy,
                                    @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                    @ModelAttribute UserSearchDTO userSearchDTO){
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        PageResponseWrapper<UserResponse> userResponsePageResponseWrapper = userService.getAll(pageRequest,userSearchDTO);
        ControllerResponse<PageResponseWrapper<UserResponse>> response = buildResponse.response(userResponsePageResponseWrapper, ConstStatus.STATUS_OK, entity, ConstMessage.M_GET);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','CUSTOMER')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        UserResponse userServiceById = userService.getById(id);
        ControllerResponse<UserResponse> response = buildResponse.response(userServiceById, ConstStatus.STATUS_OK, entity, ConstMessage.M_GET);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserRequest request){
        UserResponse userResponse = userService.update(request);
        ControllerResponse<UserResponse> response = buildResponse.response(userResponse, ConstStatus.STATUS_CREATE, entity, ConstMessage.M_UPDATE);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping("/image/{imageId}")
    public ResponseEntity<?> updateImage(@PathVariable String imageId,
                                         @RequestParam(name = "image",required = false) MultipartFile file) throws IOException {
        UserResponseImage userResponseImage = userService.updateImage(imageId, file);
        ControllerResponse<UserResponseImage> response = buildResponse.response(userResponseImage, ConstStatus.STATUS_CREATE, entity, ConstMessage.M_UPDATE);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        userService.deleteById(id);
        ControllerResponse<String> response = buildResponse.response("Data berhasil dihapus", ConstStatus.STATUS_OK, entity, ConstMessage.M_DELETE);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','CUSTOMER')")
    @GetMapping(path = "/image/{imageId}")
    public ResponseEntity<?> getImage(@PathVariable String imageId){
        byte[] image = userService.getImage(imageId);
        ControllerResponse<byte[]> response = buildResponse.response(image, ConstStatus.STATUS_OK, entity, ConstMessage.M_GET);
        return ResponseEntity.ok(response);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(image);
    }
}
