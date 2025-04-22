package org.demo.book.persistance;

import java.math.BigDecimal;

public enum BookType {
    NEW_RELEASE(BigDecimal.ZERO, BigDecimal.ZERO),
    REGULAR(BigDecimal.ZERO, new BigDecimal("0.10")),
    OLD_EDITION(new BigDecimal("0.20"), new BigDecimal("0.25"));

    private final BigDecimal baseDiscount;
    private final BigDecimal bundleDiscount;

    BookType(BigDecimal baseDiscount, BigDecimal bundleDiscount) {
        this.baseDiscount = baseDiscount;
        this.bundleDiscount = bundleDiscount;
    }

    public BigDecimal getBaseDiscount() {
        return baseDiscount;
    }

    public BigDecimal getBundleDiscount() {
        return bundleDiscount;
    }
}

