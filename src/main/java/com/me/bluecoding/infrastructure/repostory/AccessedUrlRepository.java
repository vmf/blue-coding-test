package com.me.bluecoding.infrastructure.repostory;

import com.me.bluecoding.infrastructure.entity.AccessedUrlEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccessedUrlRepository extends CrudRepository<AccessedUrlEntity, String> {
    AccessedUrlEntity findByHash(String hash);

    @Query(value = "select * from accessed_url order by access_count desc limit 100;", nativeQuery = true)
    List<AccessedUrlEntity> findTop100OrderByAccessCountDesc();
}
