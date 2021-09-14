package com.example.lby.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lby.model.Resource

class LobbyViewModel: ViewModel() {

    private var resourceListMtb: MutableLiveData<ArrayList<Resource>>? = null

    fun init() {
        if (resourceListMtb != null) {
            return
        }

        resourceListMtb = MutableLiveData();
        resourceListMtb!!.value = Resource.generateList()

    }

    fun getResource(): LiveData<ArrayList<Resource>>? {
        return resourceListMtb
    }
}