package my.sample.api.resource.assembler;

import my.sample.api.resource.representation.PageTO;
import my.sample.api.resource.representation.SampleTO;
import my.sample.domain.entity.Sample;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class SampleAssembler {

    public static Sample toDomain(SampleTO to ) {
        if (to == null) {
            return null;
        }

        Sample sample = new Sample();
        sample.setCode( to.getCode() );
        sample.setDescription( to.getDescription() );
        return sample;
    }

    public static PageTO<SampleTO> toRepresentation( Page<Sample> domain ) {
        if ( domain == null ) {
            return null;
        }

        List<Sample> samples = domain.getContent();
        List<SampleTO> rolesTO = toRepresentation( samples );

        PageTO<SampleTO> to = PageAssembler.toRepresentation( domain, rolesTO );
        return to;
    }

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
