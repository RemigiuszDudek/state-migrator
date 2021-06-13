package com.github.remigiuszdudek.statemigrator.plugin.testproject

import com.github.remigiuszdudek.statemigrator.api.PersistentState

@PersistentState
class SimpleState {
    private String stringAttribute
    private int intAttribute
    private long longAttribute

    String getStringAttribute() {
        return stringAttribute
    }

    void setStringAttribute(String stringAttribute) {
        this.stringAttribute = stringAttribute
    }

    int getIntAttribute() {
        return intAttribute
    }

    void setIntAttribute(int intAttribute) {
        this.intAttribute = intAttribute
    }

    long getLongAttribute() {
        return longAttribute
    }

    void setLongAttribute(long longAttribute) {
        this.longAttribute = longAttribute
    }
}
