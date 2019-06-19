package com.realpacific.projectnoaa.adaptiblesearchers;

import java.util.List;

public interface HasInMemory<T> {
    Searcher get(List<T> data);
}
