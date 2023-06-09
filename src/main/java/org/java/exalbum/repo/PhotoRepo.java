package org.java.exalbum.repo;

import java.util.List;

import org.java.exalbum.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Integer> {
	public List<Photo> findByTitleContaining(String title);
}
