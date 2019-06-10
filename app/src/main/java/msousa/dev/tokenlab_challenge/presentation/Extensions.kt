package msousa.dev.tokenlab_challenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations

fun <X, Y> MediatorLiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}