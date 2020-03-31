package com.bridgelabz.censusAnalyser.service;

import com.bridgelabz.censusAnalyser.exception.CensusAnalyserException;

import java.io.Reader;
import java.util.Iterator;

public interface CSV_Interface {
    public<E> Iterator<E> getCSVfileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException;
}
