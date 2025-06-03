package com.example.restapp.lotto;

import com.example.restapp._core.util.Resp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LottoController {
    private final LottoService lottoService;

    @PostMapping("/lotto")
    public ResponseEntity<?> makeLotto() {
        LottoResponse.DTO respDTO = lottoService.makeLotto();
        return Resp.ok(respDTO);
    }
}
