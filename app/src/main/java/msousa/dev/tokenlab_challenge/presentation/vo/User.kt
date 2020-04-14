package msousa.dev.tokenlab_challenge.presentation.vo

import android.os.Parcel

data class User(val name: String) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::User)
    }

    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(name)
        }
    }
}