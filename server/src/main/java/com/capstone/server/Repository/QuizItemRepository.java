package com.capstone.server.Repository;

import com.capstone.server.Domain.QuizItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizItemRepository extends JpaRepository<QuizItem, String> {
}