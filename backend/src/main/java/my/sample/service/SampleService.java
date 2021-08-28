package my.sample.service;

import my.sample.domain.entity.Sample;
import my.sample.repo.SampleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SampleService {

    private SampleRepository sampleRepository;

    public SampleService( SampleRepository repo ) {
        this.sampleRepository = repo;
    }

    @Transactional
    public Sample save( Sample sample ) {
        sample = this.sampleRepository.save( sample );
        return sample;
    }

    public Optional<Sample> getSample( Long id ) {
        Optional<Sample> optional = this.sampleRepository.findById( id );
        return optional;
    }

    public Page<Sample> getSamples( String code, int page, int pageSize ) {

        PageRequest pageable = PageRequest.of( page, pageSize );
        Page<Sample> sampleList = this.sampleRepository.findByCode( code, pageable );
        return sampleList;
    }
}
