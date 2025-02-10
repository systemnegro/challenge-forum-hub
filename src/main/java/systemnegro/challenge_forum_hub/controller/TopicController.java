package systemnegro.challenge_forum_hub.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import systemnegro.challenge_forum_hub.domain.topic.CreateTopicDTO;
import systemnegro.challenge_forum_hub.domain.topic.TopicDetailsDTO;
import systemnegro.challenge_forum_hub.domain.topic.UpdateTopicDTO;
import systemnegro.challenge_forum_hub.service.TopicService;


@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TopicDetailsDTO> create(@RequestBody @Valid CreateTopicDTO topicDTO, UriComponentsBuilder uriBuilder) {
        var topic = service.createTopic(topicDTO);
        var uri = uriBuilder.path("topicos/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDetailsDTO(topic));
    }

    @GetMapping
    public ResponseEntity<Page<TopicDetailsDTO>> listTopics(@PageableDefault Pageable pageable) {
        var page = service.listTopics(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailsDTO> getTopic(@PathVariable Long id) {
        var topic = service.getTopic(id);
        return ResponseEntity.ok(new TopicDetailsDTO(topic));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDetailsDTO> updateTopic(@RequestBody UpdateTopicDTO updateTopicDTO, @PathVariable Long id) {
        var updatedTopic = service.updateTopic(id, updateTopicDTO);
        return ResponseEntity.ok(new TopicDetailsDTO(updatedTopic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id){
        service.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

}
