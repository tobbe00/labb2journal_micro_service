package com.fullstack.labb2journal.mappers;

public interface Mapper<A,B> {
    B mapToDTO(A a);

    A mapToEntity(B b);
}
