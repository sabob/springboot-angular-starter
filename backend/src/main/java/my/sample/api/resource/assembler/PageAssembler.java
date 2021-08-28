package my.sample.api.resource.assembler;

import my.sample.api.resource.representation.PageTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageAssembler {

    public static <T> PageTO<T> toRepresentation( Page domain, List<T> content ) {
        PageTO<T> to = new PageTO<T>();
        to.setTotalPages( domain.getTotalPages() );
        to.setNumberOfElements( domain.getNumberOfElements() );
        to.setTotalElements( domain.getTotalElements() );
        to.setPageSize( domain.getPageable().getPageSize() );
        to.setPage( domain.getPageable().getPageNumber() );
        to.setContent( content );
        return to;
    }

}
