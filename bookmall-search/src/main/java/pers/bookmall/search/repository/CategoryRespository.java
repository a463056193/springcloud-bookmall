package pers.bookmall.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pers.bookmall.search.pojo.Category;

public interface CategoryRespository extends ElasticsearchRepository<Category, Long> {
}
