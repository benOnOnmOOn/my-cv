package benedykt.ziobro.cv.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import benedykt.ziobro.cv.R
import benedykt.ziobro.cv.adapter.CvAdapter
import benedykt.ziobro.cv.adapter.toCvItemModelList
import benedykt.ziobro.cv.databinding.FragmentCvBinding
import benedykt.ziobro.cv.viewmodel.CvViewModel
import kotlinx.android.synthetic.main.fragment_cv.*
import org.koin.android.ext.android.inject

class CvFragment : Fragment() {

    private val cvViewModel: CvViewModel by inject()
    private val cvAdapter: CvAdapter by inject()

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

        cv_list_items.let {
            it.layoutManager = LinearLayoutManager(activity)
            it.adapter = cvAdapter
        }

        cvViewModel.isError.observe(viewLifecycleOwner) { error ->
            error.getContentIfNotHandled()?.let {
                if (it) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_message_when_loading_cv),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        cvViewModel.cv.observe(viewLifecycleOwner) {
            cvAdapter.setItems(it.toCvItemModelList())
        }

    }

}