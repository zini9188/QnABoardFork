package krkr.board.service;

import krkr.board.domain.Question;
import krkr.board.domain.QuestionImg;
import krkr.board.dto.QuestionImgDto;
import krkr.board.repository.QuestionImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionImgService {

    private final QuestionImgRepository questionImgRepository;
    private final S3UploadService s3UploadService;

    public void createQuestionImgList(List<MultipartFile> multipartFiles, Question question) {
        List<String> fileList = s3UploadService.uploadImage(multipartFiles);

        for (String fileName : fileList) {
            String fileUrl = s3UploadService.getImagePath(fileName);
            QuestionImgDto questionImgDto = new QuestionImgDto(fileName, fileUrl, question);

            questionImgRepository.save(questionImgDto.toEntity());
        }
    }

    public void deleteQuestionImgList(Question question) {
        List<QuestionImg> questionImgList = questionImgRepository.findAllByQuestion(question);

        for (QuestionImg questionImg: questionImgList) {
            s3UploadService.deleteImage(questionImg.getImgName());
        }
    }
}
