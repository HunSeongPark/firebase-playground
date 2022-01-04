package com.hunseong.recycle.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hunseong.recycle.databinding.FragmentHomeBinding
import com.hunseong.recycle.extension.DBKey.DB_ARTICLES

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var articleDB: DatabaseReference

    private val articleList = mutableListOf<Article>()
    private val listener = object : ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val article = snapshot.getValue(Article::class.java) ?: return
            articleList.add(article)
            articleAdapter.submitList(articleList)
            articleAdapter.notifyItemChanged(articleList.lastIndex)
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}

    }

    private val articleAdapter: ArticleAdapter by lazy {
        ArticleAdapter()
    }

    private val auth: FirebaseAuth by lazy {
        Firebase.auth
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

        initViews()

    }

    private fun initViews() {

        binding.fab.setOnClickListener {

            // todo 로그인 기능 구현 후 주석 해제
//            if (auth.currentUser != null) {
                val directions = HomeFragmentDirections.homeToAdd()
                findNavController().navigate(directions)
//            } else {
//                Snackbar.make(it, "로그인 후 사용해주세요.", Snackbar.LENGTH_SHORT).show()
//            }
        }

        articleDB = Firebase.database.reference.child(DB_ARTICLES)
        articleDB.addChildEventListener(listener)

        binding.recyclerView.adapter = articleAdapter
    }

    override fun onResume() {
        super.onResume()
        articleList.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        articleDB.removeEventListener(listener)
    }
}