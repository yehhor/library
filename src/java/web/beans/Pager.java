/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yehor
 */
public class Pager<T> {
    private Integer totalBooksCount = 0;
    private List<T> currentBookList;
    private Integer selectedPage = 1;
    private Integer booksOnPage = 10;
    private List<Integer> pageCount = new ArrayList<>();
    

    public Integer getFrom()
    {
        return selectedPage * booksOnPage - booksOnPage;
    }
    
    public Integer getTo()
    {
        return booksOnPage;
    }
    
    public Integer getSelectedPage() {
        return selectedPage;
    }

    public void setSelectedPage(Integer selectedPage) {
        this.selectedPage = selectedPage;
    }

    public Integer getBooksOnPage() {
        return booksOnPage;
    }

    public void setBooksOnPage(Integer booksOnPage) {
        this.booksOnPage = booksOnPage;
    }

    
    public Integer getTotalBooksCount() {
        return totalBooksCount;
    }

    public void setTotalBooksCount(Integer totalBooksCount) {
        this.totalBooksCount = totalBooksCount;
    }

    public List<T> getCurrentBookList() {
        return currentBookList;
    }

    public void setCurrentBookList(List<T> currentBookList) {
        this.currentBookList = currentBookList;
    }

    public List<Integer> getPageCount() {
        
        pageCount.clear();
        for (int i = 1; i <= (totalBooksCount + booksOnPage / 2) / booksOnPage; i++) {
            pageCount.add(i);
        }
        
        return pageCount;
    }

    public void setPageCount(List<Integer> pageCount) {
        this.pageCount = pageCount;
    }

    
    
}
