package com.bridgelabz.CensusAnalyser;

import java.io.Reader;
import java.util.Iterator;

public interface CSV_Interface {
    public<E> Iterator<E> getCSVfileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException ;
}
