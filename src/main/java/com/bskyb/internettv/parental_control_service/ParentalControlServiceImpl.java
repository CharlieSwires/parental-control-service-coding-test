package com.bskyb.internettv.parental_control_service;

import com.bskyb.internettv.thirdparty.MovieService;
import com.bskyb.internettv.thirdparty.MovieServiceImpl;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;

public class ParentalControlServiceImpl implements ParentalControlService {

    public boolean canWatchMovie(String customerParentalControlLevel, String movieId)
            throws Exception {
        if (customerParentalControlLevel == null || movieId == null)  throw new TechnicalFailureException();
        ParentalControlEnum watcher = ParentalControlEnum.valueOfLabel(customerParentalControlLevel);
        MovieService ms = new MovieServiceImpl();
        ParentalControlEnum movie = ParentalControlEnum.valueOfLabel(ms.getParentalControlLevel(movieId));

        return ParentalControlEnum.allowAccess(movie, watcher);
    }

}
