package com.hunseong.recycle.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hunseong.recycle.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val articleAdapter: ArticleAdapter by lazy {
        ArticleAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleAdapter.submitList(listOf(
            Article(sellerId = "0",
                title = "aaaa",
                price = "100원",
                createdAt = System.currentTimeMillis(),
            imageUrl = ""),
            Article(sellerId = "1",
                title = "abba",
                price = "1200원",
                createdAt = 100000,
                imageUrl = ""),
        ))
        binding.recyclerView.adapter = articleAdapter
    }
}