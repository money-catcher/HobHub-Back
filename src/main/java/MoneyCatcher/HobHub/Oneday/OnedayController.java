package MoneyCatcher.HobHub.Oneday;

import MoneyCatcher.HobHub.Board.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//원데이클래스 출력 및 입력
@RestController
@RequestMapping("/one")
@RequiredArgsConstructor
public class OnedayController {
    //if user_hobby = 가죽, onedayentity에서 hobbyname=가죽인 요소 싹다 출력
    private final OnedayService onedayService;

    @GetMapping("/{hobby_name}")
    public ResponseEntity<List<OnedayEntity>> getOnedayClass(@PathVariable String hobby_name)
    {
        List<OnedayEntity> onedayList = onedayService.findAll(hobby_name);

        return new ResponseEntity<>(onedayList, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveOnedayClass(@RequestBody List<OnedayEntity> onedayEntity)
    {
        onedayService.save(onedayEntity);
        return ResponseEntity.status(HttpStatus.OK).body("save");
    }

}
