package com.rafal_behrendt.hadescatalogue
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val jsonData = getApplication<Application>().resources.openRawResource(R.raw.gods).bufferedReader().use { it.readText() }
    //private var  listOfGodsLiveData: MutableLiveData<List<God>>
    private val listOfGods: ArrayList<God> = loadJsonFiles(jsonData);
    private val favourites: ArrayList<God> = arrayListOf()

//    init{
//        listOfGodsLiveData = MutableLiveData()
//        listOfGodsLiveData.value = getGods()
//    }

    fun getGods(): ArrayList<God> {
        return listOfGods
    }

    fun deleteGod(god: God) {
        listOfGods.remove(god)
    }

    fun addToFavourites(god: God){
        favourites.add(god)
    }

    fun removeFromFavourites(god: God){
        favourites.remove(god)
    }

    fun getFavourites(): ArrayList<God> {
        return favourites
    }
//
//    fun getGodsMutableLiveData(): MutableLiveData<List<God>> {
//        return listOfGodsLiveData
//    }

    private fun loadJsonFiles(json: String): ArrayList<God>  {

        val gson = Gson()
        val listType = object : TypeToken<MutableList<God>>() {}.type
        return gson.fromJson(json, listType)

    }
}