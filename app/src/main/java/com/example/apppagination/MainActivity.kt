package com.example.apppagination

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apppagination.databinding.ActivityMainBinding
import com.example.apppagination.model.ModelUser
import com.example.apppagination.util.UserClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { UserAdapter(context = this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvMain.adapter = adapter

     //  binding.rvMain.layoutManager =   LinearLayoutManager(this)
        UserClient.userService.getAllUser().enqueue(object : Callback<ModelUser> {
            override fun onResponse(
                call: Call<ModelUser>,
                response: Response<ModelUser>
            ) {
                if (response.isSuccessful) {

                    response.body()?.let {
                        adapter.setData(it.data)
                    }
                } else {
                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ModelUser>, t: Throwable) {
                t.printStackTrace()

                Toast.makeText(this@MainActivity, "onFailure", Toast.LENGTH_SHORT).show()
            }

        })

    }
}