package com.bskyb.internettv.thirdparty;

import java.util.HashMap;
import java.util.Map;

public class MovieServiceImpl implements MovieService {

    private static final Map<String, MovieServiceImpl> BY_LABEL = new HashMap();
    public final String label;
    public final String value;

    private MovieServiceImpl(String label, String value) {
        this.label = label;
        this.value = value;
    }
    public MovieServiceImpl() {value=null;label = null;};

    private static String[][] movies= {
            {"Alien","18"},
            {"Star Wars", "12"},
            {"Toy Story", "U"}
    };
    static {
        for (String[] e : movies) {
            BY_LABEL.put(e[0], new MovieServiceImpl(e[0],e[1]));
        }
    }
    public static MovieServiceImpl valueOfLabel(String title) {
        return BY_LABEL.get(title);
    }

    public String getParentalControlLevel(String titleId)
            throws TitleNotFoundException, TechnicalFailureException {
        MovieServiceImpl movie = BY_LABEL.get(titleId);
        if (movie == null) throw new TitleNotFoundException();
        String parentalControl = movie.value;
        if (parentalControl == null) throw new TechnicalFailureException();
        return parentalControl;
   }

}
