package com.example.speedmath;

import android.os.Parcel;
import android.os.Parcelable;

public class OperationModel implements Parcelable {

    private int firstTerm;
    private int secondTerm;
    private double result;
    private double userResult;

    public OperationModel() {
    }

    public OperationModel(int firstTerm, int secondTerm, double result, double userResult) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
        this.result = result;
        this.userResult = userResult;
    }

    protected OperationModel(Parcel in) {
        firstTerm = in.readInt();
        secondTerm = in.readInt();
        result = in.readDouble();
        userResult = in.readDouble();
    }

    public static final Creator<OperationModel> CREATOR = new Creator<OperationModel>() {
        @Override
        public OperationModel createFromParcel(Parcel in) {
            return new OperationModel(in);
        }

        @Override
        public OperationModel[] newArray(int size) {
            return new OperationModel[size];
        }
    };

    public int getFirstTerm() {
        return firstTerm;
    }

    public int getSecondTerm() {
        return secondTerm;
    }

    public double getResult() {
        return result;
    }

    public double getUserResult() {
        return userResult;
    }

    public void setFirstTerm(int firstTerm) {
        this.firstTerm = firstTerm;
    }

    public void setSecondTerm(int secondTerm) {
        this.secondTerm = secondTerm;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setUserResult(double userResult) {
        this.userResult = userResult;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(firstTerm);
        dest.writeInt(secondTerm);
        dest.writeDouble(result);
        dest.writeDouble(userResult);
    }
}
