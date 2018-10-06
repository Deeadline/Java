package com.company.bank.domain;


public interface Savable {
    String save();

    void load(String content);
}
