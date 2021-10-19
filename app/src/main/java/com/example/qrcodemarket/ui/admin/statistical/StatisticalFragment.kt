package com.example.qrcodemarket.ui.admin.statistical

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qrcodemarket.R
import com.example.qrcodemarket.data.model.getAccessAllUser
import com.example.qrcodemarket.data.network.QRApi
import com.example.qrcodemarket.data.network.SyntheticApi
import com.example.qrcodemarket.data.respositories.StatisticalRepository
import com.example.qrcodemarket.databinding.FragmentStatisticalBinding
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_statistical.*
import kotlinx.android.synthetic.main.fragment_statistical.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class StatisticalFragment : Fragment() {


    lateinit var mView: View
    private lateinit var searchView: SearchView
    private lateinit var viewModel: StatisticalViewModel
    private lateinit var factory: StatisticalViewModelFactory
    private lateinit var newData: List<getAccessAllUser.Data>
    private lateinit var binding:FragmentStatisticalBinding
//    var disposable: Disposable? = null
//    val insertApi by lazy {
//        QRApi.create()
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_statistical,container,false)
        mView = binding.root

        mView.imgBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }
        setHasOptionsMenu(true)
        val toolbar: Toolbar = mView.findViewById(R.id.toolbar_sta)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.show()


//        val viewModel = getDataAccess()
//        setupBinding(viewModel)
//        getAllAccess()

        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = SyntheticApi()
        val repository = StatisticalRepository(api)
        factory = StatisticalViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this,factory).get(StatisticalViewModel::class.java)
        viewModel.getStatistical()
        viewModel.statisticals.observe(viewLifecycleOwner, Observer { statisticals ->
            recyAllAccess.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = StatisticalAdapter(statisticals)
            }
        })
    }

//    private fun setupBinding(viewModel: StatisticalViewModel) {
//        binding.setVariable(BR.viewModel,viewModel)
//        binding.executePendingBindings()
//        binding.recyAllAccess.apply {
//            layoutManager = LinearLayoutManager(context)
//            val decoration = DividerItemDecoration(context,VERTICAL)
//            addItemDecoration(decoration)
//
//        }
//    }

//    private fun showAll(dataAllAccess: List<getAccessAllUser.Data>) {
//
//        recyAllAccess.layoutManager = LinearLayoutManager(context)
//        recyAllAccess.adapter = AccessAllUserAdapter(dataAllAccess, object : AccessAllUserAdapter.OnAdapterListener {
//            override fun onCLick(dataAllAccess: getAccessAllUser.Data) {
//                val action =
//                    StatisticalFragmentDirections.actionStatisticalFragmentToShowInfoUserFragment(dataAllAccess.fullName)
//                findNavController().navigate(action)
//            }
//        })
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_statistical, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

//    fun getDataAccess():StatisticalViewModel{
//        val viewModel = ViewModelProviders.of(this).get(StatisticalViewModel::class.java)
//        viewModel.getRecycleListDataObserver().observe(this, Observer<dataStatistical>{
//            if(it != null){
//                viewModel.setAdapter(it.access)
//                Log.i("abc","abc"+it.access)
//            }else {
//                Toast.makeText(context, "Error get data", Toast.LENGTH_SHORT).show()
//            }
//        })
//        viewModel.callAPI()
//
//        return viewModel
//    }


//    private fun getAllAccess() {
//        disposable = insertApi.accessAllUser()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result ->
//                    showAll(result.data)
//                    newData = result.data
//                    Log.i("abc", "abc: " + result.data.toString())
//                },
//                { error ->
//                    Log.i("abc", "abc: " + error.localizedMessage + error.message + error)
//                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
//                }
//            )
//    }
//
//    private fun getAccessData(fullName: String) {
//        disposable = insertApi.allaccessbyfullname(fullName)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result ->
//                    showAll(result.data)
//                    Log.i("abc", "abc: " + result.data.toString())
//                },
//                { error ->
//                    Log.i("abc", "abc: " + error.localizedMessage + error.message + error)
//                }
//            )
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.getItemId() == R.id.All) {
//            getAllAccess()
//            return true
//        } else if (item.getItemId() == R.id.Search) {
//            searchView = item.actionView as SearchView
//            searchView.setQueryHint("Tìm kiếm...")
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    query?.let { getAccessData(it) }
//                    return false
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    val searchText = newText!!.toLowerCase(Locale.getDefault())
//                    if (searchText.isNotEmpty()) {
//
//                    } else {
//
//                    }
//                    return false
//                }
//            })
//            return true
//        }
//        return super.onOptionsItemSelected(item);
//    }
}