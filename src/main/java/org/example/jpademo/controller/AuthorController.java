package org.example.jpademo.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.jpademo.model.Author;
import org.example.jpademo.model.request.AuthorRequest;
import org.example.jpademo.model.response.APIResponse;
import org.example.jpademo.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @PostMapping("create-author")
    @Operation(summary = "Create new author")
    public ResponseEntity<?> createNewAuthor(@RequestBody AuthorRequest authorRequest){
        Author author = authorService.createNewAuthor(authorRequest);
        return new ResponseEntity<>(
                new APIResponse<>("New Author Created Successfully",
                HttpStatus.CREATED,author,201, LocalDateTime.now()
                ), HttpStatus.CREATED);
    }

    @GetMapping("get-all-author")
    @Operation(summary = "Get All Author")
    public ResponseEntity<?> getAllAuthor(){
        List<Author> authors = authorService.getAllAuthor();
        return new ResponseEntity<>(
                new APIResponse<>("Get All Author Successfully",
                        HttpStatus.OK,authors,200, LocalDateTime.now()
                ), HttpStatus.OK);
    }
    @PutMapping("update-author/{authorId}")
    @Operation(summary = "Update author by ID")
    public ResponseEntity<?> updateAuthor(@PathVariable UUID authorId,
                                          @RequestBody AuthorRequest authorRequest){
        Author author = authorService.updateAuthor(authorId,authorRequest);
        return new ResponseEntity<>(new APIResponse<>("Author update successfully"
        ,HttpStatus.OK,author,200,LocalDateTime.now()
        ),HttpStatus.OK);
    }
    @DeleteMapping("delete-author/{authorId}")
    @Operation(summary = "Delete author by ID")
    public ResponseEntity<?> deleteAuthorById(@PathVariable UUID authorId){
        authorService.deleteAuthorById(authorId);
        return new ResponseEntity<>(new APIResponse<>("Author deleted successfully"
                ,HttpStatus.OK,null,200,LocalDateTime.now()
        ),HttpStatus.OK);
    }
    @GetMapping("get-author-by-id/{authorId}")
    @Operation(summary = "Get author by ID")
    public ResponseEntity<?> getAuthorById(@PathVariable UUID authorId){
        Author author = authorService.getAuthorById(authorId);
        return new ResponseEntity<>(new APIResponse<>("Author get by ID successfully"
                ,HttpStatus.OK,author,200,LocalDateTime.now()
        ),HttpStatus.OK);
    }
    @GetMapping("get-author-by-name/{authorName}")
    @Operation(summary = "Get author by name")
    public ResponseEntity<?> getAuthorByName(@PathVariable String authorName){
        List<Author> author = authorService.getAuthorByName(authorName);
        return new ResponseEntity<>(new APIResponse<>("Author get by name successfully"
                ,HttpStatus.OK,author,200,LocalDateTime.now()
        ),HttpStatus.OK);
    }
}
