/**
 * Author: BIKASH
 */
package com.ekart.constant;

public enum ExecutionStatus {
        IN_PROGRESS("In_progress"),
        REQUESTED("requested"),
        FAILED("failed"),
        DISPATCHED("dispatched"),
        DELIVERED("delivered");


        private final String code;

        ExecutionStatus(String code) {
            this.code = code;
        }
        public String getCode() {
            return code;
        }

        @Override
        public String toString() {
            return this.code;
        }

}
