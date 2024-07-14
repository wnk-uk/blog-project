package com.blog.api.service.blog;

import com.blog.api.domain.blog.PostFile;
import com.blog.api.domain.blog.Post;
import com.blog.api.domain.blog.PostStatus;
import com.blog.api.domain.blog.Tag;
import com.blog.api.domain.blog.form.PostForm;
import com.blog.api.repository.blog.FileRepository;
import com.blog.api.repository.blog.PostRepository;
import com.blog.api.repository.blog.TagRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final TagRepository tagRepository;
    private final PostRepository postRepository;

    private final FileRepository fileRepository;

    //TODO S3 사용 여부 고려해서 나중에 리펙터링
    private String uploadFileDir = "C:/blog-image";



    public void createPost(PostForm postForm) {
        Tag tag = tagRepository.findById(postForm.getTag()).orElseThrow(RuntimeException::new);
        
        //PostForm에 Mapper로 이관필요
        Post post = postRepository.save(Post.builder()
                .tag(tag)
                .title(postForm.getTitle())
                .postAt(LocalDateTime.now())
                .createAt(LocalDateTime.now())
                .status(PostStatus.fromKey(postForm.getPostStatus()))
                .content(postForm.getContent())
                .description(postForm.getDescription())
                .thumbnail(postForm.getThumbnail())
                .build());

        tag.addPost(post);
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("post not found"));
    }

    public PostFile uploadInline(MultipartFile files) throws FileUploadException {
        PostFile postFile = PostFile.builder()
                .fileName(files.getOriginalFilename())
                .originFileName(files.getOriginalFilename())
                .fileSize(files.getSize())
                .fileName(generateUniqueFileName(files.getOriginalFilename()))
                .createAt(LocalDateTime.now())
                .contentType(files.getContentType())
                .ext(getExt(files.getOriginalFilename()))
                .build();

        try {
            File dir = new File(uploadFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            Path filePath = Paths.get(uploadFileDir + File.separator + postFile.getFileName());
            Files.copy(files.getInputStream(), filePath);
        } catch (IOException e) {
            throw new FileUploadException("File upload exception. " + e.getStackTrace());
        }

        return fileRepository.save(postFile);
    }

    private String getExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring( pos + 1 );
        return ext.toLowerCase();
    }

    private String generateUniqueFileName(String originalFilename) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        // Random 객체 생성
        Random random = new Random();
        // 0 이상 100 미만의 랜덤한 정수 반환
        String randomNumber = Integer.toString(random.nextInt(Integer.MAX_VALUE));
        String timeStamp = dateFormat.format(new Date());
        return timeStamp + randomNumber + originalFilename;
    }

    public PostFile loadToFile(Long id) throws IOException {
        PostFile postFile = fileRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not Find File"));
        String fileUrl = uploadFileDir + File.separator + postFile.getFileName();
        postFile.imageLoad(fileUrl);
        return postFile;
    }
}
