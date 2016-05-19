package web.controllers;


import entities.Genre;
import java.util.ArrayList;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;
import web.database.DataHelper;


@ManagedBean
@ApplicationScoped
public class GenreController {

    private ArrayList<String> letterList = new ArrayList<String>();
    private List<Genre> genreList = new ArrayList<Genre>();

    public GenreController() {
        fillGenresAll();
        fillLetters();
    }

    private void fillLetters()
    {
        String a = "abcdefghijklmnopqrstuvwyz".toUpperCase();
        for (String s : a.split(""))
        {
            letterList.add(s);
        }
    }

    public ArrayList<String> getLetterList() {
        return letterList;
    }
    
    
    private List<Genre> fillGenresAll() {
        return genreList = DataHelper.getInstance().getAllGenres();
    }

    public List<Genre> getGenreList() {
        if (!genreList.isEmpty()) {
            return genreList;
        } else {
            return fillGenresAll();
        }
    }
}
