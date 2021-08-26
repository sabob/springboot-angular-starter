package my.sample.api.resource.assembler;

import my.sample.api.resource.representation.SampleTO;
import my.sample.domain.entity.Sample;

import java.util.ArrayList;
import java.util.List;

public class SampleAssembler {

    public static List<SampleTO> toRepresentation( List<Sample> domainList) {
        List<SampleTO> toList = new ArrayList<>();
        if (domainList == null || domainList.isEmpty()) {
            return toList;
        }

        for (Sample sample : domainList) {
            SampleTO to = toRepresentation( sample );
            if (to != null) {
                toList.add(to);
            }
        }
        return toList;
    }

    public static SampleTO toRepresentation( Sample domain) {

        if (domain == null) {
            return null;
        }
        SampleTO to = new SampleTO();
        to.setCode( domain.getCode() );
        to.setDescription( domain.getDescription() );
        return to;
    }
}
