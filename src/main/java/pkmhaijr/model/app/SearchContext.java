package pkmhaijr.model.app;

import pkmhaijr.model.enums.FilterType;
import pkmhaijr.model.enums.Genre;
import pkmhaijr.model.enums.SortingType;

/**
 * Created by Asasello on 23-Apr-17.
 */
public class SearchContext {
    private String searchPhrase;
    private SortingType sortingType;
    private FilterType filterType;
    private Genre genreType;

    private SearchContext(final String searchPhrase, final SortingType sortingType, final FilterType filterType, final Genre genreType) {
        this.searchPhrase = searchPhrase;
        this.sortingType = sortingType;
        this.filterType = filterType;
        this.genreType = genreType;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public SortingType getSortingType() {
        return sortingType;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public Genre getGenreType() {
        return genreType;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public void setSortingType(SortingType sortingType) {
        this.sortingType = sortingType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public void setGenreType(Genre genreType) {
        this.genreType = genreType;
    }

    public static class Builder {
        private String nestedSearchPhrase;
        private SortingType neastedSortingType = SortingType.NONE;
        private FilterType neastedFilterType = FilterType.ALL;
        private Genre neastedGenreType = Genre.ALL;

        public Builder(String searchPhrase) {
            this.nestedSearchPhrase = searchPhrase;
        }

        public Builder sortingType(SortingType sortingType) {
            this.neastedSortingType = sortingType;
            return this;
        }

        public Builder filterType(FilterType filterType) {
            this.neastedFilterType = filterType;
            return this;
        }

        public Builder genreType(Genre genreType) {
            this.neastedGenreType = genreType;
            return this;
        }

        public SearchContext build(){
            return new SearchContext(nestedSearchPhrase,neastedSortingType,neastedFilterType,neastedGenreType);
        }
    }
}
