package my.sample.api.resource.representation;

import java.util.List;

public class PageTO<T> {
    private int page = 0;
    private int pageSize = 0;
    private boolean ascending = false;
    private String sortColumn;
    private long totalElements = 0;
    private long totalPages = 0;
    private long numberOfElements = 0;

    private List<T> content;

    public PageTO() {
    }

    public PageTO( List<T> content) {
        this.content = content;
    }

    public PageTO( List<T> content, int pageSize ) {
        this.content = content;
        this.pageSize = pageSize;
    }

    public PageTO( List<T> content, int page, int pageSize ) {
        this.content = content;
        this.pageSize = pageSize;
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage( int page ) {
        this.page = page;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending( boolean ascending ) {
        this.ascending = ascending;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn( String sortColumn ) {
        this.sortColumn = sortColumn;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize( int pageSize ) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements( long totalElements ) {
        this.totalElements = totalElements;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages( long totalPages ) {
        this.totalPages = totalPages;
    }

    public long getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements( long numberOfElements ) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent( List<T> content ) {
        this.content = content;
    }


}
