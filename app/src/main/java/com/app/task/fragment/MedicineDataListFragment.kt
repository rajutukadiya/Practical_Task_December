package com.app.task.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.task.Interface.ItemListener
import com.app.task.R
import com.app.task.activity.detailscreen.DetailsScreen
import com.app.task.adapter.MedicineAdapter
import com.app.task.models.MedicineData
import com.app.task.utils.AppUtils
import com.app.task.viewmodels.MedicineDataViewModelViewModel

import dagger.hilt.android.AndroidEntryPoint
import com.google.gson.Gson

@AndroidEntryPoint
class MedicineDataListFragment : Fragment(), ItemListener {

    private lateinit var mainViewModel: MedicineDataViewModelViewModel
    private var medicineDataList: List<MedicineData>? = null
    private val medicineDataRV: RecyclerView
        get() = requireActivity().findViewById(R.id.postsRV)
    private val mSwipeRefreshLayoutPost: SwipeRefreshLayout
        get() = requireActivity().findViewById(R.id.mSwipeRefreshLayoutPost)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.fragment_medicine,
            container, false
        )

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MedicineDataViewModelViewModel::class.java)

        mSwipeRefreshLayoutPost.setOnRefreshListener {

            if (AppUtils.isInterConnectionIsAvailable(requireActivity())) {

            } else {
                AppUtils.showErrorDialog(
                    requireActivity(),
                    resources.getString(R.string.internetNotAvailableStr)
                )
            }



            mainViewModel.medicineDataListLiveData.observe(requireActivity(), Observer {
                if (it!!.size == 0) {
                    Toast.makeText(requireActivity(), getString(R.string.Nopostfound), Toast.LENGTH_LONG).show()
                }
                medicineDataRV.adapter = null
                medicineDataList = it
                medicineDataRV.layoutManager = LinearLayoutManager(requireActivity())
                medicineDataRV.adapter = MedicineAdapter(it, requireActivity(), this)

            })

            mSwipeRefreshLayoutPost.setRefreshing(false)
        }


        mainViewModel.medicineDataListLiveData.observe(requireActivity(), Observer {

            if (it!!.isEmpty()) {
                Toast.makeText(requireActivity(), getString(R.string.Nopostfound), Toast.LENGTH_LONG).show()
            }

            medicineDataList = it
            medicineDataRV.layoutManager = LinearLayoutManager(requireActivity())

            medicineDataRV.adapter = MedicineAdapter(it, requireActivity(), this)
        })

    }

    override fun itemClicked(position: Int, context: Context) {
        val intent = Intent(context, DetailsScreen::class.java)
        val gson = Gson()
        val postJSON = gson.toJson(medicineDataList?.get(position))
        intent.putExtra("MedicineDetail", postJSON)
        startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.enter, R.anim.exit)

    }



}