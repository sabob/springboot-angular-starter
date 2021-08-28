package my.sample.service;

import my.sample.domain.entity.Sample;
import my.sample.repo.SampleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleService {

    private SampleRepository sampleRepository;

    public SampleService( SampleRepository repo ) {
        this.sampleRepository = repo;
    }

    public Page<Sample> getSamples( String code, int page, int pageSize ) {

        PageRequest pageable = PageRequest.of( page, pageSize );
        Page<Sample> sampleList = this.sampleRepository.findByCode( code, pageable );
        return sampleList;
    }
}
