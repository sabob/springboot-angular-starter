package my.sample.service;

import my.sample.api.resource.representation.SampleTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleService {

    public List<SampleTO> getStuff( String query, int page, int pageSize) {
        return new ArrayList();
    }
}
