package com.example.portal;

import com.example.portal.domain.Exam;
import com.example.portal.domain.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
@Component
@Data
public class ExamsDTO {
    // добавит в котроллере если кол-во экзаменов и баллов не совпадает произошла какая-то ошибка . ++ добавить запрет на больше 100 и тд
    private List<String> nameExamDTO = new ArrayList<>();
    private List<Integer> valueExamDTO = new ArrayList<>();

    public List<Exam>   transferExam(User user){
        List<Exam> exams = new ArrayList<>();
        if(nameExamDTO.size()!=valueExamDTO.size()) throw new IllegalStateException(" count of exams does not match with the scores");
        for(int i=0;i<nameExamDTO.size();i++){
            exams.add(new Exam(nameExamDTO.get(i),valueExamDTO.get(i),user));
        }
        return exams;
    }

}
