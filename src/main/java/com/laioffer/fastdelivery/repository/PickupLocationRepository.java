package com.laioffer.fastdelivery.repository;

import com.laioffer.fastdelivery.model.Location;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupLocationRepository extends ElasticsearchRepository<Location, Long> {
}
