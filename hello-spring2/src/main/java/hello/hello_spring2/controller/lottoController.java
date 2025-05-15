package hello.hello_spring2.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

import static javafx.beans.binding.Bindings.concat;

@Controller
public class lottoController {

    @GetMapping("/lotto")
    public String generateLottoNumbers(Model model) {
        // 1부터 45까지 숫자 중 6개를 중복 없이 무작위로 추출
        List<String> lottoNumbers = generateLotto();

        // 생성된 번호를 모델에 담아서 뷰로 전달
        model.addAttribute("lottoNumbers", lottoNumbers);
        model.addAttribute("bonusNumbers", lottoNumbers);

        return "lotto";  // templates/lotto.html 렌더링
    }

    // 로또 번호 생성 메서드
    private List<String> generateLotto() {
        // 1~45 숫자 리스트 생성
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }

        // 리스트를 무작위로 섞음
        Collections.shuffle(numbers);

        // 앞에서 6개 숫자만 추출 후 정렬
        List<Integer> lotto = numbers.subList(0, 6);
        Collections.sort(lotto);  // 보기 좋게 정렬

        // 보너스 번호는 남은 숫자 중에서 1개 뽑기
        Set<Integer> mainSet = new HashSet<>(lotto);
        Integer bonusNumber = null;
        for (int num : numbers) {
            if (!mainSet.contains(num)) {
                bonusNumber = num;
                break;
            }
        }

        System.out.println(lotto);
        System.out.println(lotto.get(0));
        System.out.println(lotto.get(1));
        System.out.println(lotto.get(2));
        System.out.println(lotto.get(3));
        System.out.println(lotto.get(4));
        System.out.println(lotto.get(5));
        System.out.println(bonusNumber);

        StringBuilder sb = new StringBuilder();
        sb.append(lotto.get(0));
        sb.append(",");
        sb.append(lotto.get(1));
        sb.append(",");
        sb.append(lotto.get(2));
        sb.append(",");
        sb.append(lotto.get(3));
        sb.append(",");
        sb.append(lotto.get(4));
        sb.append(",");
        sb.append(lotto.get(5));
        sb.append(", 보너스 번호:");
        sb.append(bonusNumber);
        String concat = sb.toString();

        System.out.println(concat);


        return Collections.singletonList(concat);
    }
}