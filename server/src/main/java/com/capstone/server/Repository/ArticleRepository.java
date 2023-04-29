package com.capstone.server.Repository;

import com.capstone.server.Domain.Article;
import com.capstone.server.Interface.ArticleInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

    @Query("select a.atcTitle, a.atcWriter, a.atcPhotoIn, a.difficulty, a.genre from Article a left outer join TbRead t on a.id=t.tbArticleId where t.tbUserId = :id and a.genre = :genre and t.tbArticleId is NULL")
    Page<ArticleInterface> findByArticleLEFTJOINTbRead(String id, String genre, Pageable pageable);
}
