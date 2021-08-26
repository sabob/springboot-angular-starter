package my.sample.service;

import my.sample.domain.entity.Sample;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleService {

    public List<Sample> getStuff( String query, int page, int pageSize) {
        return new ArrayList();
    }
}
