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
        User foundUser = userRepository.findUserByName(reqDTO.getName());
        if (foundUser != null) throw new Exception400("이미 존재하는 회원입니다.");
        User user = reqDTO.toEntity(reqDTO.getName());

        User userPS = userRepository.save(user);
        return new UserResponse.SaveDTO(userPS);
    }

    public UserResponse.DTO getUser(long userId) {
        User userPS = userRepository.findUserById(userId);
        if (userPS == null) throw new Exception404("해당하는 유저를 찾을 수 없습니다.");
        return new UserResponse.DTO(userPS);
    }
}
