/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controllers;

import entities.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import web.beans.Pager;
import web.database.DataHelper;
import web.enums.SearchType;

/**
 *
 * @author yehor
 */
@ManagedBean
@SessionScoped
public class SearchController implements Serializable {

    private boolean editMode = false;
    private boolean selectFromPager = false;
    private int pages;
    private long genreSelectedId;
    private String letterSelected;
    private String searchString;
    private SearchType searchType;
    private String lastSQL;
    private Map<String, SearchType> map = new HashMap<>();
    private transient int row = -1;
    
    private Pager<Book> pager = new Pager<Book>();

    public int getRow() {
        
        return ++row;
    }


    /**
     * Creates a new instance of SearchController
     */
    public SearchController() {
        ResourceBundle rb = ResourceBundle.getBundle("web.properties.messages",
                FacesContext.getCurrentInstance().getViewRoot().getLocale());

        map.put(rb.getString("author_name"), SearchType.AUTHOR);
        map.put(rb.getString("book_title"), SearchType.NAME);
    }

    public long getGenreSelectedId() {
        return genreSelectedId;
    }

    public void setGenreSelectedId(long genreSelectedId) {
        this.genreSelectedId = genreSelectedId;
    }

    public void setBooksOnPage(int booksOnPage) {
        pager.setBooksOnPage(booksOnPage);
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }
    public boolean isEditMode() {
        return editMode;
    }

    public void cancelEdit()
    {
        
        
    }
    
    public void switchMode() {
        this.editMode = !editMode;
    }

    public Pager<Book> getPager() {
        return pager;
    }

    
    public Map<String, SearchType> getSearchList() {
        return map;
    }

    public int getTotalBooksCount() {
        return pager.getTotalBooksCount();
    }
 
    public int getPageSelected() {
        return pager.getSelectedPage();
    }
    
    public String getSearchString() {
        return searchString;
    }

    public String getLetterSelected() {
        return letterSelected;
    }

    public void setLetterSelected(String letterSelected) {
        this.letterSelected = letterSelected;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public int getBooksOnPage() {
        return pager.getBooksOnPage();
    }

    public List<Book> getCurrentBookList() {
        return pager.getCurrentBookList();
    }


    public void selectPage() {
        row = -1;
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        pager.setSelectedPage(Integer.valueOf(params.get("pageSelected")));
        DataHelper.getInstance().runCurrentCriteria();
    }


    public void updateBooks()
    {
        
        cancelEdit();
    }
            

    public void fillBooksByGenre() {
        
        row = -1;
        Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer genre_id = Integer.valueOf(map.get("genre_id"));
        flushValues(1, genre_id, null);
        if (genre_id == 17) {
            fillAllBooks();
        } else {
            DataHelper.getInstance().getBooksByGenre(genre_id, pager);
        }

    }

    public void fillAllBooks() {
        row = -1;
        flushValues(1, genreSelectedId, letterSelected);
        DataHelper.getInstance().getAllBooks(pager);
    }

    public void fillBooksByString() {
        row = -1;
        flushValues(1, genreSelectedId, letterSelected);
        if(searchType == SearchType.NAME)
        {
            DataHelper.getInstance().getBooksByName(searchString, pager);
        }
        else
        {
            DataHelper.getInstance().getBooksByAuthor(searchString, pager);
        }
    }

    public void booksOnPageChange(ValueChangeEvent e)
    {
        row = -1;
        cancelEdit();
        pager.setBooksOnPage(Integer.valueOf(e.getNewValue().toString()).intValue());
        pager.setSelectedPage(1);
        DataHelper.getInstance().runCurrentCriteria();
        
    }
    
    private void flushValues(int page, long genreId, String letterSel)
    {
        pager.setSelectedPage(page);
        genreSelectedId = genreId;
        letterSelected = letterSel;
    }
    



}
