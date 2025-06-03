package com.example.restapp.user;

import com.example.restapp._core.error.ex.Exception400;
import com.example.restapp._core.error.ex.Exception404;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse.SaveDTO joinUser(UserRequest.DTO reqDTO) {
        // 1. 중복 회원 찾기
        User foundUser = userRepository.findUserByName(reqDTO.getName());
        if (foundUser != null) throw new Exception400("이미 존재하는 회원입니다.");

        // 2. 유저 객체로 변환
        User user = reqDTO.toEntity(reqDTO.getName());

        // 3. save
        User userPS = userRepository.save(user);

        // 4. DTO 반환
        return new UserResponse.SaveDTO(userPS);
    }

    public UserResponse.DTO getUser(long userId) {
        // 1. 없는 id 접근 체크
        User userPS = userRepository.findUserById(userId);
        if (userPS == null) throw new Exception404("해당하는 유저를 찾을 수 없습니다.");

        // 2. DTO 반환
        return new UserResponse.DTO(userPS);
    }

    @Transactional
    public UserResponse.DTO updateUser(UserRequest.DTO reqDTO, long userId) {
        // 1. 없는 id 접근 체크
        User userPS = userRepository.findUserById(userId);
        if (userPS == null) throw new Exception404("해당하는 유저를 찾을 수 없습니다.");

        // 2. 이름 중복 체크
        User foundUser = userRepository.findUserByName(reqDTO.getName());
        if (foundUser != null) throw new Exception400("이미 해당 이름을 가진 유저가 존재합니다.");

        // 3. update (JPA를 이용한 Dirty Checking)
        userPS.update(reqDTO.getName());

        // 4. DTO 반환
        return new UserResponse.DTO(userPS);
    }
}
