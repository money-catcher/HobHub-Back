//package MoneyCatcher.HobHub.KaKao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.io.IOException;
//import java.util.Map;
//
////아니이거 코드 200으로 잘 나오고 userInfo랑 다 잘나오는데 response.html 리턴받는 부분에서 에러뜨는듯??
//@Controller
//@RequestMapping("/member")
//public class KaKaoController {
//
//    @Autowired
//    KaKaoService ks;
//
//    @GetMapping("/do")
//    public String loginPage()
//    {
//        return "index";
//    }//로그인페이지 리턴
//
//    @GetMapping("/login")
//    public String getCI(@RequestParam(value="code") String code, Model model) throws IOException {
//        System.out.println("code = " + code);
//        String access_token = ks.getToken(code);
//        Map<String, Object> userInfo = ks.getUserInfo(access_token);
//        model.addAttribute("code", code);
//        model.addAttribute("access_token", access_token);
//        model.addAttribute("userInfo", userInfo);
//
//        return "response";
//    }
//
//}