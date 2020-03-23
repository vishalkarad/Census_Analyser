package com.bridgelabz.CensusAnalyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface CSV_Interface {
    public<E> Iterator<E> getCSVfileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException ;
    public<E> List<E> geCSVfileList(Reader reader, Class<E> csvClass) throws CensusAnalyserException ;
}
