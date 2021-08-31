package com.eddiej.apisearch.feature

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {
    protected lateinit var binding: B

    protected val disposable = CompositeDisposable()

    private var root: View? = null

    abstract fun setupViews()
    abstract fun bindViews()

    @LayoutRes
    abstract fun getLayoutResourceId() : Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null) {
            binding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
            root = binding.root

            setupViews()
        }

        return root
    }

    override fun onDestroyView() {
        disposable.dispose()
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bindViews()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.executePendingBindings()
    }

    fun hideKeyboard() {
        if (requireActivity() is BaseActivity<*>) {
            val activity = requireActivity() as BaseActivity<*>
            activity.hideKeyboard()
        }
    }
}