package com.videostore;

public enum MovieType {
    REGULAR {
        @Override
        public double getBasisAmount() {
            return 2;
        }
    },
    NEW_RELEASE {
        @Override
        public double getBasisAmount() {
            return 0;
        }
    },
    CHILDREN {
        @Override
        public double getBasisAmount() {
            return 1.5;
        }
    };

    abstract double getBasisAmount();
}
