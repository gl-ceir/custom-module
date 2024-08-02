package com.gl.custommodule.enums;

public enum Rules {
    CUSTOMS_VALID_TAC,
    TYPE_APPROVED,
    CHECK_NWL,
    CHECK_CUSTOMS,
    CHECK_STOLEN,
    CHECK_GLOBAL_BLACKLIST,
    CHECK_BLACKLIST,
    CHECK_GREYLIST,
    CHECK_EXCEPTIONLIST;

    public String getRuleName() {
        return this.name();
    }

}
