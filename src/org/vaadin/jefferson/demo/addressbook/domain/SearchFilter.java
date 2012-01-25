package org.vaadin.jefferson.demo.addressbook.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SearchFilter implements Serializable {

    private final String term;
    private final String propertyId;
    private String searchName;

    public SearchFilter(String propertyId, String searchTerm, String name) {
        this.propertyId = propertyId;
        term = searchTerm;
        searchName = name;
    }

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @return the propertyId
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * @return the name of the search
     */
    public String getSearchName() {
        return searchName;
    }

    @Override
    public String toString() {
        return getSearchName();
    }

}
