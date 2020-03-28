package com.bridgelabz.censusAnalyser;

import java.io.Reader;
import java.util.Iterator;

public interface CSV_Interface {
    public<E> Iterator<E> getCSVfileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException ;
    //public<E> List<E> geCSVfileList(Reader reader, Class<E> csvClass) throws CensusAnalyserException ;
}
