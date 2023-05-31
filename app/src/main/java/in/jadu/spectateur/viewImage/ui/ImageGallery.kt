package `in`.jadu.spectateur.viewImage.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import `in` .jadu.spectateur.R
import `in`.jadu.spectateur.databinding.FragmentImageGalleryBinding
import `in`.jadu.spectateur.viewImage.adapters.ImageLoadAdapter
import `in`.jadu.spectateur.viewImage.viewmodels.ImageGalleryViewModel

@AndroidEntryPoint
class ImageGallery : Fragment() {
    private lateinit var binding: FragmentImageGalleryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var imageAdapter:ImageLoadAdapter
    private val imageGalleryViewModel: ImageGalleryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageGalleryBinding.inflate(inflater,container,false)
        imageAdapter = ImageLoadAdapter()
        setUpRecycleView()
        return binding.root
    }
    private fun setUpRecycleView(){
        recyclerView = binding.imageGalleryRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        imageGalleryViewModel.getImageList.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                binding.loadItem.visibility = View.GONE
                Toast.makeText(requireContext(), "Image Not Found", Toast.LENGTH_SHORT).show()
            }else{
                binding.loadItem.visibility = View.GONE
                imageAdapter.itemList = it
                recyclerView.adapter = imageAdapter
                imageAdapter.notifyDataSetChanged()
            }
        }
    }


}