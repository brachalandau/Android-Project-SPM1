package com.example.spm1.Utils;

//Package Status: Start, Success and Failure//
public class AddParcelState {

    //********Variables********//
    public boolean start;
    public boolean success;
    public  boolean failure;

    //********Constructor********//
    public AddParcelState(boolean start, boolean success, boolean failure) {
        this.start = start;
        this.success = success;
        this.failure = failure;
    }
    public AddParcelState() {
        this.start = true;
        this.success = false;
        this.failure = false;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isFailure() {
        return failure;
    }

    public void setFailure(boolean failure) {
        this.failure = failure;
    }
}
