package com.example.valorantagent

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agent(
    val name: String?,
    val description: String?,
    val icon: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    companion object : Parceler<Agent> {

        override fun Agent.write(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(description)
            parcel.writeString(icon)
        }

        override fun create(parcel: Parcel): Agent {
            return Agent(parcel)
        }
    }
}
