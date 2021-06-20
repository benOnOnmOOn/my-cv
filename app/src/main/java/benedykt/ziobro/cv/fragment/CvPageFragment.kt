package benedykt.ziobro.cv.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import benedykt.ziobro.cv.R
import benedykt.ziobro.cv.adapter.CvAdapter
import benedykt.ziobro.cv.adapter.CvPageAdapter
import benedykt.ziobro.cv.adapter.toCvItemModelList
import benedykt.ziobro.cv.databinding.FragmentCvBinding
import benedykt.ziobro.cv.databinding.FragmentPageCvBinding
import benedykt.ziobro.cv.utils.PagingLoadStateAdapter
import benedykt.ziobro.cv.utils.viewBinding
import benedykt.ziobro.cv.viewmodel.CvPageViewModel
import benedykt.ziobro.cv.viewmodel.CvViewModel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CvPageFragment : Fragment(R.layout.fragment_page_cv) {

    private val cvViewModel: CvPageViewModel by viewModel()
    private val cvAdapter: CvPageAdapter by inject()
    private val binding: FragmentPageCvBinding by viewBinding(FragmentPageCvBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvListItems.adapter = cvAdapter.withLoadStateFooter(
            footer = PagingLoadStateAdapter(cvAdapter)
        )
        binding.refreshLayout.setOnRefreshListener {
            cvAdapter.refresh()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            cvViewModel.cvs.collectLatest { pagingData ->
                cvAdapter.submitData(pagingData)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            cvAdapter.loadStateFlow.collectLatest { loadStates ->
                if (binding.refreshLayout.isRefreshing)
                    binding.refreshLayout.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }

    }

}