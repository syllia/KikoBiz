package bj.kiko.projects.kikobiz.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sylliamehou-loko on 16-05-14.
 */
public class Priority implements Parcelable{
    private int realPriority;
    private int startPriority;
    private int priorityToCompare;

    public Priority(int realPriority, int startPriority){
        this.realPriority = realPriority;
        this.startPriority = startPriority;
        this.priorityToCompare = startPriority;
    }

    public int getRealPriority() {
        return realPriority;
    }

    public void setRealPriority(int realPriority) {
        this.realPriority = realPriority;
    }

    public int getStartPriority() {
        return startPriority;
    }

    public void setStartPriority(int startPriority) {
        this.startPriority = startPriority;
    }

    public int getPriorityToCompare() {
        return priorityToCompare;
    }

    public void setPriorityToCompare(int priorityToCompare) {
        this.priorityToCompare = priorityToCompare;
    }

    public Priority(Parcel source) {

        if (source.dataSize() > 0) {
            this.realPriority = source.readInt();
            this.startPriority = source.readInt();
            this.priorityToCompare = source.readInt();


        }

    }


    public final static Parcelable.Creator<Priority> CREATOR = new Parcelable.Creator<Priority>() {
        public Priority createFromParcel(Parcel in) {
            return new Priority(in);
        }

        public Priority[] newArray(int size) {
            return new Priority[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.realPriority);
        dest.writeInt(this.startPriority);
        dest.writeInt(this.priorityToCompare);

    }
}
