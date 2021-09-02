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

    abstract fun setupViews()

    @LayoutRes
    abstract fun getLayoutResourceId() : Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        binding.lifecycleOwner = activity

        return binding.root
    }

    override fun onDestroyView() {
        disposable.dispose()
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    fun hideKeyboard() {
        if (requireActivity() is BaseActivity<*>) {
            val activity = requireActivity() as BaseActivity<*>
            activity.hideKeyboard()
        }
    }
}