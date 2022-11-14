package com.transifextest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.transifextest.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.languageSelect.adapter = adapter
        binding.languageSelect.isSelected = false
        binding.languageSelect.setSelection(languages.indexOf(LocaleService.getLanguageFromPreferences(requireActivity())), false)
        binding.languageSelect.onItemSelectedListener = this
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(adView: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val langCode = languages[position]
        requireContext()
            .getSharedPreferences(PREF_DB_NAME, Context.MODE_PRIVATE).apply {
                put(PREF_TITLE_LANG, langCode)
            }
        requireActivity().recreate()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}