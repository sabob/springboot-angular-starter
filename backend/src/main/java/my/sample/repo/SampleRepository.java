package my.sample.repo;

import my.sample.domain.entity.Sample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SampleRepository extends JpaRepository<Sample, Long> {

    Sample save( Sample Sample );

    Optional<Sample> findById( Long id );

    Page<Sample> findAll( Pageable pageable );

    Page<Sample> findByCode( String code, Pageable pageable );




}
