package benedykt.ziobro.cv.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import benedykt.ziobro.cv.R
import benedykt.ziobro.cv.databinding.FragmentCvBinding
import benedykt.ziobro.cv.viewmodel.CvViewModel
import org.koin.android.ext.android.inject

class CvFragment : Fragment() {

    private val cvViewModel: CvViewModel by inject()

    private lateinit var binding: FragmentCvBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cv,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewmodel = cvViewModel
        cvViewModel.isError.observe(viewLifecycleOwner) { error ->
            error.getContentIfNotHandled()?.let {
                if(it) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_message_when_loading_cv),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }


    }

}