package MoneyCatcher.HobHub.File;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//게시판 파일 업로드 관련 컨트롤러
@Controller
@RequiredArgsConstructor
public class FileController {

    @Autowired
    private final FileService fileService;


}
