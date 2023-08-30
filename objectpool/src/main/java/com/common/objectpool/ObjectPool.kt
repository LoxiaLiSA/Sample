package com.common.objectpool

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object ObjectPool {

    val store = hashMapOf<ObjectKey, MutableLiveData<Any>>()

    inline fun <reified ObjectT : ModelObject> put(objectT: ObjectT) {
        val key = ObjectKey(objectT.objectUniqueId, ObjectT::class)
        val exist = store[key]
        if (exist != null) {
            exist.value = objectT
        } else {
            val newly = MutableLiveData<Any>()
            newly.value = objectT
            store[key] = newly
        }
    }

    inline fun <reified ObjectT : ModelObject> get(id: Long): LiveData<ObjectT> {
        val key = ObjectKey(id, ObjectT::class)
        val exist = store[key]
        return if (exist != null) {
            exist as LiveData<ObjectT>
        } else {
            val newly = MutableLiveData<Any>()
            store[key] = newly
            newly as LiveData<ObjectT>
        }
    }
}