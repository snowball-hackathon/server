package com.capstone.server.Service;

import com.capstone.server.Domain.TbRead;
import com.capstone.server.Interface.TbReadInterface;
import com.capstone.server.Repository.TbReadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
class TbReadServiceTest {
    @Mock
    TbReadRepository tbReadRepository;
    @InjectMocks
    TbReadService tbReadService;

    @Test
    void getUserReadInfo_test(){
        List list = new ArrayList();
        TbReadInterface tbReadInterface = new TbReadInterface() {
            @Override
            public int getTbArticleId() {
                return 1;
            }

            @Override
            public String getAtcTitle() {
                return "title1";
            }

            @Override
            public String getGenre() {
                return "01";
            }
        };
        list.add(tbReadInterface);
        Page<TbReadInterface> page = new PageImpl<>(list);
        when(tbReadRepository.findAllByTbUserIdLEFTJOINArticle(anyString(), anyString(), any()))
                .thenReturn(page);

        List<TbReadInterface> result = tbReadService.getUserReadInfo("test1234", "D1", 0, 1);

        assertThat(result.get(0).getTbArticleId()).isEqualTo(1);

    }

}