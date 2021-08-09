package com.ecohouse.fragmenttest

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.ecohouse.fragmenttest.databinding.FragmentSimpleBinding


/**
 * @author jaeho.lee104 on 2021. 08. 09..
 */
class SimpleFragment : Fragment() {

    private lateinit var binding: FragmentSimpleBinding
    private val title by lazy { arguments?.getString(ARGS_TITLE) ?: "" }
    private val index by lazy { arguments?.getInt(ARGS_INDEX) ?: 1 }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSimpleBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("~~ backstack Count = ${parentFragmentManager.backStackEntryCount}")
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, createBackPressedCallback())
        binding.title.text = "$title $index"
        binding.title.setOnClickListener {
            addChildFragment(
                binding.childContainer.id,
                newInstance(title, index + 1),
                "$title${index + 1}"
            )
        }
    }

    private fun createBackPressedCallback(): OnBackPressedCallback {
        return object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.popBackStack("simple2", 0)

                println("~~ backstack Count = ${parentFragmentManager.backStackEntryCount}")
            }
        }
    }


    companion object {

        private const val ARGS_TITLE = "args_title"
        private const val ARGS_INDEX = "args_index"

        fun newInstance(title: String, index: Int) = SimpleFragment().apply {
            arguments = bundleOf(
                ARGS_TITLE to title,
                ARGS_INDEX to index
            )
        }
    }

}