package ma.ensaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.ensaf.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{

}
