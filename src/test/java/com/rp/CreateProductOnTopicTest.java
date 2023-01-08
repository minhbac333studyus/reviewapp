package com.rp;

import com.rp.data.ProductOnTopic;
import com.rp.data.TopicPage;
import com.rp.repositories.ProductOnTopicRepository;
import com.rp.repositories.TopicPageRepository;
import com.rp.services.ProductOnTopicService;
import com.rp.services.TopicPageService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class CreateProductOnTopicTest {
    @Mock
    private ProductOnTopicRepository productOnTopicRepository;
    @Mock
    private TopicPageRepository topicPageRepository;
    @InjectMocks
    private ProductOnTopicService productOnTopicService;
    @InjectMocks
    private TopicPageService topicPageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void whenSaveProduct_shouldReturnProduct() {
        MockitoAnnotations.openMocks(this);
        ProductOnTopic product = new ProductOnTopic();
        product.setName("Test Name");
        when(productOnTopicRepository.save(ArgumentMatchers.any(ProductOnTopic.class))).thenReturn(product);
        ProductOnTopic created = productOnTopicService.create(product);
        assertThat(created.getName()).isSameAs(product.getName());
        verify(productOnTopicRepository).save(product);
    }

    @Test
    public void saveProductWithPage() {
        MockitoAnnotations.openMocks(this);
        ProductOnTopic product = new ProductOnTopic();
        product.setName("Test Name");
        List<ProductOnTopic> prods = new ArrayList<>();
        prods.add(product);
        TopicPage topic = new TopicPage();
        topic.setProductOnTopics(prods);
        topicPageRepository.save(topic);

    }

}
