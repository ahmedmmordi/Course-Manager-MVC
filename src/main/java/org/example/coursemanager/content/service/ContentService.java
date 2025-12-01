package org.example.coursemanager.content.service;

import org.example.coursemanager.content.model.Content;
import org.example.coursemanager.content.model.DateRange;
import org.example.coursemanager.content.repository.ContentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ContentService {
    private final ContentRepository contentRepository;
    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Page<Content> getAllContents(Pageable pageable) {
        return contentRepository.findAll(pageable);
    }

    public Optional<Content> getContentById(Long id) {
        return contentRepository.findById(id);
    }

    public Content saveContent(Content content) {
        return contentRepository.save(content);
    }

    public boolean deleteContent(Long id) {
        if (contentRepository.existsById(id)) {
            contentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Content> filterContentsByCreatedAt(DateRange range, Pageable pageable) {
        LocalDateTime startDate =
            range == DateRange.ALL_TIME ?
            LocalDateTime.MIN : LocalDateTime.now().minusMonths(range.getMonths());
        return contentRepository.findByCreatedAtAfter(startDate, pageable);
    }
}
