package com.company.server.verifiers;

public interface IVerifier<T> {
    public boolean verify(T obj);
}
