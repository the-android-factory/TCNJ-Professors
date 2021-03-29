package com.dmp.tcnjprofessors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dmp.tcnjprofessors.databinding.FragmentHomeBinding
import com.squareup.moshi.JsonAdapter

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val response = parseProfessors()

        val controller = ProfessorsController(response.professors)
        binding.epoxyRecyclerView.setControllerAndBuildModels(controller)
    }

    private fun parseProfessors(): GetProfessorsResponse {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<GetProfessorsResponse> = moshi.adapter(GetProfessorsResponse::class.java)

        val textFromFile =
            requireActivity().resources.openRawResource(R.raw.professors).bufferedReader()
                .use { it.readText() }

        return jsonAdapter.fromJson(textFromFile)!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}