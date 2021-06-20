package benedykt.ziobro.cv.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import benedykt.ziobro.cv.R
import benedykt.ziobro.cv.adapter.CvAdapter
import benedykt.ziobro.cv.adapter.toCvItemModelList
import benedykt.ziobro.cv.databinding.FragmentCvBinding
import benedykt.ziobro.cv.utils.viewBinding
import benedykt.ziobro.cv.viewmodel.CvViewModel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CvFragment : Fragment(R.layout.fragment_cv) {

    private val cvViewModel: CvViewModel by viewModel()
    private val cvAdapter: CvAdapter by inject()
    private val binding: FragmentCvBinding by viewBinding(FragmentCvBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewmodel = cvViewModel

        binding.cvListItems.adapter = cvAdapter
//
//        cvViewModel.isError.observe(viewLifecycleOwner) { error ->
//            error.getContentIfNotHandled()?.let {
//                if (it) {
//                    Toast.makeText(
//                        requireContext(),
//                        getString(R.string.error_message_when_loading_cv),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }

        lifecycleScope.launchWhenStarted {
            cvViewModel.tickFlow.collect {
                if (!it) return@collect

                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_message_when_loading_cv),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        cvViewModel.cv.observe(viewLifecycleOwner) {
            cvAdapter.setItems(it.toCvItemModelList())
        }
    }

}